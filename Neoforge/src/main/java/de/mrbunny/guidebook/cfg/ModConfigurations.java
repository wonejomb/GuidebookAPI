package de.mrbunny.guidebook.cfg;

import com.google.common.collect.Maps;
import de.mrbunny.guidebook.GuidebookMod;
import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import net.minecraft.ChatFormatting;
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
    public static ModConfigSpec clientSpec;
    public static ModConfigSpec commonSpec;

    public static void registerConfigurations ( ) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec);
    }

    public static class ClientConfigurations {

        public final ModConfigSpec.ConfigValue<Integer> entryHoverColor;

        public final Map<IBook, ModConfigSpec.ConfigValue<Integer>> bookColors = Maps.newHashMap();

        public ClientConfigurations ( ModConfigSpec.@NotNull Builder pBuilder ) {
            pBuilder.push("RenderConfiguration");

            this.entryHoverColor = pBuilder.comment("The color of the entries when the mouse is between them").defineInRange("entryHoverColor", ChatFormatting.BLUE.getColor(), 0, 16581375);

            pBuilder.comment("The color of this book.");
            for ( IBook book : GuidebookAPI.getBooks().values() )
                this.bookColors.put(book,
                        pBuilder.defineInRange(book.getId().getNamespace() + "-" + book.getId().getPath(), book.getColor().getRGB(),0, 16581375));

            pBuilder.pop();
        }

    }

    public static class CommonConfigurations {

        public final ModConfigSpec.ConfigValue<Boolean> shouldSpawnWithBooks;
        public final Map<IBook, ModConfigSpec.ConfigValue<Boolean>> spawnBooks = new HashMap<>();

        public CommonConfigurations(ModConfigSpec.@NotNull Builder pBuilder ) {
            pBuilder.push("SpawnConfigurations");

            this.shouldSpawnWithBooks = pBuilder.comment("Allows books to spawn with new players.\nThis is a global override for all books if set to false").define("shouldSpawnWithBooks", true);
            pBuilder.comment("If the player should spawn with this book").push("spawnBook");
            for ( IBook book : GuidebookAPI.getBooks().values() )
                spawnBooks.put(book,
                        pBuilder.define(book.getId().getNamespace() + "-" + book.getId().getPath(), book.shouldSpawnWithBook()));

            pBuilder.pop();
            pBuilder.pop();
        }

    }

    static {
        Pair<ClientConfigurations, ModConfigSpec> clientCfgSpec = new ModConfigSpec.Builder().configure(ClientConfigurations::new);
        clientSpec = clientCfgSpec.getRight();
        CLIENT = clientCfgSpec.getLeft();
    }

    static {
        Pair<CommonConfigurations, ModConfigSpec> serverCfgSpec = new ModConfigSpec.Builder().configure(CommonConfigurations::new);
        COMMON = serverCfgSpec.getLeft();
        commonSpec = serverCfgSpec.getRight();
    }
}
