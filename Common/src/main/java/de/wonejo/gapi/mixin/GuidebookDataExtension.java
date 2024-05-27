package de.wonejo.gapi.mixin;

import de.wonejo.gapi.ext.IGuidebookDataExtension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class GuidebookDataExtension implements IGuidebookDataExtension {

    @Unique
    private CompoundTag guidebookApiData;

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD") )
    public void GuidebookDataExtension$readAdditionalSaveData(CompoundTag pCompound, CallbackInfo ci) {
        if ( pCompound.contains("GuidebookAPI", 10) ) this.guidebookApiData = pCompound.getCompound("GuidebookAPI");
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    public void GuidebookDataExtension$saveAdditionalSaveData ( CompoundTag pCompound, CallbackInfo pCI ) {
        if ( this.guidebookApiData != null ) pCompound.put("GuidebookAPI", guidebookApiData.copy());
    }

    public CompoundTag getGuidebookData() {
        if ( this.guidebookApiData == null ) this.guidebookApiData = new CompoundTag();
        return this.guidebookApiData;
    }
}
