package dev.bodner.jack.demod;

import dev.bodner.jack.demod.block.DemodBlock;
import dev.bodner.jack.demod.item.DemodItems;
import net.fabricmc.api.ModInitializer;

public class DemodMain implements ModInitializer {
    public static final String MODID = "demod";
    @Override
    public void onInitialize() {
        DemodItems.RegisterItems();
        DemodBlock.RegisterBlocks();
    }
}
