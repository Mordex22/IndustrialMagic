package imagic.common;

import static imagic.common.blocks.states.BlockStateBasic.BasicBlock.BASIC_BLOCK;

import imagic.IndustrialMagic;
import imagic.common.blocks.BlockBasic;
import imagic.common.blocks.BlockOre;
import imagic.common.items.ItemBlockBasic;
import imagic.common.items.ItemBlockOre;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class IndustrialMagicBlocks {

    // Blocks
    public static Block BasicBlock = BlockBasic.getBlockBasic(BASIC_BLOCK);
    public static Block OreBlock = new BlockOre();

    // Adds and registers all blocks
    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.register(init(BasicBlock, "BasicBlock"));
        registry.register(init(OreBlock, "OreBlock"));
    }

    // Adds and registers all itemBlocks
    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.register(IndustrialMagicItems.init(new ItemBlockBasic(BasicBlock), "BasicBlock"));
        registry.register(IndustrialMagicItems.init(new ItemBlockOre(OreBlock), "OreBlock"));
    }

    public static Block init(Block block, String name) {
        return block.setUnlocalizedName(name).setRegistryName("imagic:" + name);
    }
}
