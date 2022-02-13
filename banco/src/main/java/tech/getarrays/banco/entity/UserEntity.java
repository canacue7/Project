package tech.getarrays.banco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "USERS")
public class UserEntity {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(length=120)
    private String userName;
    private String password;
    private String jwt;
    private String firstName;
    private String lastName;

    public UserEntity() {
    }

    public UserEntity(String userName, String password, String jwt, String firtName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.jwt = jwt;
        this.firstName = firtName;
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
