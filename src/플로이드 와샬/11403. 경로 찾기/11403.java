import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        edge = new int[N][N];

        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                edge[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        floydWarshall();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(edge[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (edge[i][k] == 1 && edge[k][j] == 1) {
                        edge[i][j] = 1;
                    }
                }
            }
        }
    }
}
