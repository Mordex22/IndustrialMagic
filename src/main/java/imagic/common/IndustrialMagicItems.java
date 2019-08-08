package imagic.common;

import imagic.IndustrialMagic;
import imagic.common.items.ItemIngot;
import imagic.common.items.ItemNugget;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class IndustrialMagicItems {

    // Multi-ID Items
    public static final Item Ingot = new ItemIngot();
    public static final Item Nugget = new ItemNugget();

    // Adds and registers all items
    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(init(Ingot, "Ingot"));
        registry.register(init(Nugget, "Nugget"));
    }

    public static Item init(Item item, String name) {
        return item.setUnlocalizedName(name).setRegistryName("imagic:" + name);
    }
}
