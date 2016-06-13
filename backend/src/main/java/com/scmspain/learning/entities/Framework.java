package com.scmspain.learning.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Framework {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User owner;
    private String name;
    private Long love;
    private Date changeData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLove() {
        return love;
    }

    public void setLove(Long love) {
        this.love = love;
    }

    public Date getChangeData() {
        return changeData;
    }

    public void setChangeData(Date changeData) {
        this.changeData = changeData;
    }
}
