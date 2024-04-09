package minecraft_hammers.minecraft_hammers;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class hammerHandler implements Listener {
    //-------------------------------- Known Issues -----------------------------------//
    //  -
    //  -
    //  -
    //--------------------------------------------------------------------------------//
    public hammerHandler(Minecraft_Hammers plugin){
        Bukkit.getPluginManager().registerEvents(this ,plugin);
    }
    private HashMap<Player,BlockFace> blockfaceCache = new HashMap<Player,BlockFace>();
    private int min = 0;
    private int max= 2;

    // used to compare hammer lore
    List<String> lore = new ArrayList<String>();


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        BlockFace blockface = e.getBlockFace();

        // add key,value (player, Blockface) to hashmap to make a cache of block interactions
        if(blockfaceCache.containsKey(player)){
            // update latest blockface interaction in cache for this player
            blockfaceCache.replace(player, blockface);
        }
        else{
            // make new entry for new player in cache
            blockfaceCache.put(player,blockface);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent e){
        World world = e.getBlock().getWorld();
        Player player = e.getPlayer();
        Block block = e.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        // setup compare list for the first time in order to filter hammer block breaks
        if(lore.isEmpty()){
            lore.add(ChatColor.GREEN + "Mines a 3x3 area");
        }

        //get block surrounding the broken block in the xyz plains
        List<Block> blocks =  new ArrayList<Block>();

        int radius = 1;
        if(item != null && itemMeta != null) {
            if(itemMeta.hasLore()) {
                if (item.getItemMeta().getLore().equals(lore)) {
                    if (String.valueOf(blockfaceCache.get(player)) == "UP" || String.valueOf(blockfaceCache.get(player)) == "DOWN") {
                        for (int x = block.getX() - radius; x <= block.getX() + radius; x++) {
                            for (int z = block.getZ() - radius; z <= block.getZ() + radius; z++) {
                                blocks.add(world.getBlockAt(x, block.getY(), z));
                            }
                        }
                    } else if (String.valueOf(blockfaceCache.get(player)) == "NORTH" || String.valueOf(blockfaceCache.get(player)) == "SOUTH") {
                        for (int x = block.getX() - radius; x <= block.getX() + radius; x++) {
                            for (int y = block.getY() - radius; y <= block.getY() + radius; y++) {
                                blocks.add(world.getBlockAt(x, y, block.getZ()));
                            }
                        }
                    } else if (String.valueOf(blockfaceCache.get(player)) == "EAST" || String.valueOf(blockfaceCache.get(player)) == "WEST") {
                        for (int z = block.getZ() - radius; z <= block.getZ() + radius; z++) {
                            for (int y = block.getY() - radius; y <= block.getY() + radius; y++) {
                                blocks.add(world.getBlockAt(block.getX(), y, z));
                            }
                        }
                    }

                    //break the blocks around the broken block
                    for (Block b : blocks) {
//                      player.sendMessage("breaking" + b.getType());
                        b.breakNaturally(item);
                    }

                    //-----------------------------------
                    // Netherite Hammers are unbreakable, so ignored in this section
                    //-----------------------------------
//                    player.sendMessage(String.valueOf(item.getType()));

                    if(!item.getType().equals(Material.NETHERITE_PICKAXE)) {
                        // update item durability
                        Damageable damageable = (Damageable) item.getItemMeta();
//                        player.sendMessage("Max Durability: " + Integer.toString(item.getData().getItemType().getMaxDurability()));

                        if (damageable.getDamage() >= item.getData().getItemType().getMaxDurability()) {
                            player.getInventory().remove(item);
                        } else {

                            //check for enchants (unbreaking)
                            if (item.getItemMeta().hasEnchant(Enchantment.DURABILITY)) {

                                int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
                                //player.sendMessage(Integer.toString((random)));
                                if (random == 0) {
                                    damageable.setDamage(damageable.getDamage() + 1);
                                    item.setItemMeta(damageable);
                                }
                            } else {
                                damageable.setDamage(damageable.getDamage() + 1);
                                item.setItemMeta(damageable);
                            }

                        }
                    }
                }
            }
        }
    }
}
