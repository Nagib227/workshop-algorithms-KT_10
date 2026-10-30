import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// Задача 1. Берём заявки с самым ранним концом. Касание в точке t ещё допустимо.
public class Requests {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        int[][] span = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            span[i][0] = Integer.parseInt(st.nextToken());
            span[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(span, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        int end = 0;
        boolean taken = false;
        for (int i = 0; i < n; i++) {
            if (!taken || span[i][0] >= end) {
                count++;
                end = span[i][1];
                taken = true;
            }
        }

        out.println(count);
        out.flush();
    }
}
