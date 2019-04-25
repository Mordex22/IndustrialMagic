package imagic.common;

import imagic.IndustrialMagic;
import imagic.common.items.ItemIngot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class IndustrialMagicItems {

    // Multi-ID Items
    public static final Item Ingot = new ItemIngot();

    // Adds and registers all items
    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(init(Ingot, "Ingot"));
    }

    public static Item init(Item item, String name) {
        return item.setUnlocalizedName(name).setRegistryName("imagic:" + name);
    }
}
