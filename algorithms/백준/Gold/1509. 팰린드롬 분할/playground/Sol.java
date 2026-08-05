import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        int n = s.length();

        boolean[][] isPal = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPal[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            isPal[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                isPal[i][j] = s.charAt(i) == s.charAt(j) && isPal[i + 1][j - 1];
            }
        }

        int[] cut = new int[n + 1];
        final int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(cut, INF);
        cut[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (isPal[i][j]) {
                    cut[i] = Math.min(cut[i], 1 + cut[j + 1]);
                }
            }
        }

        System.out.println(cut[0]);
    }
}
