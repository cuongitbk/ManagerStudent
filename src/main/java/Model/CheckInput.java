package Model;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by hungm on 27/09/2016.
 */
public class CheckInput {

    public int Choice(int end)
    {
        Scanner input = new Scanner(System.in);
        boolean check_input = true;

        int choice = 0;
        do
        {
            if (choice < 0 || choice > end)
            {
                check_input = false;
            }
            try {
                choice = input.nextInt();
                check_input = false;
            }
            catch (InputMismatchException ex) {
                input.nextLine();
            }
        }while (check_input || (choice < 0 || choice > end));
        return choice;
    }

    public int CheckInputInt()
    {
        Scanner input = new Scanner(System.in);
        int n = 0;
        boolean check_input = true;
        do {
            try {
                n = input.nextInt();
                check_input = false;
                if (n < 0) {
                    check_input = true;
                }
            }
            catch (InputMismatchException ex) {
                input.nextLine();
            }
        }
        while (check_input);
        return  n;
    }

    public double CheckInputDouble()
    {
        Scanner input = new Scanner(System.in);
        double n = 0;
        boolean check_input = true;
        do {
            try {
                n = input.nextDouble();
                check_input = false;
                if (n < 0) {
                    check_input = true;
                }
            }
            catch (InputMismatchException ex) {
                input.nextLine();
            }
        }
        while (check_input);
        return  n;
    }
}
