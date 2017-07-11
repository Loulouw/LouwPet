package fr.loulouw.louwpet;


import fr.loulouw.louwpet.command.CommandPet;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static JavaPlugin javaPlugin;

    private FileConfiguration config = getConfig();
    private String[] pets = new String[]{"",""};


    @Override
    public void onEnable(){
        javaPlugin = this;
        ArrayList<String> petList = new ArrayList<>();

        addCommand();
    }

    @Override
    public void onDisable(){

    }

    private void addCommand(){
        this.getCommand("pet").setExecutor(new CommandPet());
    }


}
