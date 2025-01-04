package top.szzz666.Surveillance.entity;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Params {
    private Integer current;
    private Integer pageSize;
    private Integer id;
    private String time;
    private String player_name;
    private String player_uuid;
    private String player_pos;
    private String event_pos;
    private String event_block;
    private String event_item;
    private String event_other;
    private String event_type;

    public Params(String json) {
        Gson gson = new Gson();
        Params temp = gson.fromJson(json, Params.class);
        this.current = temp.current;
        this.pageSize = temp.pageSize;
        this.id = temp.id;
        this.time = temp.time;
        this.player_name = temp.player_name;
        this.player_uuid = temp.player_uuid;
        this.player_pos = temp.player_pos;
        this.event_pos = temp.event_pos;
        this.event_block = temp.event_block;
        this.event_item = temp.event_item;
        this.event_other = temp.event_other;
        this.event_type = temp.event_type;
    }
    public String toJson() {
        return new Gson().toJson(this);
    }
}
