package com.sashqua.tracker.entitys;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_roles")
    @SequenceGenerator(name="seq_roles", sequenceName="seq_roles", allocationSize=1)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> userList;

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
