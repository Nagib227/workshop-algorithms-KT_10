import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// Задача 4. Сначала самые выгодные заказы. Слот берём самый поздний из ещё свободных.
public class OrderProfit {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        int[] deadline = new int[n];
        int[] profit = new int[n];
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(st.nextToken());
            deadline[i] = d < n ? d : n;
            profit[i] = Integer.parseInt(st.nextToken());
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(profit[b], profit[a]));

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++)
            parent[i] = i;

        long sum = 0;
        for (int id : order) {
            int slot = find(deadline[id]);
            if (slot == 0)
                continue;
            sum += profit[id];
            parent[slot] = find(slot - 1);
        }

        out.println(sum);
        out.flush();
    }

    static int find(int x) {
        int root = x;
        while (parent[root] != root)
            root = parent[root];
        while (parent[x] != root) {
            int next = parent[x];
            parent[x] = root;
            x = next;
        }
        return root;
    }
}
