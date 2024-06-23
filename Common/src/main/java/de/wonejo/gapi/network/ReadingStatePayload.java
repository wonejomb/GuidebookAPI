package de.wonejo.gapi.network;

import de.wonejo.gapi.api.book.item.IBookItem;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.core.ModDataComponents;
import de.wonejo.gapi.impl.service.Services;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public record ReadingStatePayload(int page, Optional<Integer> category, Optional<ResourceLocation> entry) implements CustomPacketPayload {
    public static final Type<ReadingStatePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "reading_state"));

    public static final StreamCodec<ByteBuf, ReadingStatePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ReadingStatePayload::page,
            ByteBufCodecs.VAR_INT.apply(ByteBufCodecs::optional), ReadingStatePayload::category,
            ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs::optional), ReadingStatePayload::entry,
            ReadingStatePayload::new);

    public static void handle (ReadingStatePayload pMsg, Player pPlayer) {
        Objects.requireNonNull(pPlayer);

        ItemStack book = pPlayer.getOffhandItem();
        if ( book.isEmpty() || !(book.getItem() instanceof IBookItem) ) book = pPlayer.getMainHandItem();
        if ( !book.isEmpty() && (book.getItem() instanceof IBookItem) ) {
            ItemStack finalBook = book;
            finalBook.set(ModDataComponents.PAGE, pMsg.page);
            pMsg.category.ifPresentOrElse((c) -> finalBook.set(ModDataComponents.CATEGORY, c), () -> finalBook.remove(ModDataComponents.CATEGORY));
            pMsg.entry.ifPresentOrElse((c) -> finalBook.set(ModDataComponents.ENTRY, c), () -> finalBook.remove(ModDataComponents.ENTRY));
        }
    }

    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
