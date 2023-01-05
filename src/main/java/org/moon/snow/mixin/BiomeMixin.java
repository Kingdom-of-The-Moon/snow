package org.moon.snow.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import org.moon.snow.Snow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class BiomeMixin {

    @Inject(at = @At("RETURN"), method = "getPrecipitation", cancellable = true)
    private void getPreciptation(CallbackInfoReturnable<Biome.Precipitation> cir) {
        if (Snow.SNOW) cir.setReturnValue(Biome.Precipitation.SNOW);
    }

    @Inject(at = @At("RETURN"), method = "getTemperature", cancellable = true)
    private void getTemperature(BlockPos blockPos, CallbackInfoReturnable<Float> cir) {
        if (Snow.SNOW) cir.setReturnValue(0f);
    }

    @Inject(at = @At("RETURN"), method = "getBaseTemperature", cancellable = true)
    private void getBaseTemperature(CallbackInfoReturnable<Float> cir) {
        if (Snow.SNOW) cir.setReturnValue(0f);
    }

    @Inject(at = @At("RETURN"), method = "getDownfall", cancellable = true)
    private void getDownfall(CallbackInfoReturnable<Float> cir) {
        if (Snow.SNOW) cir.setReturnValue(1f);
    }
}
