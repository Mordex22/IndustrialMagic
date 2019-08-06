package imagic.common.config;

import imagic.IndustrialMagic;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nullable;

public class IndustrialMagicConfig {

    private static IndustrialMagicConfig LOCAL = new IndustrialMagicConfig();
    private static IndustrialMagicConfig SERVER = null;
    public GeneralConfig general = new GeneralConfig();
    public ClientConfig client = new ClientConfig();

    // Current config, for use when querying the config
    public static IndustrialMagicConfig current() {
        return SERVER != null ? SERVER : LOCAL;
    }

    public static IndustrialMagicConfig local() {
        return LOCAL;
    }

    public static void setSyncedConfig(@Nullable IndustrialMagicConfig newConfig) {
        if (newConfig != null) {
            newConfig.client = LOCAL.client;
        }
        SERVER = newConfig;
    }
}
