package src.fr.eseo.e3.poo.projet.blox.modele;

import java.util.Objects;

public class Element {
    private Couleur couleur;
    private Coordonnees coordonnes;

    public Element(Coordonnees coordonnees) {
        this.coordonnes = coordonnees;
        this.couleur = Couleur.values()[0];
    }

    public Element(int abcisse, int ordonnee){
        this.coordonnes = new Coordonnees(abcisse, ordonnee);
        this.couleur = Couleur.values()[0];
    }

    public Element(Coordonnees coordonnees, Couleur couleur){
        this.coordonnes = coordonnees;
        this.couleur = couleur;
    }

    public void deplacerDe(int deltaX, int deltaY){
        setCoordonnes(new Coordonnees(this.getCoordonnees().getAbscisse() + deltaX, this.getCoordonnees().getOrdonnee() + deltaY));
    }

    /**
     * Redefinition of the {@code toString} method so it returns
     * the coordinates and color of our {@code Element}
     *
     * @return the {@code Coordonnes} and the {@code Couleur} of our {@code Element}
     */
    @Override
    public String toString() {
        return this.coordonnes.toString()+" - "+this.couleur;
    }

    /**
     * Redefinition of the {@code equals} method so it also compares
     * if the two objects have the same {@code Coordonnes} and {@code Couleur}
     *
     * @param o an object to compare to our {@code Element} instance
     * @return {@code true} if the instance and the Object are the same, or they have the
     * same {@code Coordonnes} and same {@code Couleur} -- {@code false} if the Object is not
     * a {@code instanceof} Element
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element element = (Element) o;
        return couleur == element.couleur && Objects.equals(coordonnes, element.coordonnes);
    }

    /**
     * Redefinition of the {@code hashCode} method so it returns
     * the hashCode of our {@code Couleur} and {@code Coordonnes}
     *
     * @return hash value of the parameters of our instance of {@code Element}
     */
    @Override
    public int hashCode() {
        return Objects.hash(couleur, coordonnes);
    }

    /**
     * Returns the coordinates of our {@code Element}
     *
     * @return coordinates
     */
    public Coordonnees getCoordonnees() {
        return this.coordonnes;
    }

    /**
     * Returns the color of our {@code Element}
     *
     * @return color
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Set the cordinates of our {@code Element}
     *
     * @param coordonnes new coordinates
     */
    public void setCoordonnes(Coordonnees coordonnes) {
        this.coordonnes = coordonnes;
    }

    /**
     * Set the color of our {@code Element}
     *
     * @param couleur new color
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }
}

