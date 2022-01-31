package io.wispforest.affinity.item;

import io.wispforest.affinity.Affinity;
import io.wispforest.affinity.block.impl.PeculiarClumpBlock;
import io.wispforest.affinity.init.AffinityBlocks;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class GeologicalResonatorItem extends Item {

    public GeologicalResonatorItem() {
        super(new OwoItemSettings().tab(0).group(Affinity.AFFINITY_GROUP).maxCount(1));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        final var world = context.getWorld();
        final var pos = context.getBlockPos();

        if (world.getBlockState(pos).getBlock() != AffinityBlocks.PECULIAR_CLUMP) {
            return ActionResult.PASS;
        }

        if (world.isClient()) return ActionResult.SUCCESS;
        final var validDirection = PeculiarClumpBlock.getValidDirection(pos);
        final var side = context.getSide();
        if (validDirection == side) {
            WorldOps.playSound(world, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, SoundCategory.BLOCKS);
        }

        return ActionResult.SUCCESS;
    }
}
