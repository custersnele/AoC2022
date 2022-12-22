package be.ccfun.day16;

import java.util.*;

public class FloydWarshallOld {

    private List<Valve> valves;
    private Map<String, Integer> valveIdx;
    private int n;
    private TimeToPressure[][] m;

    public FloydWarshallOld(List<Valve> valves) {
        n = valves.size();
        this.valves = valves;
        valveIdx = new HashMap<>();
        for (int i = 0; i < valves.size(); i++) {
            valveIdx.put(valves.get(i).getName(), i);
        }
        m =  new TimeToPressure[n][n];
        valves = valves;
        init();
    }

    public void init() {
        for (Valve valve : valves) {
            int idx = valveIdx.get(valve.getName());
            for (Valve v2 : valve.getAdjacent()) {
                int idx2 = valveIdx.get(v2.getName());
                m[idx][idx2] = new TimeToPressure(2, v2.getRate());
            }
        }
    }

    public void execute() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (m[i][k] != null && m[k][j] != null) {
                        TimeToPressure cost = m[i][k].add(m[k][j]);
                        m[i][j] = m[i][j] == null? cost : m[i][j].add(cost);
//                        m.successors[i][j] = m.successors[i][k];
//                    }
                    }
                }
            }
        }
    }

    public void execute2() {
        for (Valve valve : valves) {
            int idx = valveIdx.get(valve.getName());
            for (Valve v2 : valve.getAdjacent()) {
                int idx2 = valveIdx.get(v2.getName());
                TimeToPressure getBest = Arrays.asList(m[idx2]).stream().filter(Objects::nonNull).max((t1, t2) -> Integer.compare(t1.getPressure(), t2.getPressure())).get();
                m[idx][idx2] = m[idx][idx2].add(getBest);
            }
        }
    }

    public void print() {
        for (Valve valve : valves) {
            for (Valve other: valves) {
                int idx1 = valveIdx.get(valve.getName());
                int idx2 = valveIdx.get(other.getName());
                System.out.printf("%10s", m[idx1][idx2]);
            }
            System.out.println();
        }
    }
}
