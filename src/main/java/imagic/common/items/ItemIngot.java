package imagic.common.items;

import imagic.common.IMetaItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import java.util.Locale;

public class ItemIngot extends ItemIndustrialMagic implements IMetaItem {

    public static String[] en_USNames = {"Minarite"};

    public ItemIngot() {
        super();
        setHasSubtypes(true);
    }

    @Override
    public String getTexture(int meta) {
        return en_USNames[meta] + "Ingot";
    }

    @Override
    public int getVariants() {
        return en_USNames.length;
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tabs, @Nonnull NonNullList<ItemStack> itemList) {
        if (!isInCreativeTab(tabs)) {
            return;
        }
        for (int counter=0; counter<en_USNames.length; counter++) {
            itemList.add(new ItemStack(this, 1, counter));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "item." + en_USNames[itemStack.getItemDamage()].toLowerCase(Locale.ROOT) + "Ingot";
    }
}
