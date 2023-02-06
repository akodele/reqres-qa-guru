package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PutUpdate {
    private String name;
    private String job;
    private String updatedAt;
}
