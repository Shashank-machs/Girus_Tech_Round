package Knights_and_Portals;

import java.util.*;

public class Knights_and_Port {

    static class Cell {
        int r, c;
        Cell(int r, int c) {
            this.r = r; this.c = c;
        }
    }


    static int[][] bfs(int[][] grid, int startR, int startC) {
        int n = grid.length, m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, -1);
        dist[startR][startC] = 0;

        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(startR, startC));

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m
                        && grid[nr][nc] == 0 && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[curr.r][curr.c] + 1;
                    queue.add(new Cell(nr, nc));
                }
            }
        }
        return dist;
    }

    /**
     * Finds the shortest path from top-left to bottom-right
     * using normal moves and exactly one teleport between any
     * two empty cells.
     */
    public static int shortestPathWithTeleport(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // BFS from start (0,0)
        int[][] distStart = bfs(grid, 0, 0);
        // BFS from end (n-1,m-1)
        int[][] distEnd = bfs(grid, n - 1, m - 1);

        int noTeleportDist = distStart[n - 1][m - 1];
        if (noTeleportDist == -1) noTeleportDist = Integer.MAX_VALUE;

        // Collect all empty cells
        List<Cell> emptyCells = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 0) {
                    emptyCells.add(new Cell(r, c));
                }
            }
        }

        int ans = noTeleportDist;

        // Try all teleport pairs (a -> b)
        for (Cell a : emptyCells) {
            for (Cell b : emptyCells) {
                if (distStart[a.r][a.c] != -1 && distEnd[b.r][b.c] != -1) {
                    int candidate = distStart[a.r][a.c] + 1 + distEnd[b.r][b.c];
                    if (candidate < ans) ans = candidate;
                }
            }
        }

        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    // --- Test Cases ---
    public static void main(String[] args) {
        // Test case 1: simple grid with path
        int[][] grid1 = {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 1, 0}
        };
        System.out.println("Test case 1 output: " + shortestPathWithTeleport(grid1));
        // Expected output: 5

        // Test case 2: blocked path, teleport required
        int[][] grid2 = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
        System.out.println("Test case 2 output: " + shortestPathWithTeleport(grid2));
        // Expected output: 3 (teleport from start to end)

        // Test case 3: no path even with teleport
        int[][] grid3 = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println("Test case 3 output: " + shortestPathWithTeleport(grid3));
        // Expected output: -1

        // Test case 4: path without teleport is shortest
        int[][] grid4 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println("Test case 4 output: " + shortestPathWithTeleport(grid4));
        // Expected output: 4 (no teleport needed)
    }
}

