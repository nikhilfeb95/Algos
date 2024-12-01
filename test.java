import java.util.*;

class Solution {
    int [] X = {1,-1,0,0};
    int [] Y = {0,0,1,-1};
    int INF = 2147483647;
    public void islandsAndTreasure(int[][] grid) {
        Queue<int[]> store = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i<m; i++) {
            for(int j = 0; j<n; j++) {
                if(grid[i][j] == 0)
                    store.offer(new int[]{i,j});
            }
        }

        int result = 0;
        while(!store.isEmpty()) {
            int size = store.size();

            for(int i = 0; i<size; i++) {
                int [] temp = store.poll();
                grid[temp[0]][temp[1]] = result; 
                for(int j = 0; j<4; j++) {
                    int tX = X[j] + temp[0];
                    int tY = Y[j] + temp[1];

                    if(isValid(tX, tY, grid))
                        store.add(new int[]{tX, tY});
                }
            }
            result++;
        }
    }

    boolean isValid(int i, int j, int [][] grid) {
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length && grid[i][j]==INF;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.islandsAndTreasure(new int[][]{{2147483647,-1,0,2147483647},
            {2147483647,2147483647,2147483647,-1},
            {2147483647,-1,2147483647,-1},
            {0,-1,2147483647,2147483647}});
    }
}
