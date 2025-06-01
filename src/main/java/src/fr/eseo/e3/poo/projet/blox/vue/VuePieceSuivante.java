package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Vue représentant la pièce suivante dans le jeu Tetris.
 * Cette vue affiche une prévisualisation de la prochaine pièce qui sera jouée.
 * Elle est mise à jour automatiquement lorsque la pièce suivante change.
 *
 * @author Hugo
 */
public class VuePieceSuivante extends JPanel {
    /** Le puits associé à cette vue */
    private final Puits puits;
    /** Support pour la gestion des événements de propriété */
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Constructeur de la vue de la pièce suivante.
     * Initialise la vue avec le puits spécifié et configure l'écoute des changements.
     *
     * @param puits Le puits associé à cette vue
     */
    public VuePieceSuivante(Puits puits) {
        this.puits = puits;
        this.setPreferredSize(new Dimension(120, 120));
        this.setOpaque(false);

        // Écoute les changements de la pièce suivante
        this.puits.addPropertyChangeListener(evt -> {
            if ("PIECE SUIVANTE".equals(evt.getPropertyName())) {
                repaint();  // redessine quand une nouvelle pièce est définie
            }
        });
    }

    /**
     * Dessine la pièce suivante dans le panneau.
     * La pièce est centrée et mise à l'échelle pour s'adapter à la taille du panneau.
     *
     * @param g Le contexte graphique pour le dessin
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Piece next = puits.getPieceSuivante();
        if (next != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            float scale = 20f;
            int offsetX = getWidth() / 2;
            int offsetY = getHeight() / 2;

            // Utilise le premier élément comme point de référence
            Element pivot = next.getElements().get(0);
            int pivotX = pivot.getCoordonnees().getAbscisse();
            int pivotY = pivot.getCoordonnees().getOrdonnee();

            // Dessine chaque élément de la pièce
            for (Element elt : next.getElements()) {
                int dx = elt.getCoordonnees().getAbscisse() - pivotX;
                int dy = elt.getCoordonnees().getOrdonnee() - pivotY;
                int x = offsetX + dx * (int)scale;
                int y = offsetY + dy * (int)scale;

                // Dessine l'élément avec sa couleur et un effet 3D
                g2d.setColor(elt.getCouleur().getCouleurPourAffichage());
                g2d.fill3DRect(x, y, (int)scale, (int)scale, false);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, (int)scale, (int)scale);
            }
            g2d.dispose();
        }
    }

    /**
     * Ajoute un écouteur de changement de propriété.
     *
     * @param listener L'écouteur à ajouter
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Retire un écouteur de changement de propriété.
     *
     * @param listener L'écouteur à retirer
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}