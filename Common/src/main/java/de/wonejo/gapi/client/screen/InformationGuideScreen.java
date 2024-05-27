package de.wonejo.gapi.client.screen;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookInformation;
import de.wonejo.gapi.api.util.GuideScreenType;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.client.button.GuideButton;
import de.wonejo.gapi.ext.IScreenRenderablesAccessor;
import de.wonejo.gapi.mixin.ScreenRenderablesAccessor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Player;

public final class InformationGuideScreen extends GuideScreen {

    private final IBookInformation bookInformation;

    public InformationGuideScreen(Player pPlayer, IBook pBook) {
        super(pPlayer, pBook, GuideScreenType.INFO);

        this.bookInformation = pBook.information();
    }

    protected void init() {
        this.addRenderableWidget(new GuideButton(this.xOffset() + this.screenWidth() - 20,
                this.yOffset() + this.screenHeight() - 20,
                GuideButton.ButtonType.HOME, (button) -> this.minecraft.setScreen(new HomeGuideScreen(this.getPlayer(), this.getBook()))));
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        if (!this.getBook().useCustomPagesTexture()) {
            float red = ModConfigurations.CLIENT_PROVIDER.getPageBookColors().get(this.getBook()).get().getRed() / 255.0F;
            float green = ModConfigurations.CLIENT_PROVIDER.getPageBookColors().get(this.getBook()).get().getGreen() / 255.0F;
            float blue = ModConfigurations.CLIENT_PROVIDER.getPageBookColors().get(this.getBook()).get().getBlue() / 255.0F;

            pGuiGraphics.setColor(red, green, blue, 1.0F);
            RenderUtils.renderImage(pGuiGraphics, this.topTexture(), this.xOffset(), this.yOffset(), this.screenWidth(), this.screenHeight());
        }

        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderUtils.renderImage(pGuiGraphics, this.pagesTexture(), this.xOffset(), this.yOffset(), this.screenWidth(), this.screenHeight());

        pGuiGraphics.pose().pushPose();

        RenderUtils.drawCenteredStringWithoutShadow(pGuiGraphics, this.font, this.bookInformation.title(),
                this.xOffset() + this.screenWidth() / 2, this.yOffset() - 10,
                ChatFormatting.WHITE.getColor());

        RenderUtils.renderTextInRange(pGuiGraphics, this.font, this.bookInformation.description(), this.xOffset() + 9, this.yOffset() + 8, 266, ModConfigurations.CLIENT_PROVIDER.getBookTextColors().get(this.getBook()).get().getRGB());

        ((IScreenRenderablesAccessor) (Screen) this).getAllRenderables().forEach(it -> it.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick));
    }
}
