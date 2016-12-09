package net.ForceFields.main.Handlers;

import java.util.HashMap;

import net.ForceFields.main.Main;
import net.ForceFields.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandHandler implements Listener, CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main plugin;

	public CommandHandler(Main hub) {
		this.plugin = hub;
	}

	public static HashMap<Player, Player> ForceField = new HashMap<Player, Player>();
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
		if(command.getName().equalsIgnoreCase("forcefield")){
			if(args.length <= 0){
			if(sender.hasPermission("forcefield.self") || sender.isOp()){
				if(ForceField.containsKey(player)){
					ForceField.remove(player);
					sender.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &cYou no longer have a forcefield."));
				}else{
					ForceField.put(player, player);
					sender.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &aYou now have a forcefield."));
				}
			}else{
				sender.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &cYou dont have the permission &bforcefield.self"));
			}
			}
			if(args.length == 1){
				if(sender.hasPermission("forcefield.others") || sender.isOp()){
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null){
					sender.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &cYou must supply an online players name."));
				}else{
					if(ForceField.containsKey(target)){
						ForceField.remove(target);
						target.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &cYou no longer have a forcefield."));
						sender.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &b" + target.getName() + " &cno longer has a forcefield."));
					}else if(!ForceField.containsKey(target)){
						ForceField.put(target, target);
						target.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &aYou now have a forcefield."));
						sender.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &b" + target.getName() + " &anow has a forcefield."));
					}
				}
				}else{
					sender.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &cYou dont have the permission &bforcefield.others"));
				}
			}else if(args.length >= 2){
				sender.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &c/ForceField [target]"));
			}
		}
		}
		return true;
	}
	
	
	/*
	 * Type /forcefield to give yourself a forcefield or type /forcefield [target] to give another player a forcefield. The permission for giving yourself a forcefield with the /forcefield command is "forcefield.self" and the permission for giving other players forcefields with /forcefield [target] is "forcefield.others" forcefields make any players that dont have an active forcefield on get thrown back and away from the user with a forcefield if two users have forcefields and they get close to eachother they will not be thrown back and this is for collaboration purposes such as two youtubers using forcefields to keep players away.
	 */

}
