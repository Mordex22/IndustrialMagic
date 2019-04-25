package imagic;

import imagic.common.CommonProxy;
import imagic.common.IndustrialMagicBlocks;
import imagic.common.IndustrialMagicItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = IndustrialMagic.MOD_ID, name = IndustrialMagic.MOD_NAME, version = "1.0.0",
        acceptedMinecraftVersions = "[1.12]", useMetadata = true)
@Mod.EventBusSubscriber()
public class IndustrialMagic {

    public static final String MOD_ID = "imagic";
    public static final String MOD_NAME = "Industrial Magic";

    @Mod.Instance
    public static IndustrialMagic instance;

    public static Logger logger = LogManager.getLogger(MOD_NAME);

    public static CreativeTabIndustrialMagic tabIndustrialMagic = new CreativeTabIndustrialMagic();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IndustrialMagicBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IndustrialMagicItems.registerItems(event.getRegistry());
        IndustrialMagicBlocks.registerItemBlocks(event.getRegistry());
        registerOreDict();
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {

    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        proxy.registerBlockRenders();
        proxy.registerItemRenders();
    }

    // Registers specified items with the Ore Dictionary
    public static void registerOreDict() {

    }

    @SidedProxy(clientSide = "imagic.client.ClientProxy", serverSide = "imagic.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        proxy.init();
        logger.info("Mod loaded.");
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {

    }

}
