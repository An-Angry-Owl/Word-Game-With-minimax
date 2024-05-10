public class MaxTheAI extends Player {

    private final char[] letters = {'C', 'S', 'E'}; // TODO: make this dynamic
    private final int size;

    public MaxTheAI(int size) {
        super(false);
        this.size = size;
    }

    private int[] bestMove(Board board){
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){

                if (board.isValidMove(i,j)){
                    for (char letter : letters) {
                        // Call minimax for every position and with every possible letter
                        int score = minimax(board, i, j, letter, 0, false);
                        System.out.println("Score: " + score + " for position " + i + ", " + (char) (j + 'A') + ", " + letter);

                        // if this call has the best, keep it
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = new int[]{i, j, letter};
                        }
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int col, int row, char letter, int depth, boolean isMaximizing){
        // Make a new clone of the board, to not affect the original
        Board newBoard = new Board(board);
        newBoard.add(col, row, letter);

        // Evaluate the position and give it a Heuristic Evaluation (Higher score if a win is achieved faster).
        if(newBoard.isWinner() && isMaximizing){
            return depth-9;
        }

        if(newBoard.isWinner() && !isMaximizing){
            return 9-depth;
        }

        if (newBoard.isDraw()){
            return 0;
        }


        int bestScore;
        if(isMaximizing){
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (newBoard.isValidMove(i, j)) {
                        for (char c : letters) {
                            int score = minimax(newBoard, i, j, c, depth + 1, false);
                            // if this call is best, keep it
                            bestScore = Math.max(score, bestScore);
                        }
                    }
                }
            }
        }else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (newBoard.isValidMove(i, j)) {
                        for (char c : letters) {
                            int score = minimax(newBoard, i, j, c, depth + 1, true);
                            // if this call is best, keep it
                            bestScore = Math.min(score, bestScore);
                        }
                    }
                }
            }
        }
        return bestScore;
    }

    public void doSomething(Board board){
        System.out.println("Mr. Max is playing!");
        int[] moveFromMax = bestMove(board);

        int col = moveFromMax[0];
        int row = moveFromMax[1];
        char letter = (char)(moveFromMax[2]);

        board.add(col, row, letter);
    }

    public String getName(){
        return "Mr. Max";
    }
}
