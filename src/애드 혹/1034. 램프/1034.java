import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static int R;
    private static int C;
    private static int K;
    private static int[][] lamp;
    private static boolean[] visited;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        lamp = new int[R][C];
        visited = new boolean[R];

        for (int r = 0; r < R; r++) {
            String[] tmp = br.readLine().split("");
            for (int c = 0; c < C; c++) {
                lamp[r][c] = Integer.parseInt(tmp[c]);
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int r = 0; r < R; r++) {
            if (visited[r]) continue;
            visited[r] = true;

            int offCnt = 0;
            for (int c = 0; c < C; c++) {
                if (lamp[r][c] == 0) {
                    offCnt ++;
                }
            }

            if (offCnt > K || (offCnt % 2) != (K % 2)) continue;

            max = Math.max(max, getSameRowCnt(r));
        }

        System.out.println(max);
    }

    private static int getSameRowCnt(int r) {
        int sameRowCnt = 0;

        for (int i = 0; i < R; i++) {
            if (Arrays.equals(lamp[i], lamp[r])) {
                sameRowCnt ++;
            }
        }

        return sameRowCnt;
    }
}