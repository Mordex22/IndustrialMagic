package imagic.common.world;

import imagic.IndustrialMagic;
import imagic.common.IndustrialMagicBlocks;
import imagic.common.config.IndustrialMagicConfig;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.world.gen.ChunkGeneratorHell;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class GenHandler implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
                         IChunkProvider chunkProvider) {

        if (!(chunkGenerator instanceof ChunkGeneratorHell) && !(chunkGenerator instanceof ChunkGeneratorEnd)) {
            for (int i = 0; i < IndustrialMagicConfig.current().general.minaritePerChunk.val(); i++) {
                BlockPos pos = new BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(60), (chunkZ * 16) + random.nextInt(16));
                new WorldGenMinable(IndustrialMagicBlocks.OreBlock.getStateFromMeta(0), IndustrialMagicConfig.current().general.minariteMaxVeinSize.val(), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
            }
        }
    }
}
