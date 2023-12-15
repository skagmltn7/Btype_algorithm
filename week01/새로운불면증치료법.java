import java.io.*;

// 비트마스킹
// 2^10-1면 모두 탐색완료
// K%10로 자릿수 검사
// 112ms
public class 새로운불면증치료법 {
    static final int ALLFIND = (1 << 10) - 1;
    static int chk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int ans = 1;
            chk = 0;
            int N = Integer.parseInt(br.readLine());
            while (chk != ALLFIND) {
                chkNum(N * ans++);
            }
            sb.append('#').append(tc).append(" ").append((ans - 1) * N).append("\n");
        }
        System.out.println(sb.toString());
    } // end of main

    private static void chkNum(int n) {
        while (n != 0) {
            chk |= 1 << (n % 10);
            n /= 10;
        }
    }
} // end of class
