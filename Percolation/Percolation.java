import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean grid[][];
    public WeightedQuickUnionUF connect;
    public int size = 0;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        grid= new boolean[n][n];
        for(int i=0; i<n;i++){
            for(int j=0; j<n; j++){
                grid[i][j]=false; //blocked
            }
        }
        size = grid.length;
        connect = new WeightedQuickUnionUF(n*n);
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col){
        grid[row-1][col-1]=true;

        if (row - 2 >= 0 && isOpen(row - 1, col)) { // up
            connect.union((row - 2) * size+ col-1, (row-1) * size + col-1);
        }

        if (row < size && isOpen(row+1, col)) { // down
            connect.union((row-1) * size + col-1, (row) * size + col-1);
        }

        if (col - 2 >= 0 && isOpen(row, col - 1)) // left
        {
            connect.union((row-1) * size  + col-1, (row-1) * size + col - 2);
        }

        if (col< size && isOpen(row, col+1)) // right
        {
            connect.union((row-1) * size  + col-1, (row-1) * size + col);
        }
    }

    public boolean isOpen(int row, int col){return grid[row-1][col-1];}  // is site (row, col) open?

    // is site (row, col) full?
    public boolean isFull(int row, int col){
        if(isOpen(row,col))
        {
            for(int i = 0; i < size; i++)
            {

                if(connect.connected((row-1)*size+(col-1),i)) return true;
            }
        }
        return false;
    }
    public int numberOfOpenSites(){
        int cnt=0;
        for(int i=0; i<size;i++){
            for(int j=0; j<size; j++){
                if (isOpen(i,j))
                    cnt++;
            }
        }
        return cnt;
    }

    public boolean percolates(){
        for (int i = (size * (size - 1)); i < (size * size); i++) {
            for (int j = 0; j < size; j++) {
                if (connect.connected(i, j)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int ff[][]=new int[10][10];
        System.out.println("sdaohasdigf" + ff.length);

        WeightedQuickUnionUF s =new WeightedQuickUnionUF(25);

        s.union(2,5);

        Percolation qq =new Percolation(5);
        qq.open(1,2);


    }

}