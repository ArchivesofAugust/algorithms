import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'A']++;
        }

        int oddCount = 0, oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                oddCount++;
                oddChar = i;
            }
        }

        if (oddCount > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder half = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < freq[i] / 2; k++) {
                half.append((char) ('A' + i));
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(half);
        if (oddChar != -1) {
            result.append((char) ('A' + oddChar));
        }
        result.append(half.reverse());

        System.out.println(result);
    }
}
