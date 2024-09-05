package E3CeteBase;
/*
 * Représente la texture d'une Carte : pleine , à pois...
 */
public enum Texture {
    PLEINE("Pl"),
    TRANSPARENT("Tr"),
    RAYE("Ra");

    private String abreviation ;

    private Texture(String a) {abreviation = a;}
    public String abreviation() {return abreviation;}

    public static void main(String[] args) {
        //System.out.println(POIS.ordinal());  // Renvoie 1
    }
}
