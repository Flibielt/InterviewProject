package game.players;

public class PlayerB {

    private int score;
    private int[] terminals;
    private int reply;

    public PlayerB() {
        reply = 0;
    }

    private void chooseNumbers() {
        if (terminals[0] < terminals[1]) {
            score += terminals[0];
            reply = 0;
        } else {
            score += terminals[1];
            reply = 1;
        }
    }

    public void getTerminals(int[] terminals) {
        this.terminals = terminals;
    }

    public int getReply() {
        chooseNumbers();
        return reply;
    }

    public int getScore() {
        return score;
    }
}
