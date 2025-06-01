package src.fr.eseo.e3.poo.projet.blox.modele;

import java.util.Objects;

/**
 * Représente un élément du jeu Tetris.
 * Un élément est l'unité de base qui compose les pièces du jeu.
 * Chaque élément possède une position (coordonnées) et une couleur.
 *
 * @author Hugo
 */
public class Element {
    /** La couleur de l'élément */
    private Couleur couleur;
    
    /** Les coordonnées de l'élément dans le puits */
    private Coordonnees coordonnes;

    /**
     * Constructeur d'un élément avec des coordonnées.
     * Initialise l'élément avec les coordonnées spécifiées et la première couleur disponible.
     *
     * @param coordonnees Les coordonnées de l'élément
     */
    public Element(Coordonnees coordonnees) {
        this.coordonnes = coordonnees;
        this.couleur = Couleur.values()[0];
    }

    /**
     * Constructeur d'un élément avec des coordonnées spécifiques.
     * Initialise l'élément avec les coordonnées (x,y) spécifiées et la première couleur disponible.
     *
     * @param abcisse L'abscisse de l'élément
     * @param ordonnee L'ordonnée de l'élément
     */
    public Element(int abcisse, int ordonnee){
        this.coordonnes = new Coordonnees(abcisse, ordonnee);
        this.couleur = Couleur.values()[0];
    }

    /**
     * Constructeur d'un élément avec des coordonnées et une couleur.
     * Initialise l'élément avec les coordonnées et la couleur spécifiées.
     *
     * @param coordonnees Les coordonnées de l'élément
     * @param couleur La couleur de l'élément
     */
    public Element(Coordonnees coordonnees, Couleur couleur){
        this.coordonnes = coordonnees;
        this.couleur = couleur;
    }

    /**
     * Déplace l'élément d'un certain nombre de cases horizontalement et verticalement.
     * Crée de nouvelles coordonnées en ajoutant les déplacements aux coordonnées actuelles.
     *
     * @param deltaX Le déplacement horizontal (positif pour droite, négatif pour gauche)
     * @param deltaY Le déplacement vertical (positif pour bas, négatif pour haut)
     */
    public void deplacerDe(int deltaX, int deltaY){
        setCoordonnes(new Coordonnees(this.getCoordonnees().getAbscisse() + deltaX, this.getCoordonnees().getOrdonnee() + deltaY));
    }

    /**
     * Retourne une représentation textuelle de l'élément.
     * Le format est "coordonnées - couleur".
     *
     * @return Une chaîne de caractères représentant l'élément
     */
    @Override
    public String toString() {
        return this.coordonnes.toString()+" - "+this.couleur;
    }

    /**
     * Compare cet élément avec un autre objet.
     * Deux éléments sont égaux s'ils ont les mêmes coordonnées et la même couleur.
     *
     * @param o L'objet à comparer avec cet élément
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element element = (Element) o;
        return couleur == element.couleur && Objects.equals(coordonnes, element.coordonnes);
    }

    /**
     * Calcule le code de hachage de l'élément.
     * Le code de hachage est basé sur la couleur et les coordonnées.
     *
     * @return Le code de hachage de l'élément
     */
    @Override
    public int hashCode() {
        return Objects.hash(couleur, coordonnes);
    }

    /**
     * Retourne les coordonnées de l'élément.
     *
     * @return Les coordonnées de l'élément
     */
    public Coordonnees getCoordonnees() {
        return this.coordonnes;
    }

    /**
     * Retourne la couleur de l'élément.
     *
     * @return La couleur de l'élément
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Définit les coordonnées de l'élément.
     *
     * @param coordonnes Les nouvelles coordonnées de l'élément
     */
    public void setCoordonnes(Coordonnees coordonnes) {
        this.coordonnes = coordonnes;
    }

    /**
     * Définit la couleur de l'élément.
     *
     * @param couleur La nouvelle couleur de l'élément
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }
}

