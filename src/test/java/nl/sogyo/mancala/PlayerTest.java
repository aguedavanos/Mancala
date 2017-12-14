package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest
{
    @Test
    public void getItsMyTurnTest()
    {
        Pit pit = new SmallPit();

        Assert.assertTrue(pit.getPlayer().getItsMyTurn());
    }

    @Test
    public void switchTurnOwnTurnChangesTest()
    {
        Pit pit = new SmallPit();

        pit.getPlayer().switchTurn();

        Assert.assertFalse(pit.getPlayer().getItsMyTurn());
    }

    @Test
    public void playerKnowsOpponentTest()
    {
        Pit pit = new SmallPit();

        Assert.assertEquals(pit.testHelperGetNumX(7).getPlayer(), pit.testHelperGetNumX(14).getPlayer().testHelperGetOpponent());
    }

    @Test
    public void switchTurnOpponentTurnChangesTest()
    {
        Pit pit = new SmallPit();

        pit.getPlayer().switchTurn();

        Assert.assertFalse(pit.testHelperGetNumX(7).getPlayer().getItsMyTurn());
        Assert.assertTrue(pit.testHelperGetNumX(14).getPlayer().getItsMyTurn());
    }

    @Test
    public void playerKnowsWinner()
    {
        Pit pit = new SmallPit();
        for(int i = 8; i < 14; i++)
        {
            pit.testHelperGetNumX(i).testHelperSetNumStones(0);
        }

        SmallPit pit1 = (SmallPit) pit.testHelperGetNumX(1);
        pit1.doMove();

        Assert.assertEquals(pit1.getPlayer(), pit1.getPlayer().testHelperGetWinner());
        Assert.assertEquals(pit1.getPlayer(), pit.testHelperGetNumX(8).getPlayer().testHelperGetWinner());
    }
}
