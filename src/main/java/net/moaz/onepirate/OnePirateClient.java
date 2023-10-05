package net.moaz.onepirate;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.moaz.onepirate.item.ModFoodComponents;
import net.moaz.onepirate.item.ModItems;

import java.util.ArrayList;

public class OnePirateClient implements ClientModInitializer {

    public static float itemUseTime = 0;
    private static final ArrayList<Item> foodItems = new ArrayList<>(Registries.ITEM.stream().filter(Item::isFood).toList());
    static {
        foodItems.add(ModItems.GumFruit);
    }

    @Override
    public void onInitializeClient() {

        for (Item item : foodItems) {
            FabricModelPredicateProviderRegistry.register(item, new Identifier("eat"), (itemStack, clientWorld, livingEntity, i) -> {
                if (livingEntity == null) {
                    return 0.0F;
                }

                if(livingEntity instanceof OtherClientPlayerEntity && livingEntity.getItemUseTime() > 31){
                    return itemUseTime / 30;
                }
                return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 30.0F;
            });

            FabricModelPredicateProviderRegistry.register(item, new Identifier("eating"), (itemStack, clientWorld, livingEntity, i) -> {
                if (livingEntity == null) {
                    return 0.0F;
                }

                return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
            });

        }
                FabricLoader.getInstance().getModContainer("onepirate").ifPresent(eatinganimation ->
                ResourceManagerHelper.registerBuiltinResourcePack(OnePirateClient.locate("supporteatinganimationpirate"), eatinganimation, ResourcePackActivationType.DEFAULT_ENABLED));
    }
    public static Identifier locate(String path) {
        return new Identifier(path);
    }
}
