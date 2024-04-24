package de.wonejo.gapi.client.screen;

import com.google.common.collect.HashMultimap;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.util.GuideScreenType;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.client.button.GuideButton;
import de.wonejo.gapi.config.ModConfigurations;
import de.wonejo.gapi.wrapper.EntryWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public final class CategoryGuideScreen extends GuideScreen {

    private final IBookCategory category;
    private final HashMultimap<Integer, EntryWrapper> entries = HashMultimap.create();
    private int entryPage;

    public CategoryGuideScreen(@NotNull IBook pBook, IBookCategory pCategory) {
        super(pBook, GuideScreenType.NORMAL);
        this.category = pCategory;
        this.entryPage = 0;
    }

    protected void init() {
        this.entries.clear();

        this.addRenderableWidget(new GuideButton(
                this.xOffset() + 8, this.yOffset() + this.heightSize() - 18,
                GuideButton.ButtonType.BACK, (button) -> {
            if ( this.entryPage > 0 )
                this.previousPage();
        }));

        this.addRenderableWidget(new GuideButton(
                this.xOffset() + 24, this.yOffset() + this.heightSize() - 20,
                GuideButton.ButtonType.HOME, (button) -> {
            Minecraft.getInstance().setScreen(new HomeGuideScreen(this.getBook()));
        }));

        this.addRenderableWidget(new GuideButton(
                this.xOffset() + this.widthSize() - 36, this.yOffset() + this.heightSize() - 20,
                GuideButton.ButtonType.INFORMATION, (button) -> {
            Minecraft.getInstance().setScreen(new InformationGuideScreen(this.getBook()));
        }
        ));

        this.addRenderableWidget(new GuideButton(
                this.xOffset() + this.widthSize() - 22, this.yOffset() + this.heightSize() - 18,
                GuideButton.ButtonType.NEXT, (button) -> {
            if ( this.entryPage + 1 < this.entries.asMap().size() )
                this.nextPage();
        }));

        int entryX = this.xOffset() + 25;
        int entryY = this.yOffset() + 12;
        int index = 0;
        int pageNumber = 0;

        for (IBookEntry entry : this.category.entries().values()) {
            if (entry.pages().isEmpty()) continue;

            entry.render().init();

            if (index == 5) {
                entryX = this.xOffset() + this.widthSize() / 2 + 25;
                entryY = this.yOffset() + 12;
            }

            if (index >= 10) {
                pageNumber++;
                entryX = this.xOffset() + 25;
                entryY = this.yOffset() + 12;
                index = 0;
            }

            this.entries.put(pageNumber, new EntryWrapper(entry, entryX, entryY));
            entryY += 20;
            index++;
        }
    }

    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (!super.mouseClicked(pMouseX, pMouseY, pButton)) {
            for (EntryWrapper wrapper : this.entries.get(this.entryPage)) {
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

    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        if (!this.getBook().useCustomPagesTexture()) {
            float red = ModConfigurations.CLIENT.pageBookColors.get(this.getBook()).get().getRed() / 255.0F;
            float green = ModConfigurations.CLIENT.pageBookColors.get(this.getBook()).get().getGreen() / 255.0F;
            float blue = ModConfigurations.CLIENT.pageBookColors.get(this.getBook()).get().getBlue() / 255.0F;

            pGuiGraphics.setColor(red, green, blue, 1.0F);
            RenderUtils.renderImage(pGuiGraphics, this.topTexture(), this.xOffset(), this.yOffset(), this.widthSize(), this.heightSize());
        }

        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderUtils.renderImage(pGuiGraphics, this.pagesTexture(), this.xOffset(), this.yOffset(), this.widthSize(), this.heightSize());

        this.entryPage = Mth.clamp(entryPage, 0, this.entries.size() - 1);
        for ( EntryWrapper wrapper : this.entries.get(entryPage) )
            if (wrapper.canView()) wrapper.render(pGuiGraphics, Minecraft.getInstance().level.registryAccess(), pMouseX, pMouseY, this);

        for (Renderable renderable : this.renderables)
            renderable.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
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
