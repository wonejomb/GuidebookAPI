package de.mrbunny.guidebook.item;

import de.mrbunny.guidebook.GuidebookMod;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.IBookItem;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.client.GuidebookClientMod;
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

public class GuidebookItem extends Item implements IBookItem  {
    private final IBook book;
    private String translationKey;

    public GuidebookItem( IBook pBook ) {
        super(new Item.Properties().stacksTo(1));

        this.book = pBook;
        setTranslationKey(References.GUIDEBOOKAPI_ID + ".book." + pBook.getId().getNamespace() + "." + pBook.getId().getPath());
    }

    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltips, @NotNull TooltipFlag pFlags) {
        if ( this.book.getAuthor() != null ) {
            pTooltips.add(this.book.getAuthor());
            if ( pFlags == TooltipFlag.ADVANCED ) {
                pTooltips.add(Component.literal(this.book.getId().toString()));
            }
        }
    }

    public String getCreatorModId ( ItemStack pStack ) {
        return this.book.getId().getNamespace();
    }


    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return  this.getBook(pStack).getItemName() != null ? getBook(pStack).getItemName() : super.getName(pStack);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
                ItemStack heldStack = pPlayer.getItemInHand(pUsedHand);

        if ( !pLevel.isClientSide() ) return InteractionResultHolder.success(heldStack);


        GuidebookClientMod.CLIENT_PROXY.openGuidebook(pPlayer, pLevel, this.book, heldStack);
        return InteractionResultHolder.success(heldStack);
    }

    protected @NotNull String getOrCreateDescriptionId () {
        if ( this.translationKey == null )
            this.translationKey = Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this));

        return this.translationKey;
    }

    protected void setTranslationKey ( String pKey ) {
        this.translationKey = Util.makeDescriptionId("item", new ResourceLocation(References.GUIDEBOOKAPI_ID, pKey));
    }

    public IBook getBook(ItemStack pStack) {
        return this.book;
    }
}
