package com.github.iunius118.orefarmingfurnace.common;

import com.github.iunius118.orefarmingfurnace.data.ModLanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class RegistryEventHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ForgeBlockTagsProvider blockTagsProvider = new ForgeBlockTagsProvider(dataGenerator, existingFileHelper);

        if (event.includeServer()) {

        }

        if (event.includeClient()) {
            ModLanguageProvider.addProviders(dataGenerator);
        }
    }
}
