package E3CeteExt12345;
/*
 * Représente la couleur d'une Carte : jaune, rouge ...
 * En plus de donner une liste énumérative des couleurs possibles,
 * cette enumération doit permettre à la méthode toString d'une Carte de réaliser un affichage en couleur.
 */
public enum Couleur {
    ROUGE       ("  ROUGE  "),
    VERT        ("   VERT  "),
    BLEU        ("   BLEU  "),
    JAUNE       ("  JAUNE  "),
    ROSE        ("   ROSE  "),
    VIOLET      ("  VIOLET "),
    GRIS        ("   GRIS  "),
    MARRON      ("  MARRON "),
    OR          ("    OR   "),
    BLANC       ("  BLANC  "),
    CUIVRE      ("  CUIVRE "),
    ARGENT      ("  ARGENT "),
    BRONZE      ("  BRONZE "),
    TURQUOISE   ("TURQUOISE"),
    MAGENTA     (" MAGENTA "),
    LGBT        ("   LGBT  "),
    NOIR        ("   NOIR  "),
    EMERAUDE    (" EMERAUDE"),
    RUBY        ("   RUBY  ");

    private String abreviation ;

    private Couleur(String a) {abreviation = a ;}
    public String abreviation() {return abreviation ;}
    
    public static void main(String[] args) {
        //System.out.println(BLEU.ordinal());  // Renvoie 2
        //System.out.println("\u001b[32mVERT\u001b[0m");  // Affiche VERT en vert
        System.out.println("\u001B[41m\u001B[30mcaca\u001b[0m");
    }
    /*AIDE      TEXTE           FOND
     * NOIR 	\u001B[30m 	    \u001B[40m
     * ROUGE    \u001B[31m      \u001B[41m
     * VERT     \u001B[32m      \u001B[42m
     * BLEU     \u001B[34m      \u001B[44m
     */
}
