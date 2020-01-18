package models;

public enum Status {
    Placed(1),
    Waiting(2),
    Finished(3),
    Paid(4);

    private final int i;
    Status(int i) {
        this.i = i;
    }
    public int getId(){
        return this.i;
    }
}
