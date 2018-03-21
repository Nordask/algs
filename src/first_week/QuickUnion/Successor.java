package first_week.QuickUnion;

import edu.princeton.cs.algs4.QuickUnionUF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Successor {
    public QuickUnionUF suc;
    private final int size;
    public Successor (int n){
        suc = new QuickUnionUF(n);
        size = n;
    }

    public void remove(int x){
        if(x>=(size - 1)) {
            throw new IllegalArgumentException("Wrong argument");
        }
        suc.union(x, x + 1);
    }

    public int getSuccessor(int x){
        if(x>=size) {
            throw new IllegalArgumentException("Wrong argument");
        }
        return suc.find(x);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = null;
        Successor suc = null;

        try {

            br = new BufferedReader(new InputStreamReader(System.in));
            boolean isFirst = true;
            while (true) {

                String input = br.readLine();

                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }
                int x = Integer.parseInt(input);
                System.out.println("input : " + input);
                if(isFirst) {
                    suc = new Successor(x);
                    isFirst = false;
                }
                else{
                    suc.remove(x);
                    System.out.println("Successor of " + x + ": " + suc.getSuccessor(x));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
