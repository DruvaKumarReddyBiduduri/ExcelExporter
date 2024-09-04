package org.drublip.services;

import org.drublip.models.Board;
import org.drublip.models.Entity;
import org.drublip.utils.MessagePrinter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardService {
    Connection connection;
    public final static String[] SHEET_HEADERS = {"Id", "Name", "CreatedAt", "UpdatedAt", "UserId"};
    ExcelService excelService;
    MessagePrinter messagePrinter;

    public BoardService(ExcelService excelService) {
        this.excelService = excelService;
        this.messagePrinter = new MessagePrinter(BoardService.class, false);
        connection = DatabaseService.connect();
    }

    /**
     * Retrieves a list of board entities from the database.
     * @return ArrayList of board entities.
     */
    public ArrayList<Entity> getBoards() {
        ArrayList<Entity> boards = new ArrayList<>();
        try {
            messagePrinter.info("Started Fetching Board data");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM board");
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                boards.add(new Board(rows.getLong(1),
                        rows.getString(2),
                        rows.getDate(3),
                        rows.getDate(4),
                        rows.getLong(5)));
            }
            messagePrinter.success("Successfully Fetched Board data");
        } catch (SQLException e) {
            messagePrinter.error("Failed to Fetch Board data , cause:" + e.getMessage());
        }
        return boards;
    }

    /**
     * Exports the board data to an Excel sheet.
     * This function retrieves the board data from the database,
     * and then uses the ExcelService to fill the data into a sheet to an Excel file.
     */
    public void toExcelSheet() {
        ArrayList<Entity> boards = getBoards();
        messagePrinter.info("Filling the Board Sheet");
        excelService.toSheet("boards", SHEET_HEADERS, boards);
        messagePrinter.success("Successfully Filled the board data into sheet");
    }
}
