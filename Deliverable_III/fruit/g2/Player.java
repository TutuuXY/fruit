package fruit.g2;

import java.util.*;

public class Player extends fruit.sim.Player
{
    private Stat stat;
    private int nplayers;
    private int[] pref;
    // number of times that a bowl passed to the player
    // at the end of first round npassed+getindex() = nplayers
    private int npassed;
    private int round;
    // Housekeeping, useless in functionality
    private boolean picked;

    public void init(int nplayers, int[] pref) {
        stat = new Stat(nplayers, pref);
        this.nplayers = nplayers;
        this.pref = pref.clone();
        npassed = 0;
        round = 0;
        picked  = false;
    }

    public boolean pass(int[] bowl, int bowlId, int round,
                        boolean canPick,
                        boolean mustTake) {
        npassed++;
        stat.add(bowl);
        if (choiceLeft() == 0) {
            // beginning of second round
            assert(picked);
            picked = false;
            assert(round == 1);
            this.round = 1;
        }
        assert(round == this.round);
        assert(picked != canPick);
        // already picked
        if (picked)
            return false;
        // must take
        if (choiceLeft() == 1) {
            if (!picked) {
                assert(mustTake);
                picked = true;
                return true;
            }
        }
        // 2 choice left - aim for avg
        else if (choiceLeft() == 2) {
            if (stat.score(npassed-1) < stat.average())
                return false;
            else {
                picked = true;
                return true;
            }
        }
        // otherwise look for avg + std
        else
            if (stat.score(npassed-1) < stat.average() + stat.stdev())
                return false;
            else {
                picked = true;
                return true;
            }
        return false;
    }
    
    private int choiceLeft() {
        if (round == 0)
            return nplayers - getIndex()- npassed;
        else
            return nplayers - npassed;
    }
}
