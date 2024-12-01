import java.util.LinkedList;
import java.util.Queue;

public class SP {
    int [] X = {0,-1,-1,-1,0,1,1,1};
    int [] Y = {-1,-1,0,1,1,1,0,-1};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1)
            return -1;

        Queue<int []> store = new LinkedList<>();
        store.offer(new int[]{0,0});
        int result = 0;
        while(!store.isEmpty()) {
            int size = store.size();

            for(int i = 0; i<size; i++) {
                int [] temp = store.poll();

                if (temp[0] == n-1 && temp[1] == n-1)
                    return result+1;

                for(int j=0; j<8; j++) {
                    int tx = temp[0] + X[j];
                    int ty = temp[1] + Y[j];

                    if(isValid(tx, ty, grid))
                        store.offer(new int[]{tx, ty});
                }
            }
            result++;
        }


        return -1;
    }

    boolean isValid(int i, int j, int [][] grid) {
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length && grid[i][j] == 0;
    }

    public static void main(String[] args) {
        SP sp = new SP();
        System.out.println(sp.shortestPathBinaryMatrix(new int[][]{{0,1},{1,0}}));
    }
}
