package com.saienko.model;



import javax.persistence.*;

/**
 * Created by gleb on 08.11.2015.
 */
@Entity
@Table(name = "user_roles", catalog = "coachdb",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role", "username"}))
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private int userRoleId;

    private User user;
    private String role;

    public UserRole(){}
    public UserRole (User user, String role){
        this.user = user;
        this.role = role;
    }




}
