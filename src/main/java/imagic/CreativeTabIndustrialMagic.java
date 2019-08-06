package imagic;

import imagic.common.IndustrialMagicItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabIndustrialMagic extends CreativeTabs {

    public CreativeTabIndustrialMagic() {
        super("tabIndustrialMagic");
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(IndustrialMagicItems.Ingot);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(IndustrialMagicItems.Ingot);
    }
}
