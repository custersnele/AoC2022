package be.ccfun.day18;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Point3D {
	private int x;
	private int y;
	private int z;

	public Point3D(String s) {
		String[] split = s.split(",");
		x = Integer.parseInt(split[0]);
		y = Integer.parseInt(split[1]);
		z = Integer.parseInt(split[2]);
	}

	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public boolean adjacent(Point3D other) {
		return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z) == 1;
	}

	public int getExposed(Set<Point3D> all) {
		int adj = 0;
		for (Point3D other : all) {
			if (other.adjacent(this)) {
				adj++;
			}
		}
		return 6 - adj;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Point3D point3D = (Point3D) o;

		if (x != point3D.x) {
			return false;
		}
		if (y != point3D.y) {
			return false;
		}
		return z == point3D.z;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		result = 31 * result + z;
		return result;
	}

	public List<Point3D> getCandidates() {
		return Arrays.asList(new Point3D(x +1, y,z),new Point3D(x, y+1,z), new Point3D(x,y,z+1));
	}


	@Override
	public String toString() {
		return "Point3D{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}
}
