public class Tree {

    Node root = null;

    Tree(Node n)
    {root = n;}


    public Node getRoot(){return root;}

    public void setRoot (Node n) {root = n;}

    public void getfrontier(Node x)
    {
        int [][] puzstate = x.getpuz();
        int []coor = findZero(puzstate);

        makeLeft(x, coor);
        if(x.getLeft() == x.getPar())
            x.setLeft(null);
        makeRight(x,coor);
        if(x.getRight() == x.getPar())
            x.setRight(null);
        makeDwn(x,coor);
        if(x.getDown() == x.getPar())
            x.setDown(null);
        makeUp(x,coor);
        if(x.getUp() == x.getPar())
            x.setUp(null);

    }

    private int [] findZero(int [][] puzstate)
    {
        int [] coor = new int []{-1,-1}; // coordinates o.g invalid
        for(int i = 0; i < 3; i ++)
        {
            for (int j =0; j <3; j++)
            {
                if (puzstate[j][i] == 0)
                {
                    coor[0] = j;
                    coor [1] = i;
                }
            }
        }
        return coor;
    }

    private void makeLeft(Node x, int []zero)
    {
        if (zero[0] == 0)
        {
            x.setLeft(null);
            return;
        }

        int [][] temp = x.getpuz();
        int [][] puzstate = new int[3][3];
        for(int i = 0; i <3; i++)
        {
            for (int j =0; j <3; j++)
            {
                puzstate[j][i] = temp[j][i];
            }
        }
        puzstate[zero[0]][zero[1]] = puzstate[zero[0] -1][zero[1]]; // sets 0 area to place it moves to
        puzstate[zero[0] -1][zero[1]] = 0; //set new area to zero
        Node y= new Node(puzstate);
        //print(y);
        x.setLeft(y);
        y.setpar(x);

    }

    private void makeRight(Node x, int []zero)
    {
        if (zero[0] == 2)
        {
            x.setRight(null);
            return;
        }
        int [][] temp = x.getpuz();
        int [][] puzstate = new int[3][3];
        for(int i = 0; i <3; i++)
        {
            for (int j =0; j <3; j++)
            {
                puzstate[j][i] = temp[j][i];
            }
        }
        puzstate[zero[0]][zero[1]] = puzstate[zero[0] +1][zero[1]];
        puzstate[zero[0] +1][zero[1]] = 0;

        Node y= new Node(puzstate);
        //print(y);
        x.setRight(y);
        y.setpar(x);
    }

    private void makeUp(Node x, int []zero)
    {
        if (zero[1] == 0)
        {
            x.setUp(null);
            return;
        }
        int [][] temp = x.getpuz();
        int [][] puzstate = new int[3][3];
        for(int i = 0; i <3; i++)
        {
            for (int j =0; j <3; j++)
            {
                puzstate[j][i] = temp[j][i];
            }
        }
        puzstate[zero[0]][zero[1]] = puzstate[zero[0]][zero[1]-1];
        puzstate[zero[0]][zero[1]-1] = 0;

        Node y= new Node(puzstate);
        //print(y);
        x.setUp(y);
        y.setpar(x);
    }

    private void makeDwn(Node x, int []zero)
    {
        if (zero[1] == 2)
        {
            x.setDown(null);
            return;
        }
        int [][] temp = x.getpuz();
        int [][] puzstate = new int[3][3];
        for(int i = 0; i <3; i++)
        {
            for (int j =0; j <3; j++)
            {
                puzstate[j][i] = temp[j][i];
            }
        }
        puzstate[zero[0]][zero[1]] = puzstate[zero[0]][zero[1]+1];
        puzstate[zero[0]][zero[1]+1] = 0;

        Node y= new Node(puzstate);
        //print(y);
        x.setDown(y);
        y.setpar(x);
    }

    public void print(Node x)
    {
        if (x==null)
        {return;}
        int [][] puz = x.getpuz();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(puz[j][i] + "   ");
            }
            System.out.println();
        }
        System.out.println("Distance = " + x.getdist());
        System.out.println();

    }
}
