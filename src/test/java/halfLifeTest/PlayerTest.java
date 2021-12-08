package halfLifeTest;

import examPreparation.halfLifeTest.Gun;
import examPreparation.halfLifeTest.Player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlayerTest {

    private Player player;
    private Gun gun;
    private Gun gun1;

    @Before
    public void setUp() {
        this.player = new Player("dani", 10);
        this.gun = new Gun("pistol", 50);
        this.gun1 = new Gun("rifle", 100);
    }

    @Test(expected = NullPointerException.class)
    public void testAddWhenIsNull() {
        this.player = new Player(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testWhenNameIsWhiteSpace() {
        this.player = new Player("  ", 10);
    }

    @Test
    public void testGettingCorrectUsername() {
        assertEquals("dani", this.player.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenHealthIsBellowZero() {
        this.player = new Player("test", -1);
    }

    @Test
    public void testGettingCorrectHealth() {
        assertEquals(10, this.player.getHealth());
    }

    @Test
    public void testGettingCorrectGuns() {
        this.player.addGun(gun);
        this.player.addGun(gun1);
        List<Gun> guns = this.player.getGuns();
        assertEquals(2, guns.size());
    }

    @Test(expected = NullPointerException.class)
    public void testWhenGunIsNull() {
        this.player.addGun(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testWhenPlayerIsDead() {
        this.player = new Player("test", 0);
        this.player.takeDamage(10);
    }

    @Test
    public void testSettingZeroToPlayerHealth() {
        this.player.takeDamage(11);
        assertEquals(0, this.player.getHealth());
    }

    @Test
    public void testPlayerIsTakingDamage() {
        this.player.takeDamage(5);
        assertEquals(5, this.player.getHealth());
    }

    @Test
    public void testRemovingGun() {
        this.player.addGun(gun);
        this.player.addGun(gun1);
        this.player.removeGun(gun1);
        assertEquals(1, this.player.getGuns().size());
    }

    @Test
    public void testGettingCorrectGunGame() {
        this.player.addGun(gun);
        Gun expectedGun = gun;
        Gun actualGun = player.getGun(expectedGun.getName());
        assertEquals(expectedGun, actualGun);
    }

    @Test
    public void testGettingNullWhenGunIsNotPresent() {
        assertNull(this.player.getGun("test"));
    }
}