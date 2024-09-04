package org.drublip.services;

import org.drublip.utils.MessagePrinter;
import org.drublip.utils.MessageType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DatabaseService {
    public static Connection connection;

    /**
     * Initializes a database connection if it does not already exist.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void initialize() throws SQLException {
        if (connection == null) {
            MessagePrinter.log(MessageType.INFO, "Initializing Database Connection");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks", "root", ".Adgjmptw5");
            MessagePrinter.log(MessageType.SUCCESS, "Successfully Established a Connection to database");
        }
    }

    /**
     * Returns the current database connection.
     */
    public static Connection connect() {
        return connection;
    }
}
