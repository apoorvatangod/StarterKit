package hanoi;

/**
 * Hello world!
 *
 */
public class Sim 
{
	 static void Hanoi(int n, String A, String B, String C)
     {
         if(n > 0)
         {
             Hanoi(n - 1, A, C, B);
             System.out.println(A + " -> " + C);
             Hanoi(n - 1, B, A, C);
         }
     }

	 
    public static void main( String[] args )
    {
    	Hanoi(2, "A", "B", "C");
    }
}
