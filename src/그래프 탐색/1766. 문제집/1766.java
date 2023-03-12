import java.util.*;
import java.io.*;

class Main {
    static int n;   // 노드 갯수
    static int m;   // 간선 갯수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        List<List<Integer>> list = new ArrayList<>();
        int[] indegree = new int[n + 1];

        for(int i = 0; i < n + 1; i++)
            list.add(new ArrayList<>());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            // v1 -> v2
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list.get(v1).add(v2);
            indegree[v2]++;
        }

        topologicalSort(indegree, list);
    }

    // 쉬운 문제를 먼저 풀기 위해서 우선순위 큐에 담음
    static void topologicalSort(int[] indegree, List<List<Integer>> list) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0)
                pq.offer(i);
        }

        while(!pq.isEmpty()) {
            int node = pq.poll();

            for(Integer i : list.get(node)) {
                indegree[i]--;

                if(indegree[i] == 0)
                    pq.offer(i);
            }

            System.out.print(node + " ");
        }
    }
}