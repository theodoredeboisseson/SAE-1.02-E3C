package E3CeteExt12345;
/*
 * Représente la texture d'une Carte : pleine , à pois...
 */
public enum Texture {
    PLEINE      ("   PLEINE  "),
    TRANSPARENT ("TRANSPARENT"),
    RAYE        ("    RAYE   "),
    QUADRILLE   (" QUADRILLE "),
    POIS        ("    POIS   "),
    ZIGZAG      ("   ZIGZAG  "),
    DAMIER      ("   DAMIER  "),
    TACHES      ("   TACHES  "),
    CONFETTIS   (" CONFETTIS "),
    MARBRE      ("   MARBRE  "),
    BRIQUE      ("   BRIQUE  "),
    BOIS        ("    BOIS   "),
    HERBE       ("   HERBE   "),
    PLASTIQUE   (" PLASTIQUE "),
    VERRE       ("   VERRE   "),
    TERRE       ("   TERRE   "),
    SABLE       ("   SABLE   "),
    GOUDRON     ("  GOUDRON  "),
    FER         ("    FER    "),
    PEAU        ("    PEAU   "); 

    private String abreviation ;

    private Texture(String a) {abreviation = a;}
    public String abreviation() {return abreviation;}

    public static void main(String[] args) {
        //System.out.println(POIS.ordinal());  // Renvoie 1
    }
}
