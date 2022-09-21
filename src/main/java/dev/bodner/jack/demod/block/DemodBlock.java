package dev.bodner.jack.demod.block;

import dev.bodner.jack.demod.DemodMain;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public final class DemodBlock {
    private static final HashMap<Identifier, Block> BLOCKS = new HashMap<>();
    public static void RegisterBlocks() {
        for (Identifier identifier : BLOCKS.keySet()) {
            Registry.register(Registry.BLOCK, identifier, BLOCKS.get(identifier));
            Registry.register(Registry.ITEM, identifier, new BlockItem(BLOCKS.get(identifier), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        }
    }
    public static final Block CUBE = new Block(AbstractBlock.Settings.of(Material.WOOL).mapColor(MapColor.CYAN));
    static {
        BLOCKS.put(new Identifier(DemodMain.MODID, "cube"), CUBE);
    }
}
