import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;


public class SnakeGame {
	/*
	 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
	 * 
	 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
	 * 
	 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
	 * 
	 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
	 * 
	 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
	 * 
	 * Example:
	 * 
	 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
	 * 
	 * Snake snake = new Snake(width, height, food);
	 * 
	 * Initially the snake appears at position (0,0) and the food at (1,2).
	 * 
	 * |S| | |
	 * | | |F|
	 * 
	 * snake.move("R"); -> Returns 0
	 * 
	 * | |S| |
	 * | | |F|
	 * 
	 * snake.move("D"); -> Returns 0
	 * 
	 * | | | |
	 * | |S|F|
	 * 
	 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
	 * 
	 * | |F| |
	 * | |S|S|
	 * 
	 * snake.move("U"); -> Returns 1
	 * 
	 * | |F|S|
	 * | | |S|
	 * 
	 * snake.move("L"); -> Returns 2 (Snake eats the second food)
	 * 
	 * | |S|S|
	 * | | |S|
	 * 
	 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
	 */
	
    /** Initialize your data structure here.
    @param width - screen width
    @param height - screen height 
    @param food - A list of food positions
    E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
	
	// https://leetcode.com/problems/design-snake-game/discuss/82668/Java-Deque-and-HashSet-design-with-detailed-comments
	
	Set<Integer> set;     // 快速检查和自己身体相撞的case
	Deque<Integer> deque; // 保存蛇的身体，更新蛇尾
	int score;
	int[][] food;
	int foodIndex;
	int width;
	int height;
	
	public SnakeGame(int width, int height, int[][] food) {
	    this.width = width;
	    this.height = height;
	    this.food = food;
	    this.foodIndex = 0;
	    
	    set = new HashSet<>();
	    deque = new ArrayDeque<>();
	    deque.offer(0);
	}
	
	/** Moves the snake.
	    @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
	    @return The game's score after the move. Return -1 if game over. 
	    Game over when snake crosses the screen boundary or bites its body. */
	public int move(String direction) {
		// case 0: game already over: do nothing
	    if (score == -1) {
	    	return -1;
	    }
	    
	    int headR = deque.peekFirst() / width; // head row
	    int headC = deque.peekFirst() % width; // head col
	    
	    switch (direction) {
	    	case "U":
	    		headR--;
	    		break;
	    	case "D":
	    		headR++;
	    		break;
	    	case "L":
	    		headC--;
	    		break;
	    	default:
	    		headC++;		
	    }
	    
	    int head = headR * width + headC;
	    
	    //case 1: out of boundary or eating body
	    // new head is legal to be in old tail's position, remove from set temporarily
	    set.remove(deque.peekLast());
	    if (headR < 0 || headR >= height || headC < 0 || headC >= width || set.contains(head)) {
	    	return -1;
	    }
	    
	    set.add(head);
	    deque.offerFirst(head);
	    
	    //case2: eating food, keep tail, add head
	    if (foodIndex < food.length && headR == food[foodIndex][0] && headC == food[foodIndex][1]) {
	    	set.add(deque.peekLast()); // old tail does not change, so add it back to set
	    	foodIndex++;
	    	return ++score;
	    }
	    
	    //case3: normal move, remove tail, add head
	    set.remove(deque.pollLast());
	    return score;
	}
	
}
