package week02;

import java.io.*;
import java.util.*;


public class 공통조상 {
    static class Node {
        Node pre;
        int h;
        int n;
        Node left;
        Node right;
    }

    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree = new Node[V + 1];
            for (int i = 0; i <= V; i++) {
                tree[i] = new Node();
            }
            tree[1].n = 1;
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                tree[child].pre = tree[parent];
                tree[child].n = child;
                if (tree[parent].left == null) tree[parent].left = tree[child];
                else tree[parent].right = tree[child];
            }
            getHeight(tree[1], -1);
            int diff = tree[a].h - tree[b].h;
            if (diff > 0) {
                while (diff-- != 0) {
                    a = tree[a].pre.n;
                }
            } else {
                while (diff++ != 0) {
                    b = tree[b].pre.n;
                }
            }
            while (a != b) {
                a = tree[a].pre.n;
                b = tree[b].pre.n;
            }
            sb.append("#").append(tc).append(" ").append(a).append(" ").append(a == 1 ? V : travel(tree[a])).append("\n");
        }
        System.out.println(sb);
    } // end of main

    private static int travel(Node a) {
        if (a == null) return 0;
        return 1 + travel(tree[a.n].left) + travel(tree[a.n].right);
    }

    private static void getHeight(Node a, int h) {
        if (a == null) return;
        tree[a.n].h = h + 1;
        getHeight(tree[a.n].left, h + 1);
        getHeight(tree[a.n].right, h + 1);
    }
} // end of class
