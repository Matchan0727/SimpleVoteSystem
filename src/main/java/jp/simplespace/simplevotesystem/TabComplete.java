package jp.simplespace.simplevotesystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {
    List<String> Commands = new ArrayList<>();
    List<String> Completions = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        Commands.clear();
        Completions.clear();
        if (args.length == 1) {
            Commands.add("help");
            Commands.add("1");
            Commands.add("2");
            Commands.add("create");
            Commands.add("stop");
            Commands.add("info");
            StringUtil.copyPartialMatches(args[0], Commands, Completions);
        }
        Collections.sort(Completions);
        return Completions;
    }
}
