package dev.knoxy.hit360.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClientPlayerEntity.class, priority = 20000)
public abstract class NoSlowdownMixin {
	@Inject(method = "shouldSlowDown", at = @At("HEAD"), cancellable = true)
	private void disableSlowDown(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
}