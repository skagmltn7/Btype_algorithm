package week02;

import java.io.*;
import java.util.StringTokenizer;

public class 중위순회 {
    static StringBuilder ans=  new StringBuilder();;
    static char[] tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            N = Integer.parseInt(br.readLine());
            tree = new char[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int th = Integer.parseInt(st.nextToken());
                tree[th] =  st.nextToken().charAt(0);
            }
            inorder(1);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
            ans.setLength(0);
        }
        System.out.println(sb);
    } // end of main

    private static void inorder(int inx) {
        if(2*inx<=N) inorder(2 * inx);
        ans.append(tree[inx]);
        if(2*inx+1<=N) inorder(2 * inx + 1);
    }
} // end of class
