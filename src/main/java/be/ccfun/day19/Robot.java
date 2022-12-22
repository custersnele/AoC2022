package be.ccfun.day19;

public class Robot {

	private int[] cost;
	private Resource resource;

	public Robot(Resource resource) {
		this.resource = resource;
	}

	public Resource getResource() {
		return resource;
	}

	// Each ore robot costs 4 ore.
	public Robot(String description) {
		description = description.replace("Each ","").replace("robot costs ","").replace(".", "").trim();
		String[] parts = description.split(" ");
		resource = Resource.valueOf(parts[0].toUpperCase());
		cost = new int[Resource.values().length];
		for (int i = 1; i < parts.length; i+=3) {
			Resource neededResource = Resource.valueOf(parts[i + 1].toUpperCase());
			cost[neededResource.ordinal()] = Integer.parseInt(parts[i]);
		}
	}

	public int[] getCost() {
		return cost;
	}
}
