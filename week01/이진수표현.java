import java.io.*;
import java.util.*;

// 비트자릿수비교
// 144ms
public class 이진수표현 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sb.append("#").append(tc).append(" ").append(chk(N, M) ? "ON" : "OFF").append("\n");
        }
        System.out.println(sb);
    } // end of main

    private static boolean chk(int n, int m) {
        return (m & (1 << n) - 1) == (1 << n) - 1;
    }
} // end of class
