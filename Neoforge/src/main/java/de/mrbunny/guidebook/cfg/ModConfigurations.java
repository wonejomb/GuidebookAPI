package de.mrbunny.guidebook.cfg;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ModConfigurations {

    public static ClientConfigurations CLIENT;
    public static CommonConfigurations COMMON;
    private static ModConfigSpec clientSpec;
    private static ModConfigSpec commonSpec;

    public static void registerConfigurations ( ) {
        Pair<ClientConfigurations, ModConfigSpec> clientCfgSpec = new ModConfigSpec.Builder().configure(ClientConfigurations::new);
        Pair<CommonConfigurations, ModConfigSpec> serverCfgSpec = new ModConfigSpec.Builder().configure(CommonConfigurations::new);

        CLIENT = clientCfgSpec.getLeft();
        COMMON = serverCfgSpec.getLeft();
        clientSpec = clientCfgSpec.getRight();
        commonSpec = serverCfgSpec.getRight();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, commonSpec);
    }

    public static class ClientConfigurations {

        public final ModConfigSpec.BooleanValue renderCategoryToltips;
        public final ModConfigSpec.IntValue entryHolderColor;

        // This configuration isn't finalized at this moment
        public final Map<IBook, ModConfigSpec.IntValue> bookColors = new HashMap<>();

        public ClientConfigurations (ModConfigSpec.Builder pBuilder) {
            pBuilder.comment("Render cfg").push("render");

            this.renderCategoryToltips = pBuilder.comment("If this enabled category tooltips with names will render").define("renderCategoryTooltips", true);

            this.entryHolderColor = pBuilder.comment("Put here the color you want to the entry names when mouse is in a entry").defineInRange(
                    "entryHolderColor",
                    new Color(36, 68, 187).getRGB(),
                    0, 16581375);

            pBuilder.comment("Book color configurations").push("bookColors");

            for ( IBook book : GuidebookAPI.getBooks().values() )
                this.bookColors.put(book,
                        pBuilder.defineInRange(book.getId().getNamespace() + "-" + book.getId().getPath() + "-color", book.getColor().getRGB(), 0, 16581375)
                );

            pBuilder.pop();
        }

    }

    public static class CommonConfigurations {

        public final ModConfigSpec.BooleanValue shouldSpawnWithBook;
        public final Map<IBook, ModConfigSpec.BooleanValue> booksShouldSpawnWithBook = new HashMap<>();

        public CommonConfigurations(ModConfigSpec.@NotNull Builder pBuilder ) {
            pBuilder.comment("Common configuration settings").push("common");

            this.shouldSpawnWithBook = pBuilder.comment("This define if player should spawn with books").define("shouldSpawnWithBook", true);

            for ( IBook book : GuidebookAPI.getBooks().values() )
                this.booksShouldSpawnWithBook.put(book,
                        pBuilder.define(book.getId().getNamespace() + "-" + book.getId().getPath() + "-spawn", book.shouldSpawnWithBook())
                );

            pBuilder.pop();
        }

    }
}
