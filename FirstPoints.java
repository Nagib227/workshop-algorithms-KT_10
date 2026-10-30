import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

// Задача 6. Первые k пунктов: ближе к нулю, потом меньший x, y и номер.
public class FirstPoints {
    static class Point {
        int x, y, id;
    }

    static final Comparator<Point> ORDER = new Comparator<Point>() {
        public int compare(Point p, Point q) {
            long dp = (long) p.x * p.x + (long) p.y * p.y;
            long dq = (long) q.x * q.x + (long) q.y * q.y;
            if (dp != dq)
                return dp < dq ? -1 : 1;
            if (p.x != q.x)
                return p.x < q.x ? -1 : 1;
            if (p.y != q.y)
                return p.y < q.y ? -1 : 1;
            return p.id < q.id ? -1 : 1;
        }
    };

    public static void main(String[] args) throws Exception {
        In in = new In();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            Point p = new Point();
            p.x = in.nextInt();
            p.y = in.nextInt();
            p.id = i + 1;
            points[i] = p;
        }

        keepFirst(points, k);
        Arrays.sort(points, 0, k, ORDER);
        for (int i = 0; i < k; i++)
            out.println(points[i].x + " " + points[i].y + " " + points[i].id);
        out.flush();
    }

    static void keepFirst(Point[] a, int k) {
        if (k >= a.length)
            return;
        Random rnd = new Random();
        int left = 0;
        int right = a.length - 1;
        int target = k - 1;
        while (left < right) {
            int pivotAt = left + rnd.nextInt(right - left + 1);
            Point pivot = a[pivotAt];
            swap(a, pivotAt, right);
            int store = left;
            for (int i = left; i < right; i++) {
                if (ORDER.compare(a[i], pivot) < 0) {
                    swap(a, store, i);
                    store++;
                }
            }
            swap(a, store, right);
            if (store == target)
                return;
            if (target < store)
                right = store - 1;
            else
                left = store + 1;
        }
    }

    static void swap(Point[] a, int i, int j) {
        Point t = a[i];
        a[i] = a[j];
        a[j] = t;
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
