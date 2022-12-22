package be.ccfun.day19;

import java.util.*;

public class Blueprint {
	private List<Robot> robots = new ArrayList<>();


	public void addRobot(Robot robot) {
		robots.add(robot);
	}
	
	public int getMaxGeodes(int[] resources, int[] robots, int minute) {
		resources = MapUtil.addResources(resources, robots);
//		for (Robot robot : robots) {
//
//		}
		return 0;
	}

	private Robot getRobot(List<Robot> robots, Resource needed) {
		for (int i = 0; i < robots.size(); i++) {
			if (robots.get(i).getResource() == needed) {
				return robots.get(i);
			}
		}
		return null;
	}
}
