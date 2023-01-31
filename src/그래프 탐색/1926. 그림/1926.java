import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    private static int row;
    private static int col;
    private static int[][] matrix;
    private static int count = 0;
    private static int maxSize = 0;
    private static int[] rMove = new int[] {-1, 1, 0, 0};
    private static int[] cMove = new int[] {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        matrix = new int[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < col; c++) {
                matrix[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(count);
        System.out.println(maxSize);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] || matrix[i][j] == 0) {
                    continue;
                }
                visited[i][j] = true;
                queue.offer(new Node(i, j));
                count ++;
                int size = 0;

                while (!queue.isEmpty()) {
                    Node node = queue.poll();
                    int r = node.getRow();
                    int c = node.getCol();
                    size++;

                    for (int k = 0; k < 4; k ++) {
                        int nextR = r + rMove[k];
                        int nextC = c + cMove[k];

                        if (nextR < 0 || nextC < 0 || nextR >= row || nextC >= col) {
                            continue;
                        }

                        if (!visited[nextR][nextC] && matrix[nextR][nextC] == 1) {
                            queue.offer(new Node(nextR, nextC));
                            visited[nextR][nextC] = true;
                        }
                    }
                }
                maxSize = Math.max(maxSize, size);
            }
            }
        }
}

class Node {
    private int row;
    private int col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}