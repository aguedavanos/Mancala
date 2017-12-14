package nl.sogyo.mancala;

public class BigPit extends Pit
{
    public BigPit(Player player, SmallPit firstPit, int numPitsStillNeeded)
    {
        numPitsStillNeeded--;
        owner = player;
        numStones = 0;

        if(numPitsStillNeeded == 7)
        {
            neighbour = new SmallPit(new Player(player), firstPit, numPitsStillNeeded);

        }
        else
        {
            neighbour = firstPit;
        }
    }

    public Pit getOppositeNeighbour()
    {
        return getBigPit();
    }

    public void receiveStones(int numPassedStones)
    {
        if(getPlayer().getItsMyTurn())
        {
            numStones += 1;

            if(numPassedStones == 1)
            {
                endTurn();
            }
            else
            {
                neighbour.receiveStones(numPassedStones - 1);
            }
        }
        else
        {
            neighbour.receiveStones(numPassedStones);
        }
    }

    public void receiveMultipleStones(int numPassedStones)
    {
        numStones += numPassedStones;
    }

    public boolean checkIfEndGame()
    {
        if((neighbour.checkIfEndGame()))
        {

            return true;
        }
        else
        {
            return false;
        }
    }

    private void getOwnFinalScore()
    {
        SmallPit startPit = (SmallPit) getOppositeNeighbour().getNeighbour();
        startPit.giveFinalStonesToBigPit();
    }

    public void declareWinner()
    {
        getOwnFinalScore();
        Player winner = null;

        if(numStones > getOppositeNeighbour().getNumStones())
        {
            winner = owner;
        }
        else
        {
            winner = getOppositeNeighbour().getPlayer();
        }

        owner.declareWinner(winner);
    }

    protected void endTurn()
    {
        if(checkIfEndGame())
        {
           declareWinner();
        }
        else
        {
            owner.keepTurn();
        }
    }
}
