package jp.simplespace.simplevotesystem;

// 適当な投票システムです。

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

import static jp.simplespace.simplevotesystem.SimpleVoteSystem.voted;
import static jp.simplespace.simplevotesystem.SimpleVoteSystem.answer;

public class Vote {

    private static boolean vstatus = false;

    private static String recruiter;
    private static String content;
    private static String a1;
    private static String a2;

    public static void vote(Player p, int ans) {

        UUID uuid = p.getUniqueId();

        //falseの場合は投票が
        if (vstatus == true) {

        if (voted.containsKey(uuid)) {
            //実行者のUUIDがvotedに含まれていた場合の処理
            p.sendMessage("あなたはすでに「" + answer.get(voted.get(uuid)) + "」に投票しています");

        } else {
            //実行者のUUIDがvotedに含まれていなかった場合の処理
            voted.put(uuid, ans);
            p.sendMessage("あなたは「" + answer.get(voted.get(uuid)) + "」に投票しました！");
        }
    } else {
            //投票が行われていない場合。
            p.sendMessage("現在投票は行われていません");
        }
}
    //投票の作成等の処理
    public static void create(Player p, String c, String a1, String a2) {
        //すでに投票が行われていた場合の処理
        if(vstatus == true) {
            p.sendMessage("すでに投票が行われています");
        } else {
            //投票が行われていない場合投票を作成する。
            recruiter = p.getPlayer().getName();
            content = c;
            Vote.a1 = a1;
            Vote.a2 = a2;
            answer.put(1, a1);
            answer.put(2, a2);

            Bukkit.broadcastMessage(ChatColor.GREEN + "[Vote] 下記の内容で投票が開始されました");
            voteInfo();
            Bukkit.broadcastMessage(ChatColor.GREEN + "「/vote <番号>」で投票してください");

            vstatus = true;
        }
    }

    public static void voteInfo() {
        Bukkit.broadcastMessage(ChatColor.YELLOW + "---------------------------------");
        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "タイトル： " + ChatColor.RESET + content);
        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "作成者： " + ChatColor.RESET + recruiter);
        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "投票内容： " + ChatColor.GOLD + "1: " + ChatColor.RESET + Vote.a1 + ChatColor.GOLD + ", " + "2: " + ChatColor.RESET + Vote.a2);
        Bukkit.broadcastMessage(ChatColor.YELLOW + "---------------------------------");
    }
    public static void voteInfo(Player p) {
        p.sendMessage(ChatColor.YELLOW + "---------------------------------");
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "タイトル： " + ChatColor.RESET + content);
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "作成者： " + ChatColor.RESET + recruiter);
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "投票内容： " + ChatColor.GOLD + "1: " + ChatColor.RESET + Vote.a1 + ChatColor.GOLD + ", " + "2: " + ChatColor.RESET + Vote.a2);
        p.sendMessage(ChatColor.YELLOW + "---------------------------------");
    }
    public static void voteStop(Player p) {
        if (vstatus == true) {

            vstatus = false;
            int v1 = 0;
            int v2 = 0;

            for (UUID key : voted.keySet()) {
                if (voted.get(key) == 1) {
                    v1 += 1;
                } else if (voted.get(key) == 2) {
                    v2 += 1;
                }
            }

            Bukkit.broadcastMessage(ChatColor.GREEN + "[Vote] 以下の投票が終了しました。");
            voteInfo();
            Bukkit.broadcastMessage(ChatColor.YELLOW + "----------------- " + ChatColor.GOLD + "" + ChatColor.BOLD + "投票結果" + ChatColor.YELLOW + " -----------------");
            Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + a1 + "： " + ChatColor.GOLD + v1 + ChatColor.AQUA + "票");
            Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + a2 + "： " + ChatColor.GOLD + v2 + ChatColor.AQUA + "票");
            Bukkit.broadcastMessage(ChatColor.YELLOW + "--------------------------------------------------");

            voted.clear();
            answer.clear();

        } else {
            p.sendMessage("現在投票は行われていません");
        }
    }
        public static void help(Player p) {

            p.sendMessage(ChatColor.YELLOW + "----------------- " + ChatColor.GOLD + "" + ChatColor.BOLD + "コマンド一覧" + ChatColor.YELLOW + " -----------------");
            p.sendMessage(ChatColor.AQUA + "/vote help " + ChatColor.GRAY + "- ヘルプを表示します。");
            p.sendMessage(ChatColor.AQUA + "/vote <番号> " + ChatColor.GRAY + "- 指定した番号で投票します。");
            p.sendMessage(ChatColor.AQUA + "/vote create <タイトル> <回答1> <回答2> " + ChatColor.GRAY + "- 指定した引数で投票を作成します。");
            p.sendMessage(ChatColor.AQUA + "/vote info " + ChatColor.GRAY + "- 現在開始されている投票の情報を表示します。");
            p.sendMessage(ChatColor.AQUA + "/vote stop " + ChatColor.GRAY + "- 現在開始されている投票を停止して結果を表示します。");
            p.sendMessage(ChatColor.YELLOW + "--------------------------------------------------");

        }

}
