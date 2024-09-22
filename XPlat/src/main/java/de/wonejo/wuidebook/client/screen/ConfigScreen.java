package de.wonejo.wuidebook.client.screen;

import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends OptionsSubScreen {

    public ConfigScreen(Screen lastScreen, Options options) {
        super(lastScreen, options, Component.translatable("wuidebook.config.screen"));
    }

    protected void addContents() {
        super.addContents();

        this.layout.addToContents(new StringWidget(Component.translatable("wuidebook.config.screen.indevelopmentprocess"), this.font).alignCenter());
    }

    protected void addFooter() {
        this.layout.addToFooter(Button.builder(Component.translatable("wuidebook.config.screen.backandsave"), (btn) -> this.onClose()).width(200).build());
    }

    protected void addOptions() {

    }

}
