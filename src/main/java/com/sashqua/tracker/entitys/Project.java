package com.sashqua.tracker.entitys;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "comments")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_projects")
    @SequenceGenerator(name="seq_projects", sequenceName="seq_projects", allocationSize=1)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> taskList;
    @JoinTable(name = "user_project", joinColumns = {
            @JoinColumn(name = "project_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

    public Project(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
