
package fruit.sim;
import java.util.Random;
public class FruitGeneratorSingle implements FruitGenerator
{
    public int[] generate(int nplayers, int bowlsize) {
        int nfruits = nplayers * bowlsize;

        int[] dist = new int[12];
        Random random = new Random();
        int cnt = random.nextInt(12);
        for (int i = 0; i < 12; i++)
        	if(i==cnt)
        	{
        		dist[i]=nfruits;
        	}
        	else
        	{
        		dist[i]=0;
        	}	
        
        return dist;
    }
}
