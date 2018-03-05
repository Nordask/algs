import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean grid[][];
    //                        main tree   virtual tree
    public WeightedQuickUnionUF connect, isFullConnect;
    private int virtualTopSite;
    private int virtualBottomSite;
    public int size = 0;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        grid= new boolean[n][n];
        for(int i=0; i<n;i++){
            for(int j=0; j<n; j++){
                grid[i][j]=false; //blocked
            }
        }
        size = n;
        connect = new WeightedQuickUnionUF(n*n + 2);
        isFullConnect = new WeightedQuickUnionUF(n*n + 1);//+2 for virtual sites (n*n)=top (n*n+1)=bottom
        virtualTopSite = n*n;
        virtualBottomSite = n*n + 1;
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col){
        if (row <= 0 || row > size)
            throw new IndexOutOfBoundsException("row out of bound");
        if (col<= 0 || col > size)
            throw new IndexOutOfBoundsException("column out of bound");
        grid[row-1][col-1]=true;

        if (row - 2 >= 0 && isOpen(row - 1, col)) { // left
            connect.union((row - 2) * size + col-1, (row-1) * size + col-1);
            isFullConnect.union((row - 2) * size + col-1, (row-1) * size + col-1);
        }

        if (row < size && isOpen(row+1, col)) { // righ
            connect.union((row-1) * size + col-1, (row) * size + col-1);
            isFullConnect.union((row-1) * size + col-1, (row) * size + col-1);
        }

        if (col - 2 >= 0 && isOpen(row, col - 1)) // up
        {
            connect.union((row-1) * size  + col-1, (row-1) * size + col - 2);
            isFullConnect.union((row-1) * size  + col-1, (row-1) * size + col - 2);
        }

        if (col< size && isOpen(row, col+1)) // down
        {
            connect.union((row-1) * size  + col-1, (row-1) * size + col);
            isFullConnect.union((row-1) * size  + col-1, (row-1) * size + col);
        }

        if(row==1)//top row?
        {
            connect.union((row - 1) * size + col - 1, virtualTopSite);
            isFullConnect.union((row - 1) * size + col - 1, virtualTopSite);
        }
        if(row==size)//bottom row?
        {
            connect.union((row - 1) * size + col - 1, virtualBottomSite);
        }
    }

    public boolean isOpen(int row, int col){return grid[row-1][col-1];}  // is site (row, col) open?

    // is site (row, col) full?
    public boolean isFull(int row, int col){
        if(isOpen(row, col)){
            return isFullConnect.connected(virtualTopSite, (row-1)*size + (col-1));
        }
        return false;
    }
    public int numberOfOpenSites(){
        int cnt=0;
        for(int i=1; i<=size;i++){
            for(int j=1; j<=size; j++){
                if (isOpen(i,j))
                    cnt++;
            }
        }
        return cnt;
    }

    public boolean percolates(){
        return connect.connected(virtualTopSite, virtualBottomSite);
    }
}