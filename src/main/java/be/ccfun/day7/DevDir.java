package be.ccfun.day7;

import java.util.ArrayList;
import java.util.List;

public class DevDir {
	private String name;
	private DevDir parent;
	private List<DevFile> files = new ArrayList<>();
	private List<DevDir> subDirs = new ArrayList<>();

	public DevDir(String name) {
		this.name = name;
	}

	public void addFile(DevFile devFile) {
		files.add(devFile);
	}

	public void addDir(DevDir devDir) {
		subDirs.add(devDir);
		devDir.setParent(this);
	}

	public DevDir getParent() {
		return parent;
	}

	public void setParent(DevDir parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (DevFile file : files) {
			builder.append(file).append("\n");
		}
		for (DevDir dir : subDirs) {
			builder.append(dir.getSize()).append(" ").append(dir.name).append("\n");
		}
		return builder.toString();
	}

	public long getSize() {
		long size = 0;
		for (DevFile file : files) {
			size += file.getSize();
		}
		for (DevDir dir : subDirs) {
			size += dir.getSize();
		}
		return size;
	}

	public DevDir getDir(String dirName) {
		return subDirs.stream().filter(d -> d.name.equals(dirName)).findFirst().orElse(null);
	}

	public int sumAllDirs(int bound) {
		int sum = 0;
		if (getSize() < bound) {
			sum += getSize();
		}
		for (DevDir devDir : subDirs) {
			sum += devDir.sumAllDirs(bound);
		}
		return sum;
	}

	public List<Long> getAllDirSizes() {
		List<Long> result = new ArrayList<>();
		result.add(getSize());
		for (DevDir devDir : subDirs) {
			result.addAll(devDir.getAllDirSizes());
		}
		return result;
	}
}
