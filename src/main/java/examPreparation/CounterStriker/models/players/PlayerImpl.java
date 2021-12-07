package examPreparation.CounterStriker.models.players;

import examPreparation.CounterStriker.models.guns.Gun;

import static examPreparation.CounterStriker.common.ExceptionMessages.*;

public abstract class PlayerImpl implements Player {

    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String username, int health, int armor, Gun gun) {
        setUsername(username);
        setHealth(health);
        setArmor(armor);
        setGun(gun);
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }
        this.gun = gun;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void takeDamage(int points) {

        int damageArmor = this.armor - points;
        if (damageArmor < 0) {
            setArmor(0);
            int damageHealth = this.health + damageArmor;
            if (damageHealth <= 0) {
                setHealth(0);
                this.isAlive = false;
            } else {
                setHealth(damageHealth);
            }
        } else {
            setArmor(damageArmor);
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s: %s", this.getClass().getSimpleName(), this.username)).append(System.lineSeparator());
        builder.append(String.format("--Health: %d", this.health)).append(System.lineSeparator());
        builder.append(String.format("--Armor: %d", this.armor)).append(System.lineSeparator());
        builder.append(String.format("--Gun: %s", this.gun.getName()));

        return builder.toString().trim();
    }
}
