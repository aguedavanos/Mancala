package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class BigPitTest
{
    @Test
    public void BigPitHasZeroStonesAtStartup()
    {
        SmallPit pit = new SmallPit();

        Assert.assertEquals(0, pit.testHelperGetNumX(7).getNumStones());
    }

    @Test
    public void bigPitsHaveDifferentPlayersTest()
    {
        SmallPit pit = new SmallPit();

        Assert.assertNotNull(pit.testHelperGetNumX(7).getPlayer());
        Assert.assertNotNull(pit.testHelperGetNumX(14).getPlayer());
        Assert.assertNotEquals(pit.testHelperGetNumX(7).getPlayer(),pit.testHelperGetNumX(14).getPlayer());
    }

    @Test
    public void theSeventhPitIsABigPit()
    {
        SmallPit pit = new SmallPit();

        Assert.assertTrue(pit.testHelperGetNumX(7) instanceof BigPit);
    }

    @Test
    public void theFourteenthPitIsABigPitAndHasFirstPitAsNeighbour()
    {
        SmallPit pit = new SmallPit();

        Assert.assertTrue(pit.testHelperGetNumX(14) instanceof BigPit);
    }

    @Test
    public void BigPitOneHasBigPitTwoAsOppositeNeighbour()
    {
        Pit pit =  new SmallPit();

        Assert.assertEquals(pit.testHelperGetNumX(14), pit.testHelperGetNumX(7).getOppositeNeighbour());
    }

    @Test
    public void ownBigPitReceivesMultipleStonesInNormalTurnTest()
    {
        Pit pit = new SmallPit();

        SmallPit startPit = (SmallPit) pit.testHelperGetNumX(6);
        startPit.doMove();

        Assert.assertEquals(1, pit.testHelperGetNumX(7).getNumStones());
        Assert.assertFalse(pit.testHelperGetNumX(7).getPlayer().getItsMyTurn());
        Assert.assertTrue(pit.testHelperGetNumX(14).getPlayer().getItsMyTurn());
    }

    @Test
    public void ownBigPitReceivesLastStone()
    {
        Pit pit = new SmallPit();
        SmallPit startPit = (SmallPit) pit.testHelperGetNumX(6);
        startPit.testHelperSetNumStones(1);

        startPit.doMove();

        Assert.assertEquals(1, pit.testHelperGetNumX(7).getNumStones());
        Assert.assertTrue(pit.testHelperGetNumX(7).getPlayer().getItsMyTurn());
    }

    @Test
    public void opponentBigPitReceivesStone()
    {
        Pit pit = new SmallPit();
        SmallPit startPit = (SmallPit) pit.testHelperGetNumX(6);
        startPit.testHelperSetNumStones(10);

        startPit.doMove();

        Assert.assertEquals(0, pit.testHelperGetNumX(14).getNumStones());
        Assert.assertEquals(5, pit.testHelperGetNumX(1).getNumStones());
    }

    @Test
    public void gameEndsOnOwnBigPit()
    {
        Pit pit = new SmallPit();

        for(int i = 8; i < 14; i++)
        {
            pit.testHelperGetNumX(i).testHelperSetNumStones(0);
        }

        SmallPit chosenPit = (SmallPit) pit.testHelperGetNumX(3);
        chosenPit.doMove();

        Assert.assertTrue(pit.testHelperGetNumX(7).getPlayer().testHelperIsWinner);
        Assert.assertFalse(pit.testHelperGetNumX(14).getPlayer().testHelperIsWinner);
    }

    @Test
    public void gameEndsOnOpponentBigPit()
    {
        //"Ends" on Opponent BigPit, so ends on firstPit of active player
        Pit pit = new SmallPit();
        for(int i = 1; i < 7; i++)
        {
            pit.testHelperGetNumX(i).testHelperSetNumStones(0);
        }

        pit.getPlayer().switchTurn(); //player 2 starts

        SmallPit chosenPit = (SmallPit) pit.testHelperGetNumX(10);
        chosenPit.doMove();

        Assert.assertFalse(pit.testHelperGetNumX(7).getPlayer().testHelperIsWinner);
        Assert.assertTrue(pit.testHelperGetNumX(14).getPlayer().testHelperIsWinner);
    }
}
