
package com.zyao.mwfexplosionfix;

import com.modularwarfare.common.network.PacketBase;
import com.modularwarfare.common.particle.ParticleExplosion;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketExplosionFix extends PacketBase {

    private double posX;
    private double posY;
    private double posZ;

    public PacketExplosionFix() {
    }

    public PacketExplosionFix(double x, double y, double z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeDouble(this.posX);
        data.writeDouble(this.posY);
        data.writeDouble(this.posZ);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.posX = data.readDouble();
        this.posY = data.readDouble();
        this.posZ = data.readDouble();
    }

    @Override
    public void handleServerSide(EntityPlayerMP entityPlayer) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientSide(EntityPlayer entityPlayer) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;

            World world = Minecraft.getMinecraft().world;
            final Particle explosionParticle = new ParticleExplosion(world, this.posX, this.posY, this.posZ);

            Minecraft.getMinecraft().effectRenderer.addEffect(explosionParticle);

            return;
        });

    }

}