package domain.Model;

/**
 *
 * @author Nik
 */
public class Client {

    private String group;
    private String name;
    private String email;

    public Client() {
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
        this.group = null;
    }

    public Client(String name, String email, String group) {
        this.group = group;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return name + " " + email + " " + group + "\n";
    }

}
