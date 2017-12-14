package nl.sogyo.mancala;

public class Player
{
    private boolean itsMyTurn;
    private Player opponent;
    public boolean testHelperIsWinner = false;
    private Player testHelperWinner;

    public Player()
    {
        itsMyTurn = true;
    }

    public Player(Player opponent)
    {
        this.opponent = opponent;
        opponent.setOpponent(this);
        itsMyTurn = false;
    }

    public boolean getItsMyTurn()
    {
        return itsMyTurn;
    }

    public void setOpponent(Player opponent)
    {
        this.opponent = opponent;
    }

    public void switchTurn()
    {
        itsMyTurn = !itsMyTurn;
        if(itsMyTurn == opponent.getItsMyTurn())
        {
            opponent.switchTurn();
        }
        //tell GUI to start new turn
    }

    public void keepTurn()
    {
        //tell GUI to start new turn
    }

    public Player testHelperGetOpponent()
    {
        return opponent;
    }

    public void declareWinner(Player winner)
    {
        testHelperIsWinner = true;
        testHelperWinner = winner;
        if(winner != null)
        {
            if(winner != opponent)
            {
                testHelperIsWinner = true;
            }
            opponent.testHelperWinner = winner; //Needed?
            //tell GUI that winner has won
            //tell other player who won?
        }
        else
        {
            //tell GUI that it was a tie
        }
    }

    public Player testHelperGetWinner()
    {
        return testHelperWinner;
    }
}
