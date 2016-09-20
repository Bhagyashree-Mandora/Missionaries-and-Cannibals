package main.usu;


public class State {
    int missionariesOnLeft;
    int cannibalsOnLeft;
    boolean boatOnLeft;

    public State(int nMissionaries, int nCannibals, boolean boat) {
        missionariesOnLeft = nMissionaries;
        cannibalsOnLeft = nCannibals;
        boatOnLeft = boat;
    }

    public State changeBy(MCTuple action) {
        int missionariesRemaining, cannibalsRemaining;
        if(boatOnLeft) {
            missionariesRemaining = missionariesOnLeft - action.getnMissionary();
            cannibalsRemaining = cannibalsOnLeft - action.getnCannibal();
        } else {
            missionariesRemaining = missionariesOnLeft + action.getnMissionary();
            cannibalsRemaining = cannibalsOnLeft + action.getnCannibal();
        }
        return new State(missionariesRemaining,cannibalsRemaining,!boatOnLeft);
    }

    @Override
    public String toString() {
        return "State{" +
                "missionariesOnLeft=" + missionariesOnLeft +
                ", cannibalsOnLeft=" + cannibalsOnLeft +
                ", boatOnLeft=" + boatOnLeft +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (missionariesOnLeft != state.missionariesOnLeft) return false;
        if (cannibalsOnLeft != state.cannibalsOnLeft) return false;
        return boatOnLeft == state.boatOnLeft;

    }

    @Override
    public int hashCode() {
        int result = missionariesOnLeft;
        result = 31 * result + cannibalsOnLeft;
        result = 31 * result + (boatOnLeft ? 1 : 0);
        return result;
    }
}
