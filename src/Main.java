
public class Main {

    public static void main(String[] args) {
        new Main();
    }


    public Main(){
        Logger.Log(Logger.Level.INFO, "initialising starting board...");
        int[] startState = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, -1};



        //int[] completecase = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 3, -1};
        //Utils.printBoard(completecase);


        //AStarHeuristic ash = new  AStarHeuristic();
        //Logger.Log(Logger.Level.INFO, String.valueOf(ash.gn(completecase)));

        BFS_exp bfse = new BFS_exp();
        bfse.BFS(startState);
        bfse.printComplete();

       //IterativeDeepening ids = new IterativeDeepening();
       //ids.IterativeDeepening(startState);

        //DFS dfs = new DFS();
        //dfs.DFS_iterative(startState);

        //dfs.printComplete();
        //dfs.printMoves();
    }

}
