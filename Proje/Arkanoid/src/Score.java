import java.io.Serializable;

public class Score  implements Serializable, Comparable<Score> {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }
    
    public int compareTo(Score o) {
		// TODO Auto-generated method stub
		if(this.getScore() == o.getScore()) {
			return 0;
		}
		else if(this.getScore() < o.getScore()) {
			return 1;
		}
		else {
			return -1;
		}
	}
    
}