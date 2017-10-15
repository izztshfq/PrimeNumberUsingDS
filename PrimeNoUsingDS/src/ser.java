import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ser extends Thread implements Runnable {
	
	public static int MAX_NO_THREADS;
	public static int MAX_RANGE_PRIME;
	public static int NO_PRIME_TEST = 0;
	
	public static void main(String args[]) throws IOException,ClassNotFoundException
	{
		int number,temp;
		int primerange, threadcount;
		ServerSocket s1=new ServerSocket(1342); 		//specify port number
		Socket ss=s1.accept(); 							//accept request from client
		Scanner sc=new Scanner(ss.getInputStream()); 	//accept number from client
		primerange=sc.nextInt();
		threadcount = sc.nextInt();
		
		MAX_RANGE_PRIME = primerange;
		MAX_NO_THREADS = threadcount;
		
		System.out.println(primerange);
		System.out.println(threadcount);
		
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
		
		
//		temp=number*2;
		
		PrintStream p=new PrintStream(ss.getOutputStream());
//		p.println(temp);		
	}

	public static class MyRunnable extends Thread implements Runnable {
		int num = NO_PRIME_TEST;
		MyRunnable(int n){
			num = n;
		}
		
		boolean isPrime(int n) { 
		// conditions for prime number test
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
