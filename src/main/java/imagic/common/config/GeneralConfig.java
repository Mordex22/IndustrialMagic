package imagic.common.config;

public class GeneralConfig extends BaseConfig {

    public final IntOption minaritePerChunk = new IntOption(this, "general", "MinaritePerChunk", 16, "Chances for minarite to generate in a chunk", 0, Integer.MAX_VALUE);

    public final IntOption minariteMaxVeinSize = new IntOption(this, "general", "MinariteVeinSize", 8, "Max number of blocks in the minarite vein", 1, Integer.MAX_VALUE);
}
