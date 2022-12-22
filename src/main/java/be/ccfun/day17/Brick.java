package be.ccfun.day17;

public enum Brick {
    MIN_BRICK(new int[][]{{1,1,1,1}}),
    PLUS_BRICK(new int[][]{{0,1,0},{1,1,1},{0,1,0}}),
    J_BRICK(new int[][]{{0,0,1},{0,0,1},{1,1,1}}),
    I_BRICK(new int[][]{{1},{1},{1},{1}}),
    O_BRICK(new int[][]{{1,1},{1,1}});

    private int[][] pattern;
    private int heigth;
    private int width;

    Brick(int[][] pattern) {
        this.pattern = pattern;
        this.width = pattern[0].length;
        this.heigth = pattern.length;
    }

    public int[][] getPattern() {
        return pattern;
    }

    public int getHeight() {
        return heigth;
    }

    public int getWidth() {
        return width;
    }
}
