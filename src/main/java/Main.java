import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main(args);
    }

    public Main(String[] args){
        Logger.Log(Logger.Level.INFO, "initialising starting board...");

        // 5x5
        //int[] startState = {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 1,2,3,-1,0};

        // 4x4
        //int[] startState = {-1,0,0,0, 0,0,1,0, 0,0,2,0, 0,0,3,0};
        //Utils.printBoard(startState);

        int[] startState = null, goal_states = null;
        InputParser ip = new InputParser();
        GoalStateChecker gsc = null;


        for(int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-B":
                    startState = ip.parse_string(args[i+1]);
                    Logger.Log(Logger.Level.INFO, "Loaded board: \n");
                    Utils.printBoard(startState, GoalStateChecker.N);
                    break;
                case "-G":
                    goal_states = ip.parse_string(args[i+1]);
                    gsc = new GoalStateChecker(goal_states);
                    break;
                case "-N":
                    GoalStateChecker.N = Integer.parseInt(args[i+1]);
                    Logger.Log(Logger.Level.INFO, "Loaded board size " + GoalStateChecker.N );
                    break;
                default:
                    break;
            }
        }

        Long startTime = System.currentTimeMillis();


        // This code is used to setup argument-free boards when running repeatedly.

/**
        GoalStateChecker.N = 4;
        startState = new int[]  {1,0,0,0,0,0,0,3,0,0,-1,2,0,0,0,0};
        goal_states = new int[] {5,9,13};
        gsc = new GoalStateChecker(goal_states);
**/


        if(gsc == null || startState == null || goal_states == null){
            Logger.Log(Logger.Level.ERROR, "Couldn't parse starting or goal states!");
            Logger.Log(Logger.Level.ERROR, "Printing to console: \nStart states:" + Arrays.toString(startState) + "\ngoalstates: " + Arrays.toString(goal_states));
            return;
        }



        //BFS bfse = new BFS(gsc);
        //bfse.BFS(startState);

        //BFS_Tracked bfst = new BFS_Tracked(gsc);
        //bfst.BFS(startState);



        //Iterative_Deepening ids = new Iterative_Deepening(gsc);
        //ids.IterativeDeepening(startState);

        Iterative_Deepening_Tracked idst = new Iterative_Deepening_Tracked(gsc);
        idst.IterativeDeepening(startState);

        //AStar as = new AStar(gsc);
        //as.AStarStart(startState);



        //DFS_GraphSearch dfs = new DFS_GraphSearch(gsc);
        //dfs.DFS_iterative(startState);


        //DFS dfs = new DFS(gsc);
        //dfs.DFS_iterative(startState);

        Long endtime = System.currentTimeMillis();

        System.out.println("Completion time: " + (endtime - startTime));

    }
}
