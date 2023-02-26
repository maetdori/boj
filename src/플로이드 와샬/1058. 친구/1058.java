import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    private static int N;
    private static char[][] map;
    private static char[][] resultMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        resultMap = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            resultMap[i] = map[i].clone();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N ; c++) {
                if (r == c) continue;
                if (map[r][c] == 'N') {
                    for (int i = 0; i < N; i++) {
                        if (map[r][i] == 'Y' && map[i][c] == 'Y') {
                            resultMap[r][c] = 'Y';
                            resultMap[c][r] = 'Y';
                            break;
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int r = 0; r < N; r++) {
            int count = 0;
            for (int c = 0; c < N; c++) {
                if (resultMap[r][c] == 'Y')
                    count++;
            }
            result = Math.max(count, result);
        }

        System.out.println(result);
    }
}