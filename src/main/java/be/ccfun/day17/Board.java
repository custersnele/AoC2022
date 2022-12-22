package be.ccfun.day17;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<String>currentMatrix = new ArrayList<>();
    private int currentHeigth = 0;
    private int width;

    public Board(int width, int heigth) {
        this.width = width;
    }

    public void add(Brick brick, String movement) {
        for (int i = 0; i < brick.getHeight() + 3; i++) {
            currentMatrix.add(" ".repeat(width));
        }
    }


    public boolean conflict(Brick brick, int row, int col) {
        for (int i = 0; i < brick.getHeight(); i++) {
            for (int j = 0; j < brick.getWidth(); j++) {
                if (brick.getPattern()[i][j] == 1 && currentMatrix.get(row-i).charAt(col+j) == '1') {
                    return true;
                }
            }
        }
        return false;
    }


    public void print() {
        for (int i = currentMatrix.size(); i >= 0; i--) {
            for (int j = 0; j < currentMatrix.get(0).length(); j++) {
                System.out.println(currentMatrix.get(i).charAt(j));
            }
            System.out.println();
        }
    }
}
