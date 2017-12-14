package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;


public class SmallPitTest {
    @Test
    public void createNewPitTest()
    {
        SmallPit pit = new SmallPit();

        Assert.assertNotNull(pit);
        Assert.assertNotNull(pit.getNeighbour());
    }

    @Test
    public void fourteenthPitExistsTest()
    {
        SmallPit pit = new SmallPit();

        Assert.assertNotNull(pit.testHelperGetNumX(14));
    }

    @Test
    public void fourteenthPitHasFirstPitAsNeighbour()
    {
        SmallPit pit = new SmallPit();

        Assert.assertNotEquals(pit, pit.testHelperGetNumX(7).getNeighbour());
        Assert.assertEquals(pit, pit.testHelperGetNumX(14).getNeighbour());
    }

    @Test
    public void getNumStonesOnPitOneTest()
    {
        SmallPit pit = new SmallPit();

        Assert.assertEquals(4, pit.getNumStones());
    }

    @Test
    public void getNeighbourOfPitOneTest()
    {
        SmallPit pit = new SmallPit();

        Assert.assertNotNull(pit.getNeighbour());
    }

    @Test
    public void getPlayerOfPitOneTest()
    {
        SmallPit pit = new SmallPit();

        Assert.assertNotNull(pit.getPlayer());
    }

    @Test
    public void getPlayerOfPitTenTest()
    {
        SmallPit pit = new SmallPit();

        Assert.assertNotNull(pit.testHelperGetNumX(10).getPlayer());
    }

    @Test
    public void playerOneIsStartingPlayerTwoIsNot()
    {
        SmallPit pit = new SmallPit();

        Assert.assertTrue(pit.testHelperGetNumX(7).getPlayer().getItsMyTurn());
        Assert.assertFalse(pit.testHelperGetNumX(14).getPlayer().getItsMyTurn());
    }

    @Test
    public void receiveLastStoneTest()
    {
        //SmallPit receives stone, adds one to their numTotal. If it was the last stone, they tell their player to switch turns
        SmallPit pit = new SmallPit();

        pit.receiveStones(1);

        Assert.assertEquals(5, pit.getNumStones());
        Assert.assertFalse(pit.getPlayer().getItsMyTurn());
    }

    @Test
    public void receiveMultipleStonesTest()
    {
        //SmallPit receives stone, adds one to their numTotal. If it was the last stone, they tell their player to switch turns, else they tell their neighbour (who has the same player) to receive stones
        SmallPit pit = new SmallPit();

        pit.receiveStones(2);

        Assert.assertEquals(5, pit.getNumStones());
        Assert.assertEquals(5, pit.getNeighbour().getNumStones());
        Assert.assertEquals(4, pit.testHelperGetNumX(3).getNumStones());
        Assert.assertFalse(pit.getPlayer().getItsMyTurn());
        Assert.assertFalse(pit.getNeighbour().getPlayer().getItsMyTurn());
    }

    @Test
    public void startMoveOnPitOneAtStartUp()
    {
        //new board, doMove on pit 1
        SmallPit pit = new SmallPit();

        pit.doMove();

        Assert.assertEquals(0,pit.getNumStones());
        Assert.assertEquals(5,pit.testHelperGetNumX(3).getNumStones());
        Assert.assertEquals(5,pit.testHelperGetNumX(5).getNumStones());
        Assert.assertFalse(pit.testHelperGetNumX(5).getPlayer().getItsMyTurn());
    }

    @Test
    public void startMoveOnPitWithNoStones()
    {
        SmallPit pit = new SmallPit();

        pit.testHelperSetNumStones(0);

        Assert.assertFalse(pit.doMove());
    }

    @Test
    public void startMoveOnPitWithAllStones()
    {
        SmallPit pit = new SmallPit();

        pit.testHelperSetNumStones(48);
        for(int i = 2; i < 14; i++)
        {
            pit.testHelperGetNumX(i).testHelperSetNumStones(0);
        }

        pit.doMove();

        int totalStones = 0;
        for(int i = 1; i < 14; i++)
        {
            totalStones+= pit.testHelperGetNumX(i).getNumStones();
        }

        Assert.assertEquals(3, pit.getNumStones());
        Assert.assertEquals(4, pit.testHelperGetNumX(7).getNumStones());
        Assert.assertEquals(4, pit.testHelperGetNumX(10).getNumStones());
        Assert.assertEquals(3, pit.testHelperGetNumX(11).getNumStones());
        Assert.assertEquals(0, pit.testHelperGetNumX(14).getNumStones());
        Assert.assertEquals(48, totalStones);
    }


    @Test
    public void pitOneFindsOppositeNeighbour()
    {
        SmallPit pit = new SmallPit();

        Assert.assertEquals(pit.testHelperGetNumX(13), pit.getOppositeNeighbour());
    }

    @Test
    public void pitTwoFindsOppositeNeighbour()
    {
        SmallPit pit = new SmallPit();

        Assert.assertEquals(pit.testHelperGetNumX(12), pit.testHelperGetNumX(2).getOppositeNeighbour());
    }

    @Test
    public void pitSixFindsOppositeNeighbour()
    {
        SmallPit pit = new SmallPit();

        Assert.assertEquals(pit.testHelperGetNumX(8), pit.testHelperGetNumX(6).getOppositeNeighbour());
    }

    @Test
    public void pitEightFindsOppositeNeighbourWhenItsOwnersTurn()
    {
        SmallPit pit = new SmallPit();
        pit.getPlayer().switchTurn();

        Assert.assertEquals(pit.testHelperGetNumX(6), pit.testHelperGetNumX(8).getOppositeNeighbour());
    }

    @Test
    public void getBigPitTest()
    {
        Pit pit = new SmallPit();

        Assert.assertEquals(pit.testHelperGetNumX(7), pit.getBigPit());
    }

    @Test
    public void ownEmptyPitReceivesLastStone()
    {
        Pit pit = new SmallPit();
        pit.testHelperGetNumX(1).testHelperSetNumStones(1);
        pit.testHelperGetNumX(2).testHelperSetNumStones(0);

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertEquals(0, pit.testHelperGetNumX(2).getNumStones());
        Assert.assertEquals(0, pit.testHelperGetNumX(12).getNumStones());
        Assert.assertEquals(5, pit.testHelperGetNumX(7).getNumStones()); //4 in 12, 1 new in 2
    } //opposite emty

    @Test
    public void oppositeEmptyPitReceivesLastStone()
    {
        Pit pit = new SmallPit();
        pit.testHelperGetNumX(8).testHelperSetNumStones(0);
        SmallPit startPit = (SmallPit) pit.testHelperGetNumX(4);
        startPit.doMove();

        Assert.assertEquals(1, pit.testHelperGetNumX(8).getNumStones());
        Assert.assertEquals(1, pit.testHelperGetNumX(7).getNumStones());
    }

    @Test
    public void emptyPitReceivesLastStoneOppositeAlsoEmpty()
    {
        Pit pit = new SmallPit();
        pit.testHelperGetNumX(1).testHelperSetNumStones(1);
        pit.testHelperGetNumX(2).testHelperSetNumStones(0);
        pit.testHelperGetNumX(12).testHelperSetNumStones(0);

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertEquals(1, pit.testHelperGetNumX(2).getNumStones());
        Assert.assertEquals(0, pit.testHelperGetNumX(12).getNumStones());
        Assert.assertEquals(0, pit.testHelperGetNumX(7).getNumStones());
    }

    @Test
    public void allSmallPitsOfPlayerTwoAreEmpty()
    {
        Pit pit = new SmallPit();
        for(int i = 8; i < 14; i++)
        {
            pit.testHelperGetNumX(i).testHelperSetNumStones(0);
        }

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertEquals(0, pit.testHelperGetNumX(14).getNumStones());
        Assert.assertEquals(24, pit.testHelperGetNumX(7).getNumStones());
    }

    @Test
    public void allSmallPitsOfPlayerTwoAreEmptyDeclareWinner()
    {
        Pit pit = new SmallPit();
        for(int i = 8; i < 14; i++)
        {
            pit.testHelperGetNumX(i).testHelperSetNumStones(0);
        }

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertTrue(pit.testHelperGetNumX(7).getPlayer().testHelperIsWinner);
        Assert.assertFalse(pit.testHelperGetNumX(14).getPlayer().testHelperIsWinner);
        Assert.assertEquals(pit.testHelperGetNumX(7).getPlayer(), pit.testHelperGetNumX(7).getPlayer().testHelperGetWinner());
    }

    @Test
    public void allAsideTheLastSmallPitsOfPlayerTwoAreEmpty()
    {
        Pit pit = new SmallPit();
        for(int i = 8; i < 13; i++)
        {
            pit.testHelperGetNumX(i).testHelperSetNumStones(0);
        }

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertEquals(0, pit.testHelperGetNumX(14).getNumStones());
        Assert.assertEquals(0, pit.testHelperGetNumX(7).getNumStones());
        Assert.assertFalse(pit.testHelperGetNumX(7).getPlayer().testHelperIsWinner);
        Assert.assertFalse(pit.testHelperGetNumX(14).getPlayer().testHelperIsWinner);
    }

    @Test
    public void allAsideTheFirstSmallPitsOfPlayerTwoAreEmpty()
    {
        Pit pit = new SmallPit();

        for(int i = 9; i < 14; i++)
        {
            pit.testHelperGetNumX(i).testHelperSetNumStones(0);
        }

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertEquals(0, pit.testHelperGetNumX(14).getNumStones());
        Assert.assertEquals(0, pit.testHelperGetNumX(7).getNumStones());
        Assert.assertFalse(pit.testHelperGetNumX(7).getPlayer().testHelperIsWinner);
        Assert.assertFalse(pit.testHelperGetNumX(14).getPlayer().testHelperIsWinner);
    }

    @Test
    public void allSmallPitsAreEmpty()
    {
        Pit pit = new SmallPit();

        for(int i = 1; i < 13; i++)
        {
            pit.testHelperSetNumStones(0);
        }

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertEquals(0, pit.testHelperGetNumX(14).getNumStones());
        Assert.assertEquals(0, pit.testHelperGetNumX(7).getNumStones());
        Assert.assertFalse(pit.testHelperGetNumX(7).getPlayer().testHelperIsWinner);
        Assert.assertFalse(pit.testHelperGetNumX(14).getPlayer().testHelperIsWinner);
    }

    @Test
    public void gameEndsWithTie()
    {
        Pit pit = new SmallPit();

        for(int i = 1; i < 13; i++)
        {
            pit.testHelperSetNumStones(0);
        }

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertEquals(null, pit.testHelperGetNumX(7).getPlayer().testHelperGetWinner());
    }
}
