package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class VuePieceSuivante extends JPanel {
    private final Puits puits;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);


    public VuePieceSuivante(Puits puits) {
        this.puits = puits;
        this.setPreferredSize(new Dimension(120, 120));
        this.setOpaque(false);

        this.puits.addPropertyChangeListener(evt -> {
            if ("PIECE SUIVANTE".equals(evt.getPropertyName())) {
                repaint();  // redessine quand une nouvelle pièce est définie
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Piece next = puits.getPieceSuivante();
        if (next != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            float scale = 20f;
            int offsetX = getWidth() / 2;
            int offsetY = getHeight() / 2;

            Element pivot = next.getElements().get(0);
            int pivotX = pivot.getCoordonnees().getAbscisse();
            int pivotY = pivot.getCoordonnees().getOrdonnee();

            for (Element elt : next.getElements()) {
                int dx = elt.getCoordonnees().getAbscisse() - pivotX;
                int dy = elt.getCoordonnees().getOrdonnee() - pivotY;
                int x = offsetX + dx * (int)scale;
                int y = offsetY + dy * (int)scale;

                g2d.setColor(elt.getCouleur().getCouleurPourAffichage());
                g2d.fill3DRect(x, y, (int)scale, (int)scale, false);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, (int)scale, (int)scale);
            }
            g2d.dispose();
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}