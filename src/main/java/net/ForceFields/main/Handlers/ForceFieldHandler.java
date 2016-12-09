package net.ForceFields.main.Handlers;

import java.util.List;

import net.ForceFields.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ForceFieldHandler implements Listener{
	
	private Main plugin;

	public ForceFieldHandler(Main hub) {
		this.plugin = hub;
		forcefield();
	}
	
	
	
	
	public void forcefield(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
    		public void run() {
		List<Entity> nearbyPlayers;
		for(Player player : Bukkit.getOnlinePlayers()){
	    nearbyPlayers = player.getNearbyEntities(3, 3, 3);
	      	for (Entity entity : nearbyPlayers) {
	      		if(entity instanceof Player){
	      			if (CommandHandler.ForceField.containsKey(player) && CommandHandler.ForceField.containsKey(entity)){
	      				return;
	    		    }else if (CommandHandler.ForceField.containsKey(player)){
	    		    	entity.setVelocity(entity.getLocation().getDirection().multiply(-1.5).setY(1));
	    		    }
	      		}
	      	}
		}
    		}
		}, 0, 5);
    }

}
