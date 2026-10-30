import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

// Задача 5. Кэш: вытесняется редкий ключ, при равенстве — тот, кого дольше не трогали.
public class FreqCache {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int cap = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        HashMap<Integer, int[]> info = new HashMap<Integer, int[]>();
        HashMap<Integer, LinkedHashSet<Integer>> bucket = new HashMap<Integer, LinkedHashSet<Integer>>();
        int minFreq = 0;
        int size = 0;

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            String cmd = st.nextToken();
            int key = Integer.parseInt(st.nextToken());
            if (cmd.equals("GET")) {
                int[] cur = info.get(key);
                if (cur == null) {
                    out.println(-1);
                    continue;
                }
                out.println(cur[0]);
                minFreq = touch(info, bucket, minFreq, key);
            } else {
                int value = Integer.parseInt(st.nextToken());
                int[] cur = info.get(key);
                if (cur != null) {
                    cur[0] = value;
                    minFreq = touch(info, bucket, minFreq, key);
                    continue;
                }
                if (size == cap) {
                    LinkedHashSet<Integer> rare = bucket.get(minFreq);
                    int victim = rare.iterator().next();
                    rare.remove(victim);
                    if (rare.isEmpty())
                        bucket.remove(minFreq);
                    info.remove(victim);
                    size--;
                }
                int[] fresh = new int[]{value, 1};
                info.put(key, fresh);
                LinkedHashSet<Integer> ones = bucket.get(1);
                if (ones == null) {
                    ones = new LinkedHashSet<Integer>();
                    bucket.put(1, ones);
                }
                ones.add(key);
                minFreq = 1;
                size++;
            }
        }

        out.flush();
    }

    static int touch(HashMap<Integer, int[]> info, HashMap<Integer, LinkedHashSet<Integer>> bucket,
                      int minFreq, int key) {
        int[] cur = info.get(key);
        int freq = cur[1];
        LinkedHashSet<Integer> old = bucket.get(freq);
        old.remove(key);
        if (old.isEmpty()) {
            bucket.remove(freq);
            if (minFreq == freq)
                minFreq++;
        }
        cur[1] = freq + 1;
        LinkedHashSet<Integer> next = bucket.get(cur[1]);
        if (next == null) {
            next = new LinkedHashSet<Integer>();
            bucket.put(cur[1], next);
        }
        next.add(key);
        return minFreq;
    }
}
