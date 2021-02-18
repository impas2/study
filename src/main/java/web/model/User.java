package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "usertable")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String username;
    private Integer age;
    private String mailAddress;
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "userAndRole",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    protected Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", name='" + username + '\'' +
                ", age=" + age +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }

    public User() {

    }

    public User(String username, Integer age, String mailAddress) {
        this.username = username;
        this.age = age;
        this.mailAddress = mailAddress;
    }

    public User(Long id_user, String username, Integer age, String mailAddress) {
        this.id_user = id_user;
        this.username = username;
        this.age = age;
        this.mailAddress = mailAddress;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public void setUsername(String name) {
        this.username = name;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> listAuthority = new ArrayList<>();
        for(Role role : this.roles) {
            listAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return listAuthority;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
