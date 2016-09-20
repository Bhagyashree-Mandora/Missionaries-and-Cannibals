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

        move(initialState);

        printSteps(nMissionaries, nCannibals);
    }

    private void move(State currentState) {

        List<State> nextStates = new LinkedList<>();

        nextStates.add(currentState);
        visitedStates.add(currentState);

        while (!nextStates.isEmpty()) {
            System.out.println("here..");
            if (nextStates.get(0).equals(FINAL_STATE)) {
                break;
            }
            for (MCTuple action : ACTIONS) {
                System.out.println("State is: " + nextStates.get(0).toString());
                System.out.println("Action is: " + action.toString());
                State newState = nextStates.get(0).changeBy(action);
                System.out.println("State after action is: " + newState.toString());
                if (visitedStates.contains(newState)) {
                    System.out.println("visited state contains present state..");
                    continue;
                } else if (isStateValid(newState)) {
                    System.out.println("State is valid!");
                    nextStates.add(newState);
                    visitedStates.add(newState);
                }
            }
            nextStates.remove(0);
        }
    }

    private boolean isStateValid(State newState) {
        if (newState.cannibalsOnLeft > newState.missionariesOnLeft && (newState.missionariesOnLeft != 0)) {
            return false;
        } else if ((totalCannibals - newState.cannibalsOnLeft) > (totalMissionaries - newState.missionariesOnLeft) && ((totalMissionaries - newState.missionariesOnLeft) != 0)) {
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
        System.out.println("Game has ended!");
        int i = 0;
        for (State state : visitedStates) {
            System.out.println("\nPosition : " + ++i);
            System.out.println("Left: m = " + state.missionariesOnLeft + " c = " + state.cannibalsOnLeft);
            System.out.println("Right: m = " + (nMissionaries - state.missionariesOnLeft) + " c = " + (nCannibals - state.cannibalsOnLeft));
        }
    }
}
