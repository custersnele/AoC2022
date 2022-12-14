package be.ccfun.day14;

import java.util.ArrayList;
import java.util.List;

public class Sand {

	private Cave cave;
	private Position position;

	public Sand(Cave cave) throws CaveFullException {
		position = new Position(500,0);
		this.cave = cave;
		drop();
	}

	public boolean isPosition(Position position) {
		return this.position.equals(position);
	}

	public Position getPosition() {
		return position;
	}

	public void drop() throws CaveFullException{
		Position down = position.down();
		Position leftDown = position.leftDown();
		Position rightDown = position.rightDown();
		if (cave.isOut(down)) {
			throw new CaveFullException();
		}
		if (!cave.isFree(down) && !cave.isFree(leftDown) && !cave.isFree(rightDown)) {
			// sand is still
			cave.addSand(this);
		//	cave.showCave();
		}
		if (cave.isFree(down)) {
			position = down;
			drop();
		} else if (cave.isFree(leftDown)) {
			position = leftDown;
			drop();
		} else if (cave.isFree(rightDown)) {
			position = rightDown;
			drop();
		}
	}







}
