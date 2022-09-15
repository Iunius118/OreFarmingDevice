package com.github.iunius118.orefarmingdevice.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class OreFarmingDeviceConfig {
    public static class Server {
        public final BooleanValue isCobblestoneFeederAvailable;
        public final BooleanValue canAccelerateProcessingSpeedByTier;
        public final BooleanValue canIncreaseFuelConsumptionByTier;
        public final BooleanValue isFarmingEfficiencyEnabled;


        Server(ForgeConfigSpec.Builder builder) {
            builder.comment("O.F.Device's game server side settings.").push("server");

            isCobblestoneFeederAvailable = builder
                    .comment(   "Whether OF Cobblestone Feeder is available for devices.\n" +
                                "Default: true")
                    .define("isCobblestoneFeederAvailable", true);
            canAccelerateProcessingSpeedByTier = builder
                    .comment(   "Whether to accelerate the processing speed of the device by its modification.\n" +
                                "Default: true")
                    .define("accelerateProcessingSpeedByTier", true);
            canIncreaseFuelConsumptionByTier = builder
                    .comment(   "Whether to increase the fuel consumption of the device by its modification.\n" +
                                "Default: true")
                    .define("increaseFuelConsumptionByTier", true);
            isFarmingEfficiencyEnabled = builder
                    .comment(   "Whether mobs around the device increase farming efficiency of the device.\n" +
                                "Default: true")
                    .define("enableFarmingEfficiency", true);

            builder.pop();
        }

        public boolean isCobblestoneFeederAvailable() {
            return isCobblestoneFeederAvailable.get();
        }

        public boolean canAccelerateProcessingSpeedByTier() {
            return canAccelerateProcessingSpeedByTier.get();
        }

        public boolean canIncreaseFuelConsumptionByTier() {
            return canIncreaseFuelConsumptionByTier.get();
        }

        public boolean isFarmingEfficiencyEnabled() {
            return isFarmingEfficiencyEnabled.get();
        }
    }

    public static final ForgeConfigSpec serverSpec;
    public static final Server SERVER;

    static {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        serverSpec = specPair.getRight();
        SERVER = specPair.getLeft();
    }
}
