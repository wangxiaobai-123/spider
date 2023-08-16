package org.example.dao.entity;

import lombok.Data;

@Data
public class Disease {

    private int id;

    private int parentId;

    private String Name;

    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Disease(int id, String name, String type) {
        this.id = id;
        Name = name;
        this.type = type;
    }

    public Disease( String name,int parentId, String type) {
        this.parentId = parentId;
        Name = name;
        this.type = type;
    }
    public Disease( int id,int parentId, String name,String type) {
        this.parentId = parentId;
        Name = name;
        this.type = type;
    }


}
