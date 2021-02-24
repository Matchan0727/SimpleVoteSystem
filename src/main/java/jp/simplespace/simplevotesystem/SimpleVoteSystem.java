package jp.simplespace.simplevotesystem;

import jp.simplespace.simplevotesystem.Commands.vote;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class SimpleVoteSystem extends JavaPlugin {

    public static JavaPlugin plugin;
    public static String noPermission = ChatColor.RED + "あなたに実行する権限はありません。";

    public static HashMap<UUID, Integer> voted;
    public static HashMap<Integer, String> answer;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        voted = new HashMap<>();
        answer = new HashMap<>();

        getLogger().info("プラグインが読み込まれました。");
        getCommand("vote").setExecutor(new vote());

        super.onEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic


        super.onDisable();
    }
}
