package E3CeteBase;
/*
 * Représente la figure (forme) d'une Carte : ovale , triangle ...
 */
public enum Figure {
    CARRE("Ca"),
    ROND("Re"),
    LOSANGE("Lo");

    private String abreviation ;

    private Figure(String a) {abreviation = a;}
    public String abreviation() {return abreviation;}

    public static void main(String[] args) {
        Figure fig = Figure.ROND;

        System.out.println(fig.ordinal());  // Renvoie 1
        System.out.println(fig.name());  // Renvoie ROND
    }
}
