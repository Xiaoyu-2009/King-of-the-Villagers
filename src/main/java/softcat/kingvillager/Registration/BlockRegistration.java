package softcat.kingvillager.Registration;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import softcat.kingvillager.KingVillagerMod;

@Mod.EventBusSubscriber(modid = KingVillagerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistration {

    @ObjectHolder(value = KingVillagerMod.MOD_ID + ":throne", registryName = "minecraft:block")
    public static final Block throne = null;
}
