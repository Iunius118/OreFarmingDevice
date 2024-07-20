package com.github.iunius118.orefarmingdevice.client.gui;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class OFDeviceScreen extends ContainerScreen<OFDeviceContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(OreFarmingDevice.MOD_ID, "textures/gui/container/of_device.png");

    public OFDeviceScreen(OFDeviceContainer menu, PlayerInventory playerInventory, ITextComponent textComponent) {
        super(menu, playerInventory, textComponent);
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
        this.blit(matrixStack, this.leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

        if (this.menu.isLit()) {
            // Render remaining burn time bar
            int k = this.menu.getLitProgress();
            this.blit(matrixStack, this.leftPos + 78, this.topPos + 28 + 29 - k, 176, 29 - k, 8, k + 1);
        }

        // Render smelting progress bar
        int l = this.menu.getBurnProgress();
        this.blit(matrixStack, this.leftPos + 108, this.topPos + 35, 176, 30, 16, l + 1);
    }
}
