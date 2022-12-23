package be.ccfun.day22;

public enum Facing {
    RIGHT(1,0),
    DOWN(0,1),
    LEFT(-1,0),
    UP(0,-1);

    private int deltaX;
    private int deltaY;

    Facing(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public Facing left() {
        return switch (this) {
            case UP -> LEFT;
            case RIGHT -> UP;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
        };
    }

    public Facing right() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

    public Position move(Position position) {
        return position.step(this);
    }

    @Override
    public String toString() {
        return switch (this) {
            case UP -> "^";
            case RIGHT -> ">";
            case DOWN -> "v";
            case LEFT -> "<";
        };
    }
}
