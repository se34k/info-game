package com.infolk.game.combat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.infolk.game.screens.components.HealthBar;

public abstract class Entity {

    private int hp;

    private HealthBar bar;
    private float barWidth, barHeight;
    public boolean displayBar = true;

    private Vector2 direction;
    private float speed;
    private Sprite sprite;

    private boolean isCollider;

    private String name;

    private Rectangle hitbox;

    protected Entity(String name, int hp, Sprite sprite) {
        this.name = name;

        // Initialize vector with 0, 0
        direction = new Vector2(0, 0);
        speed = 100;

        setSprite(sprite);

        hitbox = new Rectangle();
        adjustHitbox();

        isCollider = true;

        this.hp = hp;

        barWidth = getSprite().getWidth();
        barHeight = barWidth / 5;

        bar = new HealthBar(getBarX(), getBarY(), barWidth, barHeight, hp, hp);
    }

    protected Entity(String name, int hp, Sprite sprite, float x, float y) {
        this(name, hp, sprite);

        setPosition(x, y);
    }

    /**
     * Sets this entity's sprite
     * 
     * @param sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    protected void adjustHitbox() {
        hitbox.setX(getX());
        hitbox.setY(getY());
        hitbox.setWidth(sprite.getWidth());
        hitbox.setHeight(sprite.getHeight());
    }

    /**
     * Gibt ein Rectangle zurück, das der um einen über den Zeitraum delta gemachten
     * Schritt bewegten Hitbox des Entities entspricht.
     * Dieses Rectangle kann mittels der Parameter xFactor und yFactor von der
     * Position her modifiziert werden, also etwa um den zwei-
     * fachen Schritt bewegt werden, wenn man für xFactor und yFactor 2 übergibt.
     * 
     * @param delta   Die Zeit, die für die Ermittlung der Größe des Schritts
     *                genutzt wird
     * @param xFactor Der Faktor, um den der Schritt in x-Achsen-Richtung
     *                multipliziert wird
     * @param yFactor Der Faktor, um den der Schritt in y-Achsen-Richtung
     *                multipliziert wird
     * 
     * @return Das Rectangle, das der bewegten Hitbox des Entities entspricht
     */
    public Rectangle getProjected(float delta, int xFactor, int yFactor) {
        return new Rectangle(getX() + getVelocity().x * delta * xFactor, getY() + getVelocity().y * delta * yFactor,
                hitbox.getWidth(), hitbox.getHeight());
    }

    /**
     * Setzt die Geschwindigkeit des Entities
     * 
     * @param speed Die neue Geschwindigkeit des Entities
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Gibt die gerichtete Geschwindigkeit des Entities als Vector2 zurück
     * 
     * @return Die gerichtete Geschwindigkeit des Entities
     */
    public Vector2 getVelocity() {
        return new Vector2(direction.x * speed, direction.y * speed);
    }

    /**
     * Gibt den Richtungsvektor des Entities zurück.
     * 
     * @return Der Richtungsvektor des Entities
     */
    public Vector2 getDirection() {
        return direction;
    }

    /**
     * Gibt die Hitbox des Entities zurück.
     * 
     * @return Die Hitbox des Charakters
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Gibt zurück, ob das Entity die übergebenen Entity berührt.
     * 
     * @param entity Die zu prüfende Entity
     * @return Ein boolean, der angibt, ob das Entity berührt wird
     */
    public boolean overlaps(Entity entity) {
        return hitbox.overlaps(entity.getHitbox());
    }

    /**
     * Gibt den Namen des Entity zurück
     * 
     * @return Der Entity-Name
     */
    public String getName() {
        return name;
    }

    /**
     * Bewegt die Entity um seine umgekehrte Richtung, kann mit factor multipliziert
     * werden.
     * 
     * @param factor Der Faktor, um den der Schritt zurück getan werden soll.
     */
    public void moveBack(Vector2 factor) {
        factor.x = factor.x * getDirection().x * -1;
        factor.y = factor.y * getDirection().y * -1;

        move(factor.x, factor.y);
    }

    /**
     * Bewegt das Entity entsprechend seiner Geschwindigkeit und die in delta
     * übergebene vergangene Zeit.
     * 
     * @param delta Die vergangene Zeit seit dem letzten Schritt
     */
    public void move(float delta) {
        move(getVelocity().x * delta, getVelocity().y * delta);
    }

    /**
     * Bewegt das Entity horizontal entsprechend seiner Geschwindigkeit und der in
     * delta übergebenen Zeit.
     * 
     * @param delta Die vergangene Zeit seit dem letzten Schritt
     */
    public void moveHoriz(float delta) {
        move(getVelocity().x * delta, 0);
    }

    /**
     * Bewegt das Entity vertikal entsprechend seiner Geschwindigkeit und der in
     * delta übergebenen Zeit.
     * 
     * @param delta Die vergangene Zeit seit dem letzten Schritt
     */
    public void moveVert(float delta) {
        move(0, getVelocity().y * delta);
    }

    /**
     * Ändert die Position des Entities um die in x und y angegebenen Schritte
     * 
     * @param x Die Zahl, um die die x-Position geändert werden soll
     * @param y Die Zahl, um die die y-Position geändert werden soll
     */
    public void move(float x, float y) {
        setPosition(getX() + x, getY() + y);
    }

    /**
     * Setzt die Richtung des Entities
     * 
     * @param direction Ein Vector2 mit der Richtung. Sollte als Koordinaten nur
     *                  Ganzzahlen im Intervall [-1; 1] besitzen.
     */
    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    /**
     * Setzt die Position des Entities auf x und y
     * 
     * @param x Die zu setzende x-Position
     * @param y Die zu setzende y-Position
     */
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        hitbox.setPosition(x, y);
    }

    /**
     * Bewegt das Entity um den übergebenen x-Wert
     * 
     * @param x Die x-Länge des Schritts
     */
    public void moveX(float x) {
        move(x, 0);
    }

    public void moveY(float y) {
        move(0, y);
    }

    public float getX() {
        return this.sprite.getX();
    }

    public float getY() {
        return this.sprite.getY();
    }

    /**
     * Gibt die aktuelle Position des Entities als Vector2 zurück.
     * 
     * @return Ein Vector2 mit der aktuellen Position
     */
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    /**
     * Zeichnet das Sprite des Entities.
     * 
     * @param batch Der zu nutzende SpriteBatch
     */
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);

        if (displayBar) {
            bar.x = getBarX();
            bar.y = getBarY();
            bar.update();
            bar.draw(batch);
        }
    }

    private float getBarX() {
        return getSprite().getX() + getSprite().getWidth() / 2 - barWidth / 2;
    }

    private float getBarY() {
        return getSprite().getY() + getSprite().getHeight();
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void changeHP(int change) {
        setHP(hp + change);
        bar.setCurrentHealth(hp);
    }

    /**
     * Can be used to calculate the distance between this entity and another given
     * entity, for example
     * to use in the combat system when checking whether two opponents are near each
     * other
     * 
     * @param entity The entity to compare to
     * 
     * @return A float value with the distance between the two entities
     */
    public float distanceTo(Entity entity) {
        // Pythagoras is our friend
        return (float) Math.sqrt(Math.pow(entity.getX() - getX(), 2) + Math.pow(entity.getY() - getY(), 2));
    }

    /**
     * Gibt den Vekto, der diese Entity mit der übergebenen verbindet, zurück.
     * 
     * @param entity Die Entity, mit der der Vektor berechnet werden soll
     * 
     * @return Ein Vector2, der die Verbindung zwischen diesem Entity und entity
     *         beschreibt
     */
    public Vector2 vectorTo(Entity entity) {
        return entity.getPosition().sub(getPosition());
    }

    /**
     * Wird aufgerufen, wenn das Entity mit anderen Entities kollidiert
     * 
     * @param targets Die kollidierenden Entities
     */
    public void onCollision(ArrayList<Entity> targets) {
        // Placeholder
    }
}
