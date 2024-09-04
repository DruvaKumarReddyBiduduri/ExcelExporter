package org.drublip.models;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Task implements Entity {
    long id, boardId, userId;
    String title, description, status, priority;
    Date createdAt, updatedAt;

    public Task(long id, String title, String description, String status, String priority, Date createdAt, Date updatedAt, long boardId, long userId) {
        this.id = id;
        this.boardId = boardId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.priority = priority;
    }

    /**
     * Converts the Task object into a Map of key-value pairs.
     *
     * @return A Map containing the Task's properties.
     */
    @Override
    public Map<String, String> toMap() {
        Map<String, String> properties = new HashMap<>();
        properties.put("Id", String.valueOf(this.id));
        properties.put("Name", this.title);
        properties.put("Description", this.description);
        properties.put("Status", this.status);
        properties.put("Priority", this.priority);
        properties.put("CreatedAt", this.createdAt.toString());
        properties.put("UpdatedAt", this.updatedAt.toString());
        properties.put("BoardId", String.valueOf(this.boardId));
        properties.put("UserId", String.valueOf(this.userId));
        return properties;
    }
}
