import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 2. Журнал как стек. Минимум и максимум лежат рядом с каждым уровнем.
public class Journal {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int q = Integer.parseInt(in.readLine().trim());
        int[] value = new int[q];
        int[] low = new int[q];
        int[] high = new int[q];
        int size = 0;

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("PUSH")) {
                int x = Integer.parseInt(st.nextToken());
                value[size] = x;
                if (size == 0) {
                    low[size] = x;
                    high[size] = x;
                } else {
                    low[size] = Math.min(low[size - 1], x);
                    high[size] = Math.max(high[size - 1], x);
                }
                size++;
            } else if (cmd.equals("POP")) {
                size--;
            } else if (cmd.equals("MIN")) {
                out.println(low[size - 1]);
            } else {
                out.println(high[size - 1]);
            }
        }

        out.flush();
    }
}
