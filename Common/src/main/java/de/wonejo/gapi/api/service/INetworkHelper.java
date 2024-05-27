package de.wonejo.gapi.api.service;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public interface INetworkHelper {

    void sendToServer (Player pPlayer, CustomPacketPayload pPayload);

}
