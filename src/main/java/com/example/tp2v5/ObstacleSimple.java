package com.example.tp2v5;

public class ObstacleSimple extends Obstacle{

    /**
     * Deplace l'obstacle en 'x' seulement selon la vitesse du jeux.
     *
     * @param deltaTime difference de temps.
     */
    @Override
    public void move(double deltaTime)
    {
        setX(this.getX() - deltaTime * Modele.VITESSEX);
    }

}
