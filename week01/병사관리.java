public class 병사관리 {
    class Node {
        int mId;
        int mTeam;
        Node pre;
        Node nxt;

        public Node() {
        }

        public Node(int mId, int mTeam) {
            this.mId = mId;
            this.mTeam = mTeam;
            pre = null;
            nxt = null;
        }

    }

    Node[][] teams;
    Node[] info;
    Node[][] tails;

    public void init() {
        teams = new Node[6][6];
        tails = new Node[6][6];
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                Node head = new Node(-1,-1);
                Node tail = new Node(-2,-2);
                head.nxt = tail;
                tail.pre = head;
                teams[i][j] = head;
                tails[i][j] = tail;
            }
        }
        info = new Node[100001];
    }

    public void hire(int mID, int mTeam, int mScore) {
        info[mID] = new Node(mID, mTeam);
        insertNode(mID, mTeam, mScore);
    }

    private void insertNode(int mID, int mTeam, int mScore) {
        info[mID].pre = teams[mTeam][mScore];
        info[mID].nxt = teams[mTeam][mScore].nxt;
        teams[mTeam][mScore].nxt.pre = info[mID];
        teams[mTeam][mScore].nxt = info[mID];
    }

    public void fire(int mID) {
        info[mID].pre.nxt = info[mID].nxt;
        info[mID].nxt.pre = info[mID].pre;
    }

    public void updateSoldier(int mID, int mScore) {
        fire(mID);
        insertNode(mID, info[mID].mTeam, mScore);
    }

    /**
     * ‘변경 전 평판 점수 + mChangeScore’가 5보다 클 경우, 평판 점수를 5로 변경한다.
     * ‘변경 전 평판 점수 + mChangeScore’가 1보다 작을 경우, 평판 점수를 1로 변경한다.
     * 그 외의 경우, 평판 점수를 ‘변경 전 평판 점수 + mChangeScore’로 변경한다.
     **/
    public void updateTeam(int mTeam, int mChangeScore) {
        if (mChangeScore > 0) {
            for (int i = 5; i >= 1; i--) {
                if(teams[mTeam][i].nxt.nxt==null)continue;
                int score = i + mChangeScore;
                if (score > 5) {
                    score = 5;
                } else if (score < 1) {
                    score = 1;
                }
                if(score != i){
                    insertList(mTeam,i,score);
                }
            }
        } else {
            for (int i = 1; i <= 5; i++) {
                if(teams[mTeam][i].nxt.nxt==null)continue;
                int score = i + mChangeScore;
                if (score > 5) {
                    score = 5;
                } else if (score < 1) {
                    score = 1;
                }
                if(score != i){
                    insertList(mTeam,i,score);
                }
            }
        }
    }

    private void insertList(int mTeam, int i, int num) {
        teams[mTeam][i].nxt.pre = teams[mTeam][num];
        tails[mTeam][i].pre.nxt = teams[mTeam][num].nxt;
        teams[mTeam][num].nxt.pre = tails[mTeam][i].pre;
        teams[mTeam][num].nxt = teams[mTeam][i].nxt;
        teams[mTeam][i].nxt = tails[mTeam][i];
        tails[mTeam][i].pre = teams[mTeam][i];
    }

    /**
     * 소속팀이 mTeam인 병사들 중 평판 점수가 가장 높은 병사의 고유번호를 반환한다.
     * 평판 점수가 가장 높은 병사가 여러 명일 경우, 고유번호가 가장 큰 병사의 고유번호를 반환한다.
     **/
    public int bestSoldier(int mTeam) {
        int id = 0;
        for (int i = 5; i >= 1; i--) {
            for(Node iter = teams[mTeam][i].nxt; iter.nxt!=null;iter = iter.nxt){
                if (id < iter.mId) {
                    id = iter.mId;
                }
            }
            if(id!=0)return id;
        }
        return id;
    }
}