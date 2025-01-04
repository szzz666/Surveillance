package top.szzz666.Surveillance.tools;


import top.szzz666.StarrySkyAuth.apis.SAuth;
import top.szzz666.Surveillance.entity.Params;


import static top.szzz666.Assistant.config.AssistantConfig.isAssistant;
import static top.szzz666.Surveillance.SurveillanceMain.db1;
import static top.szzz666.Surveillance.SurveillanceMain.hs;
import static top.szzz666.Surveillance.config.SeConfig.*;
import static top.szzz666.Surveillance.tools.pluginUtil.*;

public class HttpRouter {
    public HttpRouter() {
        hs.post("/login", (req, res) -> {
            String body = req.body();
            String username = JsonUtil.getStringValue(body, "username");
            String password = JsonUtil.getStringValue(body, "password");
            if (allowAssistant) {
                if (!isAssistant(username)) {
                    return "{\"code\":400, \"msg\":\"你没有登录权限\"}";
                }
            } else if (!allowUsrPwd.containsKey(username)) {
                return "{\"code\":400, \"msg\":\"你没有登录权限\"}";
            }
            if (useStarrySkyAuth) {
                if (!new SAuth().isAuthSuccess(username, password)) {
                    return "{\"code\":400, \"msg\":\"登录失败\"}";
                }
            } else if (!allowUsrPwd.get(username).equals(password)) {
                return "{\"code\":400, \"msg\":\"登录失败\"}";
            }
            return "{\"code\":200, \"msg\":\"登录成功\"}";
        });
        hs.post("/data", (req, res) -> {
            Params params = new Params(req.body());
            if (!isSAuthed(req.headers("Authorization"))) {
                return "{\"code\":401, \"msg\":\"请先登录\"}";
            }
            return db1.dbQuery(params);
        });
    }
}


