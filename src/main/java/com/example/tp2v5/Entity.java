package com.example.tp2v5;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class abstaite pour toute les objects ayant des position,
 * vitesse, size et une image.
 * @author Jordan Boulais Richard
 * @author abdelrahmanibrahim
 */
public abstract class Entity {

    private double x, y;
    private double vx, vy;
    private double ax, ay;
    private double size;
    private ImageView image;

    /**
     * Setter attibut 'x'.
     *
     * @param x valeur pour 'x'.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Setter attibut 'y'.
     *
     * @param y valeur pour 'y'.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Setter attibut 'vx'.
     *
     * @param vx valeur pour 'vx'.
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * Setter attibut 'vy'.
     *
     * @param vy valeur pour 'vy'.
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    /**
     * Setter attibut 'ax'.
     *
     * @param ax valeur pour 'ax'.
     */
    public void setAx(double ax) {
        this.ax = ax;
    }

    /**
     * Setter attibut 'ay'.
     *
     * @param ay valeur pour 'ay'.
     */
    public void setAy(double ay) {
        this.ay = ay;
    }

    /**
     * Setter attibut 'size'.
     *
     * @param size valeur pour 'size'.
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Setter attribut 'image'.
     *
     * @param image valeur pour 'image'.
     */
    public void setImage(ImageView image) {
        this.image = image;
    }

    /**
     * Getter attribut 'x'.
     *
     * @return attribut 'x'.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter attribut 'y'.
     *
     * @return attribut 'y'.
     */
    public double getY() {
        return y;
    }

    /**
     * Getter attribut 'vx'.
     *
     * @return attribut 'vx'.
     */
    public double getVx() {
        return vx;
    }

    /**
     * Getter attribut 'vy'.
     *
     * @return attribut 'vy'.
     */
    public double getVy() {
        return vy;
    }

    /**
     * Getter attribut 'ax'.
     *
     * @return attribut 'ax'.
     */
    public double getAx() {
        return ax;
    }

    /**
     * Getter attribut 'ay'.
     *
     * @return attribut 'ay'.
     */
    public double getAy() {
        return ay;
    }

    /**
     * Getter attribut 'size'.
     *
     * @return attribut 'size'.
     */
    public double getSize() {
        return size;
    }

    /**
     * Getter attribut 'image'.
     *
     * @return attribut 'image'.
     */
    public ImageView getImage() {
        return image;
    }

    /**
     * Method a etre overwritten par class enfant.
     * Deplace entity selon sa position actuelle et la difference de temps.
     *
     * @param deltaTime difference de temps.
     */
    public abstract void move(double deltaTime);


}

