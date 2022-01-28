import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class hw1 {

    public static void main(String[] args)throws IOException {//terrain.png, mpp.txt, redOut.png

        File dictFile = new File(args[0]);
        String word1 = args[1];
        String word2 = args[2];
        int n = word1.length();

        Scanner s = new Scanner(dictFile);
        ArrayList<String> dict = new ArrayList<>();//number of english words
        String ss = s.nextLine();
        configDict(s, dict, ss);

        //BFS

        ArrayList<WordNode> explored = new ArrayList<>();

        WordNode A = new WordNode(word1, dict);

        explored.add(A);

        Queue<WordNode> frontier = new PriorityQueue<>();

        frontier.addAll(A.GetNeighbors());

        WordNode Goal = new WordNode(word2, null);

        boolean reached = false;

        while(!frontier.isEmpty()){

            A = frontier.poll();
            explored.add(A);
            frontier.addAll(A.GetNeighbors());

            if(A.equals(Goal)){

                reached = true;
                break;

            }

        }

        if(reached){

            for (WordNode w:
                 frontier) {

                System.out.println(w);

            }

        }
        else{

            System.out.println("No Solution");

        }

        //add neighbors to frontier




    }

    private static void configDict(Scanner s, List<String> dict, String ss) {
        while(true){

            String word = ss;

            dict.add(word);

            try{

                ss = s.nextLine();

            }catch( NoSuchElementException E){

                break;

            }

        }
    }
}
