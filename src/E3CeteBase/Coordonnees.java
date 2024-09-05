package E3CeteBase;
/**
 * La classe Coordonnees représente les coordonnées (i,j) d'une Carte sur la Table
 * ou i représenta la ligne et j la colonne
 * Cette classe est utilisée uniquement pour intéragir avec l'utilisateur
 */
public class Coordonnees {
    private int i;
    private int j;


    /**
     * Pre-requis : x,y>=0
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    public Coordonnees(int x, int y) {
        this.i = x;
        this.j = y;
    }


    /**
     * Pre-requis : input est sous la forme suivante : int,int
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    public Coordonnees(String input) {
        String[] split = input.split(",");
        i = Integer.parseInt(split[0]);
        j = Integer.parseInt(split[1]);
    }


    /**
     * Action : Retourne le numéro de la ligne
     */
    public int getLigne() {
        return this.i;
    }


    /**
     * Action : Retourne le numéro de la colonne
     */
    public int getColonne() {
        return this.j;
    }

    
    /**
     * Pre-requis : aucun
     * Action : Retourne vrai si la variable input est dans un format valide à savoir int,int
     * Aide : On peut utiliser Ut.estNombre pour vérifier qu'une chaîne de caractères est bien un nombre.
     */
    public static boolean formatEstValide(String input) {
        String[] splited = input.split(",");
        if (splited.length != 2 || !Ut.estNombre(splited[0]) || !Ut.estNombre(splited[1]))
            return false;
        return true;
    }

    public static void main(String[] args) {
        Coordonnees coordonnees1 = new Coordonnees(2, 7);
        System.out.println(coordonnees1.i);
        System.out.println(coordonnees1.j);

        Coordonnees coordonnees = new Coordonnees("1,3");
        System.out.println(coordonnees.getLigne());
        System.out.println(coordonnees.getColonne());

        System.out.println("---------");

        System.out.println(formatEstValide("1,3")); //true
        System.out.println(formatEstValide("1;3")); //false
        System.out.println(formatEstValide("a,3")); //false
        System.out.println(formatEstValide("1, 3")); //false

    }
}
