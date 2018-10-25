import components.CoordinateStatus;
import junit.framework.Assert;
import org.junit.Test;

public class CoordinateStatusTest {

    @Test
    public void testGetSquareStateBySign() {
        Assert.assertTrue(CoordinateStatus.START_POINT == CoordinateStatus.getSquareState('S'));
        Assert.assertTrue(CoordinateStatus.GOAL == CoordinateStatus.getSquareState('F'));
        Assert.assertTrue(CoordinateStatus.BLOCKED == CoordinateStatus.getSquareState('X'));
        Assert.assertTrue(CoordinateStatus.CLEAR == CoordinateStatus.getSquareState(' '));

        Assert.assertTrue(CoordinateStatus.UNKNOWN == CoordinateStatus.getSquareState('?'));
    }
}
