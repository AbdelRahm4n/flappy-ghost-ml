package com.example.tp2v5;

import javafx.scene.image.ImageView;

/**
 *  Personnage/Joueur
 */
public class Ghost extends Entity{


    /**
     * Constructor de Ghost
     * Assign les attributs de base.
     * Position Ghost au centre de la zone de jeux.
     * Assign l'image de Ghost.
     * @author Jordan Boulais Richard
     * @author abdelrahmanibrahim
     */
    public Ghost()
    {
        // Set Ghost position et image.
        this.setX(FlappyGhost.largeurJeu/2 - 30);
        this.setY(FlappyGhost.hauteurJeu/2 - 30);
        this.setImage(new ImageView("file:ghost.png"));
        this.getImage().setPreserveRatio(true);
        this.getImage().setFitWidth(60);
        this.setVy(0);
        this.setSize(60);
    }

    /**
     * Deplace Ghost en Y seulement selon sa vitesse et la gravite.
     * Lorsque que Ghost touche le haut ou bas de la zone de jeux
     * il rebondit.
     *
     * @param deltaTime difference de temps.
     */
    @Override
    public void move(double deltaTime)
    {

        this.setVy( this.getVy() + (float) deltaTime * Modele.gravity);
        double newY = (float) (this.getY() + deltaTime * this.getVy());

        // Si Ghost touche le haut ou bas de la zone de jeux, il rebondit.
        if (newY > FlappyGhost.hauteurJeu - this.getSize())
        {
            newY = FlappyGhost.hauteurJeu - this.getSize();
            this.setVy(this.getVy()*-1);
        } else if (newY < 0) {
            this.setVy(this.getVy()*-1);
            newY = 0;
        }
       // si la vitesse est plus grande que la vitesse max, on la met a la vitesse max.
        if (this.getVy() > Modele.maxVY) {
            this.setVy(Modele.maxVY);
        } else if (this.getVy() < - Modele.maxVY) {
            this.setVy(- Modele.maxVY);
        }

        this.setY(newY);

    }

}