import jdk.internal.util.xml.impl.Input;

public class Main {

    public static void main(String[] args) {
        new Main(args);
    }

    public Main(String[] args){
        Logger.Log(Logger.Level.INFO, "initialising starting board...");

        // 5x5
        //int[] startState = {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 1,2,3,-1,0};

        // 4x4
        //int[] startState = {-1,0,0,0, 0,0,0,0, 0,0,0,0, 1,2,3,0};
        //Utils.printBoard(startState);


        InputParser ip = new InputParser();
        int[] startState = ip.parse_string(args[0]);
        int[] goal_states = ip.parse_string(args[1]);
        GoalStateChecker gsc = new GoalStateChecker(goal_states);

        Logger.Log(Logger.Level.INFO, "Loaded board: \n");
        Utils.printBoard(startState);





        //BFS bfse = new BFS();
        //bfse.BFS(startState);


      //Iterative_Deepening ids = new Iterative_Deepening();
       //ids.IterativeDeepening(startState);

        //Iterative_Deepening_Tracked idst = new Iterative_Deepening_Tracked();
        //idst.IterativeDeepening(startState);

        //AStar as = new AStar();
        //as.AStarStart(startState);



        DFS_GraphSearch dfs = new DFS_GraphSearch(gsc);
        dfs.DFS_iterative(startState);


        //DFS dfs = new DFS();
        //dfs.DFS_iterative(startState);



    }
}
