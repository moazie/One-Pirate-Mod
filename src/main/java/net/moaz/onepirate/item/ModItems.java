package net.moaz.onepirate.item;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moaz.onepirate.OnePirate;

public class ModItems {

    public static final Item GumFruit = registerItem("gumgumfruit", new Item(new FabricItemSettings().food(ModFoodComponents.GUM_FRUIT)));

    private static void  ItemsToTabGroup(FabricItemGroupEntries entries) {
        entries.add(GumFruit);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OnePirate.MOD_ID, name), item);
    }

    public static void registerModItems() {
        OnePirate.LOGGER.info("Registering Mod Items For"+ OnePirate.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::ItemsToTabGroup);
    }
}
