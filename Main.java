/*
Adam Johnson
awj160030
CS 4365.002
 */

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        File input = new File("input.txt");
        Scanner x = new Scanner(input);

        int[][] puzzle = new int[3][3]; //puzzle = initial state
        for (int i = 0; i < 3; i++) {
            for (int b = 0; b < 3; b++) {
                int temp = x.nextInt();
                puzzle[b][i] = temp;
            }
        }

        Node root = new Node(puzzle);
        Tree lul = new Tree(root);
        rbfsHelper(lul);



    }

    public static void rbfsHelper(Tree pineapple)
    {
        Node x = pineapple.getRoot();
        x.setsecbest(100000);
        rbfs (x, pineapple);
    }

    private static void rbfs(Node x, Tree tree)
    {
        if (x==null)
        {return;}


        if (x.getdist() == 0)
        {helpPrint(x);
        return;}

        if(x.getVisit() == false) {
            tree.getfrontier(x); // only finds the frontier if it hasn't
            x.setVisit(true);
        }


        Node left = null;
        Node up = null;
        Node right = null;
        Node down = null;
        int l = 1000,r = 1000,u = 1000,d = 1000; //hold the distances to the goal,
        // set to high values that won't be used


        if (x.getLeft()!=null)
        {
            left = x.getLeft();
            if(!comparePuz(tree.getRoot(), left))
            {
                l=left.getdist();
            }
            else
            {
                x.setLeft(null);
            }
        }

        if (x.getRight()!=null)
        {
            right = x.getRight();
            if(!comparePuz(tree.getRoot(), right))
            {
                r=right.getdist();
            }
            else
            {
                x.setRight(null);
            }
        }

        if (x.getUp()!=null)
        {
            up = x.getUp();
            if(!comparePuz(tree.getRoot(), up)) {

                u = up.getdist();
            }
            else
            {
                x.setUp(null);
            }
        }

        if (x.getDown()!=null)
        {
            down = x.getDown();
            if(!comparePuz(tree.getRoot(), down)) {

                d = down.getdist();
            }
            else
            {
                x.setDown(null);
            }
        }

        int lowest, seclow;
        lowest = FindLowest(l,r,u,d);
        seclow = secLowest(l,r,u,d);

        //call function again with the lowest value node


        if(x.getsecbest() < seclow)
        {
            seclow = x.getsecbest();
        }

        if (x.getsecbest() < lowest)
        {
            x.setdist(lowest);
            rbfs(x.getPar(), tree);

        }

        else if(lowest == l)
        {
            left.setsecbest(seclow);
            rbfs(x.getLeft(), tree);
        }
        else if (lowest == r)
        {
            right.setsecbest(seclow);
            rbfs(x.getRight(), tree);
        }
        else if (lowest == u)
        {
            up.setsecbest(seclow);
            rbfs(x.getUp(), tree);
        }
        else if (lowest == d)
        {
            down.setsecbest(seclow);
            rbfs(x.getDown(), tree);
        }

        return;
    }

    public static int FindLowest(int l, int r, int u, int d)
    {
        int low = l;
        if (low > r)
            low = r;
        if(low > u)
            low = u;
        if(low > d)
            low = d;

        return low;
    }

    public static int secLowest(int l, int r, int u, int d)
    {
        int low, seclow;
        low = l;
        if (low > r) // r is lowest
        {
            seclow = low;
            low =r;
        }
        else // r is second lowest
        {
            seclow = r;
        }

        if (seclow > u)
        {
            if (low > u) // u is new lowest
            {
                seclow = low;
                low = u;
            }
            else // u is second lowest
            {
                seclow = u;
            }
        }

        if (seclow > d)
        {
            if (low > d) // d is new lowest
            {
                seclow = low;
                low = d;
            }
            else // d is second lowest
            {
                seclow = d;
            }
        }

        return seclow;
    }

    public static void helpPrint(Node x) {
        Stack stack = new Stack();
        LoadStack(x, stack);
    }
    public static void LoadStack (Node x, Stack<Node> stack) // will be the goal node
    {
        stack.push(x);
        if (x.getPar()!= null)
            LoadStack(x.getPar(), stack);
        else // parent is null so we are at the root
        {
            System.out.println("Printing");
            print(stack);
        }

    }

    private static void print(Stack<Node> x)
    {
        System.out.println("Num moves = " +(x.size()-1));
        while (!x.empty())
        {
            Node bub = x.pop();
            int [][] puz = bub.getpuz();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(puz[j][i] + "   ");
                }
                System.out.println();
            }
            System.out.println();
        }


    }

    private static boolean comparePuz( Node x, Node bby)
    {
        if (x == bby)
        {
            return false;
        }
        int [][] one = x.getpuz();
        int [][] two = bby.getpuz();

        boolean same = false;
        int count = 0;
        for(int i = 0; i <3; i ++) // compares to parent's parent aka grandparent
        {
            for (int j = 0; j<3; j++)
            {
                if(one[j][i] == two[j][i])
                    count ++;
            }
        }
        if (count == 9)
            return true;
        if(x.getLeft()!=null)
            same = comparePuz(x.getLeft(), bby);
        if (same)
            return same;
        if(x.getRight() !=null)
            same =comparePuz((x.getRight()),bby);
        if (same)
            return same;
        if(x.getUp()!=null)
            same = comparePuz(x.getUp(),bby);
        if (same)
            return same;
        if(x.getDown()!=null)
            same = comparePuz(x.getDown(), bby);
        if (same)
            return same;

        same = false;
        return same;

        /*
        if (x==null)
            return false;
        boolean same = true;
        int[][] two = x.getpuz();

        for(int i = 0; i <3; i ++) // compares to parent's parent aka grandparent
        {
            for (int j = 0; j<3; j++)
            {
                if(one[j][i] != two[j][i])
                    same = false;
            }
        }

        return same;
        */


    }

    private static boolean compareSec(Node par, Node child)
    {
        boolean found = false;

        int pardist = par.getsecbest();
        int childdist = child.getdist();
        if(pardist ==childdist)
            found = true;

        return found;
    }


}


