package src.fr.eseo.e3.poo.projet.blox.modele;

import java.util.Objects;

public class Coordonnees {
    private int abscisse;
    private int ordonnee;

    public Coordonnees(int abscisse, int ordonnee){
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    /**
     * Redefinition of the {@code toString} method so it returns
     * the x-coordinate and the y-coordinate of our {@code Coordonnees} instance
     *
     * @return the {@code abscisse} ( x-coordinate ) and the {@code ordonnee} ( y-coordinate ) of our {@code Coordonnees} instance
     */
    @Override
    public String toString() {
        return "("+abscisse+", "+ordonnee+")";
    }

    /**
     * Redefinission of the {@code equals} method so it also compares
     * if the two objects have the same {@code abscisse} ( x-coordinate ) and {@code ordonnee} ( y-coordinate )
     *
     * @param o an object to compare to our {@code Coordonnees} instance
     * @return {@code true} if the instance and the Object are the same, or they have the
     * same {@code abscisse} ( x-coordinate ) and same {@code ordonnee} ( y-coordinate ) -- {@code false} if the Object is not
     * a {@code instanceof} Coordonnees
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordonnees)) return false;
        Coordonnees coord = (Coordonnees) o;
        return this.abscisse == coord.abscisse && this.ordonnee == coord.ordonnee;
    }

    /**
     * Redefinission of the {@code hashCode} method so it returns
     * the hashCode of our {@code abscisse} ( x-coordinate ) and {@code ordonnee} ( y-coordinate )
     *
     * @return hash value of the parameters of our instance of {@code Coordonnees}
     */
    @Override
    public int hashCode() {
        return Objects.hash(abscisse, ordonnee);
    }

    /**
     * Returns the x-coordinate of our {@code Coordonnees} instance
     *
     * @return  x-coordinate
     */
    public int getAbscisse() {
        return this.abscisse;
    }

    /**
     * Returns the y-coordinate of our {@code Coordonnees} instance
     *
     * @return  y-coordinate
     */
    public int getOrdonnee() {
        return this.ordonnee;
    }

    /**
     * Set the x-coordinate of our {@code Coordonnees} instance
     *
     * @param abscisse new x-coordinate
     */
    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }

    /**
     * Set the y-coordinate of our {@code Coordonnees} instance
     *
     * @param ordonnee new y-coordinate
     */
    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }
}
