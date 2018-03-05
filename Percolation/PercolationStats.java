import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double mean;    // sample mean of percolation threshold
    private double stddev;  // sample standard deviation of percolation threshold
    private double confidenceLo;    // low  endpoint of 95% confidence interval
    private double confidenceHi;    // high endpoint of 95% confidence interval
    public PercolationStats(int n, int trials){
        if(n <= 0){
            throw new IllegalArgumentException("Grid size must be more then 0");
        }

        if(trials <= 0){
            throw new IllegalArgumentException("Number of experiments must be more then 0");
        }

        //StdStats experiments;
        //StdRandom randomizer
        double []experimentArray = new double[trials];

        int experimentsCounter = 0;
        do{
            Percolation test = new Percolation(n);
            //StdRandom.setSeed(n);
            for (int i = 0; i < StdRandom.uniform(1,n*n); i++){
                test.open(StdRandom.uniform(1, n),StdRandom.uniform(1, n));
            }
            experimentArray[experimentsCounter]= (double)test.numberOfOpenSites()/(double)(n*n);
            experimentsCounter++;
        }while(experimentsCounter < trials);

        mean = StdStats.mean(experimentArray);
        stddev = StdStats.stddev(experimentArray);
        confidenceLo = mean - (1.96*stddev)/Math.sqrt(trials);
        confidenceHi = mean + (1.96*stddev)/Math.sqrt(trials);

    }

    public double mean()    {return mean;}  //average value
    public double stddev()  {return stddev;}    //standard deviation
    public double confidenceLo()    {return confidenceLo;}
    public double confidenceHi()    {return confidenceHi;}

    public static void main(String[] args)
    {
        Stopwatch stp = new Stopwatch();
        PercolationStats results = new PercolationStats(10, 100);
        System.out.println(stp.elapsedTime());
        System.out.println("mean = " + results.mean());
        System.out.println("stddev = " + results.stddev());
        System.out.println("confidenceLo = " + results.confidenceLo());
        System.out.println("confidenceHi = " + results.confidenceHi());
    }

}