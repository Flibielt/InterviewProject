package game.controller;

import game.field.Field;
import game.players.PlayerA;
import game.players.PlayerB;

import java.util.Random;

/**
 * The game controller.
 */
public class Controller {

    /**
     * The current sequence.
     */
    private Field field;
    /**
     * The {@link PlayerA}.
     */
    private PlayerA playerA;
    /**
     * The {@link PlayerB}.
     */
    private PlayerB playerB;
    /**
     * The statistic of the {@link PlayerB}.
     */
    private int[] results;
    /**
     * Indicates, if the game can continue.
     */
    private boolean matchCanContinue;
    /**
     * The time that has been passed at specific rounds.
     */
    private long[] times;

    /**
     * The constructor of the {@link Controller}.
     */
    public Controller() {
        field = new Field();
        playerA = new PlayerA();
        playerB = new PlayerB();
        results = new int[3];
    }

    /**
     * Sends the terminals of the current sequence to the players.
     */
    private void sendTerminals() {
        int[] terminals = field.getTerminals();

        playerA.setTerminals(terminals);
        playerB.setTerminals(terminals);
    }

    /**
     * Creates a new match.
     */
    private void newMatch() {
        field.newSequence();
        matchCanContinue = true;
    }

    /**
     * Gives {@code true}, if the current sequence is empty.
     *
     * @return {@code true}, if the sequence is empty, else {@code false}
     */
    private boolean isFieldEmpty() {
        return field.isEmpty();
    }

    /**
     * Deletes a terminal from the current sequence.
     *
     * @param position 0, if the first, 1, if the last
     */
    private void deleteFieldElement(int position) {
        field.deleteElement(position == 0);
        if (isFieldEmpty()) {
            matchCanContinue = false;
        }
    }

    /**
     * Checks, if the game can continue.
     *
     * @return {@code true}, if the game can continue, else {@code false}
     */
    private boolean canMatchContinue() {
        return matchCanContinue;
    }

    /**
     * Carries out a new round.
     *
     * @param bStart {@code true}, if the {@link PlayerB} starts
     */
    public void nextRound(boolean bStart) {
        int[] terminals = field.getTerminals();
        if (bStart) {
            playerBStep();
            playerAStep();
        } else {
            playerAStep();
            playerBStep();
        }

        if (!isFieldEmpty()) {
            if (terminals[0] == field.getTerminals()[0]) {
                if (terminals[1] == field.getTerminals()[1]) {
                    deleteFieldElement(0);
                    if (!isFieldEmpty()) {
                        deleteFieldElement(1);
                    }
                }
            }
        }
    }

    /**
     * Makes the {@link PlayerA} step.
     */
    private void playerAStep() {
        if (matchCanContinue) {
            sendTerminals();
            int reply = playerA.getReply();
            if (reply != -1) {
                deleteFieldElement(reply);
            }
        }
    }

    /**
     * Makes the {@link PlayerB} step.
     */
    private void playerBStep() {
        if (matchCanContinue) {
            sendTerminals();
            int reply = playerB.getReply();
            if (reply != -1) {
                deleteFieldElement(reply);
            }
        }
    }

    /**
     * Makes the statistics for the game.
     */
    private void analyzeGame() {
        if (playerA.getScore() > playerB.getScore()) {
            results[0]++;
        } else if (playerA.getScore() < playerB.getScore()) {
            results[2]++;
        } else {
            results[1]++;
        }
        playerA.newMatch();
        playerB.newMatch();
    }

    /**
     * Writes the statistics on the console.
     */
    private void writeResults() {
        System.out.println("PlayerA wins: " + results[0]);
        System.out.println("Draw: " + results[1]);
        System.out.println("PlayerB wins: " + results[2]);
        double percentage = ((double)results[2] / ((double)results[0] + (double)results[1] + (double)results[2])) * 100;
        System.out.println("PlayerB wins: " + percentage + "%");
    }

    /**
     * Gives the count of the {@link PlayerB} victories.
     *
     * @return the count of {@link PlayerB} victories
     */
    public int getBVictories() {
        return results[2];
    }

    /**
     * Gives the count of the {@link PlayerB} loses.
     *
     * @return the count of {@link PlayerB} loses.
     */
    public int getBLoses() {
        return results[0];
    }

    /**
     * Gives the count of draws.
     *
     * @return the count of draws
     */
    public int getDraws() {
        return results[1];
    }

    /**
     * Starts the simulation.
     */
    public void startSimulation() {
        long startTime = System.currentTimeMillis();
        times = new long[6];
        long time;
        boolean bStart;
        Random rand = new Random();

        for (int i = 0; i < 1000000; i++) {
            newMatch();
            bStart = rand.nextBoolean();

            while (!isFieldEmpty() && canMatchContinue()) {
                nextRound(bStart);
            }
            time = System.currentTimeMillis();
            if (i == 10) {
                times[0] = time - startTime;
            } else if (i == 100 - 1) {
                times[1] = time - startTime;
            } else if (i == 1000 - 1) {
                times[2] = time - startTime;
            } else if (i == 10000 -1 ) {
                times[3] = time -startTime;
            } else if (i == 100000 - 1) {
                times[4] = time - startTime;
            } else if (i == 1000000- 1) {
                times[5] = time - startTime;
            }
            analyzeGame();
        }

        writeResults();
    }

    /**
     * Gives the elapsed time in specific rounds.
     *
     * @return elapsed times
     */
    public long[] getTimes() {
        return times;
    }

}
