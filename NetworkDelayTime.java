import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class NetworkDelayTime {

    class Edge {
        int val;
        int to;
        int weight;

        public Edge(int val, int to, int weight){
            this.val = val;
            this.to = to;
            this.weight = weight;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        for(int[] time : times) {
            if(!graph.containsKey(time[0])){
                graph.put(time[0], new ArrayList<>());
            }
            graph.get(time[0]).add(new Edge(time[0], time[1], time[2]));
        }

        if(!graph.containsKey(k) && n > 1) return -1;

        Set<Integer> visited = new HashSet<>();
        visited.add(k);
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for(Edge e : graph.get(k)){
            pq.offer(e);
        }
    int time = 0;
    while(!pq.isEmpty()) {
        int size = pq.size();
        time++;
        for(int i = 0; i < size; i++) {
            Edge temp = pq.poll();
            if(visited.contains(temp.to)) continue;
            if(temp.weight > time) {
                pq.offer(temp);
                continue;
            }
            visited.add(temp.to);
            if(!graph.containsKey(temp.to)) continue;
            for(Edge e : graph.get(temp.to)) {
                if(visited.contains(e.to)) continue;
                e.weight += time;
                pq.offer(e);
            }
        }
    }
        if(visited.size() == n) return time;
        return -1;
    }

    public static void main(String[] args) {
        NetworkDelayTime nt = new NetworkDelayTime();
        System.out.println(nt.networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4, 2));
        System.out.println(nt.networkDelayTime(new int[][]{{1,2,1}}, 2, 2)); 
        System.out.println(nt.networkDelayTime(new int[][]{{1,2,1},{2,1,3}}, 2, 2));
    }
}
