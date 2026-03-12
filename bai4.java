import java.util.*;

public class bai4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] a = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        int NEG = -1000000000;

        // dp[i][s] = so phan tu lon nhat khi xet tu 1...i, co tong bang s
        int[][] dp = new int[n+1][k+1];
        boolean[][] take = new boolean[n+1][k+1];
        
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], NEG);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int s = 0; s <= k; s++) {
                // Khong lay a[i]
                dp[i][s] = dp[i-1][s];
                take[i][s] = false;
                
                // Co lay a[i]
                if (s >= a[i] && dp[i-1][s-a[i]] != NEG) {
                    int cand = dp[i-1][s-a[i]] + 1;
                    if (cand > dp[i][s]) {
                        dp[i][s] = cand;
                        take[i][s] = true;
                    }
                }
            }
        }

        if (dp[n][k] < 0) {
            System.out.println("Khong co day con nao co tong bang " + k);
        }

        // Truy vet de lay dap an
        List<Integer> ans = new ArrayList<>();
        int s = k;

        for (int i = n; i >= 1; i--) {
            if (take[i][s]) {
                ans.add(a[i]);
                s -= a[i];
            }
        }

        Collections.reverse(ans);
        
        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) System.out.print(",");
            System.out.print(ans.get(i));
        }
    }
}
