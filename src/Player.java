
/**
 * Player class holds color and score of player.
 */
public class Player {
    private String color;
    private String name;
    private String colorCode;
    private String oppositeColor;
    private int score;

    /**
     * Create a new player
     * @param color player color
     */
    public Player(String color, String name) {
        this.color = color;
        this.name = name;
        score = 0;
        if (color.equals("black")) {
            colorCode = "\u25CF";
            oppositeColor = "\u25CB";
        }
        else {
            colorCode = "\u25CB";
            oppositeColor = "\u25CF";
        }
    }

    /**
     * get color of player
     * @return color
     */
    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    /**
     * get color code
     * @return color code
     */
    public String getColorCode() {
        return colorCode;
    }

    /**
     * get opposite color code
     * @return opposite color code
     */
    public String getOppositeColor() {
        return oppositeColor;
    }

    /**
     * get score of player
     * @return player score
     */
    public int getScore() {
        return score;
    }

    /**
     * set score
     * @param score player score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
