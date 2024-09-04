package org.drublip.models;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class User implements Entity {
    long id;
    String name,email,password;
    Date createdAt,updatedAt;

    public User(long id, String name, String email, String password, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Converts the User object into a Map of key-value pairs.
     *
     * @return A Map containing the User's properties.
     */
    @Override
    public Map<String,String> toMap(){
        Map<String,String> properties=new HashMap<>();
        properties.put("Id", String.valueOf(this.id));
        properties.put("Name",this.name);
        properties.put("Email",this.email);
        properties.put("Password",this.password);
        properties.put("CreatedAt",this.createdAt.toString());
        properties.put("UpdatedAt",this.updatedAt.toString());
        return properties;
    }
}
