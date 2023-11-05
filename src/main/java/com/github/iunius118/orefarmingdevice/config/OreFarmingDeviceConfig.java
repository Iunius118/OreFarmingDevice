package com.github.iunius118.orefarmingdevice.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class OreFarmingDeviceConfig {
    public static class Server {
        public final BooleanValue isCobblestoneFeederAvailable;
        public final BooleanValue canAccelerateProcessingSpeedByMod;
        public final BooleanValue canIncreaseFuelConsumptionByMod;
        public final BooleanValue isFarmingEfficiencyEnabled;


        Server(ForgeConfigSpec.Builder builder) {
            builder.comment("O.F.Device's game server-side settings.").push("server");

            isCobblestoneFeederAvailable = builder
                    .comment("===========================================================",
                            "isCobblestoneFeederAvailable as boolean",
                            "  Whether OF Cobblestone Feeder is available for devices.",
                            "  Default: true")
                    .define("isCobblestoneFeederAvailable", true);

            isFarmingEfficiencyEnabled = builder
                    .comment("===========================================================",
                            "enableFarmingEfficiency as boolean",
                            "  Whether mobs around device increase farming efficiency of device.",
                            "  Default: true")
                    .define("enableFarmingEfficiency", true);

            canAccelerateProcessingSpeedByMod = builder
                    .comment("===========================================================",
                            "accelerateProcessingSpeedByMod as boolean",
                            "  Whether to accelerate processing speed of device by modification.",
                            "         Production time       ",
                            "        (Seconds/Product)      ",
                            "           Mod 0  Mod 1  Mod 2 ",
                            "    true    10.0    5.0    2.5 ",
                            "    false   10.0   10.0   10.0 ",
                            "  Default: true")
                    .define("accelerateProcessingSpeedByMod", true);

            canIncreaseFuelConsumptionByMod = builder
                    .comment("===========================================================",
                            "increaseFuelConsumptionByMod as boolean",
                            "  Whether to increase fuel consumption of device by modification.",
                            "         Fuel Consumption      ",
                            "    Ratios of Devices to Mod 0 ",
                            "           Mod 0  Mod 1  Mod 2 ",
                            "    true     1      2      4   ",
                            "    false    1      1      1   ",
                            "  Default: true")
                    .define("increaseFuelConsumptionByMod", true);

            builder.pop();
        }

        public boolean isCobblestoneFeederAvailable() {
            return isCobblestoneFeederAvailable.get();
        }

        public boolean canAccelerateProcessingSpeedByMod() {
            return canAccelerateProcessingSpeedByMod.get();
        }

        public boolean canIncreaseFuelConsumptionByMod() {
            return canIncreaseFuelConsumptionByMod.get();
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
