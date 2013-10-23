package fruit.g8;

import java.util.*;

public class Player extends fruit.sim.Player
{
	int nplayer;
	int[] pref;
	int magic;
    public void init(int nplayers, int[] pref) {
    	this.nplayer=nplayers;
    	this.pref=pref;
       // System.out.printf("the number of players is %d\n", nplayers);
        //System.out.printf("the index is %d\n", this.getIndex());
    	magic=(int)(0.369*(nplayers-this.getIndex())+1);
    }
    int max=0;
    int counter=0;
    public boolean pass(int[] bowl, int bowlId, int round,
                        boolean canPick,
                        boolean musTake) {
    	counter++;
    	System.out.printf("magic is %d", magic);
        if(counter<=magic){
        	//System.out.println("we won't pick the bowl");
        	//System.out.printf("the score for %d bowl is %d\n", counter, score(bowl));
        	if (max<score(bowl)){
        		max=score(bowl);
        	}
        }else{
        	System.out.println("we are in the picking round");
        	if(score(bowl)>=max){
        		return true;
        	}else{
        		if (musTake){
        			return true;
        		}
        		return false;
        	}
        }
        return false;
    }
    
    private int score(int[] bowl){
    	int sum=0;
    	for(int i=0;i<12;i++){
    		sum+=pref[i]*bowl[i];
    	}
    	return sum;
    }

    private Random random = new Random();
}
