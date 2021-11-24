package unitTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {

    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 50;

    private Dummy dummy;
    private Dummy deadDummy;


    @Before
    public void createUnit() {
        this.dummy = new Dummy(HEALTH, EXPERIENCE);
        this.deadDummy = new Dummy(0, EXPERIENCE);
    }


    @Test
    public void testDummyLosesHealthWhenAttacked() {
        dummy.takeAttack(10);
        Assert.assertEquals(HEALTH - 10, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testDeadDummyThrowsWhenAttacked() {
        deadDummy.takeAttack(10);
    }

    @Test
    public void testDeadDummyGiveExperience() {
        Assert.assertEquals(EXPERIENCE, deadDummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyThrowsWhenGivingXp() {
        dummy.giveExperience();
    }
}