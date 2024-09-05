package E3CeteExt12345;
/*
 * Représente la figure (forme) d'une Carte : ovale , triangle ...
 */
public enum Figure {
    CARRE   ("  CARRE  "),
    ROND    ("   ROND  "),
    LOSANGE (" LOSANGE "),
    TRIANGLE(" TRIANGLE"),
    VAGUE   ("  VAGUE  "),
    TRAPEZE (" TRAPEZE "),
    RECTANGLE("RECTANGLE"),
    CONE    ("   CONE  "),
    ETOILE  ("  ETOILE "),
    FLECHE  ("  FLECHE "),
    COEUR   ("  COEUR  "),
    TREFLE  ("  TREFLE "),
    PIQUE   ("  PIQUE  "),
    HEXAGONE(" HEXAGONE"),
    CROIX   ("  CROIX  "),
    SPIRALE (" SPIRALE "),
    ECLAIR  ("  ECLAIR "),
    EPEE    ("   EPEE  "),
    AVION   ("  AVION  "),
    VOITURE (" VOITURE ");

    private String abreviation ;

    private Figure(String a) {abreviation = a;}
    public String abreviation() {return abreviation;}

    public static void main(String[] args) {
        Figure fig = Figure.ROND;

        System.out.println(fig.ordinal());  // Renvoie 1
        System.out.println(fig.name());  // Renvoie ROND
    }
}
