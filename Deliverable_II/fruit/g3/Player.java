package fruit.g3;

import java.util.*;

public class Player extends fruit.sim.Player
{
    public void init(int nplayers, int[] pref) {
		this.preferences = pref.clone();
    }

    public boolean pass(int[] bowl, int bowlId, int round,
                        boolean canPick,
                        boolean musTake) {
		int fruitQuantity = 0;
		int bowlScore = 0;
		int expectedScore = 0;
		System.out.println("ID: " + bowlId);
		
		for (int i = 0; i < bowl.length; i++) {
			System.out.println(i + " " + bowl[i]);
			fruitQuantity = fruitQuantity + bowl[i];
			bowlScore = bowlScore + (bowl[i]*preferences[i]);
		}
		expectedScore = ((fruitQuantity*(fruitQuantity+1)/2)*(fruitQuantity/12))+((fruitQuantity%12)*((fruitQuantity%12)+1)/2);
		
		for (int i = 0; i < preferences.length; i++) {
			System.out.print(i + " " + preferences[i] + " , ");
		}
		System.out.println();
		System.out.println("Expected Score: " + expectedScore);
		System.out.println("Bowl Score: " + bowlScore);
		return bowlScore > expectedScore;
    }
    

    private Random random = new Random();
    private int[] preferences = new int[12];
}
