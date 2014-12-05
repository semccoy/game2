package game2;

public class Score implements Constants{

    Integer score;

    public Score() {
        score = 0;
    }

    public Score(int score) {
        this.score = score;
    }

    public Score increaseBy(int delta) {
        score = (this.score + delta);
        return new Score(score);
    }

    public String print() {
        return this.score.toString();
    }
}
