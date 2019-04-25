package imagic;

import imagic.common.IndustrialMagicItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CreativeTabIndustrialMagic extends CreativeTabs {

    public CreativeTabIndustrialMagic() {
        super("tabIndustrialMagic");
    }

    @Nonnull
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(IndustrialMagicItems.Ingot);
    }
}
