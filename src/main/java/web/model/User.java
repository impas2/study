package web.model;

import javax.persistence.*;

@Entity
@Table(name = "usertable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String name;
    private Integer age;
    private String mailAddress;

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }

    public User() {

    }

    public User(String name, Integer age, String mailAddress) {
        this.name = name;
        this.age = age;
        this.mailAddress = mailAddress;
    }

    public User(Long id_user, String name, Integer age, String mailAddress) {
        this.id_user = id_user;
        this.name = name;
        this.age = age;
        this.mailAddress = mailAddress;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
