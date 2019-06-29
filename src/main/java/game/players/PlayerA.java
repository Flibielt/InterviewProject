package game.players;

public class PlayerA {

    private int score;
    private int[] terminals;
    private int reply;

    public PlayerA() {
        score = 0;
    }

    public void getTerminals(int[] terminals) {
        this.terminals = terminals;
    }

    private void chooseNumber() {
        if (terminals[0] > terminals[1]) {
            score += terminals[0];
            reply = 0;
        }

        score += terminals[1];
        reply = 1;
    }

    public int getReply() {
        chooseNumber();
        return reply;
    }

    public int getScore() {
        return score;
    }
}
