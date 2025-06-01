package src.fr.eseo.e3.poo.projet.blox.modele.pieces;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import src.fr.eseo.e3.poo.projet.blox.utils.Globals;
import src.fr.eseo.e3.poo.projet.blox.views.VueGameOver;

/**
 * Représente le tas d'éléments dans le jeu Tetris.
 * Le tas est composé des éléments qui sont tombés et se sont empilés au fond du puits.
 * Il gère l'ajout de nouvelles pièces, la suppression des lignes complètes
 * et la détection de fin de partie.
 *
 * @author Hugo
 */
public class Tas {
    /** Nom de l'événement déclenché lorsqu'une ligne est complétée */
    public static final String EVT_LIGNE_COMPLETE = "COMPLETE";
    
    /** Liste des éléments composant le tas */
    private List<Element> elements;
    
    /** Référence au puits dans lequel le tas évolue */
    private Puits puits;
    
    /** Support pour la gestion des événements de propriété */
    private PropertyChangeSupport pcs;

    /**
     * Constructeur principal du tas.
     * Initialise le tas avec un nombre spécifié d'éléments répartis sur un nombre de lignes.
     *
     * @param puits Le puits dans lequel le tas évolue
     * @param nbElements Le nombre d'éléments à créer
     * @param nbLignes Le nombre de lignes sur lesquelles répartir les éléments
     * @param rand Le générateur de nombres aléatoires à utiliser
     * @throws IllegalArgumentException Si le nombre d'éléments est trop grand
     */
    public Tas(Puits puits, int nbElements, int nbLignes, Random rand) throws IllegalArgumentException {
        if (nbElements > puits.getLargeur() * nbLignes || nbElements > puits.getLargeur() * puits.getProfondeur()) {
            throw new IllegalArgumentException("Erreur lors de la création du tas : trop d'éléments !");
        }
        this.elements = new ArrayList<Element>();
        this.puits = puits;
        if (rand == null) {
            rand = new Random();
        }
        this.construireTas(nbElements, nbLignes, rand);
        this.pcs = new PropertyChangeSupport(this);
    }

    /**
     * Constructeur d'un tas vide.
     *
     * @param puits Le puits dans lequel le tas évolue
     */
    public Tas(Puits puits) throws IllegalArgumentException {
        this(puits, 0, 0, null);
    }

    /**
     * Constructeur d'un tas avec un nombre spécifié d'éléments.
     *
     * @param puits Le puits dans lequel le tas évolue
     * @param nbElements Le nombre d'éléments à créer
     */
    public Tas(Puits puits, int nbElements) throws IllegalArgumentException {
        this(puits, nbElements, nbElements / puits.getLargeur() + 1, null);
    }

    /**
     * Constructeur d'un tas avec un nombre spécifié d'éléments et de lignes.
     *
     * @param puits Le puits dans lequel le tas évolue
     * @param nbElements Le nombre d'éléments à créer
     * @param nbLignes Le nombre de lignes sur lesquelles répartir les éléments
     */
    public Tas(Puits puits, int nbElements, int nbLignes) throws IllegalArgumentException {
        this(puits, nbElements, nbLignes, null);
    }

    /**
     * Construit le tas en plaçant aléatoirement les éléments.
     *
     * @param nbElements Le nombre d'éléments à placer
     * @param nbLignes Le nombre de lignes disponibles
     * @param rand Le générateur de nombres aléatoires
     * @throws IllegalArgumentException Si le nombre d'éléments est trop grand
     */
    private void construireTas(int nbElements, int nbLignes, Random rand) throws IllegalArgumentException {
        if (nbElements > this.puits.getLargeur() * nbLignes || nbElements > this.puits.getLargeur() * this.puits.getProfondeur()) {
            throw new IllegalArgumentException("Erreur lors de la création du tas : trop d'éléments !");
        }
        int nbPlace = 0;
        Couleur[] couleurs = Couleur.values();
        while (nbPlace != nbElements) {
            // Génération de coordonnées aléatoires
            int ordon = this.puits.getProfondeur() - rand.nextInt(nbLignes + 1);
            int absci = rand.nextInt(this.puits.getLargeur());
            
            // Vérification que la position n'est pas déjà occupée
            boolean occupe = false;
            for (Element elt : this.elements) {
                if (!new Coordonnees(absci, ordon).equals(elt.getCoordonnees())) continue;
                occupe = true;
            }
            if (occupe) continue;
            
            // Ajout d'un nouvel élément avec une couleur aléatoire
            int indiceCouleur = rand.nextInt(couleurs.length);
            this.elements.add(new Element(new Coordonnees(absci, ordon), couleurs[indiceCouleur]));
            ++nbPlace;
        }
    }

    /**
     * Ajoute les éléments d'une pièce au tas.
     * Vérifie si la partie est terminée après l'ajout.
     *
     * @param piece La pièce dont les éléments doivent être ajoutés
     */
    public void ajouterElements(Piece piece) {
        this.elements.addAll(piece.getElements());
        if (isGameOver()){
            VueGameOver gameOverView = (VueGameOver) Globals.routeur.getVue("GAMEOVER");
            gameOverView.updateScore();
            Globals.routeur.afficherVue("GAMEOVER");
        }
    }

    /**
     * Ajoute un écouteur pour les changements de propriétés.
     *
     * @param listener L'écouteur à ajouter
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * Retire un écouteur des changements de propriétés.
     *
     * @param listener L'écouteur à retirer
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    /**
     * Retourne la liste des éléments du tas.
     *
     * @return La liste des éléments
     */
    public List<Element> getElements() {
        return this.elements;
    }

    /**
     * Retourne le puits associé au tas.
     *
     * @return Le puits
     */
    public Puits getPuits() {
        return this.puits;
    }

    /**
     * Vérifie si la partie est terminée.
     * La partie est terminée si un élément atteint le haut du puits.
     *
     * @return true si la partie est terminée, false sinon
     */
    public boolean isGameOver() {
        for (Element elt : this.elements) {
            if (elt.getCoordonnees().getOrdonnee() <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Supprime les lignes complètes et fait descendre les éléments au-dessus.
     * Met à jour le score en fonction du nombre de lignes supprimées.
     */
    public void supprimerLignesCompletes() {
        int largeur = puits.getLargeur();
        int profondeur = puits.getProfondeur();

        // Comptage des éléments par ligne
        int[] compteurs = new int[profondeur];
        for (Element elt : elements) {
            int y = elt.getCoordonnees().getOrdonnee();
            if (y >= 0 && y < profondeur) {
                compteurs[y]++;
            }
        }

        int lignesSupprimees = 0;

        // Suppression des lignes complètes
        for (int y = 0; y < profondeur; y++) {
            final int ligne = y;
            if (compteurs[ligne] == largeur) {
                // Suppression des éléments de la ligne
                elements.removeIf(elt -> elt.getCoordonnees().getOrdonnee() == ligne);

                // Descente des éléments au-dessus
                for (Element elt : elements) {
                    if (elt.getCoordonnees().getOrdonnee() < ligne) {
                        elt.setCoordonnes(new Coordonnees(
                            elt.getCoordonnees().getAbscisse(),
                            elt.getCoordonnees().getOrdonnee() + 1
                        ));
                    }
                }

                lignesSupprimees++; 
                supprimerLignesCompletes();
                break;
            }
        }

        // Mise à jour du score
        if (lignesSupprimees > 0) {
            Globals.score.ajouter(lignesSupprimees * 100);
        }   
    }

    /**
     * Vide le tas en supprimant tous ses éléments.
     */
    public void clear() {
        this.elements.clear();
    }
}

