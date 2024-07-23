package com.github.iunius118.orefarmingdevice.client.gui;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.CobblestoneDeviceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CobblestoneDeviceScreen extends AbstractContainerScreen<CobblestoneDeviceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(OreFarmingDevice.MOD_ID, "textures/gui/container/c_feeder.png");

    public CobblestoneDeviceScreen(CobblestoneDeviceMenu menu, Inventory playerInventory, Component textComponent) {
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
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float renderTicks) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, renderTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float renderTicks, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }
}
