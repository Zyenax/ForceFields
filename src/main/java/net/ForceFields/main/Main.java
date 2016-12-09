package net.ForceFields.main;

import net.ForceFields.main.Handlers.CommandHandler;
import net.ForceFields.main.Handlers.ForceFieldHandler;
import net.ForceFields.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public void onEnable(){
		Listeners();
		registerCommands();
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &aPlugin Enabled"));
	}
	
	public void onDisable(){
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(Utils.color("&8&l[&e&lForceFields&8&l] &cPlugin Disabled"));
	}
	
	public void Listeners(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new ForceFieldHandler(this), this);
		pm.registerEvents(new CommandHandler(this), this);
		pm.registerEvents(new Utils(this), this);
	}
	
	public void registerCommands(){
		getCommand("forcefield").setExecutor(new CommandHandler(this));
	}
	
}
