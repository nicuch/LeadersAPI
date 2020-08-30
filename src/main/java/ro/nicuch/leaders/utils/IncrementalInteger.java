package ro.nicuch.leaders.utils;

public class IncrementalInteger {
    private int n;

    public IncrementalInteger(int start) {
        this.n = start;
    }

    public void increment(int i) {
        this.n+=i;
    }

    public int get() {
        return this.n;
    }
}
