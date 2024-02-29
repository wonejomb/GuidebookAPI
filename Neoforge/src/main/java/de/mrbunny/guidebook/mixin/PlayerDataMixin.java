package de.mrbunny.guidebook.mixin;

import de.mrbunny.guidebook.ext.IPersistentDataExtension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class PlayerDataMixin implements IPersistentDataExtension {

    @Unique
    private CompoundTag guidebookApiPersistentData;

    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    protected void saveWithoutId ( CompoundTag pNbt, CallbackInfoReturnable<CompoundTag> pCir ) {
        if ( this.guidebookApiPersistentData != null ) pNbt.put("guidebookApiPersistentData", this.guidebookApiPersistentData);
    }

    @Inject ( method = "load", at = @At("HEAD") )
    protected void injectLoadMethod (CompoundTag pNbt, CallbackInfo pInfo ) {
        if ( pNbt.contains("guidebookApiPersistentData", 10) )
            this.guidebookApiPersistentData = pNbt.getCompound("guidebookApiPersistentData");
    }

    public CompoundTag guidebookApiPersistentData() {
        if ( this.guidebookApiPersistentData == null )
            this.guidebookApiPersistentData = new CompoundTag();

        return this.guidebookApiPersistentData;
    }
}
