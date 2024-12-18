package com.github.iunius118.orefarmingdevice.data;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class ModDataGenerator {
    public static void gatherData(GatherDataEvent.Client event) {
        var dataGenerator = event.getGenerator();
        var packOutput = dataGenerator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        // Data
        event.addProvider(new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
        event.addProvider(new ModLootTableProvider(packOutput, lookupProvider));
        event.addProvider(new ModRecipeProvider.Runner(packOutput, lookupProvider));
        // Disable data pack Experimental_1202 since 1.20.2
        // Experimental1202DataProvider.addProviders(event);

        // Assets
        event.addProvider(new ModBlockStateProvider(packOutput, existingFileHelper));
        event.addProvider(new ModItemModelProvider(packOutput, existingFileHelper));
        ModLanguageProvider.addProviders(event);
    }
}
