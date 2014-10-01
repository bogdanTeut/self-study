package enums.annotations;
import java.util.*;

/**
 * Created by bogdan.teut on 26/09/2014.
 */
public class FieldDTO {
    private ConstraintDTO constraints;
    private String name;
    private String type;

    public FieldDTO(String name, String type) {
        this.name = name;
        this.type = type;        
    }

    public ConstraintDTO getConstraints() {
        return constraints;
    }

    public void setConstraints(ConstraintDTO constraints) {
        this.constraints = constraints;
    }
}
