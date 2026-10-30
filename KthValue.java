import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.Random;

// Задача 3. k-е по возрастанию. Меняем сам массив, в среднем за линейное время.
public class KthValue {
    public static void main(String[] args) throws Exception {
        In in = new In();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        out.println(select(a, k - 1));
        out.flush();
    }

    static int select(int[] a, int index) {
        Random rnd = new Random();
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            int pivot = a[left + rnd.nextInt(right - left + 1)];
            int lt = left;
            int i = left;
            int gt = right;
            while (i <= gt) {
                if (a[i] < pivot) {
                    int tmp = a[lt];
                    a[lt] = a[i];
                    a[i] = tmp;
                    lt++;
                    i++;
                } else if (a[i] > pivot) {
                    int tmp = a[i];
                    a[i] = a[gt];
                    a[gt] = tmp;
                    gt--;
                } else {
                    i++;
                }
            }
            if (index < lt)
                right = lt - 1;
            else if (index > gt)
                left = gt + 1;
            else
                return a[index];
        }
        return a[left];
    }

    static class In {
        BufferedInputStream in = new BufferedInputStream(System.in, 1 << 16);
        byte[] buf = new byte[1 << 16];
        int len, pos;

        int read() throws Exception {
            if (pos >= len) {
                len = in.read(buf);
                pos = 0;
                if (len <= 0)
                    return -1;
            }
            return buf[pos++];
        }

        int nextInt() throws Exception {
            int c = read();
            while (c <= ' ')
                c = read();
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long v = 0;
            while (c > ' ') {
                v = v * 10 + c - '0';
                c = read();
            }
            return (int) (v * sign);
        }
    }
}
