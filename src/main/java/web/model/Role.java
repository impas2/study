package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_role;
    String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    protected Set<User> users;

    public Role() {

    }

    public Long getId_role() {
        return id_role;
    }

    public void setId_role(Long roleID) {
        this.id_role = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
