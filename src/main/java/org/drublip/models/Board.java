package org.drublip.models;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Board implements Entity {
    String name;
    long id, userId;
    Date createdAt, updatedAt;

    public Board(long id, String name, Date createdAt, Date updatedAt, long userId) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    /**
     * Converts the Board object into a Map of key-value pairs.
     *
     * @return A Map containing the Board's properties.
     */
    @Override
    public Map<String, String> toMap() {
        Map<String, String> properties = new HashMap<>();
        properties.put("Id", String.valueOf(this.id));
        properties.put("Name", this.name);
        properties.put("CreatedAt", this.createdAt.toString());
        properties.put("UpdatedAt", this.updatedAt.toString());
        properties.put("UserId", String.valueOf(this.userId));
        return properties;
    }
}
