package fruit.g9;

//for the class Random
import java.util.*;

public class FruitGenerator2Tier implements fruit.sim.FruitGenerator
{
	private Random random = new Random();

    public int[] generate(int nplayers, int bowlsize) {
        int nfruits = nplayers * bowlsize;

        //The Implementation of 20%-80% Distribution
        //The randomly chosen 25% kinds (3) of fruits contribute to the 75% amount of all fruits
        //And the rest 75% (9) kinds share the rest 25% amount

        int[] dist = new int[12];
        Arrays.fill(dist, 0);

        //for the 3 kinds in 1st tier, each kind will have 9 unit amount = total/4
        //for the 9 kinds in 2nd tier, each kind will have 1 unit amount = total/36

        int small_unit = nfruits / 36;
        int rest = nfruits - 9 * small_unit;
        System.out.println("small_unit:" + small_unit);
        System.out.println("rest:" + rest);
        System.out.print("1st-tier: ");

        // randomly choosing 3 kinds to be in the 1st tier
        for (int j = 0; j < 3 ; j++) {
        	int i = random.nextInt(12);
        	while( dist[i] != 0 ) {
        		i = random.nextInt(12);
        	}
        	dist[i] = rest / 3;
        	switch( rest % 3) {
        		case 1:
        		case 2:
					dist[i]++;
        			rest--;
        			break;
        		default:
        			break;
        	}
            System.out.print(" " + i + ", ");
        }
        System.out.println();
        // then the rest to be in the 2nd tier
        for (int i = 0; i < 12; i++) {
        	if( dist[i] == 0 ) {
        		dist[i] = small_unit;
        	}
        }
        
        return dist;
    }
}
