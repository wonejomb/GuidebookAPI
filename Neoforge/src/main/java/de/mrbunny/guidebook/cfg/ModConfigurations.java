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
    public static ServerConfigurations SERVER;
    private static ModConfigSpec clientSpec;
    private static ModConfigSpec serverSpec;

    public static void registerConfigurations ( ) {
        Pair<ClientConfigurations, ModConfigSpec> clientCfgSpec = new ModConfigSpec.Builder().configure(ClientConfigurations::new);
        Pair<ServerConfigurations, ModConfigSpec> serverCfgSpec = new ModConfigSpec.Builder().configure(ServerConfigurations::new);

        CLIENT = clientCfgSpec.getLeft();
        SERVER = serverCfgSpec.getLeft();
        clientSpec = clientCfgSpec.getRight();
        serverSpec = serverCfgSpec.getRight();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverSpec);
    }

    private static class ClientConfigurations {

        public final ModConfigSpec.BooleanValue renderCategoryToltips;
        public final ModConfigSpec.IntValue entryHolderColor;


        public final Map<IBook, ModConfigSpec.IntValue> bookColors = new HashMap<>();

        public ClientConfigurations (ModConfigSpec.Builder pBuilder) {
            pBuilder.comment("Render cfg").push("render");

            this.renderCategoryToltips = pBuilder.comment("If this enabled category tooltips with names will render").define("renderCategoryTooltips", true);

            this.entryHolderColor = pBuilder.comment("Put here the color you want to the entry names when mouse is in a entry").defineInRange(
                    "entryHolderColor",
                    new Color(36, 68, 187).getRGB(),
                    0, 16581375);

            pBuilder.comment("Book color configuratins").push("bookColors");

            for ( IBook book : GuidebookAPI.getBooks().values() )
                this.bookColors.put(book,
                        pBuilder.defineInRange(book.getId().getNamespace() + "-" + book.getId().getPath() + "-color", book.getColor().getRGB(), 0, 16581375)
                );

            pBuilder.pop();
        }

    }

    private static class ServerConfigurations {

        public final ModConfigSpec.BooleanValue shouldSpawnWithBook;
        public final Map<IBook, ModConfigSpec.BooleanValue> booksShouldSpawnWithBook = new HashMap<>();

        public ServerConfigurations ( ModConfigSpec.@NotNull Builder pBuilder ) {
            pBuilder.comment("Server configuration settings").push("server");

            this.shouldSpawnWithBook = pBuilder.comment("This define if player should spawn with books").define("shouldSpawnWithBook", true);

            for ( IBook book : GuidebookAPI.getBooks().values() )
                this.booksShouldSpawnWithBook.put(book,
                        pBuilder.define(book.getId().getNamespace() + "-" + book.getId().getPath() + "-spawn", book.shouldSpawnWithBook())
                );

            pBuilder.pop();
        }

    }
}
