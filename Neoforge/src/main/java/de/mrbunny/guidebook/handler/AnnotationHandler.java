package de.mrbunny.guidebook.handler;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import de.mrbunny.guidebook.api.Guidebook;
import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.util.APIUtils;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.apache.commons.lang3.tuple.Pair;
import org.objectweb.asm.Type;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;

public class AnnotationHandler {

    public static final List<Pair<IBook, IGuidebook>> BOOK_CLASSES = Lists.newArrayList();
    private static final Type GUIDE = Type.getType(Guidebook.class);

    public static void gatherBooks () {
        final List<ModFileScanData.AnnotationData> annotations = ModList.get().getAllScanData().stream()
                .map(ModFileScanData::getAnnotations)
                .flatMap(Collection::stream)
                .filter(a -> GUIDE.equals(a.annotationType()))
                .toList();

        for ( ModFileScanData.AnnotationData data : annotations ) {

            try {
                Class<?> genericClass = Class.forName(data.clazz().getClassName());
                if ( !IGuidebook.class.isAssignableFrom(genericClass) ) continue;
                Constructor<?>[] constructors = genericClass.getConstructors();
                var constructor = constructors[0];
                IGuidebook guide = (IGuidebook) constructor.newInstance();
                IBook book = guide.buildBook().build();
                if ( book == null ) continue;
                APIUtils.registerBook(book);
                BOOK_CLASSES.add(Pair.of(book, guide));
            } catch (Exception pException) {
                LogUtils.getLogger().error("Error registering book for class {}", data.clazz().getClassName());
                pException.printStackTrace();
            }
        }

        APIUtils.setIndexedBooks(Lists.newArrayList(GuidebookAPI.getBooks().values()));
    }
}
