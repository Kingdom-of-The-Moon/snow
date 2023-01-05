package org.moon.snow.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.moon.snow.Snow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowLayerBlock.class)
public class SnowLayerBlockMixin extends Block {

    public SnowLayerBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(at = @At("RETURN"), method = "randomTick")
    private void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (!Snow.SNOW && world.isRaining() && !world.getBiome(pos).value().coldEnoughToSnow(pos)) {
            dropResources(state, world, pos);
            world.removeBlock(pos, false);
        }
    }
}
