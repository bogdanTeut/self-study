package enums.annotations;

/**
 * Created by bogdan.teut on 26/09/2014.
 */
public class ConstraintDTO {
    private boolean primaryKey;
    private boolean allowNull;
    private boolean unique;

    public ConstraintDTO(boolean primaryKey, boolean allowNull, boolean unique) {
        this.primaryKey = primaryKey;
        this.allowNull = allowNull;
        this.unique = unique;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
}
