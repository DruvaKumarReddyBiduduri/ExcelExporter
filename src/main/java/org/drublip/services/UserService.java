package org.drublip.services;

import org.drublip.models.Entity;
import org.drublip.models.User;
import org.drublip.utils.MessagePrinter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * A service class for managing user-related operations.
 * This class provides methods for retrieving user data from the database,
 * filling an Excel sheet with user data, and exporting the sheet to a file.
 */
public class UserService {
    Connection connection;
    public final static String[] SHEET_HEADERS = {"Id", "Name", "Email", "Password", "CreatedAt", "UpdatedAt"};
    ExcelService excelService;
    MessagePrinter messagePrinter;

    /**
     * Constructs a new UserService instance with the given ExcelService.
     *
     * @param excelService The ExcelService instance to use.
     */
    public UserService(ExcelService excelService) {
        this.excelService = excelService;
        this.messagePrinter = new MessagePrinter(UserService.class, false);
        connection = DatabaseService.getConnection();
    }

    /**
     * Retrieves a list of user entities from the database.
     */
    public ArrayList<Entity> getUsers() {
        ArrayList<Entity> users = new ArrayList<>();
        try {
            messagePrinter.info("Started Fetching User data");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                users.add(new User(rows.getLong(1),
                        rows.getString(2),
                        rows.getString(3),
                        rows.getString(4),
                        rows.getDate(5),
                        rows.getDate(6)));
            }
            messagePrinter.success("Successfully fetched user data");
        } catch (SQLException e) {
            messagePrinter.error("Failed to Fetch User data , cause:" + e.getMessage());
        }
        return users;
    }

    /**
     * Exports user data to an Excel sheet.
     * This function retrieves a list of users from the database,
     * fills an Excel sheet with the user data, and exports the sheet to a file.
     */
    public void toExcelSheet() {
        ArrayList<Entity> users = getUsers();
        messagePrinter.info("Filling the User Sheet");
        excelService.toSheet("users", SHEET_HEADERS, users);
        messagePrinter.success("Successfully filled the user data into sheet");
    }
}
