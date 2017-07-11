package fr.loulouw.louwpet.inventory;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InventoryPet extends InventoryBase{

    public InventoryPet(Player owner){
        super(1,ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Menu Pet",owner);
    }

    @Override
    void generateInventory() {

    }


}
