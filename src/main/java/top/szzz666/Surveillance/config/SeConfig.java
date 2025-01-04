package top.szzz666.Surveillance.config;

import cn.nukkit.utils.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static top.szzz666.Surveillance.SurveillanceMain.ConfigPath;
import static top.szzz666.Surveillance.SurveillanceMain.plugin;

public class SeConfig {
    public static int HttpServerPort = 234000;
    public static int maxPageSize = 100;
    public static boolean useStarrySkyAuth = true;
    public static boolean allowAssistant = false;

    public static Map<String, String> allowUsrPwd = new HashMap<>();


    public static boolean loadConfig() {
        plugin.saveResource("config.yml");
        Config config = new Config(ConfigPath + "/config.yml", Config.YAML);
        HttpServerPort = config.getInt("HttpServerPort", 234000);
        maxPageSize = config.getInt("maxPageSize", 100);
        useStarrySkyAuth = config.getBoolean("useStarrySkyAuth", true);
        allowAssistant = config.getBoolean("allowAssistant", false);
        allowUsrPwd = (HashMap<String, String>) config.get("allowUsrPwd");
        if (allowUsrPwd == null) {
            allowUsrPwd = new HashMap<>();
        }
        saveConfig();
        return true;
    }

    public static boolean saveConfig() {
        Config config = new Config(ConfigPath + "/config.yml", Config.YAML);
        config.set("HttpServerPort", HttpServerPort);
        config.set("pageSize", maxPageSize);
        config.set("useStarrySkyAuth", useStarrySkyAuth);
        config.set("allowAssistant", allowAssistant);
        config.set("allowUsrPwd", allowUsrPwd);
        config.save();
        return true;
    }

}
