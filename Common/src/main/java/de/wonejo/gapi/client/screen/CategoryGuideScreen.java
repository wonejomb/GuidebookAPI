package de.wonejo.gapi.client.screen;

import com.google.common.collect.HashMultimap;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.util.GuideScreenType;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.client.button.GuideButton;
import de.wonejo.gapi.ext.IScreenRenderablesAccessor;
import de.wonejo.gapi.mixin.ScreenRenderablesAccessor;
import de.wonejo.gapi.wrapper.EntryWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Mth;

public final class CategoryGuideScreen extends GuideScreen {

    private final ICategory category;
    private final HashMultimap<Integer, EntryWrapper> entries = HashMultimap.create();
    private int entryPage;

    public CategoryGuideScreen(IBook pBook, ICategory pCategory) {
        super(pBook, GuideScreenType.NORMAL);
        this.category = pCategory;
        this.entryPage = 0;
    }

    protected void init() {
        this.entries.clear();

        this.addRenderableWidget(new GuideButton(
                this.xOffset() + 8, this.yOffset() + this.screenHeight() - 18,
                GuideButton.ButtonType.BACK, (button) -> {
            if ( this.entryPage > 0 )
                this.previousPage();
        }));

        this.addRenderableWidget(new GuideButton(
                this.xOffset() + 24, this.yOffset() + this.screenHeight() - 20,
                GuideButton.ButtonType.HOME, (button) -> {
            Minecraft.getInstance().setScreen(new HomeGuideScreen(this.getBook()));
        }));

        this.addRenderableWidget(new GuideButton(
                this.xOffset() + this.screenWidth() - 36, this.yOffset() + this.screenHeight() - 20,
                GuideButton.ButtonType.INFORMATION, (button) -> {
            Minecraft.getInstance().setScreen(new InformationGuideScreen(this.getBook()));
        }
        ));

        this.addRenderableWidget(new GuideButton(
                this.xOffset() + this.screenWidth() - 22, this.yOffset() + this.screenHeight() - 18,
                GuideButton.ButtonType.NEXT, (button) -> {
            if ( this.entryPage + 1 < this.entries.asMap().size() )
                this.nextPage();
        }));

        int entryX = this.xOffset() + 25;
        int entryY = this.yOffset() + 12;
        int index = 0;
        int pageNumber = 0;

        for (IEntry entry : this.category.entries().values()) {
            if (entry.getPages().isEmpty()) continue;

            entry.getRender().init();

            if (index == 5) {
                entryX = this.xOffset() + this.screenWidth() / 2 + 25;
                entryY = this.yOffset() + 12;
            }

            if (index >= 10) {
                pageNumber++;
                entryX = this.xOffset() + 25;
                entryY = this.yOffset() + 12;
                index = 0;
            }

            this.entries.put(pageNumber, new EntryWrapper(this.getBook(), entry, entryX, entryY));
            entryY += 20;
            index++;
        }
    }

    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (!super.mouseClicked(pMouseX, pMouseY, pButton)) {
            for (EntryWrapper wrapper : this.entries.get(this.entryPage)) {
                wrapper.get().getRender().onClick(this.getBook(), pMouseX, pMouseY, pButton);
                wrapper.get().onClick(this.getBook(), this.category, pMouseX, pMouseY, pButton);

                return true;
            }

            return false;
        }

        return true;
    }

    public void tick() {
        this.entries.forEach((integer, entryWrapper) -> entryWrapper.tick());
    }

    public void render( GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
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

        this.entryPage = Mth.clamp(entryPage, 0, this.entries.size() - 1);
        for ( EntryWrapper wrapper : this.entries.get(entryPage) )
            if (wrapper.canView()) wrapper.render(pGuiGraphics, Minecraft.getInstance().level.registryAccess(), pMouseX, pMouseY, this);

        ((IScreenRenderablesAccessor) (Screen) this).getAllRenderables().forEach(it -> it.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick));
    }


    private void nextPage () {
        if ( this.entryPage >= this.entries.asMap().size() )
            this.entryPage = this.entries.asMap().size() - 1;
        if ( this.entryPage != this.entries.asMap().size() - 1 && !this.entries.asMap().isEmpty() )
            this.entryPage++;
    }

    private void previousPage () {
        if ( this.entryPage >= this.entries.asMap().size() )
            this.entryPage = this.entries.asMap().size() - 1;
        if ( this.entryPage != 0 )
            this.entryPage--;
    }
}
