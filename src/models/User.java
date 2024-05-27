package models;
import java.io.Serializable;


public class User implements Serializable {
    private int id;
    private final String name;
    private final String password;
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '\'' +
                '}';
    }

}
