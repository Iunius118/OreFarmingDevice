package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider {
    public static void addProviders(DataGenerator gen) {
        gen.addProvider(new ModEnUsLanguageProvider(gen));
        gen.addProvider(new ModJaJpLanguageProvider(gen));
    }

    public static class ModEnUsLanguageProvider extends LanguageProvider {
        public ModEnUsLanguageProvider(DataGenerator gen) {
            super(gen, OreFarmingDevice.MOD_ID, "en_us");
        }

        public ModEnUsLanguageProvider(DataGenerator gen, String modID, String locale) {
            super(gen, modID, locale);
        }

        @Override
        protected void addTranslations() {
            // Item groups
            add("itemGroup." + OreFarmingDevice.MOD_ID, OreFarmingDevice.MOD_NAME);

            // Items

            // Blocks

            // Container titles
            add(OFDeviceType.MK_1.getContainerTranslationKey(), "OF Device Mk I");
            add(OFDeviceType.MK_2.getContainerTranslationKey(), "OF Device Mk II");
            add(OFDeviceType.MK_3.getContainerTranslationKey(), "OF Device Mk III");
        }

        @Override
        public String getName() {
            return super.getName() + ": " +  OreFarmingDevice.MOD_NAME;
        }
    }

    public static class ModJaJpLanguageProvider extends ModEnUsLanguageProvider {
        public ModJaJpLanguageProvider(DataGenerator gen) {
            super(gen,  OreFarmingDevice.MOD_ID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            // Item groups
            add("itemGroup." + OreFarmingDevice.MOD_ID, OreFarmingDevice.MOD_NAME);

            // Items

            // Blocks

            // Container titles
            add(OFDeviceType.MK_1.getContainerTranslationKey(), "ＯＦ１号装置");
            add(OFDeviceType.MK_2.getContainerTranslationKey(), "ＯＦ２号装置");
            add(OFDeviceType.MK_3.getContainerTranslationKey(), "ＯＦ３号装置");
        }
    }
}
