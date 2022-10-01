package dev.bodner.jack.demod.item;

import dev.bodner.jack.demod.DemodMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public final class DemodItems {
    private static final HashMap<Identifier, Item> ITEMS = new HashMap<>();
    public static void RegisterItems(){
        for (Identifier identifier : ITEMS.keySet()){
            Registry.register(Registry.ITEM, identifier, ITEMS.get(identifier));
        }
    }
    public static final Item SQUARE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final SwordItem POWER_SWORD = new SwordItem(ToolMaterials.DIAMOND, 999, -3, new Item.Settings().group(ItemGroup.COMBAT));
    public static final SpeedtestItem QUICK_STICK = new SpeedtestItem(1,0, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
    public static final SpeedtestItem SLOW_STICK = new SpeedtestItem(1,1024.0f, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));

    public static final SpeedtestItem NOPE_STICK = new SpeedtestItem(1,-1.0f, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));

    static {
        ITEMS.put(new Identifier(DemodMain.MODID,"square"), SQUARE);
        ITEMS.put(new Identifier(DemodMain.MODID, "power_sword"), POWER_SWORD);
        ITEMS.put(new Identifier(DemodMain.MODID, "quick_stick"), QUICK_STICK);
        ITEMS.put(new Identifier(DemodMain.MODID, "slow_stick"), SLOW_STICK);
        ITEMS.put(new Identifier(DemodMain.MODID, "nope_stick"), NOPE_STICK);
    }
}
