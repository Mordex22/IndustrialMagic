package imagic.common.items;

import imagic.common.blocks.states.BlockStateBasic.BasicBlockType;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/*
 * This class allows for dealing with multiple metal block IDs.
 * 0:0: Minarite Block
 */
public class ItemBlockBasic extends ItemBlock {

    public Block metaBlock;

    public ItemBlockBasic(Block block) {
        super(block);
        metaBlock = block;
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        BasicBlockType type = BasicBlockType.get(itemStack);

        if (type != null) {
            String name = getUnlocalizedName() + "." + type.name;

            return name;
        }

        return "Invalid Basic Block";
    }
}
