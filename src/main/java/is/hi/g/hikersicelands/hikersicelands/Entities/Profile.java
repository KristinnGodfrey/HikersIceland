package is.hi.g.hikersicelands.hikersicelands.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String password;


    private String username;
    private String name;
    private int age;
    private boolean priv;

    // Tómur smiður
    public Profile(){

    }

    // Smiður
    public Profile(String username, String name, int age, boolean priv){
        this.username = username;
        this.name = name;
        this.age= age;
        this.priv = priv;
    }

    // Getters og setters
    public long getId(){return id;}

    public void setId(long id) {this.id = id;}

    public String getPassword(){return password;}

    public void setPassword(String password) {this.password = password;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public boolean getPriv() {return priv;}

    public void setPriv(boolean priv) {this.priv = priv;}
}
