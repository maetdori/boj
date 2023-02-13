import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.String.valueOf;

class Main {
    private static int[][] map = new int[5][5];
    private static int[] rMove = new int[] {-1, 1, 0, 0};
    private static int[] cMove = new int[] {0, 0, -1, 1};
    private static Set<String> stringSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                bfs(new Node(i, j, valueOf(map[i][j])));
            }
        }

        System.out.println(stringSet.size());
    }

    private static void bfs(Node startingNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(startingNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.str.length() >= 6) {
                stringSet.add(current.str);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = current.r + rMove[i];
                int nextC = current.c + cMove[i];

                if (nextR < 0 || nextC < 0 || nextR >= 5 || nextC >= 5) continue;
                queue.offer(new Node(nextR, nextC, current.str + valueOf(map[nextR][nextC])));
            }
        }
    }
}

class Node {
    int r;
    int c;
    String str;

    Node(int r, int c, String str) {
        this.r = r;
        this.c = c;
        this.str = str;
    }
}