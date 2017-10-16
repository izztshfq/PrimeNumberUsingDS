
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ser extends Thread implements Runnable {
	
	public static int MAXTHREAD; 
	public static int PRIME_UPPER_RANGE;
	public static int PRIMECOUNT = 0;
	public static List<Integer> primeArray = new ArrayList<>();
	/* Use static variable to ensure variable is available throughout the running of program*/
	
	public static void main(String args[]) throws IOException,ClassNotFoundException
	{
		int primerange, threadcount;
		
		System.out.println("  ____       _                      _                  ");
		System.out.println(" |  _ \\ _ __(_)_ __ ___   ___      | | __ ___   ____ _ ");
		System.out.println(" | |_) | '__| | '_ ` _ \\ / _ \\  _  | |/ _` \\ \\ / / _` |");
		System.out.println(" |  __/| |  | | | | | | |  __/ | |_| | (_| |\\ V / (_| |");
		System.out.println(" |_|   |_|  |_|_| |_| |_|\\___|  \\___/ \\__,_| \\_/ \\__,_|");
		System.out.println("                                           SERVER  v.0.1 ");
		System.out.println("Ready for Connection");
		ServerSocket socketServer = new ServerSocket(9999); //Specifying port number
		
		while (true) {
			
			Socket socketReq = socketServer.accept(); //Listens for a connection to be made to this socket and accepts it.
			Scanner sc = new Scanner(socketReq.getInputStream()); //Returns an InputStream representing the data
			
			primerange = sc.nextInt();
			threadcount = sc.nextInt();
			
			PRIME_UPPER_RANGE = primerange;
			MAXTHREAD = threadcount;
			
			System.out.println(socketReq); //Outputs client details (i.e. IP Address, port)
			System.out.println("Client Connected.");
			
			ExecutorService exe = Executors.newFixedThreadPool(MAXTHREAD);
			long t0 = System.currentTimeMillis();
			for (int i = 0; i < PRIME_UPPER_RANGE; i++) {
				Runnable process = new RunnableFunc(i);
				exe.execute(process);
			}
			long t1 = System.currentTimeMillis();
			exe.shutdown();
			// Wait until all threads are finish
			while (!exe.isTerminated()) {

			}
			
			System.out.println("\nFinished all threads");
			long executionTime = t1-t0;
			String executionTimeString = Long.toString(executionTime); //converts long variable to string
			
			System.out.printf("Process completed in %s ms.\n", executionTimeString);
			PrintStream p = new PrintStream(socketReq.getOutputStream());
			
			p.println(executionTimeString);
			p.println(primeArray);
		}

	}

	public static class RunnableFunc extends Thread implements Runnable {
		int num = PRIMECOUNT;
		RunnableFunc(int n){
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
            PRIMECOUNT++;
            
            
            if(isPrime(num)) {
                System.out.println("Prime No Found: "+num+" Thread Name:"+Thread.currentThread().getName());
                primeArray.add(num);
            }
            else {
            	PRIMECOUNT++;
            }
            
        }
	
	}
}
