package knowledge.算法.branch.and.bound;

import java.util.Scanner;

/**
 * 单源最短路径
 * <p>
 * https://www.cnblogs.com/zhanghongcan/p/8684465.html
 * https://blog.csdn.net/gloria0610/article/details/23742799
 */
public class SingleSourceShortestPath {

    private static int N;
    private static int M;
    private static int max;
    private static int[] visit;
    private static int[][] distance;
    private static int[] bestmin;
    private static String[] path;

    private static void dijkstra() {
        visit[1] = 1;
        bestmin[1] = 0;

        //大循环（搞定这里就算搞定该算法了，后面的输出什么的可以不看）
        for (int l = 2; l <= N; l++) {
            int dTemp = max;
            int k = -1;

            //步骤①
            for (int i = 2; i <= N; i++) {
                if (visit[i] == 0 && distance[1][i] < dTemp) {
                    dTemp = distance[1][i];
                    k = i;
                }
            }
            visit[k] = 1;
            bestmin[k] = dTemp;

            //步骤②
            for (int i = 2; i <= N; i++) {
                if (visit[i] == 0 && (distance[1][k] + distance[k][i]) < distance[1][i]) {
                    distance[1][i] = distance[1][k] + distance[k][i];
                    path[i] = path[k] + "-->" + i;
                }
            }
        }

        //输出路径
        for (int i = 1; i <= N; i++) {
            System.out.println("从" + 1 + "出发到" + i + "的最短路径为：" + path[i]);
        }
        System.out.println("=====================================");
        for (int i = 1; i <= N; i++) {
            System.out.println("从1出发到" + i + "点的最短距离为：" + bestmin[i]);
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("请输入节点个数N，路径总数M： ");
        N = input.nextInt();
        M = input.nextInt();
        max = 10000;
        bestmin = new int[N + 1];
        distance = new int[N + 1][N + 1];
        visit = new int[N + 1];
        path = new String[N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = max;
                }
            }
            bestmin[i] = max;
            path[i] = "1-->" + i;
        }

        System.out.println("请输入" + M + "条数据x，y，z（表示x点到y点的距离为z）：");
        for (int i = 1; i <= M; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            int z = input.nextInt();
            distance[x][y] = z;
        }
        input.close();

        dijkstra();
    }

}
