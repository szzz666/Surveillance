package top.szzz666.Surveillance.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.handler.FormResponseHandler;
import cn.nukkit.form.window.FormWindowSimple;

import static top.szzz666.Surveillance.config.SeConfig.loadConfig;


public class SeForm {
    public static void mainForm(Player player) {
        FormWindowSimple form = new FormWindowSimple("mainForm_title", "mainForm_content");
        form.addButton(new ElementButton("mainForm_button1"));
        form.addButton(new ElementButton("mainForm_button2"));
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;
            int buttonIndex = form.getResponse().getClickedButtonId();
            if (buttonIndex == 0) {
                player.sendMessage("mainForm_sendMessage");
            } else {
                loadConfig();
                player.sendMessage("mainForm_sendMessage");
            }
        }));
        player.showFormWindow(form);
    }


}
