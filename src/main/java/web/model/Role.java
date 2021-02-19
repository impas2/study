package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_role;
    String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    protected Set<User> users = new HashSet<>();

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

    @Override
    public String toString() {
        return this.roleName;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((id_role == null) ? 0 : id_role.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Role other = (Role) obj;
//        if (id_role == null) {
//            if (other.id_role != null)
//                return false;
//        } else if (!id_role.equals(other.id_role))
//            return false;
//        return true;
//    }
}
