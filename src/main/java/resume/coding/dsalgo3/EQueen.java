package resume.coding.dsalgo3;

public class EQueen {

    private int[] queens = new int[8];

    public boolean solve(int col) {
        if (col == 8) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            queens[col] = i;

            boolean flag = true;

            for (int j = 0; j < col; j++) {
                int rowDiff = Math.abs(queens[j] - i);
                int colDiff = col - j;

                if (rowDiff == 0 || rowDiff == colDiff) {
                    flag = false;
                    break;
                }
            }

            if (flag && solve(col+1)) {
                return true;
            }
        }

        return false;
    }

    private void printQueue() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.queens[j] == i) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        EQueen queen = new EQueen();
        queen.solve(0);
        queen.printQueue();
    }
}
