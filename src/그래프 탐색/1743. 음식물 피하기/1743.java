import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    private final static int[] rMove = new int[]{-1, 1, 0, 0};
    private final static int[] cMove = new int[]{0, 0, -1, 1};
    private static int R;
    private static int C;
    private static int N;
    private static boolean[][] map;
    private static boolean[][] visited;
    private static int maxSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()) + 1;
        C = Integer.parseInt(st.nextToken()) + 1;
        N = Integer.parseInt(st.nextToken());
        map = new boolean[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col] = true;
        }

        for (int r = 1; r < R; r++) {
            for (int c = 1; c < C; c++) {
                if (!visited[r][c] && map[r][c]) {
                    bfs(new Node(r, c));
                }
            }
        }

        System.out.println(maxSize);
    }

    private static void bfs(Node startingNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(startingNode);
        visited[startingNode.getR()][startingNode.getC()] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = currNode.getR() + rMove[i];
                int nextC = currNode.getC() + cMove[i];

                if (nextR < 1 || nextC < 1 || nextR >= R || nextC >= C)
                    continue;

                if (!visited[nextR][nextC] && map[nextR][nextC]) {
                    queue.offer(new Node(nextR, nextC));
                    visited[nextR][nextC] = true;
                    count++;
                }
            }
        }

        maxSize = Math.max(count, maxSize);
    }
}

class Node {
    private int r;
    private int c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getR() {
        return this.r;
    }

    public int getC() {
        return this.c;
    }
}