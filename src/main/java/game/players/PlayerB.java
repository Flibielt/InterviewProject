package game.players;

import java.util.ArrayList;
import java.util.List;

/**
 * The algorithm of the {@link PlayerB}.
 */
public class PlayerB {

    /**
     * The elements that have been chosen.
     */
    private List<Integer> elements;
    /**
     * The sum of the chosen elements.
     */
    private int score;
    /**
     * The terminals of the current sequence.
     */
    private int[] terminals;
    /**
     * The reply of the current round, 0 if the first terminal, else 1.
     */
    private int reply;

    /**
     * The constructor of the {@link PlayerB}.
     */
    public PlayerB() {
        elements = new ArrayList<Integer>();
        reply = 0;
    }

    /**
     * Chooses the biggest terminal, that has not been chosen.
     */
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

    /**
     * Sets the current terminals.
     *
     * @param terminals the current terminals
     */
    public void setTerminals(int[] terminals) {
        this.terminals = terminals;
    }

    /**
     * Gives the reply for the current round.
     *
     * @return 0, if the first, 1, if the last element
     */
    public int getReply() {
        chooseNumbers();
        return reply;
    }

    /**
     * Gives the current score of the player.
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's data to default.
     */
    public void newMatch() {
        score = 0;
        elements.clear();
    }
}
