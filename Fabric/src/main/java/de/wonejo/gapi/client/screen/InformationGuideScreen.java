package de.wonejo.gapi.client.screen;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookInformation;
import de.wonejo.gapi.api.util.ComponentUtils;
import de.wonejo.gapi.api.util.GuideScreenType;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.client.button.GuideButton;
import de.wonejo.gapi.config.ModConfigurations;
import de.wonejo.gapi.ext.IScreenRenderablesAccessor;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public final class InformationGuideScreen extends GuideScreen {

    private final IBookInformation bookInformation;

    public InformationGuideScreen(@NotNull IBook pBook) {
        super(pBook, GuideScreenType.INFO);

        this.bookInformation = pBook.information();
    }

    protected void init() {
        this.addRenderableWidget(new GuideButton(this.xOffset() + this.widthSize() - 20,
                this.yOffset() + this.heightSize() - 20,
                GuideButton.ButtonType.HOME, (button) -> this.minecraft.setScreen(new HomeGuideScreen(this.getBook()))));
    }

    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        Color bookColor = new Color(ModConfigurations.CLIENT.bookColors.get(this.getBook()).get());

        float red = bookColor.getRed() / 255.0F;
        float green = bookColor.getGreen() / 255.0F;
        float blue = bookColor.getBlue() / 255.0F;

        pGuiGraphics.setColor(red, green, blue, 1.0F);
        RenderUtils.renderImage(pGuiGraphics, this.topTexture(), this.xOffset(), this.yOffset(), this.widthSize(), this.heightSize());
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderUtils.renderImage(pGuiGraphics, this.pagesTexture(), this.xOffset(), this.yOffset(), this.widthSize(), this.heightSize());

        pGuiGraphics.pose().pushPose();

        RenderUtils.drawCenteredStringWithoutShadow(pGuiGraphics, this.font, this.bookInformation.title(),
                this.xOffset() + this.widthSize() / 2, this.yOffset() - 10,
                ChatFormatting.WHITE.getColor());

        RenderUtils.renderTextInRange(pGuiGraphics, this.font, this.bookInformation.description(), this.xOffset() + 9, this.yOffset() + 8, 266, ModConfigurations.CLIENT.textColor.get());

        String modNameComponent = ComponentUtils.parseEffect("text.gapi.information.modName", this.bookInformation.modName().getString());

        pGuiGraphics.drawString(this.font, modNameComponent,
                this.xOffset() + 8, this.yOffset() + this.heightSize() - 28, ModConfigurations.CLIENT.textColor.get(), false);

        String creditsText = ComponentUtils.parseEffect("text.gapi.information.credits", this.bookInformation.credits().getString());

        pGuiGraphics.drawString(this.font, creditsText,
                this.xOffset() + 8, this.yOffset() + this.heightSize() - 16,
                ModConfigurations.CLIENT.textColor.get(),
                false);

        ((IScreenRenderablesAccessor) (Screen) this).getAllRenderables().forEach(it -> it.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick));

    }
}
