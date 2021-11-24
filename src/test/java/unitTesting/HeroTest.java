package unitTesting;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void testHeroGainsExperienceWhenTargetDies() {
        Weapon weapon = mock(Weapon.class);
        Hero hero = new Hero("Java", weapon);

        Target target = mock(Target.class);
        when(target.isDead()).thenReturn(true);
        when(target.giveExperience()).thenReturn(100);
        hero.attack(target);
        assertEquals(100, hero.getExperience());
    }

    @Test
    public void testWhenHeroKillsTargetInventoryShouldAddLoot() {
        Weapon weapon = mock(Weapon.class);
        Hero hero = new Hero("Java", weapon);
        Target target = mock(Target.class);
        when(target.isDead()).thenReturn(true);
        when(target.getLoot()).thenReturn(new Axe(37, 43));
        hero.attack(target);
        List<Weapon> inventory = hero.getInventory();
        assertEquals(1, inventory.size());
        Weapon loot = inventory.get(0);
        assertEquals(37, loot.getAttackPoints());
        assertEquals(43, loot.getDurabilityPoints());
    }
}