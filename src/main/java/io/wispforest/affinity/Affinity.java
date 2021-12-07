package io.wispforest.affinity;

import io.wispforest.affinity.util.aethumflux.AethumNetworkMember;
import io.wispforest.affinity.util.aethumflux.AethumNetworkNode;
import io.wispforest.affinity.item.AffinityItemGroup;
import io.wispforest.affinity.registries.AffinityBlocks;
import io.wispforest.affinity.registries.AffinityItems;
import io.wispforest.affinity.registries.AffinityStatusEffects;
import io.wispforest.affinity.util.recipe.PotionMixingRecipe;
import io.wispforest.affinity.util.recipe.PotionMixingRecipeSerializer;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Affinity implements ModInitializer {

    public static final String MOD_ID = "affinity";
    public static final OwoItemGroup AFFINITY_GROUP = new AffinityItemGroup(id("affinity"));

    public static final BlockApiLookup<AethumNetworkMember, Void> AETHUM_MEMBER = BlockApiLookup.get(id("aethum_member"), AethumNetworkMember.class, Void.class);
    public static final BlockApiLookup<AethumNetworkNode, Void> AETHUM_NODE = BlockApiLookup.get(id("aethum_node"), AethumNetworkNode.class, Void.class);

    @Override
    public void onInitialize() {
        FieldRegistrationHandler.register(AffinityBlocks.class, MOD_ID, false);
        FieldRegistrationHandler.register(AffinityBlocks.Entities.class, MOD_ID, false);

        FieldRegistrationHandler.register(AffinityItems.class, MOD_ID, false);

        AffinityStatusEffects.register();

        Registry.register(Registry.RECIPE_TYPE, PotionMixingRecipe.Type.ID, PotionMixingRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, PotionMixingRecipeSerializer.ID, PotionMixingRecipeSerializer.INSTANCE);

        AFFINITY_GROUP.initialize();
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

}
