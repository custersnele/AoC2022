package be.ccfun.day19;

import java.util.Arrays;
import java.util.Map;

public class MapUtil {
    public static <T> void add(Map<T, Integer> map, T key, int add) {
        int current = map.get(key) == null? 0 : map.get(key);
        map.put(key, current + add);
    }

    public static boolean isSufficient(Map<Resource, Integer> available, Map<Resource, Integer> cost) {
        for (Resource resource: cost.keySet()) {
            if (!available.containsKey(resource)) {
                return false;
            }
            int numberAvailable = available.get(resource);
            if (numberAvailable < cost.get(resource)) {
                return false;
            }
        }
        return true;
    }

    public static void pay(Map<Resource, Integer> available, Map<Resource, Integer> cost) {
        for (Resource resource: cost.keySet()) {
            add(available, resource, -cost.get(resource));
        }
    }

    public static int[] addResources(int[] currentResources, int[] newResources) {
        int[] copy = Arrays.copyOf(currentResources, currentResources.length);
        for (int i = 0; i < currentResources.length; i++) {
            copy[i] += newResources[i];
        }
        return copy;
    }

    public static boolean isAvailable(int[] currentResources, int[] neededResources) {
        for (int i = 0; i < currentResources.length; i++) {
            if (currentResources[i] < neededResources[i]) {
                return false;
            }
        }
        return true;
    }

    public static void print(Map<Resource, Integer> map) {
        for (Resource resource: map.keySet()) {
            System.out.println(resource + " -> " + map.get(resource));
        }
    }
}
