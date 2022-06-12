package com.github.iunius118.orefarmingdevice.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class OreFarmingDeviceConfig {
    public static class Server {
        public final BooleanValue isCobblestoneFeederAvailable;

        Server(ForgeConfigSpec.Builder builder) {
            builder.comment("O.F.Device's game server side settings.").push("server");

            isCobblestoneFeederAvailable = builder
                    .comment("Whether OF Cobblestone Feeder is available for OF Devices.\n" +
                            "Default: true")
                    .define("isCobblestoneFeederAvailable", true);

            builder.pop();
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
