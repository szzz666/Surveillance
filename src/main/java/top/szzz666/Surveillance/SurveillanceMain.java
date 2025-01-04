package top.szzz666.Surveillance;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import top.szzz666.Surveillance.command.SeCommand;
import top.szzz666.Surveillance.db.SQLiteDB;
import top.szzz666.Surveillance.event.Listeners;
import top.szzz666.Surveillance.tools.HttpRouter;
import top.szzz666.WebAPI.web.HttpServer;

import static top.szzz666.Surveillance.config.SeConfig.loadConfig;
import static top.szzz666.Surveillance.tools.pluginUtil.nkConsole;
import static top.szzz666.WebAPI.tools.FileUtil.saveResourceFolder;


public class SurveillanceMain extends PluginBase {
    public static Plugin plugin;
    public static Server nkServer;
    public static CommandSender consoleObjects;
    public static String ConfigPath;
    public static SQLiteDB db1;
    public static HttpServer hs;

    @Override
    public void onLoad() {
        //插件读取
        nkServer = getServer();
        plugin = this;
        consoleObjects = getServer().getConsoleSender();
        ConfigPath = getDataFolder().getPath();
        loadConfig();
        // 保存web资源文件
        saveResourceFolder("/public", ConfigPath, this.getClass());
        db1 = new SQLiteDB();
        nkConsole("&bSurveillance插件读取...");
    }

    @Override
    public void onEnable() {
        //注册监听器
        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
        //注册命令
        this.getServer().getCommandMap().register(this.getName(), new SeCommand());
        hs = new HttpServer(plugin);
        new HttpRouter();
        nkConsole("&bSurveillance插件开启");
        nkConsole("&c如果遇到任何bug，请加入Q群进行反馈：894279534", 1);

    }

    @Override
    public void onDisable() {
        try {
            db1.conn.close();
        } catch (Exception e) {
            nkConsole(e.getMessage(), 2);
        }
        //插件关闭
        nkConsole("&bSurveillance插件关闭");
    }

}
