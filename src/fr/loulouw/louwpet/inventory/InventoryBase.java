package fr.loulouw.louwpet.inventory;

import fr.loulouw.louwpet.utils.menu.Menu;
import org.bukkit.entity.Player;

public abstract class InventoryBase {

    private Menu menu;

    InventoryBase(int numberOfRows, String name, Player owner){
        if(name == null && owner == null){
            menu = new Menu(numberOfRows);
        }else if(owner == null){
            menu = new Menu(numberOfRows,name);
        }else{
            menu = new Menu(numberOfRows,name,owner);
        }
        generateInventory();
    }

    abstract void generateInventory();

    public Menu getMenu() {
        return menu;
    }

    public void openInventory(Player owner){
        menu.openInventory(owner);
    }

}
