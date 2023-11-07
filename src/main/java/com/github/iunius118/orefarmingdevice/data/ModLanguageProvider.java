package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public final static String MOD_ITEM_GROUP_KEY = "itemGroup." + OreFarmingDevice.MOD_ID + ".main";
    public final TranslatedNameProvider translatedNameProvider;

    public ModLanguageProvider(PackOutput packOutput, String locale, TranslatedNameProvider translatedNameProvider) {
        super(packOutput, OreFarmingDevice.MOD_ID, locale);
        this.translatedNameProvider = translatedNameProvider;
    }

    public static void addProviders(boolean needsRun, DataGenerator gen) {
        var packOutput = gen.getPackOutput();
        gen.addProvider(needsRun, new ModLanguageProvider(packOutput, "en_us", new TranslatedNameProvider()));
        gen.addProvider(needsRun, new ModLanguageProvider(packOutput, "ja_jp", new JaJpNameProvider()));
    }

    // en_us
    public static class TranslatedNameProvider {
        public String deviceModZeroName = "OF Device";
        public String deviceModOneName = "OF Device Mod 1";
        public String deviceModTwoName = "OF Device Mod 2";
        public String cobblestoneFeederName = "OF C Feeder I";
    }

    // ja_jp
    public static class JaJpNameProvider extends TranslatedNameProvider {
        private JaJpNameProvider() {
            deviceModZeroName = "ＯＦ装置";
            deviceModOneName = "ＯＦ装置改";
            deviceModTwoName = "ＯＦ装置改二";
            cobblestoneFeederName = "ＯＦ丙供給装置一型";
        }
    }

    @Override
    protected void addTranslations() {
        // Item groups
        add(MOD_ITEM_GROUP_KEY, OreFarmingDevice.MOD_NAME);

        // Items
        add(ModItems.COBBLESTONE_FEEDER, translatedNameProvider.cobblestoneFeederName);

        // Blocks
        add(ModBlocks.DEVICE_0, translatedNameProvider.deviceModZeroName);
        add(ModBlocks.DEVICE_1, translatedNameProvider.deviceModOneName);
        add(ModBlocks.DEVICE_2, translatedNameProvider.deviceModTwoName);

        // Container titles
        add(OFDeviceType.MOD_0.getContainerTranslationKey(), translatedNameProvider.deviceModZeroName);
        add(OFDeviceType.MOD_1.getContainerTranslationKey(), translatedNameProvider.deviceModOneName);
        add(OFDeviceType.MOD_2.getContainerTranslationKey(), translatedNameProvider.deviceModTwoName);
    }
}
