package top.szzz666.Surveillance.tools;

import cn.nukkit.utils.TextFormat;
import top.szzz666.StarrySkyAuth.apis.SAuth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import static top.szzz666.Surveillance.SurveillanceMain.plugin;

public class pluginUtil {
    public static boolean isEmptyString(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isSAuthed(String header) {
        if (header == null) {
            return false;
        }
        String token = Base64Encode(header.replace("Basic ", ""));
        String username = token.split(":")[0];
        String password = token.split(":")[1];
        return new SAuth().isAuthSuccess(username, password);
    }

    public static String Base64Encode(String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        return new String(decodedBytes);
    }

    //获取当前时间
    public static String getTimeStr() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
        return currentDateTime.format(formatter);
    }

    //将输入的字符串按行打印到控制台。
    public static void lineConsole(String s) {
        String[] lines = s.split("\n");
        for (String line : lines) {
            nkConsole(line);
        }
    }

    //使用nk插件的控制台输出
    public static void nkConsole(String msg) {
        plugin.getLogger().info(TextFormat.colorize('&', msg));
    }

    public static void nkConsole(String msg, int typeNum) {
        if (typeNum == 1) {
            plugin.getLogger().warning(TextFormat.colorize('&', msg));
        } else if (typeNum == 2) {
            plugin.getLogger().error(TextFormat.colorize('&', msg));
        } else {
            plugin.getLogger().info(TextFormat.colorize('&', msg));
        }
    }
}
