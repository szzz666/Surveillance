package top.szzz666.Surveillance.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;


public class SeCommand extends Command {
    public SeCommand() {
        super("CommandName", "命令描述");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {

        }
        return false;
    }

}
