package com.github.iunius118.orefarmingdevice.data;

import net.minecraftforge.data.event.GatherDataEvent;

public final class ModDataGenerator {
    public static void gatherData(GatherDataEvent event) {
        var dataGenerator = event.getGenerator();
        var packOutput = dataGenerator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        // Server
        boolean includesServer = event.includeServer();
        dataGenerator.addProvider(includesServer, new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
        dataGenerator.addProvider(includesServer, new ModLootTableProvider(packOutput, lookupProvider));
        dataGenerator.addProvider(includesServer, new ModRecipeProvider(packOutput, lookupProvider));
        // Disable data pack Experimental_1202 since 1.20.2
        // Experimental1202DataProvider.addProviders(event);

        // Client
        boolean includesClient = event.includeClient();
        dataGenerator.addProvider(includesClient, new ModBlockStateProvider(packOutput, existingFileHelper));
        dataGenerator.addProvider(includesClient, new ModItemModelProvider(packOutput, existingFileHelper));
        ModLanguageProvider.addProviders(includesClient, dataGenerator);
    }
}
