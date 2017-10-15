import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedPrimeExecutor {

    private static int MAX_THREADS;
    private static int MAX_LIMIT;
    private static int numToTest = 0;

    public static void main(String[] args) {

        int max_threads = Integer.parseInt(args[0]);
        int max_limit = Integer.parseInt(args[1]);

        MAX_THREADS = max_threads;
        MAX_LIMIT = max_limit;

        GeneratePrime();
}



private static void GeneratePrime() {

    class PrimeNumberGen implements Runnable {

        int num = numToTest;

        PrimeNumberGen(int n) {
            num = n;
        }

        boolean isPrime(int n) { //first test is 0
            if(n<2) return false;
            if(n==2) return true;
            if(n%2==0) return false;

            int max = n/2;
            for(int i=3; i< max; i=i+2) {
                if (n % i == 0)
                    return false;
            }
                    return true;
        }

        public void  run() {
            numToTest++;
            if(isPrime(num)) {
                System.out.println("Prime Number: "+num+" Thread #:"+Thread.currentThread().getId());
            }
            else {
                numToTest++;
            }

        }
    }

    ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    for (int i = 0;i <= MAX_LIMIT; i++) {
        Runnable worker = new PrimeNumberGen(numToTest);
        executor.execute(worker);
    }


 }
}