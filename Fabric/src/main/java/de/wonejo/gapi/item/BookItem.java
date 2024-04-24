package de.wonejo.gapi.item;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.item.IBookItem;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.client.GapiClientMod;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class BookItem extends Item implements IBookItem {
    private final IBook book;
    private String translationKey;

    public BookItem(@NotNull IBook pBook) {
        super(new Properties().stacksTo(1));

        this.book = pBook;
        this.setTranslationKey("%s.book.%s".formatted(Constants.MOD_ID, pBook.id().getPath()));

    }

    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if ( this.book.author() != null ) {
            pTooltipComponents.add(this.book.author());
            if ( pIsAdvanced == TooltipFlag.ADVANCED ) {
                pTooltipComponents.add(Component.literal(this.book.id().toString()));
            }
        }
    }

    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return this.get().itemName() != null ? get().itemName() : super.getName(pStack);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack heldStack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide()) return InteractionResultHolder.success(heldStack);

        GapiClientMod.CLIENT_PROXY.openGuidebook(pPlayer, pLevel, this.book, heldStack);
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
        this.translationKey = Util.makeDescriptionId("item", new ResourceLocation(Constants.MOD_ID, pKey));
    }
}
