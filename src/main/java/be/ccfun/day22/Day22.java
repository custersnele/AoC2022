package be.ccfun.day22;

import be.ccfun.day21.MathNumber;
import be.ccfun.day21.MathOperation;
import be.ccfun.day21.MathResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day22 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/day22/day22.txt"));
        BoardMap boardMap = new BoardMap(lines.subList(0, lines.size() - 2));
        boardMap.executeSteps(lines.get(lines.size() - 1));
        System.out.println(boardMap.getFinalPassword());
        System.out.println("Part 2");
        BoardMapCube boardMapCube = new BoardMapCube(lines.subList(0, lines.size() - 2));
        boardMapCube.executeSteps(lines.get(lines.size() - 1));
        System.out.println(boardMapCube.getFinalPassword());
    }
}
