package be.ccfun.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day2 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day2/day2.txt"));

		long scoreMe = 0;
		long scoreMeTwo = 0;
		for (String line : lines) {
			String[] plays = line.split(" ");
			RPC me = RPC.getByLetterMe(plays[1]);
			RPC elf = RPC.getByLetterElf(plays[0]);
			RPC meTwo = RPC.getPlay(elf, plays[1]);
			scoreMe += getScore(me, elf);
			scoreMeTwo += getScore(meTwo, elf);
		}
		System.out.println(scoreMe);
		System.out.println(scoreMeTwo);

	}

	public static long getScore(RPC me, RPC elf) {
		int i = me.iWin(elf);
		int result = me.ordinal() + 1;
		if (i == 1) {
			result += 6;
		}  else if (i == 0) {
			result += 3;
		}
		return result;
	}

}
