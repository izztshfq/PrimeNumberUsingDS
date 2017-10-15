import java.util.Scanner;
//TODO  Computes the number of primes less than or equal to n using Sieve of Eratosthenes.
public class PrimeSiev
{
    /** Function to calculate all primes less than n **/
    private int[] calcPrimes(int N)
    {
        int[] arr = new int[N + 1];
        for (int i = 2; i <= Math.sqrt(N); i++)
        {
            if (arr[i] == 0)
            {
                for (int j = i * i; j <= N; j += i)
                {
                    arr[j] = 1;
                }
            }
        }
        return arr;
    }
    /** Function to get all primes **/
    public void getPrimes(int N)
    {
        int[] primes = calcPrimes(N);
        display(primes);
    }
    /** Function to display all primes **/
    public void display(int[] primes)
    {
        System.out.print("\nPrimes = ");
        for (int i = 2; i < primes.length; i++)
            if (primes[i] == 0)
                System.out.print(i +" ");
        System.out.println();
    }
    /** Main function **/
    public static void main (String[] args) 
    {
        System.out.println("Finding Prime No within Range\n");
        /** Make an object of SieveOfEratosthenes class **/
        PrimeSiev soe = new PrimeSiev();
        /** Accept n **/
        System.out.println("Enter number to find all primes less than the number\n");
        int n = new Scanner(System.in).nextInt();
        soe.getPrimes(n);        
    }
}

