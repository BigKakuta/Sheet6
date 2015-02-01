package edu.kit.informatik;

/**
 * @author Daniel Meyer
 * @version 1.0
 *
 * The type Program.
 */
public class Program {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            Terminal.printLine("Error, too many or no command line arguments.");
            System.exit(1);
        }
        ProgramExecuter executer = new ProgramExecuter(args[0]);
        executer.run();
    }
}
