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


public class Tas {
    public static final String EVT_LIGNE_COMPLETE = "COMPLETE";
    private List<Element> elements;
    private Puits puits;
    private PropertyChangeSupport pcs;

    public Tas(Puits puits, int nbElements, int nbLignes, Random rand) throws IllegalArgumentException {
        if (nbElements > puits.getLargeur() * nbLignes || nbElements > puits.getLargeur() * puits.getProfondeur()) {
            throw new IllegalArgumentException("Erreur lors de la cr\u00e9ation du tas : trop d'\u00e9l\u00e9ments !");
        }
        this.elements = new ArrayList<Element>();
        this.puits = puits;
        if (rand == null) {
            rand = new Random();
        }
        this.construireTas(nbElements, nbLignes, rand);
        this.pcs = new PropertyChangeSupport(this);
    }

    public Tas(Puits puits) throws IllegalArgumentException {
        this(puits, 0, 0, null);
    }

    public Tas(Puits puits, int nbElements) throws IllegalArgumentException {
        this(puits, nbElements, nbElements / puits.getLargeur() + 1, null);
    }

    public Tas(Puits puits, int nbElements, int nbLignes) throws IllegalArgumentException {
        this(puits, nbElements, nbLignes, null);
    }

    private void construireTas(int nbElements, int nbLignes, Random rand) throws IllegalArgumentException {
        if (nbElements > this.puits.getLargeur() * nbLignes || nbElements > this.puits.getLargeur() * this.puits.getProfondeur()) {
            throw new IllegalArgumentException("Erreur lors de la cr\u00e9ation du tas : trop d'\u00e9l\u00e9ments !");
        }
        int nbPlace = 0;
        Couleur[] couleurs = Couleur.values();
        while (nbPlace != nbElements) {
            int ordon = this.puits.getProfondeur() - rand.nextInt(nbLignes + 1);
            int absci = rand.nextInt(this.puits.getLargeur());
            boolean occupe = false;
            for (Element elt : this.elements) {
                if (!new Coordonnees(absci, ordon).equals(elt.getCoordonnees())) continue;
                occupe = true;
            }
            if (occupe) continue;
            int indiceCouleur = rand.nextInt(couleurs.length);
            this.elements.add(new Element(new Coordonnees(absci, ordon), couleurs[indiceCouleur]));
            ++nbPlace;
        }
    }

    public void ajouterElements(Piece piece) {
        this.elements.addAll(piece.getElements());
        if (isGameOver()){
            VueGameOver gameOverView = (VueGameOver) Globals.routeur.getVue("GAMEOVER");
            gameOverView.updateScore();
            Globals.routeur.afficherVue("GAMEOVER");
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public List<Element> getElements() {
        return this.elements;
    }

    public Puits getPuits() {
        return this.puits;
    }

    public boolean isGameOver() {
        for (Element elt : this.elements) {
            if (elt.getCoordonnees().getOrdonnee() <= 0) {
                return true;
            }
        }
        return false;
    }

    public void supprimerLignesCompletes() {
        int largeur = puits.getLargeur();
        int profondeur = puits.getProfondeur();

        int[] compteurs = new int[profondeur];
        for (Element elt : elements) {
            int y = elt.getCoordonnees().getOrdonnee();
            if (y >= 0 && y < profondeur) {
                compteurs[y]++;
            }
        }

        int lignesSupprimees = 0;

        for (int y = 0; y < profondeur; y++) {
            final int ligne = y;
            if (compteurs[ligne] == largeur) {
                elements.removeIf(elt -> elt.getCoordonnees().getOrdonnee() == ligne);

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

        if (lignesSupprimees > 0) {
            Globals.score.ajouter(lignesSupprimees * 100);
        }   
    }

    public void clear() {
        this.elements.clear();
    }
}

