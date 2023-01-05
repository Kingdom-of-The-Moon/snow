package org.moon.snow.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.moon.snow.Snow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin extends Block {

    public IceBlockMixin(Properties properties) {
        super(properties);
    }

    @Shadow protected abstract void melt(BlockState state, Level world, BlockPos pos);

    @Inject(at = @At("RETURN"), method = "randomTick")
    private void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (!Snow.SNOW && world.isRaining() && !world.getBiome(pos).value().coldEnoughToSnow(pos))
            this.melt(state, world, pos);
    }
}
