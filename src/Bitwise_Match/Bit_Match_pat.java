package Bitwise_Match;


public class Bit_Match_pat {

    public static int nextHigherWithSameOnes(int n) {
        int c = n;
        int c0 = 0;
        int c1 = 0;

        // Count trailing zeros (c0)
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // Count ones (c1)
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // If n == 11..1100..00 no bigger number possible
        if (c0 + c1 == 31 || c0 + c1 == 0) return -1;

        int p = c0 + c1; // position of rightmost non-trailing zero

        // Flip rightmost non-trailing zero
        n |= (1 << p);

        // Clear bits to the right of p
        n &= ~((1 << p) - 1);

        // Insert (c1-1) ones on the right
        n |= (1 << (c1 - 1)) - 1;

        return n;
    }

    // Sample test cases
    public static void main(String[] args) {
        int[] testCases = {6, 13, 15, 1, 0, 7};

        for (int n : testCases) {
            int next = nextHigherWithSameOnes(n);
            System.out.printf("n = %d (%s) --> next = %d (%s)%n",
                    n, Integer.toBinaryString(n), next, next == -1 ? "N/A" : Integer.toBinaryString(next));
        }
    }
}

