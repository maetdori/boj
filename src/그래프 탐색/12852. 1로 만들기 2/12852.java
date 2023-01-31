import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
    private static final int TARGET_NUMBER = 1;
    private static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());

        bfs();
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(X, 0, String.valueOf(X)));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int number = node.getNumber();
            int count = node.getCount();
            String series = node.getSeries();

            if (number == TARGET_NUMBER) {
                System.out.println(count);
                System.out.println(series);
                return;
            }

            if (number > TARGET_NUMBER) {
                if (number % 3 == 0) {
                    queue.offer(new Node(number / 3, count + 1, series + " " + (number / 3)));
                }
                if (number % 2 == 0) {
                    queue.offer(new Node(number / 2, count + 1, series + " " + (number / 2)));
                }
                queue.offer(new Node(number - 1, count + 1, series + " " + (number - 1)));
            }
        }
    }
}

class Node {
    private int number;
    private int count;
    private String series;


    public Node(int number, int count, String series) {
        this.number = number;
        this.count = count;
        this.series = series;
    }

    public int getNumber() {
        return this.number;
    }

    public int getCount() {
        return this.count;
    }

    public String getSeries() {
        return this.series;
    }
}
