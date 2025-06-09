package Matrix_Islands;


public class Matrix_Island {

    static int rows, cols;
    static int[][] matrix;
    static boolean[][] visited;

    // Directions including diagonals: 8 neighbors
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    /**
     * Counts number of islands considering diagonal connections.
     * @param mat 2D matrix of 0s and 1s
     * @return count of islands
     */
    public static int countIslands(int[][] mat) {
        matrix = mat;
        rows = matrix.length;
        cols = matrix[0].length;
        visited = new boolean[rows][cols];

        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 1 && !visited[r][c]) {
                    dfs(r, c);
                    count++;
                }
            }
        }
        return count;
    }

    // DFS to mark all connected cells of the island
    private static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                if (matrix[nr][nc] == 1 && !visited[nr][nc]) {
                    dfs(nr, nc);
                }
            }
        }
    }

    // --- Test cases ---
    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 1, 0, 0},
                {0, 1, 0, 1},
                {1, 0, 0, 1},
                {0, 0, 1, 1}
        };
        System.out.println("Number of islands (matrix1): " + countIslands(matrix1));
        // Expected output: 2

        int[][] matrix2 = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        System.out.println("Number of islands (matrix2): " + countIslands(matrix2));
        // Expected output: 3

        int[][] matrix3 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println("Number of islands (matrix3): " + countIslands(matrix3));
        // Expected output: 1
    }
}

