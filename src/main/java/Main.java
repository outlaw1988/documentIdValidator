import validator.PolishIdValidator;
import validator.Validator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the id number:");
        String idNumber = sc.next();
        Validator validator = new PolishIdValidator();
        boolean isValid = validator.validate(idNumber);

        if (isValid) {
            System.out.println("You're id number is correct");
        } else {
            System.out.println("You're id number is incorrect");
        }
    }

}
