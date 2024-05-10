import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

    private String name;

    public Player() {
        this(true);
    }

    public Player(boolean isNameNeeded) {
        if(isNameNeeded){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type your name: ");
            name = scanner.next();
            scanner.nextLine();
        }
    }

    public void doSomething(Board board){
        System.out.println("Player " + name + " is playing!");
        int row = 0, col = 0;
        char letter = '-';

        do{
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter row (0-2): ");
                row = scanner.nextInt();

                System.out.print("Enter column (A-C): ");
                col = scanner.next().toLowerCase().charAt(0) - 'a';

                System.out.print("Enter the letter (C or E or S): ");
                letter = scanner.next().toUpperCase().charAt(0);

                scanner.nextLine();
            } catch (InputMismatchException _) {
                //System.out.println("Invalid input!");
            }


        }while (!board.add(row, col, letter));

    }

    public String getName() {
        return name;
    }
}
