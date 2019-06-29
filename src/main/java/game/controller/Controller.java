package game.controller;

import game.field.Field;
import game.players.PlayerA;
import game.players.PlayerB;

public class Controller {

    private Field field;
    private PlayerA playerA;
    private PlayerB playerB;

    private Controller() {
        field = new Field();
        playerA = new PlayerA();
        playerB = new PlayerB();
    }

    public void sendTerminals() {
        int [] terminals = field.getTerminals();

        playerA.getTerminals(terminals);
        playerB.getTerminals(terminals);
    }

    private void newGame() {
        field.createSequence();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();

    }
}
