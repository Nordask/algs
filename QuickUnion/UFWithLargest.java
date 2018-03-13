public class UFWithLargest {
    private int[] parent;  // parent[i] = parent of i
    private int count;     // number of components
    private int largest[];


    public UFWithLargest(int n) {
        parent = new int[n];
        largest = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            largest[i] = i;
        }
    }

    public int find(int p) {
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    public int findLargest(int p) {
        while (p != largest[p])
            p = largest[p];
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;

        if(rootP > rootQ)
            largest[rootQ] = rootP;
        else
            largest[rootP] = rootQ;
    }

    public static void main(String args[]){
        UFWithLargest lg = new UFWithLargest(100);

        lg.union(1,2);
        lg.union(69,6);
        lg.union(2,6);
        lg.union(33,58);
        lg.union(69,58);
        lg.union(33,99);

        System.out.println(lg.findLargest(2));
    }
}

