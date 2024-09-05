package E3CeteExt12345;
/**
 * La classe Coordonnees représente les coordonnées (i,j) d'une Carte sur la Table
 * ou i représenta la ligne et j la colonne
 * Cette classe est utilisée uniquement pour intéragir avec l'utilisateur
 */
public class Coordonnees {
    private char i; //EXT
    private int j;


    /**
     * Pre-requis : x,y>=0
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    //EXTENSION
    public Coordonnees(char x, int y) {
        this.i = x;
        this.j = y;
    }


    /**
     * Pre-requis : input est sous la forme suivante : int,int
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    //EXTENSION
    public Coordonnees(String input) {
        String[] split = input.split(",");

        i = split[0].charAt(0);
        i = Character.toUpperCase(i);

        j = Integer.parseInt(split[1]);
    }


    /**
     * Action : Retourne le numéro de la ligne
     */
    //EXTENSION
    public int getLigne() {
        int asciiA = (int) 'A';
        int asciiLigne = (int) i;
        return asciiLigne - asciiA + 1;
    }


    /**
     * Action : Retourne le numéro de la colonne
     */
    //EXTENSION
    public int getColonne() {
        return this.j;
    }


    public static String ligneToString(int adr){
        int asciiA = (int) 'A';
        char lettre = (char) (asciiA+adr);
        return lettre+"";
    }


    public static String colonneToString(int e){
        return (e+1)+" ";
    }

    
    /**
     * Pre-requis : aucun
     * Action : Retourne vrai si la variable input est dans un format valide à savoir int,int
     * Aide : On peut utiliser Ut.estNombre pour vérifier qu'une chaîne de caractères est bien un nombre.
     */
    //EXTENSION
    public static boolean formatEstValide(String input) {
        String[] splited = input.split(",");
        return !(splited.length != 2 || !Ut.estNombre(splited[1]));
    }

    public static void main(String[] args) {
        Coordonnees coordonnees1 = new Coordonnees('A', 7);
        System.out.println(coordonnees1.i);
        System.out.println(coordonnees1.j);

        Coordonnees coordonnees = new Coordonnees("A,3");
        System.out.println(coordonnees.getLigne());
        System.out.println(coordonnees.getColonne());

        System.out.println("---------");

        System.out.println(formatEstValide("1,3")); //true
        System.out.println(formatEstValide("1;3")); //false
        System.out.println(formatEstValide("a,3")); //false
        System.out.println(formatEstValide("1, 3")); //false

    }
}
