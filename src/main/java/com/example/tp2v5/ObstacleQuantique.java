package com.example.tp2v5;

import java.util.Random;

public class ObstacleQuantique extends Obstacle{

    double time=0;
    double centerY = 0;
    double centerX = 0;
    double randomX;
    double randomY;
    Random random = new Random();

    /**
     * Constructeur com.example.tp2v5.ObstacleSinus.
     *
     * Assign l'attribut startY qui represent le point en y
     * a partir du quel l'obstacle va osciller.
     *
     * @author Jordan Boulais Richard
     * @author abdelrahmanibrahim
     */
    ObstacleQuantique()
    {
        centerY = this.getY();
        centerX = this.getX();
        randomPosition();
    }

    /**
     * Deplace le centre de l'obstacle en 'x' selon la vitesse du jeux.
     *
     * ajoute une position random a l'obstacle toute les 0.2 secondes.
     *
     * @param deltaTime difference de temps.
     */
    @Override
    public void move(double deltaTime)
    {
        centerX = centerX - deltaTime * Modele.VITESSEX;

        time += deltaTime;
        if (time > 0.2)
        {
            randomPosition();
            time = 0;
        }

        setX(centerX + randomX);
        setY(centerY + randomY);

    }

    /**
     * Assigne une position random entre 30 et -30 en x et a y
     * a l'obstacle
     *
     */
    public void randomPosition()
    {
        randomX = random.nextInt(60)-30;
        randomY = random.nextInt(60)-30;
    }

}
