package imagic.client;

import imagic.common.CommonProxy;
import imagic.common.IndustrialMagicBlocks;
import imagic.common.IndustrialMagicItems;
import imagic.common.blocks.states.BlockStateBasic;
import imagic.common.blocks.states.BlockStateBasic.BasicBlockType;
import imagic.common.blocks.states.BlockStateOre;
import imagic.common.blocks.states.BlockStateOre.EnumOreType;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private static final IStateMapper basicMapper = new BlockStateBasic.BasicBlockStateMapper();

    public static Map<String, ModelResourceLocation> basicResources = new HashMap<>();

    // All blocks get registered here
    @Override
    public void registerBlockRenders() {
        ModelLoader.setCustomStateMapper(IndustrialMagicBlocks.BasicBlock, basicMapper);

        for (BlockStateOre.EnumOreType ore : BlockStateOre.EnumOreType.values()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(IndustrialMagicBlocks.OreBlock), ore.ordinal(),
                    new ModelResourceLocation("imagic:OreBlock", "type=" + ore.getName()));
        }

        for (BasicBlockType type : BasicBlockType.values()) {
            List<ModelResourceLocation> modelsToAdd = new ArrayList<>();
            String resource = "imagic:" + type.getName();

            while(true) {
                if (basicResources.get(resource) == null) {
                    List<String> entries = new ArrayList<>();

                    if (type.hasActiveTexture()) {
                        entries.add("active=false");
                    }

                    if (type.hasRotations()) {
                        entries.add("facing=north");
                    }

                    String properties = "";

                    for (int i=0; i<entries.size(); i++) {
                        properties += entries.get(i);

                        if (i < entries.size() - 1) {
                            properties += ",";
                        }
                    }

                    ModelResourceLocation model = new ModelResourceLocation(resource, properties);

                    basicResources.put(resource, model);
                    modelsToAdd.add(model);
                }

                break;
            }

            ModelLoader.registerItemVariants(Item.getItemFromBlock(type.blockType.getBlock()),
                    modelsToAdd.toArray(new ModelResourceLocation[]{}));
        }

        for (EnumOreType ore : EnumOreType.values()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(IndustrialMagicBlocks.OreBlock), ore.ordinal(),
                    new ModelResourceLocation("imagic:OreBlock", "type=" + ore.getName()));
        }

        ItemMeshDefinition basicMesher = stack -> {
            BasicBlockType type = BasicBlockType.get(stack);

            if (type != null) {
                String resource = "imagic:" + type.getName();

                return basicResources.get(resource);
            }

            return null;
        };

        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(IndustrialMagicBlocks.BasicBlock), basicMesher);
    }

    // All items get registered here
    @Override
    public void registerItemRenders() {
        registerItemRender(IndustrialMagicItems.Ingot);
    }

    public void registerItemRender(Item item) {
        IndustrialMagicRenderer.registerItemRender("imagic", item);
    }

    @Override
    public void preInit() {
        IndustrialMagicRenderer.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void init() {
        super.init();
    }
}
