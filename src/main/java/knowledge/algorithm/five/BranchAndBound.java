package knowledge.algorithm.five;

import java.util.Scanner;
import java.util.stream.IntStream;

import static l.demo.Demo.p;
import static l.demo.Demo.phr;

/**
 * Branch and Bound
 * 分支定界
 *
 * @author ljh
 * @since 2020/9/29 10:28
 */
public class BranchAndBound {

    /**
     * 单源最短路径
     *
     * @see <a href="https://www.cnblogs.com/zhanghongcan/p/8684465.html">最短路径（Single Source Shortest Path）dijkstra算法求解</a>
     * @see <a href="https://blog.csdn.net/gloria0610/article/details/23742799">单源最短路径（ Dijkstra算法）</a>
     */
    private static class SingleSourceShortestPath {

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

            // 大循环（搞定这里就算搞定该算法了，后面的输出什么的可以不看）
            for (int l = 2; l <= N; l++) {
                int dTemp = max;
                int k = -1;

                // 1)
                for (int i = 2; i <= N; i++) {
                    if (visit[i] == 0 && distance[1][i] < dTemp) {
                        dTemp = distance[1][i];
                        k = i;
                    }
                }
                visit[k] = 1;
                bestmin[k] = dTemp;

                // 2)
                for (int i = 2; i <= N; i++) {
                    if (visit[i] == 0 && (distance[1][k] + distance[k][i]) < distance[1][i]) {
                        distance[1][i] = distance[1][k] + distance[k][i];
                        path[i] = path[k] + "-->" + i;
                    }
                }
            }

            // 输出路径
            IntStream.rangeClosed(1, N).forEach(i -> p("从" + 1 + "出发到" + i + "的最短路径为：" + path[i]));
            phr(35);
            IntStream.rangeClosed(1, N).forEach(i -> p("从1出发到" + i + "点的最短距离为：" + bestmin[i]));
        }

        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.print("请输入节点个数N，路径总数M： ");
            N = scan.nextInt();
            M = scan.nextInt();
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

            p("请输入" + M + "条数据x，y，z（表示x点到y点的距离为z）：");
            for (int i = 1; i <= M; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                int z = scan.nextInt();
                distance[x][y] = z;
            }
            scan.close();

            dijkstra();
        }
    }
}
