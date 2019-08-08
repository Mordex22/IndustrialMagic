package imagic.common.items;

import imagic.common.IMetaItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Locale;

public class ItemNugget extends ItemIndustrialMagic implements IMetaItem {

    public ItemNugget() {
        super();
        setHasSubtypes(true);
    }

    @Override
    public String getTexture(int meta) {
        return ItemIngot.en_USNames[meta] + "Nugget";
    }

    @Override
    public int getVariants() {
       return ItemIngot.en_USNames.length;
    }

    @Override
    public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> itemList) {
        if (!isInCreativeTab(tabs)) {
            return;
        }
        for (int i = 0; i < ItemIngot.en_USNames.length; i++) {
            itemList.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "item." + ItemIngot.en_USNames[itemStack.getItemDamage()].toLowerCase(Locale.ROOT) + "Nugget";
    }
}
