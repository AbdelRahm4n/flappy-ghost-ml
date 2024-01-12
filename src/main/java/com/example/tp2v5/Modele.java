package com.example.tp2v5;

import java.util.Random;
import java.util.ArrayList;

/**
 * Contient les données essentielles au jeu et au calcul des collisions
 * Classe Modele.
 * @author Jordan Boulais Richard
 * @author abdelrahmanibrahim
 */
public class Modele {

    public static boolean DEBUGMODE = false;
    public static boolean INPAUSE = false;
    private boolean increaseDifficulty = false;
    private int points;
    private int obstacleDepasser; // Afin d'augmenter la difficulte.
    private Entity[] entities = new Entity[]{};
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private BG[] bgs = new BG[]{new BG(0,0), new BG(640, 0)};
    private Ghost ghost = new Ghost();
    public static double gravity = 500;
    public static double maxVY = 300;
    public static double VITESSEX = 120;
    public Random random = new Random();

    // GETTER
    //

    /**
     * Getter attribut 'point'.
     *
     * @return attribut 'point'.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter attribut 'obstacleDepasser'.
     * (Nomber d'obstacles depasser)
     *
     * @return attribut 'obstacleDepasser'.
     */
    public int getObstacleDepasser() {
        return obstacleDepasser;
    }

    /**
     * Getter attribut 'increaseDifficulty'.
     *boolean attribut pour regarder si la difficulte doit etre augmenter.
     *
     * @return attribut 'increaseDifficulty'.
     */
    public boolean getIncreaseDifficulty() {
        return increaseDifficulty;
    }

    /**
     * Setter attribut 'increaseDifficulty'
     *
     * @param increaseDifficulty value pour 'increaseDifficulty' attribut.
     */
    public void setIncreaseDifficulty(boolean increaseDifficulty) {
        this.increaseDifficulty = increaseDifficulty;
    }

    /**
     * Getter attribut 'entities'.
     *
     * @return attribut 'entities'.
     */
    public Entity[] getEntities() {
        return entities;
    }

    /**
     * Getter attribut 'obstacles'.
     *
     * @return attribut 'obstacles'.
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Getter attribut 'bgs'.
     *
     * @return attribut 'bgs'.
     */
    public BG[] getBgs() {
        return bgs;
    }

    /**
     * Getter attribut 'ghost'.
     *
     * @return attribut 'ghost'.
     */
    public Ghost getGhost() {
        return ghost;
    }

    // SETTER
    //

    /**
     * Setter attribut 'points'.
     *
     * @param points value pour attribut 'points'.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Setter attribut 'ObstacleDepasser'.
     *
     * @param obstacleDepasser value pour attribut 'ObstacleDepasser'.
     */
    public void setObstacleDepasser(int obstacleDepasser) {
        this.obstacleDepasser = obstacleDepasser;
    }

    /**
     * Setter attribut 'entities'.
     *
     * @param entities value pour attribut 'entities'.
     */
    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    /**
     * Setter attribut 'obstacles'.
     *
     * @param obstacles value pour attribut 'obstacles'.
     */
    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * Setter attribut 'bgs'.
     *
     * @param bgs value pour attribut 'bgs'.
     */
    public void setBgs(BG[] bgs) {
        this.bgs = bgs;
    }

    /**
     * Setter attribut 'ghost'.
     *
     * @param ghost value pour attribut 'ghost'.
     */
    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }


    /**
     * Genere aleatoirement un nouvel obstacle parmis
     * ObstacleSimple, ObstacleSinus et ObstacleQuantique.
     * Nouvel obstacle est ajouter a l'attribut 'obstacles'.
     */
    public void generateNewObstacle()
    {
        int randomObstacle = random.nextInt(3);
        Obstacle obstacle = null;
        switch (randomObstacle) { // Genere un obstacle aleatoire grâce a un switch.
            case 0:
                obstacle = new ObstacleSimple();
                break;

            case 1:
                obstacle = new ObstacleSinus();
                break;

            case 2:
                obstacle = new ObstacleQuantique();
        }


        this.obstacles.add(obstacle); // Ajoute l'obstacle a la liste d'obstacles.

    }

    /**
     * Pour chaque obstacle n'ayant pas l'attribut 'isDepasser'
     * a 'true', on regarde si son extremite de droite a depasser
     * l'extremite gauche de Ghost.
     *
     */
    public void calulateObstacleDepasse(){

        for (Obstacle obstacle : this.obstacles) // Pour chaque obstacle.
        {
            if (obstacle.isDepasser()){ // Si l'obstacle a deja ete depasser, on passe au suivant.

                continue;
            }

            if (obstacle.getX() + obstacle.getSize() < this.ghost.getX()) // Si l'extremite droite de l'obstacle
                                                                           // est plus petite que l'extremite gauche de Ghost.
            {
                obstacle.setDepasser(true); // On set l'attribut 'isDepasser' a 'true'.
                obstacleDepasser += 1;
                if (obstacleDepasser != 0) {
                    increaseDifficulty = true; // On set l'attribut 'increaseDifficulty' a 'true'.
                }
                break; // On devrais depasser un seul obstacle a la fois.
            }

        }

    }

    /**
     * Gère la collision entre Ghost et les obstacles.
     *
     * @return collision null|Obstacle
     */
    public Obstacle collision()
    {
        Obstacle collision = null;

        for (Obstacle obstacle : this.obstacles)
        {
            collision = calculateCollision(obstacle);
            if (collision != null) // Si il y a collision, on retourne l'obstacle.
            {
                return collision;
            }
        }

        return collision;

    }

    /**
     * Calcule la collision entre Ghost et un Obstacle.
     *
     * @param obstacle Obstacle avec lequel verifier si il y a colision.
     */
    public Obstacle calculateCollision(Obstacle obstacle){ // On calcule la collision entre Ghost et l'obstacle.

        Obstacle collision = null;

        double distance = Math.sqrt( Math.pow(ghost.getX() - obstacle.getX() ,2) + Math.pow(ghost.getY() - obstacle.getY() ,2) ); // Distance entre les deux centres.

        double sommeRayon = ghost.getSize()/2 + obstacle.getSize()/2; // Somme des rayons des deux cercles.

        if (distance < sommeRayon) // Si la distance est plus petite que la somme des rayons, il y a collision.
        {
            collision = obstacle;
        }

        return collision;
    }

}