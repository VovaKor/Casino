package softgroup.ua.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вова on 04.03.2017.
 */
@Entity
@Table(name = "roles", schema = "casino")
//@NamedQueries({
//        @NamedQuery(name = "RoleEntity.findAll", query = "SELECT r FROM RoleEntity r")})
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int roleId;
    private String roleName;
    private String description;
    private List<UserEntity> userList = new ArrayList<>();
    public RoleEntity(){}

    public RoleEntity(int roleId, String roleName, String description) {
        this.roleId=roleId;
        this.roleName=roleName;
        this.description=description;
    }

    @Id
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name", nullable = false, length = 20)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy = "rolesList", cascade = CascadeType.DETACH)
    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (roleId != that.roleId) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
