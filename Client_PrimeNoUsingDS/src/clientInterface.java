import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class clientInterface {

	public static void main(String args[]) throws UnknownHostException, IOException
	{
		int number,temp;
		int primerange, threadcount;
		
		Scanner sc= new Scanner(System.in); //Scanner function for client input
		Socket s = new Socket("127.0.0.1",1342); // Define server host & port numnber
		Scanner sc1= new Scanner(s.getInputStream()); //Scanner function to be sent to server as request
		
//		PrintWriter out = new PrintWriter(s.getOutputStream(),true); //
		
		
		
		System.out.println("  ____       _                      _                  ");
		System.out.println(" |  _ \\ _ __(_)_ __ ___   ___      | | __ ___   ____ _ ");
		System.out.println(" | |_) | '__| | '_ ` _ \\ / _ \\  _  | |/ _` \\ \\ / / _` |");
		System.out.println(" |  __/| |  | | | | | | |  __/ | |_| | (_| |\\ V / (_| |");
		System.out.println(" |_|   |_|  |_|_| |_| |_|\\___|  \\___/ \\__,_| \\_/ \\__,_|");
		System.out.println("                                                   v.0.1 ");
		System.out.println("Find Prime No within Range with Multithread");
		System.out.println("Input maximum range of prime number :");
		
		//error handling for max prime no range INT
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a valid range number. Range must be integer");
			sc.next();	
			}		
		primerange= sc.nextInt();
		
		
		System.out.println("Input no of threads you want the process to run :");
		//error handling for thread count INT
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a valid number. No of threads must be integer");
			sc.next();	
		} 
		threadcount = sc.nextInt();
		
		
		
	
		
		PrintStream p= new PrintStream(s.getOutputStream());
		p.println(primerange);
		p.println(threadcount);
//		temp=sc1.nextInt();
//		System.out.println(temp);
		
	}
}
