package game.engine.entities;

import game.interfaces.Collidable;
import game.interfaces.CollisionDetector;
import game.interfaces.Drawable;
import game.utils.math.Vector2D;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public class BulletManager implements Collidable, Drawable {
    private static BulletManager instance = new BulletManager();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Bullet> otherBullets = new ArrayList<>();

    private BulletManager() {
    }

    public static BulletManager getInstance() {
        return instance;
    }

    public void setOtherBullets(ArrayList<Bullet> otherBullets) {
        this.otherBullets = otherBullets;
    }

    public void add(Bullet bullet) {
        bullets.add(bullet);
    }

    public int getBulletCount() {
        return bullets.size();
    }

    @Override
    public void tick(CollisionDetector collisionDetector) {
        bullets.forEach(bullet -> bullet.tick(collisionDetector));
        bullets = new ArrayList<>(bullets.stream().filter(Bullet::isAlive).collect(Collectors.toList()));
    }


    @Override
    public void draw() {
        bullets.forEach(Bullet::draw);
        otherBullets.forEach(Bullet::draw);
    }

    public boolean collidesWith(Vector2D player, CollisionDetector collisionDetector) {
        for (Bullet bullet : bullets) {
            if (collisionDetector.collidesWithBullet(player, Tank.SIZE, bullet.getPosition(), Bullet.SIZE))
                return true;
        }
        for (Bullet bullet : otherBullets) {
            if (collisionDetector.collidesWithBullet(player, Tank.SIZE, bullet.getPosition(), Bullet.SIZE))
                return true;
        }
        return false;
    }
}
