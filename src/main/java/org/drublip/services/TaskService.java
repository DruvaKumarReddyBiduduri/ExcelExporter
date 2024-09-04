package org.drublip.services;

import org.drublip.models.Entity;
import org.drublip.models.Task;
import org.drublip.utils.MessagePrinter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A service class for managing task-related operations.
 * This class provides methods for retrieving task data from the database,
 * filling an Excel sheet with task data, and exporting the sheet to a file.
 */
public class TaskService {
    Connection connection;
    public final static String[] SHEET_HEADERS = {"Id", "Name", "Description", "Status", "Priority", "CreatedAt", "UpdatedAt", "UserId", "BoardId"};
    public final static int CELLS = 5;
    ExcelService excelService;
    MessagePrinter messagePrinter;

    public TaskService(ExcelService excelService) {
        this.excelService = excelService;
        this.messagePrinter = new MessagePrinter(TaskService.class,false);
        connection = DatabaseService.connect();
    }

    /**
     * Retrieves a list of tasks from the database.
     *
     * @return An ArrayList of Entity objects representing the tasks.
     */
    public ArrayList<Entity> getTasks() {
        ArrayList<Entity> tasks = new ArrayList<>();
        try {
            messagePrinter.info("Started Fetching Task data");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM task");
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                tasks.add(new Task(rows.getLong(1),
                        rows.getString(2),
                        rows.getString(3),
                        rows.getString(6),
                        rows.getString(7),
                        rows.getDate(4),
                        rows.getDate(5),
                        rows.getLong(8),
                        rows.getLong(9)));
            }
            messagePrinter.success("Successfully Fetched Task data");
        } catch (SQLException e) {
            messagePrinter.error("Failed to Fetch Task data , cause:" + e.getMessage());
        }
        return tasks;
    }

    /**
     * Exports task data to an Excel sheet.
     *
     * This function retrieves a list of tasks from the database,
     * fills an Excel sheet with the task data, and exports the sheet to a file.
     *
     * @return          None
     */
    public void toExcelSheet() {
        ArrayList<Entity> tasks = getTasks();
        messagePrinter.info("Filling the data into Sheet");
        excelService.toSheet("output.xlsx", "tasks", SHEET_HEADERS, tasks);
        messagePrinter.success("Successfully Filled the task data into sheet");
    }
}
