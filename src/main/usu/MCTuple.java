package main.usu;

public class MCTuple {
    int nMissionary;
    int nCannibal;

    public MCTuple(int nMissionary, int nCannibal) {
        this.nMissionary = nMissionary;
        this.nCannibal = nCannibal;
    }

    public int getnMissionary() {
        return nMissionary;
    }

    public int getnCannibal() {
        return nCannibal;
    }

    @Override
    public String toString() {
        return "MCTuple{" +
                "nMissionary=" + nMissionary +
                ", nCannibal=" + nCannibal +
                '}';
    }
}
