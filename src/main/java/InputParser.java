import java.util.ArrayList;

public class InputParser {

    public int[] parse_string(String input){
        ArrayList<Integer> parsed_string = new ArrayList<>();
        String[] split_string = input.split(",");
        int[] return_list = new int[split_string.length-1];
        int index = 0;
        for(String new_int : split_string){
            try {
                return_list[index] = Integer.parseInt(new_int);
            } catch (NumberFormatException e){
                Logger.Log(Logger.Level.ERROR, "Couldn't parse string " + new_int);
            }
        }
        return return_list;
    }
}
