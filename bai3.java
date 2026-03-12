import java.util.*;

public class bai3 {
    static class Point{
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static Point p0;

    static long dist2(Point a, Point b) {
        long dx = a.x - b.x; 
        long dy = a.y - b.y;
        return dx * dx + dy * dy;
    }

    static int orientation(Point a, Point b, Point c) {
        long cross = (long) (b.x - a.x) * (c.y - a.y) 
                   - (long) (b.y - a.y) * (c.x - a.x);
        
        if (cross == 0) return 0;
        return cross > 0 ? 1 : -1;
    }

    static List<Point> grahamScan(List<Point> points) {
        int n = points.size();
        if (n <= 1) return new ArrayList<>(points);

        // Chon diem co y nho nhat, neu trung thi x nho nhat
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (points.get(i).y < points.get(minIndex).y ||
                (points.get(i).y == points.get(minIndex).y &&
                points.get(i).x < points.get(minIndex).x)) {
                    minIndex = i;
            }
        }

        Collections.swap(points, 0, minIndex);
        p0 = points.get(0);

        // Sap xep theo goc cuc quanh p0
        points.subList(1,n).sort((a, b) -> {
            int o = orientation(p0, a, b);
            if (o == 0) {
                return Long.compare(dist2(p0, a), dist2(p0, b));
            }
            return (o == 1) ? -1 : 1;
        });

        // Voi cac diem cung goc, chi giu diem xa nhat
        List<Point> filtered = new ArrayList<>();
        filtered.add(points.get(0));

        for (int i = 1; i < n; i++) {
            while ( i < n - 1 && orientation(p0, points.get(i), points.get(i + 1)) == 0) {
                i++;
            }
            filtered.add(points.get(i));
        }

        if (filtered.size() <= 2) return filtered;

        // Graham Scan voi Stack
        List<Point> hull = new ArrayList<>();
        hull.add(filtered.get(0));
        hull.add(filtered.get(1));
        hull.add(filtered.get(2));

        for (int i = 3; i < filtered.size(); i++) {
            while (hull.size() >= 2 &&
                   orientation(hull.get(hull.size()-2), 
                               hull.get(hull.size()-1),
                               filtered.get(i)) <= 0) {
                hull.remove(hull.size()-1);
            }
            hull.add(filtered.get(i));
        }
        return hull;
    }

    // Dua ra ket qua ve thu tu giong mau hon
    // IN theo chieu kim dong ho va bat day tu dien co x nho nhat, neu trung thi y nho nhat
    static List<Point> normalizeOutput(List<Point> hull) {
        if (hull.size() <= 1) return hull;

        List<Point> result = new ArrayList<>(hull);
        Collections.reverse(result);

        int start = 0;
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i).x < result.get(start).x || 
               (result.get(i).x == result.get(start).x &&
                result.get(i).y < result.get(start).y)) {
                    start = i;
                }
        }

        List<Point> rotated = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            rotated.add(result.get((start + i) % result.size()));
        }

        return rotated;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new Point(x, y));
        }

        List<Point> hull = grahamScan(points);
        hull = normalizeOutput(hull);

        for (Point p : hull) {
            System.out.println(p.x + " " + p.y);
        }

        sc.close();
    }
}
