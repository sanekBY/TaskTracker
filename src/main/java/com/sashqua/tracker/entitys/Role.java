package com.sashqua.tracker.entitys;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    public static final int DEVELOPER = 2;
    public static final int MANAGER = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_roles")
    @SequenceGenerator(name="seq_roles", sequenceName="seq_roles", allocationSize=1)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> userList;

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
