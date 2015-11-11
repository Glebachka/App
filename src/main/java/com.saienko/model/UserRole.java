package com.saienko.model;


import javax.persistence.*;

/**
 * Created by gleb on 08.11.2015.
 */
@Entity
@Table(name = "userrole", catalog = "coachdb")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int userRoleId;

    @Column(name = "ROLE", length = 15, unique = true, nullable = false)
    private String role = UserRoleType.USER.getUserRoleType();

    public int getUserRoleId() {

        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleId=" + userRoleId +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;

        UserRole userRole = (UserRole) o;

        if (getUserRoleId() != userRole.getUserRoleId()) return false;
        return getRole().equals(userRole.getRole());

    }

    @Override
    public int hashCode() {
        int result = getUserRoleId();
        result = 31 * result + getRole().hashCode();
        return result;
    }


}
