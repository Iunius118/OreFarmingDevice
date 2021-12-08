package com.github.iunius118.orefarmingdevice.client.gui;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OFDeviceScreen extends AbstractContainerScreen<OFDeviceContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(OreFarmingDevice.MOD_ID, "textures/gui/container/of_device.png");

    public OFDeviceScreen(OFDeviceContainer menu, Inventory playerInventory, Component textComponent) {
        super(menu, playerInventory, textComponent);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(PoseStack matrixStack, int x, int y, float renderTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, x, y, renderTicks);
        renderTooltip(matrixStack, x, y);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(matrixStack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

        if (this.menu.isLit()) {
            // Render remaining burn time bar
            int k = this.menu.getLitProgress();
            this.blit(matrixStack, leftPos + 78, topPos + 28 + 29 - k, 176, 29 - k, 8, k + 1);
        }

        // Render smelting progress bar
        int l = this.menu.getBurnProgress();
        this.blit(matrixStack, leftPos + 108, topPos + 35, 176, 30, 16, l + 1);
    }
}
