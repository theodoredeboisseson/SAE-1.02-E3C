package E3CeteBase;
/**
 * La classe Carte représente une carte possèdant une figure répétée un certain nombre de fois avec une texture et une couleur.
 * On a besoin de connaître :
 * - La figure représentée,
 * - Le nombre de fois où la figure est représentée,
 * - La couleur de la figure,
 * - La texture de la figure.
 */
public class Carte  {
    private Couleur couleur;
    private int nbFigures;
    private Figure figure;
    private Texture texture;


    /**
     * Pre-requis : nbFigures > 0
     * Action : Construit une carte contenant nbFigures "figures" qui possèdent une "texture" et une "couleur"
     * Exemple : new Carte(Couleur.ROUGE, 2, Figure.OVALE, Texture.PLEIN) représente une carte contenant 2 figures ovales rouge et pleines
     */
    public Carte(Couleur couleur, int nbFigures, Figure figure, Texture texture) {
        this.couleur = couleur;
        this.nbFigures = nbFigures;
        this.figure = figure;
        this.texture = texture;
    }

    public int getNbFigures() {
        return this.nbFigures;
    }

    public Figure getFigure() {
        return this.figure;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public Texture getTexture() {
        return this.texture;
    }


    /**
     * Action : compare les attributs de "this" et de "carte"
     * afin de déterminer si this est plus petit, égal ou plus grand que "carte"
     *
     * L'ordre d'importance des attributs est celui donné dans le constructeur (du plus prioritaire au moins prioritaire) :
     * Couleur, nombre de figures, figure, texture.
     * Pour comparer les couleurs, les figures et les textures, on utilisera leur position (ordinal) dans
     * leurs énumérations respectives.
     * Ainsi, pour toute paire {c1,c2} de Carte, c1 est inférieure à c2 si et seulement si
     * la valeur de c1 est inférieure à celle de c2 pour la caractéristique ayant la plus grande priorité
     * parmi celles pour lesquelles c1 et c2 ont des valeurs différentes.
     *
     *
     * Résultat :
     *  0 si "this" est égal à "carte"
     *  Un nombre négatif si "this" est inférieur à "carte"
     *  Un nombre strictement positif si "this "est supérieur à "carte"
     */
    public int compareTo(Carte carte) {
        /*CODE SANS compare()
        if(this.couleur.ordinal() < carte.couleur.ordinal())
            return 1;
        else if(this.couleur.ordinal() > carte.couleur.ordinal())
            return -1;
        else{
            if(this.nbFigures > carte.nbFigures) 
                return 1;
            else if(this.nbFigures < carte.nbFigures)
                return -1;
            else{
                if(this.figure.ordinal() < carte.figure.ordinal()) 
                    return 1;
                else if(this.figure.ordinal() > carte.figure.ordinal()) 
                    return -1;
                else{
                    if(this.texture.ordinal() < carte.texture.ordinal()) 
                        return 1;
                    else if(this.texture.ordinal() > carte.texture.ordinal()) 
                        return -1;
                    else
                        return 0;
                }
            }
        }*/
        //AVEC compare(); Fonctionnement identique mais plus lisible
        int compareCoul = Integer.compare(this.couleur.ordinal(), carte.couleur.ordinal()); //Importance de couleur Descendant
        if (compareCoul != 0) return compareCoul;

        int compareNbFig = Integer.compare(this.nbFigures, carte.nbFigures); //Ascendant
        if (compareNbFig != 0) return compareNbFig;
        
        int compareFig = Integer.compare(this.figure.ordinal(), carte.figure.ordinal()); //Descendant
        if (compareFig != 0) return compareFig;
        
        int compareTex = Integer.compare(this.texture.ordinal(), carte.texture.ordinal()); //Descendant
        return compareTex;
    }


    /**
     * Résultat :
     * Une chaîne de caractères représentant la carte de la manière suivante :
     *  - Le texte est coloré selon la couleur de la carte
     *  - La chaîne de caractères retournée doit faire apparaitre toutes les caractériqtiques d'une carte sauf la couleur puisque le texte est affiché en couleur
     *  (Vous devez choisir une représentation agréable pour l'utilisateur)
     */
    @Override
    public String toString() {
        String carte = "";
        
        if(this.couleur == Couleur.ROUGE)
            carte = "\u001B[1;31m";
        else if(this.couleur == Couleur.VERT)
            carte = "\u001B[1;32m";
        else if(this.couleur == Couleur.BLEU)
            carte = "\u001B[1;34m";
        else System.out.println("Couleur mal définie");

        carte += nbFigures +" "+ figure.abreviation() +" "+ texture.abreviation() + "\u001b[0m";
        
        return carte;
    }

    public static void main(String[] args) {
        /*
        Carte c1 = new Carte(Couleur.ROUGE, 3, Figure.LOSANGE,Texture.PLEINE);
        Carte c2 = new Carte(Couleur.BLEU, 3, Figure.LOSANGE,Texture.PLEINE);
        System.out.println(c1.compareTo(c2));
        System.out.println(c.toString());
        */

        Carte c = new Carte(Couleur.ROUGE,3,Figure.ROND,Texture.PLEINE);
        System.out.println(c.getNbFigures());
	    if (c.getNbFigures()==3) System.out.println(0);
	    else System.out.println(1);
    }
}