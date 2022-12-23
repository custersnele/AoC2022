package be.ccfun.day22;

import java.util.List;

public class BoardMapCube {

    private List<String> map;
    private int size;
    private Position position;
    private Facing facing;
    private List<Position> luc; // left upper corners
    private List<Position> ruc; // right upper corners

    record Wrap(Position position, Facing facing) {
    }

    public BoardMapCube(List<String> map) {
        this.map = map;
        facing = Facing.RIGHT;
        position = new Position(map.get(0).indexOf('.'), 0);
        size = map.get(0).length() / 3;
        System.out.println(size);
    }

    public boolean isWall(Position position) {
        if (position.getY() == 201) {
            System.out.println("debug");
        }
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

    public Wrap getTop(Position position) {
        // voor side 2, side 4 en side 6
        int side = position.getX() / size;
        int x = position.getX() % size;
        int y = position.getY() % size;
        if (side == 2) { // ZIJDE 2
            // ga rechterkant van side 3 naar links
            System.out.println("VAN ONDERKANT ZIJDE 2 NAAR RECHTKANT ZIJDE 3");
            System.out.println(position);
            Position newPosition = new Position(99, 50 + x);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.LEFT);
        } else if (side == 1) { // ZIJDE 4
            // ga rechterkant side 6 naar links
            System.out.println("VAN ONDERKANT ZIJDE 4 NAAR RECHTKANT ZIJDE 6");
            System.out.println(position);
            Position newPosition = new Position(49, 150 + x);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.LEFT);
        } else if (side == 0) { // ZIJDE 6
            // ga naar bovenkant side 2 naar onder
            System.out.println("VAN ONDERKANT ZIJDE 6 NAAR BOVENKANT ZIJDE 2");
            System.out.println(position);
            Position newPosition = new Position(100 + x, 0);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.DOWN);
        } else {
            System.out.println("Unseen situation top " + side);
            return null;
        }
    }

    public Wrap getBottom(Position position) {
        // voor side 1 en side 2
        int side = position.getX() / size;
        int x = position.getX() % size;
        int y = position.getY() % size;
        if (side == 0) { // ZIJDE 5
            // ga linkerkant 3 naar rechts
            System.out.println("VAN BOVENKANT ZIJDE 5 NAAR LINKERKANT ZIJDE 3");
            System.out.println(position);
            Position newPosition = new Position(50, 50 + x);
            System.out.println(newPosition);
            return new Wrap(new Position(50, 50 + x), Facing.RIGHT);
        } else if (side == 1) { // ZIJDE 1
            // ga linkerkant van side 6 naar rechts
            System.out.println("VAN BOVENKANT  ZIJDE 1 NAAR LINKERKANT ZIJDE 6");
            System.out.println(position);
            Position newPosition = new Position(0, 150 + x);
            System.out.println(newPosition);
            return new Wrap(new Position(0, 150 + x), Facing.RIGHT);
        } else if (side == 2) { // ZIJDE 2
            // ga onderkant side 6 omhoog
            System.out.println("VAN BOVENKANT  ZIJDE 2 NAAR ONDERKANT ZIJDE 6");
            System.out.println(position);
            Position newPosition = new Position(x, 199);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.UP);
        } else {
            System.out.println("Unseen situation bottom " + side);
            return null;
        }
    }

    public Wrap getLeft(Position position) {
        // voor side 3 en side 4
        int side = position.getY() / size;
        int x = position.getX() % size;
        int y = position.getY() % size;
        if (side == 0) { // ZIJDE 2
            // ga naar rechterkant side 4 naar links
            System.out.println("VAN RECHTERKANT ZIJDE 2 NAAR RECHTERKANT ZIJDE 4");
            System.out.println(position);
            Position newPosition = new Position(99, 149 - y);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.LEFT); // OK
        } else if (side == 1) { // ZIJDE 3
            // ga naar onderkant side 2 naar boven
            System.out.println("VAN RECHTERKANT ZIJDE 3 NAAR ONDERKANT ZIJDE 2");
            System.out.println(position);
            Position newPosition = new Position(100 + y, 49);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.UP); // OK
        } else if (side == 2) { // ZIJDE 4
            // ga rechterkant side 2 naar links
            System.out.println("VAN RECHTERKANT ZIJDE 4 NAAR RECHTERKANT ZIJDE 2");
            System.out.println(position);
            Position newPosition = new Position(149, 49 - y);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.LEFT); // OK
        } else if (side == 3) {// ZIJDE 6
            // ga onderkant 4 omhoog
            System.out.println("VAN RECHTERKANT ZIJDE 6 NAAR ONDERKANT ZIJDE 4");
            System.out.println(position);
            Position newPosition = new Position(50+y, 149);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.UP);
        } else {
            System.out.println("Unseen situation left " + side);
            return null;
        }
    }

    public Wrap getRight(Position position) {
        // voor side 1, 3, 5 en 6
        int side = position.getY() / size;
        int x = position.getX() % size;
        int y = position.getY() % size;
        if (side == 0) { // ZIJDE 1
            // ga naar linkerkant side 5 naar rechts
            System.out.println("VAN LINKERKANT ZIJDE 1 NAAR LINKERKANT ZIJDE 5");
            System.out.println(position);
            Position newPosition = new Position(0, 149 - y);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.RIGHT); // OK
        } else if (side == 1) { // ZIJDE 3
            // ga naar bovenkant side 5 naar onder
            System.out.println("VAN LINKERKANT ZIJDE 3 NAAR BOVENKANT ZIJDE 5");
            System.out.println(position);
            Position newPosition = new Position(y, 100);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.DOWN); // OK
        } else if (side == 2) { // ZIJDE 5
            // ga linkerkant side 1 naar rechts
            System.out.println("VAN LINKERKANT ZIJDE 5 NAAR LINKERKANT ZIJDE 1");
            System.out.println(position);
            Position newPosition = new Position(50, 49 - y);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.RIGHT); // OK
        } else if (side == 3) { // ZIJDE 6
            // ga bovenkant 1 naar onder
            System.out.println("VAN LINKERKANT ZIJDE 6 NAAR BOVENKANT ZIJDE 1");
            System.out.println(position);
            Position newPosition = new Position(50 + y, 0);
            System.out.println(newPosition);
            return new Wrap(newPosition, Facing.DOWN); // OK
        } else {
            System.out.println("Unseen situation right " + side);
            return null;
        }
    }

    public void executeSteps(String instructions) {
        instructions = instructions.replace("L", ",L,").replace("R", ",R,");
        String[] split = instructions.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.println("I>" + split[i]);
            if (split[i].equals("L")) {
                facing = facing.left();
            } else if (split[i].equals("R")) {
                facing = facing.right();
            } else {
                int number = Integer.parseInt(split[i]);
                for (int j = 0; j < number; j++) {
                    Position next = position.step(facing);
                    Facing nextFacing = facing;
                    if (isEnd(next)) {
                        switch (facing) {
                            case RIGHT -> {
                                Wrap left = getLeft(position);
                                next = left.position;
                                nextFacing = left.facing;
                            }
                            case LEFT -> {
                                Wrap right = getRight(position);
                                next = right.position;
                                nextFacing = right.facing;
                            }
                            case DOWN -> {
                                Wrap top = getTop(position);
                                next = top.position;
                                nextFacing = top.facing;
                            }
                            case UP -> {
                                Wrap bottom = getBottom(position);
                                next = bottom.position;
                                nextFacing = bottom.facing;
                            }
                        }
                    }
                    if (isWall(next)) {
                        System.out.println("WALL");
                        break;
                    } else {
                        position = next;
                        System.out.println(getSide(position));
                        facing = nextFacing;
                    }
                }
            }
        }
    }

    public int getSide(Position position) {
        int col = position.getX() / size;
        int row = position.getY() / size;
        if (col == 0) {
            if (row == 2) {
                return 5;
            } else if (row == 3) {
                return 6;
            }
        } else if (col == 1) {
            if (row == 0) {
                return 1;
            } else if (row == 1) {
                return 3;
            } else if (row == 2) {
                return 4;
            }
        } else if (col == 2) {
            return 2;
        }
        return -1;
    }
}
