	package fruit.g5;
     
    import java.util.Random;
     
    public class Player extends fruit.sim.Player
    {

		double maxScore1 = 0.0, maxScore2 = 0.0;
		int nplayers, index, cutoffPoint1, cutoffPoint2, startPoint2;
		int choice = 0;
		int[] pref;

		public void init(int nplayers, int[] pref)
		{
            this.nplayers = nplayers;
            this.pref = pref;
			this.index = getIndex();
			cutoffPoint1 = (int) Math.ceil((nplayers + 1 - index) / Math.E);
			
			
			if( (nplayers + 1 - index) < (index / (Math.E - 1)) )
			{
				startPoint2 = 0;
				cutoffPoint2 = (int) Math.ceil((nplayers + 1) / Math.E);
			}
			else
			{
				startPoint2 = (int) Math.ceil((nplayers + 1 - index) - (index / (Math.E - 1)));
				cutoffPoint2 = (int) Math.ceil(index / (Math.E - 1));
			}

			
        }
	 
        public boolean pass(int[] bowl, int bowlId, int round, boolean canPick, boolean musTake)
		{
			choice++;
     
            if (choice <= cutoffPoint1)
			{
				if(bowlScore(bowl) > maxScore1)
					maxScore1 = bowlScore(bowl);
			}
			
            if (choice >= startPoint2 && choice <= (startPoint2 + cutoffPoint2))
			{
				if(bowlScore(bowl) > maxScore2)
					maxScore2 = bowlScore(bowl);
			}
     
            if (round == 0 && choice >= cutoffPoint1 && bowlScore(bowl) >= maxScore1)
                return true;
				
			else if (round == 1 && choice >= (startPoint2 + cutoffPoint2) && bowlScore(bowl) >= maxScore2)
				return true;
     
			//else if(musTake)
			//	return true;
				
            return false;
        }
     
        public int bowlScore(int[] bowl)
		{
            int score = 0;
            for (int i = 0; i < bowl.length; i++)
			{
                score += pref[i] * bowl[i];
            }
            return score;
        }
    }

