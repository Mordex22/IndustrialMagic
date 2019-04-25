package imagic.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/*
 * Item class for dealing with multiple ore block IDs.
 * 0: Minarite Ore
 */
public class ItemBlockOre extends ItemBlock {

    public Block metaBlock;

    public ItemBlockOre(Block block) {
        super(block);
        metaBlock = block;
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        String name;
        switch (itemStack.getItemDamage()) {
            case 0:
                name = "MinariteOre";
                break;
            default:
                name = "Unknown";
                break;
        }
        return getUnlocalizedName() + "." + name;
    }
}
