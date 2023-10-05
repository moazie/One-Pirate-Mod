package net.moaz.onepirate.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.moaz.onepirate.OnePirate;

public class ModItemGroups {
    public static final ItemGroup ONE_PIRATE = Registry.register(Registries.ITEM_GROUP,
            new Identifier(OnePirate.MOD_ID, "onepirate"),
            FabricItemGroup.builder().displayName(Text.translatable("One Pirate")).icon(() -> new ItemStack(ModItems.GumFruit)).entries((displayContext, entries) -> {
                        entries.add(ModItems.GumFruit);
                    }).build());

    public static void  registerItemGroups() {
        OnePirate.LOGGER.info("Registering Item Groups for " + OnePirate.MOD_ID);
    }
}
