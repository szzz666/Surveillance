package top.szzz666.Surveillance.entity;

import cn.nukkit.Player;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.szzz666.Surveillance.tools.pluginUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DatabaseTable(tableName = "se_data")
public class SeData {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "time")
    private String time;
    @DatabaseField(columnName = "player_name")
    private String playerName;
    @DatabaseField(columnName = "player_uuid")
    private String playerUUID;
    @DatabaseField(columnName = "player_pos")
    private String playerPos;
    @DatabaseField(columnName = "event_type")
    private String eventType;
    @DatabaseField(columnName = "event_block")
    private String eventBlock;
    @DatabaseField(columnName = "event_item")
    private String eventItem;
    @DatabaseField(columnName = "event_other")
    private String eventOther;
    @DatabaseField(columnName = "event_pos")
    private String eventPos;

    public void setPlayerData(Player player){
        this.time = pluginUtil.getTimeStr();
        this.playerName = player.getName();
        this.playerUUID = player.getUniqueId().toString();
        this.playerPos = player.getPosition().toString();
    }

}
