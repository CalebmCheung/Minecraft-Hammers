package minecraft_hammers.minecraft_hammers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Minecraft_Hammers extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting Hammers Plugin");
        Bukkit.addRecipe(getWoodHammerRecipe());
        Bukkit.addRecipe(getIronHammerRecipe());
        Bukkit.addRecipe(getGoldHammerRecipe());
        Bukkit.addRecipe(getDiamondHammerRecipe());
        Bukkit.addRecipe(getNetheriteHammerRecipe());
        new hammerHandler(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ShapedRecipe getWoodHammerRecipe(){
        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Wooden Hammer");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Mines a 3x3 area");
        meta.setLore(lore);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wooden_hammer");

        ShapedRecipe recipe = new ShapedRecipe(key,item);

        recipe.shape("BBB"," S "," S ");

        recipe.setIngredient('B',Material.OAK_LOG);
        recipe.setIngredient('S',Material.STICK);

        return recipe;
    }

    public ShapedRecipe getIronHammerRecipe(){
        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Iron Hammer");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Mines a 3x3 area");
        meta.setLore(lore);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "iron_hammer");

        ShapedRecipe recipe = new ShapedRecipe(key,item);

        recipe.shape("BBB"," S "," S ");

        recipe.setIngredient('B',Material.IRON_BLOCK);
        recipe.setIngredient('S',Material.STICK);

        return recipe;
    }

    public ShapedRecipe getGoldHammerRecipe(){
        ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Gold Hammer");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Mines a 3x3 area");
        meta.setLore(lore);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "gold_hammer");

        ShapedRecipe recipe = new ShapedRecipe(key,item);

        recipe.shape("BBB"," S "," S ");

        recipe.setIngredient('B',Material.GOLD_BLOCK);
        recipe.setIngredient('S',Material.STICK);

        return recipe;
    }

    public ShapedRecipe getDiamondHammerRecipe(){
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Diamond Hammer");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Mines a 3x3 area");
        meta.setLore(lore);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "diamond_hammer");

        ShapedRecipe recipe = new ShapedRecipe(key,item);

        recipe.shape("BBB"," S "," S ");

        recipe.setIngredient('B',Material.DIAMOND_BLOCK);
        recipe.setIngredient('S',Material.STICK);

        return recipe;
    }

    public ShapedRecipe getNetheriteHammerRecipe(){
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Netherite Hammer");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Mines a 3x3 area");
        meta.setLore(lore);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "netherite_hammer");

        ShapedRecipe recipe = new ShapedRecipe(key,item);

        recipe.shape("BBB"," S "," S ");

        recipe.setIngredient('B',Material.NETHERITE_BLOCK);
        recipe.setIngredient('S',Material.STICK);

        return recipe;
    }
}
