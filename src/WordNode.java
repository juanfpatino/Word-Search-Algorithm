import java.util.ArrayList;

public class WordNode implements Comparable<WordNode> {

    public String word;
    private ArrayList<String> dict = new ArrayList<>();
    public int dist;
    private WordNode parent = null;

    public WordNode(String word, ArrayList<String> dict, int dist) {

        this.word = word;
        this.dict = dict;
        this.dist = dist;

    }

    public ArrayList<WordNode> GetNeighbors() { //only return neighbors if prompted!

        ArrayList<WordNode> neighbors = new ArrayList<>();

        int n = word.length();
        for (int i = 0; i < n; i++) {

            String[] alphabet = new String[]{

                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w"
                    , "x", "y", "z"

            };

            for (int j = 0; j < alphabet.length - 1; j++) {

                String thisWord = word.substring(0, i) + alphabet[j] + word.substring(i + 1);

                if (thisWord.equals(word)) continue;

                if (dict.contains(thisWord)) {

                    WordNode neigh = new WordNode(thisWord, dict, dist + 1);
                    neigh.setParent(this);
                    neighbors.add(neigh);

                }

            }


        }

        return neighbors;


    }

    public WordNode getParent() {

        return parent;

    }

    public void setParent(WordNode p) {

        this.parent = p;

    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int compareTo(WordNode o) {
        return dist;
    }
}

