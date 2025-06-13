package softcat.kingvillager.Registration;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import softcat.kingvillager.KingVillagerMod;

@Mod.EventBusSubscriber(modid = KingVillagerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProfessionRegistration {

    @ObjectHolder(value = KingVillagerMod.MOD_ID + ":king", registryName = "minecraft:villager_profession")
    public static final VillagerProfession king = null;
    
    @ObjectHolder(value = KingVillagerMod.MOD_ID + ":kingpoi", registryName = "minecraft:point_of_interest_type")
    public static final PoiType kingpoi = null;
}
