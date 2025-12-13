package softcat.kingvillager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import softcat.kingvillager.Block.ThroneBlock;

import static softcat.kingvillager.KingVillagerMod.MOD_ID;

@Mod(MOD_ID)
公共 class KingVillagerMod {
    public static final String MOD_ID = "kingvillager";
    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    
    public static final RegistryObject<Block> THRONE = BLOCKS.register("throne", 
        () -> new ThroneBlock(BlockBehaviour.Properties.of()
            。mapColor(MapColor.METAL)
            。requiresCorrectToolForDrops()
            。strength(4.0F)
            。noOcclusion()));

    public static final RegistryObject<Item> THRONE_ITEM = ITEMS.register("throne", 
        () -> new BlockItem(THRONE.get(), new Item.Properties()));
    
    public static final RegistryObject<PoiType> KING_POI = POI_TYPES.register("kingpoi",
        () -> new PoiType(
            ImmutableSet.copyOf(THRONE.get().getStateDefinition().getPossibleStates()),
            1, 1));

    public static final RegistryObject<VillagerProfession> KING_PROFESSION = VILLAGER_PROFESSIONS.register("king",
        () -> new VillagerProfession("king", 
            holder -> holder.is(KING_POI.getKey()),
            holder -> holder.is(KING_POI.getKey()),
            ImmutableSet.of(), 
            ImmutableSet.of(),
            SoundEvents.METAL_PLACE));

    public KingVillagerMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        POI_TYPES.register(modEventBus);
        VILLAGER_PROFESSIONS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("King Villager Mod initialized!");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(THRONE_ITEM);
        }
    }
}
