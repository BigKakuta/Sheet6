package edu.kit.informatik;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Daniel Meyer
 * @version 1.0
 *
 * The type Program executer.
 */
public class ProgramExecuter {

    private boolean isRunning = true;
    private BookMap map = new BookMap();
    private String bookPath;

    /**
     * Instantiates a new Program executer.
     *
     * @param bookPath the book path
     */
    public ProgramExecuter(String bookPath) {
        this.bookPath = bookPath;
        readBookIn();
    }

    /**
     * Run void.
     */
    public void run() {
        while (isRunning) {
            String command = Terminal.readLine();
            if (command.equals("quit")) {
                isRunning = false;
            } else if (command.matches("search " + "\\w+")) {
                Terminal.printLine(map.getValueAt(command.split(" ")[1]));
            } else if (command.equals("info")) {
                Terminal.printLine(map.info());
            } else {
                Terminal.printLine("Error, invalid user input");
            }

        }
    }

    /**
     * Read book in.
     */
    public void readBookIn() {
        FileReader in = null;
        try {
            in = new FileReader(bookPath);
        } catch (FileNotFoundException e) {
            Terminal.printLine("Error, file not found");
            System.exit(1);
        }
        BufferedReader reader = new BufferedReader(in);
        try {
            String line = reader.readLine();
            while (line != null) {
                map.put(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            Terminal.printLine("Error, failed or interrupted I/O operations");
            System.exit(1);
        }
    }
}
