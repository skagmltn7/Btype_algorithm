package week02;

import java.io.*;
import java.util.*;

public class 파핑파핑지뢰찾기 {
    static final int[] DY = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] DX = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int cnt, N, noBomb;
    static char[][] map;
    static int[][] cntBomb;
    static Queue<Point> queue = new ArrayDeque<>();
    static boolean[][] visited;
    static ArrayDeque<Point> zero = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N + 2][N + 2];
            visited = new boolean[N + 2][N + 2];
            cntBomb = new int[N + 2][N + 2];
            noBomb = 0;
            for (int i = 1; i <= N; i++) {
                String st = br.readLine();
                for (int j = 1, inx = 0; j <= N; j++, inx++) {
                    map[i][j] = st.charAt(inx);
                    if (map[i][j] == '.') noBomb++;
                }
            }
            countBomb();
            cnt = 0;
            while (!zero.isEmpty()) {
                Point cur = zero.pop();
                if (!visited[cur.y][cur.x]) {
                    cnt++;
                    queue.offer(cur);
                    startGame();
                }
            }
            sb.append("#").append(tc).append(" ").append(cnt + noBomb).append("\n");
        }
        System.out.println(sb);
    } // end of main

    private static void countBomb() {
        int bomb;
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (map[y][x] == '.') {
                    bomb = 0;
                    for (int i = 0; i < 8; i++) {
                        int dy = y + DY[i];
                        int dx = x + DX[i];
                        if (dy == 0 || dy == N + 1 || dx == 0 || dx == N + 1) continue;
                        if (map[dy][dx] == '*') bomb++;
                    }
                    cntBomb[y][x] = bomb;
                    if (bomb == 0) zero.push(new Point(y, x));
                }
            }
        }
    }

    private static void startGame() {
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (visited[cur.y][cur.x]) continue;
            visited[cur.y][cur.x] = true;
            noBomb--;
            for (int i = 0; i < 8; i++) {
                int dy = cur.y + DY[i];
                int dx = cur.x + DX[i];
                if (!visited[dy][dx] && map[dy][dx] == '.' && cntBomb[cur.y][cur.x] == 0)
                    queue.offer(new Point(dy, dx));
            }
        }
    }
} // end of class
