package Sodaku_Validator;

import java.util.*;

public class Sodaku_Prgm {
    public boolean isValidSudoku(char[][] board, int[][] customZones) {
        // Validate rows, columns, and 3x3 boxes
        for (int i = 0; i < 9; i++) {
            Set<Character> rows = new HashSet<>();
            Set<Character> cols = new HashSet<>();
            Set<Character> box = new HashSet<>();

            for (int j = 0; j < 9; j++) {
                // Check row
                char r = board[i][j];
                if (r != '.' && !rows.add(r)) return false;

                // Check column
                char c = board[j][i];
                if (c != '.' && !cols.add(c)) return false;

                // Check 3x3 box
                int RowIndex = 3 * (i / 3);
                int ColIndex = 3 * (i % 3);
                char b = board[RowIndex + j / 3][ColIndex + j % 3];
                if (b != '.' && !box.add(b)) return false;
            }
        }
        // Validate custom zones
        for (int[] zone : customZones) {
            Set<Character> zoneSet = new HashSet<>();
            for (int index : zone) {
                int row = index / 9, col = index % 9;
                char val = board[row][col];
                if (val != '.' && !zoneSet.add(val)) return false;
            }
        }
        return true;
    }
}

