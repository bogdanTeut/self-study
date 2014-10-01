package enums.annotations;

/**
 * Created by bogdan.teut on 26/09/2014.
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30)
    String firstName;
    
    @SQLString(50)
    String lastName;
    
    @SQLInteger
    Integer age;
    
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String handle;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }
}
