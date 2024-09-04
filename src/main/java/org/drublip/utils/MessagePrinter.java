package org.drublip.utils;

import com.diogonunes.jcolor.Attribute;

import java.time.LocalDateTime;

import static com.diogonunes.jcolor.Ansi.colorize;

public class MessagePrinter {
    Class<?> source;
    boolean richColors = false;

    public MessagePrinter(Class<?> source, boolean richColors) {
        this.source = source;
        this.richColors = richColors;
    }

    /**
     * Prints an informational message to the console, including the current date and time,
     * the name of the source class that generated the message, and the provided message.
     *
     * @param message the message to be printed
     */
    public void info(String message) {
        String text = String.format("[%s] - [%s] - [INFO]    : %s", LocalDateTime.now(), source.getName(), message);
        System.out.println(text);
    }

    /**
     * Prints a success message to the console, including the current date and time,
     * the name of the source class, that generated the message. If rich colors are enabled,
     * the message is printed in green.
     *
     * @param message    the success message to be printed
     */
    public void success(String message) {
        String text = String.format("[%s] - [%s] - [SUCCESS] : %s", LocalDateTime.now(), source.getName(), message);
        if (richColors) {
            System.out.println(colorize(text, Attribute.GREEN_TEXT()));
        }
        System.out.println(text);
    }

    /**
     * Prints an error message to the console, including the current date and time,
     * the name of the source class that generated the message.
     * If rich colors are enabled, the message is printed in green.
     *
     * @param message    the error message to be printed
     */
    public void error(String message) {
        String text = String.format("[%s] - [%s] - [ERROR]   : %s", LocalDateTime.now(), source.getName(), message);
        if (richColors) {
            System.out.println(colorize(text, Attribute.GREEN_TEXT()));
        }
        System.out.println(text);
    }

    /**
     * Logs a message to the console with a specified type.
     *
     * @param type     the type of message (SUCCESS, ERROR, or INFO)
     * @param message  the message to be logged
     */
    public static void log(MessageType type, String message) {
        String text = String.format("[%s] - [%s] : %s", LocalDateTime.now(), type, message);
        if (type == MessageType.ERROR) {
            System.out.println(colorize(text, Attribute.RED_TEXT()));
        } else if (type == MessageType.SUCCESS) {
            System.out.println(colorize(text, Attribute.GREEN_TEXT()));
        } else {
            System.out.println(text);
        }
    }
}
