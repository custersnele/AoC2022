package be.ccfun.day22;

import java.util.List;

public class BoardMap {

    private List<String> map;
    private Position position;
    private Facing facing;

    public BoardMap(List<String> map) {
        this.map = map;
        facing = Facing.RIGHT;
        position = new Position(map.get(0).indexOf('.'), 0);
    }

    public boolean isWall(Position position) {
        return map.get(position.getY()).charAt(position.getX()) == '#';
    }

    public boolean isEnd(Position position) {
        try {
            return map.get(position.getY()).charAt(position.getX()) == ' ';
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
    }

    public char getChar(Position position) {
        return map.get(position.getY()).charAt(position.getX());
    }

    public long getFinalPassword() {
        return facing.ordinal() + 1000L * (position.getY() + 1) + 4L * (position.getX() + 1);
    }

    public Position getTop(Position position) {
        try {
            while (getChar(position) == '.' || getChar(position) == '#') {
                position = position.step(Facing.UP);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return position.step(Facing.DOWN);
    }

    public Position getBottom(Position position) {
        try {
            while (getChar(position) == '.' || getChar(position) == '#') {
                position = position.step(Facing.DOWN);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return position.step(Facing.UP);
    }

    public Position getLeft(Position position) {
        try {
            while (getChar(position) == '.' || getChar(position) == '#') {
                position = position.step(Facing.LEFT);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return position.step(Facing.RIGHT);
    }

    public Position getRight(Position position) {
        try {
            while (getChar(position) == '.' || getChar(position) == '#') {
                position = position.step(Facing.RIGHT);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return position.step(Facing.LEFT);
    }

    public void executeSteps(String instructions) {
        instructions = instructions.replace("L", ",L,").replace("R", ",R,");
        String[] split = instructions.split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("L")) {
                facing = facing.left();
            } else if (split[i].equals("R")) {
                facing = facing.right();
            } else {
                int number = Integer.parseInt(split[i]);
                for (int j = 0; j < number; j++) {
                    Position next = position.step(facing);
                    if (isEnd(next)) {
                        next = switch (facing) {
                            case RIGHT -> new Position(getLeft(position).getX(), position.getY());
                            case LEFT -> new Position(getRight(position).getX(), position.getY());
                            case DOWN -> new Position(position.getX(), getTop(position).getY());
                            case UP -> new Position(position.getX(), getBottom(position).getY());
                        };
                    }
                    if (isWall(next)) {
                        break;
                    } else {
                        position = next;
                    }
                }
            }
        }
    }
}
