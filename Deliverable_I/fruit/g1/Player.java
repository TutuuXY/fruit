package fruit.g1;

import java.util.*;

public class Player extends fruit.sim.Player
{

    private Random random = new Random();
    
    private int nplayers;
    private int[] preferences;
    
    public void init(int nplayers, int[] pref) {
        this.nplayers = nplayers;
        preferences = pref.clone();
        
        //System.out.println("g1 index: " + getIndex());
    }

    public boolean pass(int[] bowl, int bowlId, int round,
                        boolean canPick,
                        boolean musTake) {
        
        // compute the score of the bowl we received
        int score = 0;
        for (int i = 0; i < preferences.length; i++) {
            score += bowl[i] * preferences[i];
        }
        
        // compute the expected value of a bowl, assuming
        // an even distribution of fruit
        int fruitinbowl = 0;
        for (int i = 0; i < bowl.length; i++) {
            fruitinbowl += bowl[i];
        }
        double dist = (double) fruitinbowl / bowl.length;
        double eV = 0.0;
        for (int i = 0; i < bowl.length; i++) {
            eV += preferences[i] * dist;
        }
        
        // take the bowl if the score exceed expected value
        return score > eV;
        
    }
       
}
