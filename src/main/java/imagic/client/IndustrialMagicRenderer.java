package imagic.client;

import imagic.common.IMetaItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class IndustrialMagicRenderer {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new IndustrialMagicRenderer());
    }

    public static void registerItemRender(String domain, Item item) {
        if (item instanceof IMetaItem) {
            IMetaItem metaItem = (IMetaItem) item;
            List<ModelResourceLocation> variants = new ArrayList<>();

            for (int i=0; i<metaItem.getVariants(); i++) {
                if (metaItem.getTexture(i) == null) {
                    continue;
                }

                ModelResourceLocation loc = new ModelResourceLocation(domain + ":" + metaItem.getTexture(i), "inventory");
                ModelLoader.setCustomModelResourceLocation(item, i, loc);
                variants.add(loc);
                ModelBakery.registerItemVariants(item, new ResourceLocation(domain + ":" + metaItem.getTexture(i)));
            }

            return;
        }
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
