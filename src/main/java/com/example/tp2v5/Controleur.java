package com.example.tp2v5;


import javafx.scene.paint.Color;

/**
 * Affiche les elements a la vue et update les donner du modele.
 * @author Jordan Boulais Richard
 * @author abdelrahmanibrahim
 */
public class Controleur {

    private Modele modele;
    private FlappyGhost vue;

    /**
     * Constructeur Controleur.
     * Assign la vue et le modele.
     *
     * @param vue La vue du jeu.
     */
    public Controleur(FlappyGhost vue)
    {
        this.modele = new Modele();
        this.vue = vue;
    }

    /**
     * Getter attribut 'modele'.
     *
     * @return attribut 'modele'.
     */
    public Modele getModele() {
        return this.modele;
    }

    /**
     * Getter attribut 'vue'.
     *
     * @return attribut 'vue'.
     */
    public FlappyGhost getVue(){return this.vue;}

    /**
     * Update la vue et le modele. Reposition tout les entity. (BGs, Obstacle, Ghost)
     * Regarde si il y a collision entre le Ghost et les obstacle.
     * Si oui, on reset la partie si DEBUGMODE est a false.
     *
     *
     * @param deltaTime temps depuis le dernier moment.
     */
    public void updateVueModele(double deltaTime){

        // On reset le canvas
        vue.getContext().clearRect(0, 0, FlappyGhost.largeurJeu, FlappyGhost.largeurJeu);

        // On update les positions des BGs, Obstacles et Ghost
        updateEverything(deltaTime);

        // On affiche les BGs
        displayBGs();

        // On regarde si il y a collision
        Obstacle collision = modele.collision();
        // SI PAS EN MODE DEBUG ON RESET LA PARTIE
        if (!Modele.DEBUGMODE && collision != null) {
            resetPartie();

        }

        //---------------- NON DEBUG ----------------
        //
        if (!Modele.DEBUGMODE) {
            ////// DISPLAY GHOST
            displayNonDebugMode();
        }
        else {
            //----------------  DEBUG ----------------
            displayDebugMode(collision);
        }

        // Ajoute nouvel obstacle A CHAQUE 3 SECONDES
        if ((vue.getTimer().getNowTime() - vue.getTimer().getTimeSinceLastObject()) * 1e-9 > 3)
        {
            modele.generateNewObstacle();
            vue.getTimer().setTimeSinceLastObject(vue.getTimer().getNowTime());
        }

        // REGARDE SI UN OBSTACLE A ETE DEPASSER
        modele.calulateObstacleDepasse();
        if (modele.getObstacleDepasser() != 0 && modele.getIncreaseDifficulty())
        {
            Modele.VITESSEX += 15; // augmente la vitesse du jeux.
            Modele.gravity += 15;  // augmente la gravite du jeux.
            vue.getScore().setText("Score: " + (modele.getObstacleDepasser() * 5));  // augmente le score.
            modele.setIncreaseDifficulty(false);  // on set l'attribut 'increaseDifficulty' a 'false'.
        }

    }

    /**
     * Affiche les obstacles et Ghost en mode debug.
     * Ghost est une sphere noir et les obstacles des spheres jaune.
     * Si Ghost et un obstacle sont en collision, l'obstacle est
     * afficher en rouge.
     *
     * @param collision
     */
    public void displayDebugMode(Obstacle collision)
    {
        // DISPLAY GHOST
        vue.getContext().setFill(Color.BLACK);
        vue.getContext().fillOval(modele.getGhost().getX(), modele.getGhost().getY(), 60,60);
        // DISPLAY OBJECTS
        for(Obstacle obstacle: modele.getObstacles())
        {
            vue.getContext().setFill(Color.YELLOW);

            if(collision == obstacle)
            {
                vue.getContext().setFill(Color.RED);
            }
            vue.getContext().fillOval(obstacle.getX(), obstacle.getY(), obstacle.getSize(), obstacle.getSize());
        }
    }

    /**
     * Affiche les obstacles et Ghost en mode non-debug.(avec leur image)
     *
     */
    public void displayNonDebugMode()
    {
        vue.getContext().drawImage(modele.getGhost().getImage().getImage(), modele.getGhost().getX(), modele.getGhost().getY());

        // DISPLAY OBJECTS
        for(Obstacle obstacle: modele.getObstacles())
        {
            vue.getContext().drawImage(obstacle.getImage().getImage(), obstacle.getX(), obstacle.getY(), obstacle.getSize(), obstacle.getSize());
        }
    }

    /**
     * Update position Bgs, obstacles et Ghost.
     *
     * @param deltaTime difference de temps depuis le dernier moment.
     */
    public void updateEverything(double deltaTime)
    {
        this.updateBGsPosition(deltaTime);
        this.updateObstaclesPosition(deltaTime);
        modele.getGhost().move(deltaTime);
    }

    /**
     * Update position Bgs
     *
     * @param deltaTime difference de temps depuis le dernier moment.
     */
    public void updateBGsPosition(double deltaTime)
    {
        for (BG bg : modele.getBgs())
        {
            bg.move(deltaTime);
        }

    }

    /**
     * Update positions des obstacles.
     * Si un obstacles sort du cadre de la zone de jeux
     * il est retirer de l'attribut 'obstacles'.
     *
     * @param deltaTime difference de temps depuis le dernier moment.
     */
    public void updateObstaclesPosition(double deltaTime)
    {
        Obstacle obstacleToRemove = null;
        for (Obstacle obstacle: modele.getObstacles())
        {
            obstacle.move(deltaTime);
            // enleve obstacle si hors de la zone de jeux.
            if (obstacle.getX() + obstacle.getSize() < 0)
            {
                obstacleToRemove = obstacle;
            }
        }

        modele.getObstacles().remove(obstacleToRemove);
    }

    /**
     * Dessine le BG selon la difference de temps.
     *
     */
    public void displayBGs()
    {
        BG[] bgs = modele.getBgs();
        for(BG bg : bgs) {
            vue.getContext().drawImage(bg.getImage().getImage(), bg.getX(), bg.getY());
        }
    }

    /**
     * Remet la partie a neuf. La gravite, vitesse et position du fantome ont leur
     * valeurs remis a leur valeur de depart.
     * Enleve aussi tout les obstacles.
     *
     */
    public void resetPartie()
    {
        Modele.gravity = 500;
        Modele.VITESSEX = 120;
        modele.setObstacleDepasser(0);
        modele.setIncreaseDifficulty(false);
        modele.getGhost().setX(FlappyGhost.largeurJeu/2 - 30);
        modele.getGhost().setY(FlappyGhost.hauteurJeu/2 - 30);
        modele.getGhost().setVy(0);
        modele.getObstacles().clear();
        modele.setPoints(0);
        vue.getScore().setText("Score: 0");

    }

}