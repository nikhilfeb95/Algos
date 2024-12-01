import java.util.PriorityQueue;

public class ConnectPoints {
    class Edge{
        int from;
        int to;
        int cost;
        
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        public String toString(){
            return "From : " + from + " To : " + to + " Cost : " + cost;
        }
    }
    int [] parent;
    int [] rank;
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)-> a.cost - b.cost);
        this.parent = new int[points.length];
        this.rank = new int[points.length];
        for(int i = 0; i<points.length-1; i++){
            for(int j = i+1; j<points.length; j++){
                pq.offer(new Edge(i,j,Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }
        for(int i = 0; i<points.length; i++){
            parent[i] = i;
            rank[i] = 1;
        }
        int result = 0;
        int edge = points.length;
        while(!pq.isEmpty() && edge>1){
            Edge temp = pq.poll();
            if(find(temp.from) != find(temp.to)){
                if(!union(temp.from, temp.to)){
                    result += temp.cost;
                    edge--;
                }
            }
        }
        return result;
    }
    
    //Find the union of two nodes
    boolean union(int node1, int node2){
       if(find(node1) == find(node2))
           return true;
        
        if(rank[node1] >= rank[node2]){
            parent[node2] = parent[node1];
            rank[node1]++;
        }    
        else{
            parent[node1] = parent[node2];
            rank[node2]++;
        }
            
        return false;
    }
    
    //Find the parent of the node
    int find(int node){
        if(parent[node] == node){
            return node;
        }
        
        //Node compression here
        return parent[node] = find(parent[node]);
    }

    public static void main(String[] args) {
        ConnectPoints cp = new ConnectPoints();
        System.out.println(cp.minCostConnectPoints(new int[][]{{7,18},{-15,19},{-18,-15},{-7,14},{4,1},{-6,3}}));
    }
}
