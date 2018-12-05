public class Node {

    private boolean visited = false;
    private int[][] puzstate = new int [3][3];
    private int [][] goal = new int [3][3];

    private int dist=0;

    private int secbest = -1; // will store the next best node to go to

    private Node left, right, up, down, parent;

    Node(int [][] x) {
        puzstate = x;
        left = null;
        right = null;
        up = null;
        parent = null;
        down = null;

        int count = 0;
        for (int i = 0; i < 3; i++) {  // sets the goal state
            for (int j = 0; j < 3; j++) {
                goal[j][i] = count;
                count++;
            }
        }

        setdist(); // initializes the distance
    }

    public boolean getVisit()
    {return visited;}
    public void setVisit(Boolean x)
    {
        visited = x;
    }
    public int [][] getpuz()
    {return puzstate; }

    public void setpuz(int x[][])
    {puzstate = x;
    setdist();}

    public void setdist(int x)
    {dist = x;}
    public void setdist() // sets distance to goal
    {
        for (int val = 1; val < 9; val++) {
            int pstate[] = new int[2]; // position column = 0, row = 1 //puzzle state
            int gstate[] = new int[2]; // goal state

            pstate = location(puzstate, val);
            gstate = location(goal, val);
            dist += Math.abs(pstate[0]-gstate[0])+ Math.abs(pstate[1]-gstate[1]);
    }
    }

    private int [] location (int [][] state, int val) // finds location to get distance
    {
        int []loc = new int []{-1,-1}; // value can't be -1 serves as debug

        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j< 3; j++)
            {
                if (state[j][i] == val)
                {
                    loc[0] = j;
                    loc[1] = i;
                }
            }
        }
        return loc;
    }

    public int getdist()
    {return dist;}


    public Node getLeft()
    {return left;}

    public Node getRight()
    {return right;}

    public Node getUp()
    {return up;}

    public Node getDown()
    {return down;}

    public void setUp(Node n)
    {up = n;}

    public void setDown(Node n)
    {down =n;}
    public void setLeft(Node n)
    {left = n;}

    public void setRight(Node n)
    {right = n;}

    public int getsecbest(){
    return secbest;}

    public void setsecbest(int x)
    {secbest = x;}

    public void setpar(Node x)
    {parent = x;}

    public Node getPar()
    {return parent;}
}
