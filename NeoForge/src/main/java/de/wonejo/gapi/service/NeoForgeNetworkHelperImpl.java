package de.wonejo.gapi.service;

import de.wonejo.gapi.api.service.INetworkHelper;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgeNetworkHelperImpl implements INetworkHelper {

    public void sendToServer(Player pPlayer, CustomPacketPayload pPayload) {
        PacketDistributor.sendToServer(pPayload);
    }

}
