import java.util.Random;

public class Utils {

    private Utils(){}

    public static Random rand = new Random();
    public static int previous_rand = 1;


    public static int generate_rand(int limit){
        if (previous_rand == 0) return previous_rand = rand.nextInt(limit) + 1;
        if(limit == 1){ return 1;}
        final int rnd = rand.nextInt(limit - 1) + 1;
        return previous_rand = (rnd < previous_rand? rnd : rnd + 1);
    }

    public static BoardOperations.Move getmove(int currentagent, int move, int n){
        if(move == currentagent - 1){
            return   BoardOperations.Move.L;
        } else if(move == currentagent + 1){
            return BoardOperations.Move.R;
        } else if(move == currentagent - n){
            return BoardOperations.Move.U;
        } else if(move == currentagent + n){
            return BoardOperations.Move.D;
        } else {
            // LETS BREAK EVERYTHING
            return null;
        }
    }

    public static void printBoard(int[] board, int N) {
        int[] currentBoard = board;
        for(int i = 0; i < currentBoard.length; i++){
            if(i % N == 0 && i != 0){
                Logger.LogLine("");
            }
            Logger.Log("| " + currentBoard[i] + " ");
        }
        Logger.LogLine("");
    }
}
