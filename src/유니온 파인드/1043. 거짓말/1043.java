import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int N; // 사람의 수
    private static int M; // 파티의 수
    private static int truthNum; // 진실을 아는 사람의 수
    private static int[] parent;
    private static boolean[] knows;
    private static Set<Integer>[] parties;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        knows = new boolean[N + 1];
        parties = new HashSet[M];

        st = new StringTokenizer(br.readLine());
        truthNum = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < truthNum; i++) {
            knows[Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 0; i < M; i++) {
            parties[i] = new HashSet<>();
            String[] inputs = br.readLine().split(" ");
            int partyMemberCnt = Integer.parseInt(inputs[0]);

            if (partyMemberCnt == 1) {
                parties[i].add(Integer.parseInt(inputs[1]));
                continue;
            }

            for (int j = 1; j < partyMemberCnt; j++) {
                int node1 = Integer.parseInt(inputs[j]);
                int node2 = Integer.parseInt(inputs[j+1]);

                union(node1, node2);

                parties[i].add(node1);
                parties[i].add(node2);
            }
        }

        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (knows[i] && !visited[i]) {
                int root = find(i);
                for (int j = 1; j < N + 1; j++) {
                    if (find(j) == root) {
                        knows[j] = true;
                        visited[j] = true;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            boolean flag = false;
            for (int member: parties[i]) {
                if (knows[member]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) result++;
        }

        System.out.println(result);
    }

    private static void union(int node1, int node2) {
        if (find(node1) == find(node2)) return;
        int node2Parent = find(node2);
        parent[node2Parent] = node1;
    }

    private static int find(int node) {
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }
}