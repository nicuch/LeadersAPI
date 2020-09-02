package ro.nicuch.leaders.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class PlaceholderUtils {

    public static String setPlaceholders(OfflinePlayer player, String text, boolean translateColorCodes) {
        if (translateColorCodes)
            return PlaceholderAPI.setPlaceholders(player, PlaceholderAPI.setBracketPlaceholders(player, ChatColor.translateAlternateColorCodes('&', text)));
        return PlaceholderAPI.setPlaceholders(player, PlaceholderAPI.setBracketPlaceholders(player, text));
    }
}
