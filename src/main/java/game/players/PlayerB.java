package game.players;

import java.util.ArrayList;
import java.util.List;

public class PlayerB {

    private List<Integer> elements;
    private int score;
    private int[] terminals;
    private int reply;

    public PlayerB() {
        elements = new ArrayList<Integer>();
        reply = 0;
    }

    private void chooseNumbers() {
        if (terminals[0] > terminals[1]) {
            reply = 0;
        } else {
            reply = 1;
        }

        if (elements.contains(terminals[reply])) {
            if (elements.contains(terminals[Math.abs(reply - 1)])) {
                reply = -1;
            } else {
                reply = Math.abs(reply - 1);
                score += terminals[reply];
                elements.add(terminals[reply]);
            }
        } else {
            score += terminals[reply];
            elements.add(terminals[reply]);
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

    public void newMatch() {
        score = 0;
        elements.clear();
    }
}
