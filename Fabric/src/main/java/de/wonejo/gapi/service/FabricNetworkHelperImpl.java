package de.wonejo.gapi.service;

import de.wonejo.gapi.api.service.INetworkHelper;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;


public class FabricNetworkHelperImpl implements INetworkHelper {

    public void sendToServer(Player pPlayer, CustomPacketPayload pPayload) {
        ClientPlayNetworking.send(pPayload);
    }

}
