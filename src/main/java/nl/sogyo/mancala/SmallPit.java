package nl.sogyo.mancala;

public class SmallPit extends Pit
{
    public SmallPit()
    {
        int numPitsStillNeeded = 13;
        owner = new Player();
        neighbour = new SmallPit(owner, this, numPitsStillNeeded);
    }
    public SmallPit(Player player, SmallPit firstPit, int numPitsStillNeeded)
    {
        numPitsStillNeeded--;
        owner = player;

        if(numPitsStillNeeded == 8 || numPitsStillNeeded == 1)
        {
            neighbour = new BigPit(owner, firstPit, numPitsStillNeeded);
        }
        else
        {
            neighbour = new SmallPit(owner, firstPit, numPitsStillNeeded);
        }
    }

    public boolean doMove()
    {
        if(isValidChoice())
        {
            int passStones = numStones;
            numStones = 0;
            neighbour.receiveStones(passStones);
            return true;
        }
        //Tell GUI to try again
        return false;
    }

    private boolean isValidChoice()
    {
        if(numStones > 0 && owner.getItsMyTurn())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    protected void receiveStones(int numPassedStones)
    {
        numStones += 1;

        if(numPassedStones == 1) //= last stone
        {
            captureIfPossible();

            endTurn();
        }
        else
        {
            neighbour.receiveStones(numPassedStones - 1);
        }
    }

    private void captureIfPossible()
    {
        if((numStones == 1) && (owner.getItsMyTurn())) //= possible capture
        {
            SmallPit opposite = (SmallPit) getOppositeNeighbour();
            if(opposite.captureStones()) //= valid capture
            {
                numStones = 0;
                BigPit bigPit = (BigPit) getBigPit();
                bigPit.receiveMultipleStones(1);
            }
        }
    }

    protected boolean captureStones()
    {
        if(numStones != 0)
        {
            BigPit bigPit = (BigPit) getBigPit().getOppositeNeighbour();
            bigPit.receiveMultipleStones(numStones);
            numStones = 0;
            return true;
        }
        return false;
    }

    protected void endTurn()
    {
        BigPit activePit = (BigPit) getActivePlayerBigPit();
        if((activePit.checkIfEndGame()))
        {
            activePit.declareWinner();
        }
        else
        {
            owner.switchTurn();
        }
    }

    public boolean checkIfEndGame()
    {
        if(numStones > 0)
        {
            return false;
        }

        if(neighbour instanceof BigPit && numStones == 0) //test laatste wel vol
        {
            return true;
        }
        else
        {
            return neighbour.checkIfEndGame();
        }
    }

    public void giveFinalStonesToBigPit()
    {
        BigPit bigPit = (BigPit) getBigPit();
        bigPit.receiveMultipleStones(numStones);
        numStones = 0;

        if(!(neighbour instanceof BigPit))
        {
            SmallPit n = (SmallPit) neighbour;
            n.giveFinalStonesToBigPit();
        }
    }
}