package org.drublip;


import org.drublip.services.*;
import org.drublip.utils.MessagePrinter;
import org.drublip.utils.MessageType;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseService.initialize();

            ExcelService service = new ExcelService();

            UserService userService = new UserService(service);
            BoardService boardService = new BoardService(service);
            TaskService taskService = new TaskService(service);

            userService.toExcelSheet();
            boardService.toExcelSheet();
            taskService.toExcelSheet();

            service.writeToFile("output.xlsx");
            MessagePrinter.log(MessageType.SUCCESS, "successfully exported data into a excel file");
        } catch (SQLException e) {
            MessagePrinter.log(MessageType.ERROR, "Error establishing connection to database cause : " + e.getMessage());
        }
    }
}