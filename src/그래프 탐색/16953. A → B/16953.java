import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    private static int A;
    private static int B;

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    private static long bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(A, 1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            long number = node.getNumber();
            long count = node.getCount();

            if (number == B) {
                return count;
            }

            if (number < B) {
                queue.offer(new Node(getMultipliedValue(number), count + 1));
                queue.offer(new Node(getAppendedValue(number), count + 1));
            }
        }

        return -1;
    }

    private static long getMultipliedValue(long a) {
        return a * 2;
    }

    private static long getAppendedValue(long a) {
        return Long.parseLong(a + "1");
    }
}

class Node {
    private long number;
    private long count;

    public Node(long number, long count) {
        this.number = number;
        this.count = count;
    }

    public long getNumber() {
        return this.number;
    }

    public long getCount() {
        return this.count;
    }
}
