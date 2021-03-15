package jp.simplespace.simplevotesystem.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import jp.simplespace.simplevotesystem.Vote;
import jp.simplespace.simplevotesystem.SimpleVoteSystem;

public class vote implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーのみ実行できます。");
            return true;
        }
        //このクラスでPlayer型変数pにsenderをPlayerにキャストして代入。
        Player p = (Player) sender;

        switch(args.length) {
            case 1:
                if (args[0].equalsIgnoreCase("help")) { Vote.help(p); }
                else if (args[0].equals("1")) {
                    //Voteクラスのvoteメソッドを実行。
                    Vote.vote(p, 1);
                } else if (args[0].equals("2")) {
                    //Voteクラスのvoteメソッドを実行。
                    Vote.vote(p, 2);
                } else if (args[0].equalsIgnoreCase("stop")) {
                    if (p.hasPermission("SimpleVote.command.vote.stop")) {

                        Vote.voteStop(p);
                    } else {
                        p.sendMessage(SimpleVoteSystem.noPermission);
                    }
                } else if (args[0].equalsIgnoreCase("info")) {
                    //infoコマンド
                    Vote.voteInfo(p);
            } else {
                    p.sendMessage("無効な引数です。");
                }
                break;
            case 4:
                if (args[0].equalsIgnoreCase("create")) {
                    if (p.hasPermission("SimpleVote.command.vote.create")) {
                        // /vote create <タイトル> <回答1> <回答2>
                        if (args.length == 4) {
                            Vote.create(p, args[1], args[2], args[3]);

                        } else {
                            p.sendMessage("引数が足りないもしくは無効な引数です。");
                        }
                    } else {
                        p.sendMessage(SimpleVoteSystem.noPermission);
                    }
                } else {
                    p.sendMessage("無効な引数です。");
                }
                break;
            case 0:
            default:
                Vote.help(p);
                break;
        }

        return true;
    }
}
