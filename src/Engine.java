import java.util.Scanner;

public class Engine {

    public static void main() {
        Engine engine = new Engine();
        engine.startGame();
    }

    private boolean checkIfOver(Board board, Player player){
        if(board.isWinner()){
            System.out.println("Player " + player.getName() + " won!");
            return true;
        } else if (board.isDraw()) {
            System.out.println("Its a draw");
            return true;
        }
        return false;

    }

    private void startGame(){
        System.out.print("Hello and welcome!\n");
        boolean isMaximizing = whoPlaysFirst();
        Player player = new Player();
        MaxTheAI max = new MaxTheAI(3);
        Board board = new Board("CSE");
        board.printBoard();

        if(isMaximizing){
            while(true){
                player.doSomething(board);
                board.printBoard();
                if(checkIfOver(board, player)) break;
                max.doSomething(board);
                board.printBoard();
                if(checkIfOver(board, max)) break;
            }
        }else {
            while(true){
                max.doSomething(board);
                board.printBoard();
                if(checkIfOver(board, player)) break;
                player.doSomething(board);
                board.printBoard();
                if(checkIfOver(board, max)) break;
            }
        }

    }

    private boolean whoPlaysFirst() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to play first? (yes or no): ");
        String choice = scanner.nextLine();
        while (true) {
            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                return true;
            } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Type \"yes\" or \"no\".");
            }
            choice = scanner.nextLine();
        }
    }

}
