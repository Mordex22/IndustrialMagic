package imagic.common.blocks;

import imagic.IndustrialMagic;
import imagic.common.blocks.states.BlockStateOre;
import imagic.common.blocks.states.BlockStateOre.EnumOreType;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockOre extends Block {

    /*
     * Block class for dealing with multiple ore block IDs.
     * 0: Minarite Ore
     *
     * Modified from Mekanism's structure
     */
    public BlockOre() {
        super(Material.ROCK);
        setHardness(3F);
        setResistance(5F);
        setCreativeTab(IndustrialMagic.tabIndustrialMagic);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateOre(this);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BlockStateOre.typeProperty).ordinal();
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
        for (EnumOreType ore : EnumOreType.values()) {
            list.add(new ItemStack(this, 1, ore.ordinal()));
        }
    }
}
