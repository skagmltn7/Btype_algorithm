import java.io.*;
import java.util.*;

public class 수열편집 {
    private static ArrayList<Integer> list = new ArrayList<>();
    private static int N, M, L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                char command = st.nextToken().charAt(0);
                int x = Integer.parseInt(st.nextToken());
                switch (command) {
                    case 'I':
                        list.add(x, Integer.parseInt(st.nextToken()));
                        break;
                    case 'D':
                        list.remove(x);
                        break;
                    case 'C':
                        list.set(x, Integer.parseInt(st.nextToken()));
                        break;
                }
            }
            sb.append("#").append(tc).append(" ").append(list.size() < L ? -1 : list.get(L)).append("\n");
            list.clear();
        }
        System.out.println(sb);
    } // end of main
} // end of class
