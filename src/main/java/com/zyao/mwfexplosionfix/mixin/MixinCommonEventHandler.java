package com.zyao.mwfexplosionfix.mixin;

import com.modularwarfare.common.handler.CommonEventHandler;
import com.zyao.mwfexplosionfix.PacketExplosionFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.modularwarfare.ModularWarfare;
import com.modularwarfare.common.network.PacketExplosion;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.world.ExplosionEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CommonEventHandler.class, remap = false, priority = 1001)
public class MixinCommonEventHandler {

    @Inject(method = "explosionEvent", at = @At("HEAD"), cancellable = true, remap = false)
    public void explosionEvent(ExplosionEvent e, CallbackInfo ci) {
        ci.cancel();
        Vec3d pos = e.getExplosion().getPosition();
        ModularWarfare.NETWORK.sendToAll(new PacketExplosion(pos.x, pos.y, pos.z));
        ModularWarfare.NETWORK.sendToAll(new PacketExplosionFix(pos.x, pos.y, pos.z));
    }
}
