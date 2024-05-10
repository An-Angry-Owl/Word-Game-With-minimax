import java.util.Random;

public class Board {

    private final char[][] board;
    private int remainingMoves;
    private final int size;
    private final String word;

    public Board(String word) {
        // sets the board size
        this.size = word.length();
        this.word = word;
        board = new char[size][size];
        remainingMoves = size*size;
        initializeBoard();
    }

    public Board(Board board) {
        // Makes a new copy of the board
        this.size = board.size;
        this.remainingMoves = board.remainingMoves;
        this.word = board.word;
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board.board[i], 0, this.board[i], 0, size);
        }

    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
        add(1, new Random().nextInt(2)*2,'S'); // adds a 'S' in a random position
    }

    public void printBoard() {
        // prints the top " A B C" part
        StringBuilder top = new StringBuilder(" ");
        for (int i = 0; i < size; i++) {
            char letter = (char)('A' + i);
            top.append(" ").append(letter);
        }
        System.out.println(top);
        // prints the rest of the board
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row, int col) {
        //check if the move is within bounds and the poss is empty
        return (row >= 0) && (row < size) && (col >= 0) && (col < size) && (board[row][col] == ' ');
    }

    public boolean add(int row, int col, char letter) {
        if (isValidMove(row, col) && Character.isLetter(letter) && (word.indexOf(letter) != -1)) {
            board[row][col] = letter;
            remainingMoves -= 1;
            return true;
        }
        System.out.println("Invalid move or position unavailable!");
        return false;
    }

    public void remove(int row, int col) {
        board[row][col] = ' ';
        remainingMoves += 1;
    }

    // TODO: make it dynamic with any word
    public boolean isWinner() {
        // οριζόντια
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == 'C' && board[i][1] == 'S' && board[i][2] == 'E')  ||  ((board[i][0] == 'E' && board[i][1] == 'S' && board[i][2] == 'C'))) {
                return true;
            }
        }
        // κατακόρυφα
        for (int j = 0; j < 3; j++) {
            if ((board[0][j] == 'C' && board[1][j] == 'S' && board[2][j] == 'E'  ||  (board[0][j] == 'E' && board[1][j] == 'S' && board[2][j] == 'C'))) {
                return true;
            }
        }
        // διαγώνια (από πάνω αριστερά προς κάτω δεξιά)
        if ((board[0][0] == 'C' && board[1][1] == 'S' && board[2][2] == 'E')  ||  (board[0][0] == 'E' && board[1][1] == 'S' && board[2][2] == 'C')) {
            return true;
        }
        // διαγώνια (από πάνω δεξιά προς κάτω αριστερά)
        if ((board[0][2] == 'C' && board[1][1] == 'S' && board[2][0] == 'E')  ||  (board[0][2] == 'E' && board[1][1] == 'S' && board[2][0] == 'C')) {
            return true;
        }
        return false;
    }

    public boolean isDraw() {
        return remainingMoves == 0;
    }

}
