import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static int N;
    private static int M;
    private static List<Integer>[] adjList;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new List[N];

        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }

        System.out.println(hasConnection() ? 1 : 0);
    }

    private static boolean hasConnection() {
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            if (dfs(i, 1))
                return true;
        }
        return false;
    }

    private static boolean dfs(int node, int count) {
        if (count == 5)
            return true;

        for (int friend : adjList[node]) {
            if (visited[friend])
                continue;

            visited[friend] = true;
            if (dfs(friend, count + 1))
                return true;
            visited[friend] = false;
        }
        return false;
    }
}