package de.wonejo.wuidebook.item;

import de.wonejo.wuidebook.api.book.BuiltBook;
import de.wonejo.wuidebook.api.book.IBuiltBookItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BuiltBookItem extends Item implements IBuiltBookItem {

    private final BuiltBook book;

    public BuiltBookItem(Properties properties, BuiltBook pBook) {
        super(properties);
        this.book = pBook;
    }

    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(this.book.getInformation().author());
    }

    protected @NotNull String getOrCreateDescriptionId() {
        return "wuidebook." + this.book.getInformation().id().getNamespace() + "." + this.book.getInformation().id().getPath();
    }

    public BuiltBook getBook() {
        return this.book;
    }

}
