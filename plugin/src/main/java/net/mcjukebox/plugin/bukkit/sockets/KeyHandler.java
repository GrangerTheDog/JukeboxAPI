package net.mcjukebox.plugin.bukkit.sockets;

import lombok.Getter;
import net.mcjukebox.plugin.bukkit.MCJukebox;
import net.mcjukebox.plugin.bukkit.utils.OldDataUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.File;

public class KeyHandler {

	private SocketHandler socketHandler;
	@Getter private CommandSender currentlyTryingKey;

	public KeyHandler(SocketHandler socketHandler) {
		this.socketHandler = socketHandler;
	}

	public void onKeyRejected(String reason) {
		CommandSender toNotify = currentlyTryingKey != null ? currentlyTryingKey : Bukkit.getConsoleSender();
		toNotify.sendMessage(ChatColor.RED + "API key rejected with message: " + reason);
		deleteKey();
		currentlyTryingKey = null;
	}

	public void tryKey(CommandSender sender, String key) {
		currentlyTryingKey = sender;
		OldDataUtils.saveObjectToPath(key, MCJukebox.getInstance().getDataFolder() + "/api.key");
		socketHandler.disconnect();
		socketHandler.attemptConnection();
	}

	private void deleteKey() {
		new File(MCJukebox.getInstance().getDataFolder() + "/api.key").delete();
	}

}
