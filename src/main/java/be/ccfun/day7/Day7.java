package be.ccfun.day7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day7 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day7/day7.txt"));
		DevDir currentDir = new DevDir("/");
		DevDir home = currentDir;
		int i = 1;
		String line = lines.get(0);
		while (i < lines.size()) {
			line = lines.get(i);
			if (line.startsWith("$ cd ..")) {
				currentDir = currentDir.getParent();
			} else if (line.startsWith("$ cd ")) {
				String dirName = line.substring(5);
				currentDir = currentDir.getDir(dirName);
			} else if (line.startsWith("$ ls")) {
				line = lines.get(++i);
				while (!line.startsWith("$")) {
					if (line.startsWith("dir")) {
						currentDir.addDir(new DevDir(line.substring(4)));
					} else {
						currentDir.addFile(new DevFile(line));
					}
					i++;
					if (i >= lines.size()) {
						line="$";
					} else {
						line = lines.get(i);
					}
				}
				i--;
			}
			i++;
		}
		System.out.println(home);
		long sum = home.sumAllDirs(100000);

		System.out.println(sum);
		System.out.println();
		System.out.println("home size:" + home.getSize());
		long spaceFee = 70000000 - home.getSize();
		long needed = 30000000 - spaceFee;
		System.out.println("extra needed: " + needed);
		System.out.println();
		List<Long> sizes = home.getAllDirSizes();
		sizes.stream().sorted().forEach(System.out::println);
		Long aLong = sizes.stream().mapToLong(l -> l).filter(l -> l >= needed).min().getAsLong();
		System.out.println();
		System.out.println(aLong);
	}

}
