import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;


public class PrimeNumberWithExecutorService{
	public static int MAX_NO_THREADS;
	public static int MAX_RANGE_PRIME;
	public static int NO_PRIME_TEST = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Find Prime No within Range + Multithreaded");
		System.out.println("Input maximum range of prime number :");
		//error handling for max range INT
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a valid range number. Range must be integer");
			sc.next();	
		} 
		MAX_RANGE_PRIME = sc.nextInt();

		System.out.println("Input no of threads you want the process to run :");
		//error handling for max range INT
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a valid number. No of threads must be integer");
			sc.next();	
		} 
		MAX_NO_THREADS = sc.nextInt();

		ExecutorService executor = Executors.newFixedThreadPool(MAX_NO_THREADS);
		for (int i=0; i < MAX_RANGE_PRIME; i++)	{
			Runnable process = new MyRunnable(i);
			executor.execute(process);
		}
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
 
		}
		System.out.println("\nFinished all threads");
	}

	public static class MyRunnable implements Runnable {
		int num = NO_PRIME_TEST;
		MyRunnable(int n){
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
            NO_PRIME_TEST++;
            if(isPrime(num)) {
                System.out.println("Prime Number: "+num+" Thread Name:"+Thread.currentThread().getName());
            }
            else {
            	NO_PRIME_TEST++;
            }

        }
		


		
	}


}
