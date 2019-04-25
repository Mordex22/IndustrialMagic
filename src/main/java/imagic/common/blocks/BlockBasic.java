package imagic.common.blocks;

import imagic.IndustrialMagic;
import imagic.common.blocks.states.BlockStateBasic;
import imagic.common.blocks.states.BlockStateBasic.BasicBlockType;
import imagic.common.blocks.states.BlockStateBasic.BasicBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

/*
 * Block class for dealing with multiple metal block IDs.
 * 0:0: Minarite Block
 *
 * Modified from Mekanism's structure
 */
public abstract class BlockBasic extends Block {

    public BlockBasic() {
        super(Material.IRON);
        setHardness(5F);
        setResistance(20F);
        setCreativeTab(IndustrialMagic.tabIndustrialMagic);

    }

    public static BlockBasic getBlockBasic(BasicBlock block) {
        return new BlockBasic() {
            @Override
            public BasicBlock getBasicBlock() {
                return block;
            }
        };
    }

    public abstract BasicBlock getBasicBlock();

    @Nonnull
    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateBasic(this, getTypeProperty());
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        BasicBlockType type = state.getValue(getTypeProperty());
        return type.meta;
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
        for (BlockStateBasic.BasicBlockType type : BasicBlockType.values()) {
            if (type.blockType == getBasicBlock()) {
                list.add(new ItemStack(this, 1, type.meta));
            }
        }
    }

    public PropertyEnum<BlockStateBasic.BasicBlockType> getTypeProperty() {
        return getBasicBlock().getProperty();
    }
}
