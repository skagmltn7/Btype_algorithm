package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Knapsack {
    static class Bag implements Comparable<Bag>{
        int v;
        int c;
        public Bag(int v, int c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Bag o) {
            return this.c - o.c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Bag[] bags;
        int[] dp;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            bags = new Bag[N];
            int maxCost = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                bags[i]=new Bag(v,c);
                if(maxCost<c) maxCost = c;
            }
            Arrays.sort(bags);
            dp = new int[K];
            for (int i = 0; i < N; i++) {
                for (int j = K; j >= bags[i].v; j--) {
                    dp[j] = Math.max(dp[j], dp[j-bags[i].v]+bags[i].c);
                }
            }
            int ans = 0;
            for (int i = 1; i <= K; i++) {
                ans = Math.max(dp[i], ans);
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }// end of main
} // end of class
