package fr.loulouw.louwpet.command;

import fr.loulouw.louwpet.Main;
import fr.loulouw.louwpet.inventory.InventoryBase;
import fr.loulouw.louwpet.inventory.InventoryPet;
import net.minecraft.server.v1_12_R1.AttributeInstance;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import net.minecraft.server.v1_12_R1.PathEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandPet implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            /*InventoryBase inv = new InventoryPet(player);
            inv.openInventory(player);*/
            MushroomCow z = (MushroomCow) player.getWorld().spawnEntity(player.getLocation(), EntityType.MUSHROOM_COW);
            z.setBaby();
            z.setInvulnerable(true);
            z.setAgeLock(true);
            entityFollow(z,player);
        }
        return true;
    }

    public void entityFollow(final Entity entity, final Player player) {
        new BukkitRunnable() {
            final double SPEED = 0.21;
            final double DISTANCE = 13;

            public void run() {
                if ((!entity.isValid())) {
                    this.cancel();
                }

                net.minecraft.server.v1_12_R1.Entity entity2 = ((CraftEntity) entity).getHandle();
                ((EntityInsentient) entity2).getNavigation().a(2);

                Object entityObject = ((CraftEntity) entity).getHandle();

                Location targetLocation = player.getLocation();

                if(entity.getLocation().distance(player.getLocation()) > DISTANCE){
                    entity.teleport(player.getLocation());
                }

                PathEntity path = ((EntityInsentient) entityObject).getNavigation().a(targetLocation.getX() + 1, targetLocation.getY(), targetLocation.getZ() + 1);
                if (path != null) {
                    ((EntityInsentient) entityObject).getNavigation().a(path, 1.0D);
                    ((EntityInsentient) entityObject).getNavigation().a(2.0D);
                }

                AttributeInstance attributes = ((EntityInsentient) ((CraftEntity) entity).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
                attributes.setValue(SPEED);

                AttributeInstance attributes2 = ((EntityInsentient)((CraftEntity)entity).getHandle()).getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
                attributes2.setValue(DISTANCE);
            }

        }.runTaskTimer(Main.javaPlugin, 0L, 12L);
    }
}
