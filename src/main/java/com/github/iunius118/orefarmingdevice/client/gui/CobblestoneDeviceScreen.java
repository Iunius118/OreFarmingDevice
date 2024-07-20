package com.github.iunius118.orefarmingdevice.client.gui;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.CobblestoneDeviceContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CobblestoneDeviceScreen extends ContainerScreen<CobblestoneDeviceContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(OreFarmingDevice.MOD_ID, "textures/gui/container/c_feeder.png");

    public CobblestoneDeviceScreen(CobblestoneDeviceContainer menu, PlayerInventory playerInventory, ITextComponent textComponent) {
        super(menu, playerInventory, textComponent);
        this.imageHeight = 133;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float renderTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, renderTicks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float renderTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }
}
