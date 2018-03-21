package first_week.QuickUnion;
import edu.princeton.cs.algs4.QuickUnionUF;
import java.util.Random;

public class connectivity {

    private QuickUnionUF friendship;
    private final int size;
    private int counter;

    public connectivity(int n){
        friendship = new QuickUnionUF(n);
        size = n;
    }

    public void union(int p, int q){
        if(!friendship.connected(p,q))
            counter++;
        friendship.union(p,q);
    }

    public boolean areAllFriends(){
        return counter == (size-1);
    }

    public static void main(String args[]){
        int number = 100;
        connectivity con = new connectivity(number);
        Random rand = new Random();
        int count = 0;
        while(!con.areAllFriends()){
            con.union(rand.nextInt(number), rand.nextInt(number));
            count++;
        }
        System.out.println(count);
    }

}
