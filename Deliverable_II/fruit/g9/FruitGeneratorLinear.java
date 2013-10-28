package fruit.g9;

//for the class Random
import java.util.*;

public class FruitGeneratorLinear implements fruit.sim.FruitGenerator
{
    public int[] generate(int nplayers, int bowlsize) {
        int nfruits = nplayers * bowlsize;

        //The Implementation of almost linear distribution

        int[] dist = new int[12];
        Arrays.fill(dist, 0);
        
        int avg = nfruits / 12;
        int rest = nfruits - avg * 12;
        double baseline[] = new double[12];

        //set a baseline for the linear distribution we would like to approximate with integers
        //and a uniformly distributed distribution to modified later
        for( int i = 0; i < 12; i++ ) {
            dist[i] = avg;
            baseline[i] = (double)(i) * (double)(nfruits) / 72.0;
        }

        //Do the initial modification: the lower approximation for the smaller half
        for( int i = 0; i < 6; i++ ) {
            dist[11-i] += dist[i] - (int)(baseline[i]);
            dist[i] = (int)(baseline[i]);
            // 0 amount of fruits is not intersting, modify it
            if( 0 == dist[i] ) {
                dist[i] = 1;
                dist[11-i] -= 1;
            }
        }

        //Put the "rest" part back into the distribution
        while( rest > 0 ) {
            for( int i = 0; i < 11; i++) {
                if( dist[i] < dist[i+1] && rest > 0) {
                    dist[i]++;
                    rest--;
                }
                if (rest == 0){
                    break;
                }
            }
        }
        //At last, we do some random permutation to map the distribution to fruits
        dist = randomizeArray(dist);
        return dist;
    }

    private Random random = new Random();
    private int[] randomizeArray(int[] originalArray) {
        //This is a function that randomly permutes the original given array and return it.
        int length = originalArray.length;
        int ret[] = new int[length];
        int last = 0;
        Arrays.fill(ret, 0);
        for(int j = 0; j < length; j++) {
            int step = random.nextInt(length);

            int i = last;
            //go through the array
            //and count the number of 0's we see
            //and settle for the last one
            //if we have go to the end, start from the first
            while(true) {
                if(ret[i] == 0) {
                    if(step == 0) {
                        break;
                    }
                    step--;
                }
                i++;
                i %= length;
            }
            ret[i] = originalArray[j];
        }
        return ret;
    }

}