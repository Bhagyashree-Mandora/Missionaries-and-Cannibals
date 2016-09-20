package main.usu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private static int totalMissionaries;
    private static int totalCannibals;
    private static final boolean BOAT_ON_LEFT = true;
    private static final State FINAL_STATE = new State(0, 0, false);
    private static List<MCTuple> ACTIONS = new ArrayList<>();
    private List<State> visitedStates = new ArrayList<>();
    private List<State> positionsToGoal = new ArrayList<>();

    public void play(int nMissionaries, int nCannibals) {
        totalMissionaries = nMissionaries;
        totalCannibals = nCannibals;
        State initialState = new State(nMissionaries, nCannibals, BOAT_ON_LEFT);

        ACTIONS.add(new MCTuple(2, 0));
        ACTIONS.add(new MCTuple(0, 2));
        ACTIONS.add(new MCTuple(1, 1));
        ACTIONS.add(new MCTuple(1, 0));
        ACTIONS.add(new MCTuple(0, 1));

        System.out.println("Game has begun-");

        boolean solved = move(initialState);

        if (solved) {
            printSteps(nMissionaries, nCannibals);
            System.out.println("Solved!");
        }
    }

    private boolean move(State currentState) {

        List<State> nextStates = new LinkedList<>();
        boolean deadEnd;

        nextStates.add(currentState);
        visitedStates.add(currentState);

        while (!nextStates.isEmpty()) {

            currentState = nextStates.get(0);

            if (currentState.equals(FINAL_STATE)) {
                break;
            }
            deadEnd = true;
            for (MCTuple action : ACTIONS) {
                State newState = currentState.changeBy(action);
                if (visitedStates.contains(newState)) {
                    continue;
                } else if (isStateValid(newState)) {
                    deadEnd = false;
                    nextStates.add(newState);
                    visitedStates.add(newState);
                }
            }
            if (!deadEnd) {
                positionsToGoal.add(currentState);
            }
            nextStates.remove(0);
        }

        if (!currentState.equals(FINAL_STATE) && nextStates.isEmpty()) {
            System.out.println("Problem cannot be solved in this case!");
            return false;
        } else {
            positionsToGoal.add(FINAL_STATE);
            return true;
        }
    }

    private boolean isStateValid(State newState) {
        if (newState.cannibalsOnLeft > newState.missionariesOnLeft && (newState.missionariesOnLeft != 0)) {
            return false;
        } else if ((totalCannibals - newState.cannibalsOnLeft) > (totalMissionaries - newState.missionariesOnLeft) &&
                ((totalMissionaries - newState.missionariesOnLeft) != 0)) {
            return false;
        }
        if (newState.missionariesOnLeft > totalMissionaries || newState.cannibalsOnLeft > totalCannibals) {
            return false;
        }
        if (newState.missionariesOnLeft < 0 || newState.cannibalsOnLeft < 0) {
            return false;
        } else return true;
    }

    private void printSteps(int nMissionaries, int nCannibals) {
        int i = 0;
        for (State state : positionsToGoal) {
            System.out.println("\nPosition : " + i++);
            System.out.println("Left: m = " + state.missionariesOnLeft + " c = " + state.cannibalsOnLeft);
            System.out.println("Right: m = " + (nMissionaries - state.missionariesOnLeft) + " c = " + (nCannibals - state.cannibalsOnLeft));
        }
    }
}