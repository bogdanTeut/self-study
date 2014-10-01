package enums.annotations;
import java.util.*;
/**
 * Created by bogdan.teut on 26/09/2014.
 */
public class TableDTO {
    private String tableName;
    private List<FieldDTO> fields;

    public TableDTO(String tableName) {
        this.tableName = tableName;
    }

    public List<FieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<FieldDTO> fields) {
        this.fields = fields;
    }
}
