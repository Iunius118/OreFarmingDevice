package com.github.iunius118.orefarmingdevice.client.gui;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class OFDeviceScreen extends AbstractContainerScreen<OFDeviceMenu> {
    private static final ResourceLocation LIT_PROGRESS_SPRITE = OreFarmingDevice.makeId("container/of_device/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = OreFarmingDevice.makeId("container/of_device/burn_progress");
    private static final ResourceLocation TEXTURE = OreFarmingDevice.makeId("textures/gui/container/of_device.png");

    public OFDeviceScreen(OFDeviceMenu menu, Inventory playerInventory, Component textComponent) {
        super(menu, playerInventory, textComponent);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float renderTicks) {
        renderBackground(guiGraphics, mouseX, mouseY, renderTicks);
        super.render(guiGraphics, mouseX, mouseY, renderTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float renderTicks, int mouseX, int mouseY) {
        int left = this.leftPos;
        int top = this.topPos;
        guiGraphics.blit(RenderType::guiTextured, TEXTURE, left, top, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        if (this.menu.isLit()) {
            // Render remaining burn time bar
            int litProgress = Mth.ceil(this.menu.getLitProgress() * 30.0F);
            guiGraphics.blitSprite(RenderType::guiTextured, LIT_PROGRESS_SPRITE, 8, 30, 0, 30 - litProgress, left + 78, top + 28 + 30 - litProgress, 8, litProgress);
        }

        // Render smelting progress bar
        int burnProgress = Mth.ceil(this.menu.getBurnProgress() * 16.0F);
        guiGraphics.blitSprite(RenderType::guiTextured, BURN_PROGRESS_SPRITE, 14, 16, 0, 0, left + 108, top + 35, 16, burnProgress);
    }
}
