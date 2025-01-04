package top.szzz666.Surveillance.entity;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrunQuery {
    private Long total;
    private List<SeData> data;
    public String toJson() {
        return new Gson().toJson(this);
    }
}
