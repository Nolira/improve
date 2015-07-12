package ru.improve.testtask.entities;

/**
 * Created by Nolira on 06.07.2015.
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cat", schema = "public")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String  name;

    public Category(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
