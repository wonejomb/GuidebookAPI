package de.wonejo.gapi.item;

import de.wonejo.gapi.CommonGapiMod;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.item.IBookItem;
import de.wonejo.gapi.api.util.Constants;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class GuideItem extends Item implements IBookItem {
    private final IBook book;
    private String translationKey;

    public GuideItem(IBook pBook) {
        super(new Properties().stacksTo(1));

        this.book = pBook;
        this.setTranslationKey("%s.book.%s".formatted(Constants.MOD_ID, pBook.id().getPath()));

    }

    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        if ( this.book.author() != null ) {
            tooltipComponents.add(this.book.author());
            if ( tooltipFlag == TooltipFlag.ADVANCED )
                tooltipComponents.add(Component.literal(this.book.id().toString()));
        }
    }

    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return this.get().itemName() != null ? get().itemName() : super.getName(pStack);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack heldStack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide()) return InteractionResultHolder.success(heldStack);

        pPlayer.playSound(SoundEvents.BOOK_PAGE_TURN);
        CommonGapiMod.CLIENT_PROXY.openGuide(pPlayer, pLevel, this.book, heldStack);
        return InteractionResultHolder.success(heldStack);
    }

    protected @NotNull String getOrCreateDescriptionId() {
        if ( this.translationKey == null )
            this.translationKey = Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this));

        return this.translationKey;
    }

    public IBook get() {
        return this.book;
    }

    private void setTranslationKey ( String pKey ) {
        this.translationKey = Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, pKey));
    }
}
