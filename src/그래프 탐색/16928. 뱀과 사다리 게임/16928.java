import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    private static int[] portal = new int[101];
    private static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            portal[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        bfs();

        System.out.println(minCount);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];

        queue.offer(new Node(1, 0));
        visited[1] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.getPos() == 100) {
                minCount = Math.min(minCount, curr.getCount());
                return;
            }

            for (int i = 1; i < 7; i++) {
                int nextPos = curr.getPos() + i;
                if (nextPos > 100) continue;

                nextPos = portal[nextPos] == 0 ? nextPos : portal[nextPos];
                if (visited[nextPos]) continue;

                queue.offer(new Node(nextPos, curr.getCount() + 1));
                visited[nextPos] = true;
            }
        }
    }

    private static class Node {
        private int pos;
        private int count;

        Node(int pos, int count) {
            this.pos = pos;
            this.count = count;
        }

        public int getPos() {
            return this.pos;
        }

        public int getCount() {
            return this.count;
        }
    }
}