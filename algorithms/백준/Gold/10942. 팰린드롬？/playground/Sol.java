import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Sol {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        in.nextToken();
        int n = (int) in.nval;
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            in.nextToken();
            a[i] = (int) in.nval;
        }

        boolean[][] dp = new boolean[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i + 1]) {
                dp[i][i + 1] = true;
            }
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = a[i] == a[j] && dp[i + 1][j - 1];
            }
        }

        in.nextToken();
        int m = (int) in.nval;
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < m; q++) {
            in.nextToken();
            int s = (int) in.nval;
            in.nextToken();
            int e = (int) in.nval;
            sb.append(dp[s][e] ? 1 : 0).append('\n');
        }
        System.out.print(sb);
    }
}
