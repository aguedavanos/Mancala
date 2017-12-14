package nl.sogyo.mancala;

public abstract class Pit
{
    protected int numStones = 4;
    protected Player owner;
    protected Pit neighbour;

    public int getNumStones()
    {
        return numStones;
    }

    public Pit getNeighbour()
    {
        return neighbour;
    }

    public Player getPlayer()
    {
        return owner;
    }

    public Pit testHelperGetNumX(int numPitsOverStartCountFromCurrent)
    {
            if(numPitsOverStartCountFromCurrent == 1)
            {
                return this;
            }
           else
           {
                return neighbour.testHelperGetNumX((numPitsOverStartCountFromCurrent - 1));
           }
    }

    public void testHelperSetNumStones(int newNum)
    {
        numStones = newNum;
    }

    protected Pit getOppositeNeighbour()
    {
        return neighbour.getOppositeNeighbour(0);
    }
     protected Pit getOppositeNeighbour(int steps)
    {
        //starts with zero on OG
        if(owner.getItsMyTurn() == false)
        {
            if(steps == 1)
            {
                return this;
            }
            else
            {
                return neighbour.getOppositeNeighbour((steps-1));
            }
        }
        else
        {
            return neighbour.getOppositeNeighbour((steps+1));
        }
    }

    protected Pit getBigPit()
    {
        if(neighbour instanceof BigPit)
        {
            return neighbour;
        }
        else
        {
            return neighbour.getBigPit();
        }
    }

    protected Pit getActivePlayerBigPit()
    {
        BigPit bigPit = (BigPit) getBigPit();

        if(bigPit.owner.getItsMyTurn())
        {
            return bigPit;
        }
        else
        {
            return bigPit.getOppositeNeighbour();
        }
    }

    protected abstract void receiveStones(int numPassedStones);

    protected abstract boolean checkIfEndGame();

    protected abstract void endTurn();
}
