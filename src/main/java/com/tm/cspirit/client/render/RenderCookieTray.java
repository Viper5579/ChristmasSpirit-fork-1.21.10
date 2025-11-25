package com.tm.cspirit.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tm.cspirit.tileentity.TileEntityCookieTray;
import com.tm.cspirit.tileentity.base.CSItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class RenderCookieTray implements BlockEntityRenderer<TileEntityCookieTray> {

    public RenderCookieTray(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render (TileEntityCookieTray tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {

        CSItemHandler inv = tileEntity.getInventory();

        for (int i = 0; i < inv.getStackInSlot(0).getCount(); i++) {
            renderItem(inv.getStackInSlot(0), 0.5F, 0.1F + (i * 0.029F), 0.6F, partialTicks, matrixStack, buffer, combinedLight);
        }

        for (int i = 0; i < inv.getStackInSlot(1).getCount(); i++) {
            renderItem(inv.getStackInSlot(1), 0.9F, 0.099F + (i * 0.029F), 0.8F, partialTicks, matrixStack, buffer, combinedLight);
        }

        for (int i = 0; i < inv.getStackInSlot(2).getCount(); i++) {
            renderItem(inv.getStackInSlot(2), 0.9F, 0.098F + (i * 0.029F), 0.35F, partialTicks, matrixStack, buffer, combinedLight);
        }
    }

    private void renderItem (ItemStack stack, float x, float y, float z, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight) {

        matrixStack.pushPose();
        matrixStack.scale(0.7F, 0.7F, 0.7F);
        matrixStack.translate(x, y, z);
        matrixStack.mulPose(Axis.XP.rotationDegrees(90));
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemDisplayContext.GROUND, combinedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer, null, 0);
        matrixStack.popPose();
    }
}
