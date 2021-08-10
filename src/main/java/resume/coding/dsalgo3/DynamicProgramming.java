package resume.coding.dsalgo3;

import java.util.LinkedList;

/**
 * 使用动态规划解决问题：一张n*n地图，每个点上有若干金币，找到一条路径从左上到右下获得金币最多
 */
public class DynamicProgramming {

    static int[][][] dpSolve(int[][] map) {
        final var L = map.length;
        var records = new int[L+1][L+1][3];

        for (int j = 0; j < L+1; j++) {
            for (int i = 0; i < L+1; i++) {
                if (i == 0 && j == 0) {
                    records[i][j][0] = 0;
                    continue;
                }
                if (i == 0 || j == 0) {
                    records[i][j][0] = -1000;
                    continue;
                }

                int max = records[i-1][j-1][0];
                int px = i - 1;
                int py = j - 1;

                if (records[i-1][j][0] > max) {
                    max = records[i-1][j][0];
                    py = j;
                }
                if (records[i][j-1][0] > max) {
                    max = records[i][j-1][0];
                    px = i;
                    // 要覆盖上面可能修改的y坐标
                    py = j - 1;
                }

                records[i][j][0] = max + map[i-1][j-1];
                records[i][j][1] = px;
                records[i][j][2] = py;
            }
        }

        System.out.println("DP Diagram");
        for (int i = 0; i < L+1; i++) {
            for (int j = 0; j < L+1; j++) {
                System.out.printf("%6d(%d, %d) ", records[i][j][0], records[i][j][1], records[i][j][2]);
            }
            System.out.println();
        }

        return records;
    }

    static void printPath(int[][][] path) {
        int l = path.length;
        int x = l-1;
        int y = l-1;

        LinkedList<String> printStack = new LinkedList<>();
        while (x != 0 || y != 0) {
            printStack.addFirst(String.format("(%d, %d)", x, y));
            int tx = path[x][y][1];
            int ty = path[x][y][2];
            x = tx;
            y = ty;
        }
        printStack.addFirst("(0, 0)");

        while (!printStack.isEmpty())
            System.out.printf("%s -> ", printStack.remove());
    }

    public static void main(String[] args) {
        var map = new int[][]{
                {1, 2, 3, 4},
                {4, 66, 6, 7},
                {8, 9, 99, 11},
                {12, 13, 14, 15},
        };

        int[][][] path = dpSolve(map);
        printPath(path);
    }
}
