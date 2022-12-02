package be.ccfun.day2;

public enum RPC {
	ROCK,
	PAPER,
	SCISSORS;

	public static RPC getByLetterMe(String character) {
		return switch (character) {
			case "X" -> ROCK;
			case "Y" -> PAPER;
			case "Z" -> SCISSORS;
			default -> null;
		};
	}

	public static RPC getByLetterElf(String character) {
		return switch (character) {
			case "A" -> ROCK;
			case "B" -> PAPER;
			case "C" -> SCISSORS;
			default -> null;
		};
	}

	public int iWin(RPC elf) {
		if (this == elf) {
			return 0;
		}
		if (this == SCISSORS && elf == ROCK) {
			return -1;
		}
		if (this == ROCK && elf == PAPER) {
			return -1;
		}
		if (this == PAPER && elf == SCISSORS) {
			return -1;
		}
		return 1;
	}

	public static RPC getPlay(RPC elf, String winOrLose) {
		if (winOrLose.equals("Y")) {
			return elf;
		}
		if (elf == SCISSORS) {
			if (winOrLose.equals("Z")) {
				return ROCK;
			} else  {
				return PAPER;
			}
		} else if (elf == ROCK) {
			if (winOrLose.equals("Z")) {
				return PAPER;
			} else {
				return SCISSORS;
			}
		} else {
			if (winOrLose.equals("Z")) {
				return SCISSORS;
			} else {
				return ROCK;
			}
		}
	}

}
