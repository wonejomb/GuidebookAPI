package de.mrbunny.guidebook.mixin;

import de.mrbunny.guidebook.ext.IEntityDataExtension;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
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
public abstract class EntityDataMixin implements IEntityDataExtension {
    private CompoundTag persistentData;


    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    protected void injectSaveMethod (CompoundTag pNbt, CallbackInfoReturnable<CompoundTag> cir ) {
        if ( this.persistentData != null ) pNbt.put("guidebookAPI", persistentData);
    }

    @Inject(method = "load", at = @At("HEAD"))
    protected void injectLoadMethod ( CompoundTag pNbt, CallbackInfo pInfo ) {
        if ( pNbt.contains("guidebookAPI", 10) )
            this.persistentData = pNbt.getCompound("guidebookAPI");
    }

    public CompoundTag getPersistentData() {
        if ( this.persistentData == null )
            this.persistentData = new CompoundTag();

        return this.persistentData;
    }
}
