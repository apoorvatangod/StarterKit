import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.coins.Coins;
import com.capgemini.fibonacci.Fibonacci;
import com.capgemini.pascalrectangle.PascalRectangle;
import com.capgemini.placeToSplit.PlaceToSplit;
import com.capgemini.pythagorean.Pythagorean;
import com.capgemini.nodes.Node;
import com.capgemini.nodes.NodeValidators;

public class Main {

	public static void main(String[] args) {
/** COINS
		List<Integer> coins = new ArrayList<Integer>();
		coins.add(1);
		coins.add(1);
		coins.add(0);
		coins.add(1);
		coins.add(0);
		coins.add(0);
		
		System.out.println(Coins.solution(coins));
**/
		
/** FIBONACCI
		System.out.println(Fibonacci.fib(8));
**/
		
/** PASCAL RECTANGLE
		try {
			System.out.println(PascalRectangle.pascal(3, 9));
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
**/		
/** PLACE TO SPLIT		
		int[] nums = {10,1,10,1};
		System.out.println(PlaceToSplit.canBalance(nums));
**/		
	
/**	PYTHAGOEAN	
		System.out.println(Pythagorean.getResult());
**/
/** NODE
		Node node1 = new Node("0001", "Something", null);
		Node node2 = new Node("0002", "Something", "0001");
		Node node3 = new Node("0003", "Something", "0002");
		Node node4 = new Node("0004", "Something", "0003");
		Node node5 = new Node("0005", "Something", "0004");
		Node node6 = new Node("0006", "Something", "0004");
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);	
		NodeValidators.validateMethod(nodes);
**/
		
	}
}
