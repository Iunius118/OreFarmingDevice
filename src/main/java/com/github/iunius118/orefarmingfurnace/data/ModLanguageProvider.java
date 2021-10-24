package com.github.iunius118.orefarmingfurnace.data;

import com.github.iunius118.orefarmingfurnace.OreFarmingFurnace;
import com.github.iunius118.orefarmingfurnace.world.level.block.entity.OFFDeviceType;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider {
    public static void addProviders(DataGenerator gen) {
        gen.addProvider(new ModEnUsLanguageProvider(gen));
        gen.addProvider(new ModJaJpLanguageProvider(gen));
    }

    public static class ModEnUsLanguageProvider extends LanguageProvider {
        public ModEnUsLanguageProvider(DataGenerator gen) {
            super(gen, OreFarmingFurnace.MOD_ID, "en_us");
        }

        public ModEnUsLanguageProvider(DataGenerator gen, String modID, String locale) {
            super(gen, modID, locale);
        }

        @Override
        protected void addTranslations() {
            // Item groups
            add("itemGroup." + OreFarmingFurnace.MOD_ID, OreFarmingFurnace.MOD_NAME);

            // Items

            // Blocks

            // Container titles
            add(OFFDeviceType.MK_1.getContainerTranslationKey(), "O.F.F. Device Mk I");
            add(OFFDeviceType.MK_2.getContainerTranslationKey(), "O.F.F. Device Mk II");
            add(OFFDeviceType.MK_3.getContainerTranslationKey(), "O.F.F. Device Mk III");
        }

        @Override
        public String getName() {
            return super.getName() + ": " + OreFarmingFurnace.MOD_NAME;
        }
    }

    public static class ModJaJpLanguageProvider extends ModEnUsLanguageProvider {
        public ModJaJpLanguageProvider(DataGenerator gen) {
            super(gen, OreFarmingFurnace.MOD_ID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            // Item groups
            add("itemGroup." + OreFarmingFurnace.MOD_ID, OreFarmingFurnace.MOD_NAME);

            // Items

            // Blocks

            // Container titles
            add(OFFDeviceType.MK_1.getContainerTranslationKey(), "O.F.F. 1号装置");
            add(OFFDeviceType.MK_2.getContainerTranslationKey(), "O.F.F. 2号装置");
            add(OFFDeviceType.MK_3.getContainerTranslationKey(), "O.F.F. 3号装置");
        }
    }
}
