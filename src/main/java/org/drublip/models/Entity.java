package org.drublip.models;

import java.util.Map;

public interface Entity {
    /**
     * Converts the entity to a map representation.
     * Utility Function to convert Entity to Map containing Object properties
     */
    Map<String, String> toMap();
}
