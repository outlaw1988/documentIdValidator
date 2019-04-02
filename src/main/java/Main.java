import picocli.CommandLine;
import picocli.CommandLine.*;
import validator.PolishIdValidator;
import validator.Validator;

public class Main {

    @Option(names = { "-i", "--id" }, description = "ID number")
    private static String id;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help and exit")
    private static boolean help;

    public static void main(String[] args) {
        Main app = CommandLine.populateCommand(new Main(), args);
        if (help || args.length == 0) {
            CommandLine.usage(new Main(), System.out);
            return;
        }
        Validator idValidator = new PolishIdValidator();
        boolean isValid = idValidator.validate(id);
        if (isValid) System.out.println(String.format("Passed ID number %s is correct.", id));
        else System.out.println(String.format("Passed ID number %s is incorrect.", id));
    }

}
