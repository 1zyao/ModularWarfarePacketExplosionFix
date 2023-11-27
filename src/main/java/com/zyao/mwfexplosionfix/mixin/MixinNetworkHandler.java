package com.zyao.mwfexplosionfix.mixin;

import com.modularwarfare.ModularWarfare;
import com.modularwarfare.common.network.NetworkHandler;
import com.modularwarfare.common.network.PacketClientKillFeedEntry;
import com.modularwarfare.common.network.PacketExplosion;
import com.modularwarfare.common.network.*;
import com.zyao.mwfexplosionfix.PacketExplosionFix;
import io.netty.channel.ChannelHandler;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(value = NetworkHandler.class, remap = false, priority = 1001)
public class MixinNetworkHandler {
    @Shadow
    private EnumMap<Side, FMLEmbeddedChannel> channels;

    @Shadow
    private LinkedList<Class<? extends PacketBase>> packets = new LinkedList<Class<? extends PacketBase>>();

    @Shadow
    private boolean modInitialised = false;

    @Shadow
    public boolean registerPacket(Class<? extends PacketBase> cl) {
        if (packets.size() > 256) {
            ModularWarfare.LOGGER.warn("Packet limit exceeded in Modular Warfare packet handler by packet " + cl.getCanonicalName() + ".");
            return false;
        }
        if (packets.contains(cl)) {
            ModularWarfare.LOGGER.warn("Tried to register " + cl.getCanonicalName() + " packet class twice.");
            return false;
        }
        if (modInitialised) {
            ModularWarfare.LOGGER.warn("Tried to register packet " + cl.getCanonicalName() + " after mod initialisation.");
            return false;
        }

        packets.add(cl);
        return true;
    }

    @Inject(method = "initialise", at = @At("HEAD"), cancellable = true, remap = false)
    public void initialise(CallbackInfo ci) {
        ci.cancel();
        channels = NetworkRegistry.INSTANCE.newChannel("ModularWarfare", (ChannelHandler) this);

        registerPacket(PacketGunFire.class);
        registerPacket(PacketPlaySound.class);
        registerPacket(PacketPlayHitmarker.class);
        registerPacket(PacketGunSwitchMode.class);
        registerPacket(PacketGunReload.class);
        registerPacket(PacketGunReloadEnhancedTask.class);
        registerPacket(PacketGunReloadEnhancedStop.class);
        registerPacket(PacketGunReloadSound.class);
        registerPacket(PacketGunAddAttachment.class);
        registerPacket(PacketGunUnloadAttachment.class);
        registerPacket(PacketClientAnimation.class);
        registerPacket(PacketGunTrail.class);
        registerPacket(PacketAimingRequest.class);
        registerPacket(PacketAimingReponse.class);
        registerPacket(PacketDecal.class);
        registerPacket(PacketOpenNormalInventory.class);
        registerPacket(PacketOpenExtraArmorInventory.class);
        registerPacket(PacketSyncBackWeapons.class);
        registerPacket(PacketBulletSnap.class);
        registerPacket(PacketPlayerHit.class);

        registerPacket(PacketSyncExtraSlot.class);
        registerPacket(PacketOpenGui.class);
        registerPacket(PacketExplosion.class);
        registerPacket(PacketExplosionFix.class);
        registerPacket(PacketClientKillFeedEntry.class);

        registerPacket(PacketFlashClient.class);
        registerPacket(PacketExpGunFire.class);
        registerPacket(PacketGunTrailAskServer.class);
        registerPacket(PacketExpShot.class);

    }
}