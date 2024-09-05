package E3CeteExt12345;
/**
 * La classe Table représente une table de jeu contenant des cartes.
 *
 * La table est représentée graphiquement par une matrice.
 * On peut donc avoir des tables de dimensions 3x3, 3x4, 4x4, 5x5, 10x15...
 * En mémoire, la Table est représentée par un simple tableau (à une dimension)
 * Quand elle est initialisée, la table est vide.
 *
 * Pour désigner une carte sur la table, on utilise des coordonées (i,j) ou i représenta la ligne et j la colonne.
 * Les lignes et colonnes sont numérotés à partir de 1.
 * Les cartes sont numérotées à partir de 1.
 *
 * Par exemple, sur une table 3x3, la carte en position (1,1) est la premiere carte du tableau, soit celle à l'indice 0.
 * La carte (2,1) => carte numéro 4 stockée à l'indice 3  dans le tableau représenatnt la table
 * La carte (3,3) => carte numéro 9 stockée à l'indice 8  dans le tableau représenatnt la table
 */
public class Table {
    private Carte[] cartes;
    private int h;
    private int l;

    /*
     * Pre-requis : hauteur >=3, largeur >=3
     *
     * Action : Construit une table "vide" avec les dimensions précisées en paramètre.
     *
     * Exemple : hauteur : 3, largeur : 3 => construit une table 3x3 (pouvant donc accueillir 9 cartes).
     */
    public Table(int hauteur, int largeur){
        h = hauteur;
        l = largeur;
        cartes = new Carte[h*l];
    }


    /*
     * Résullat : Le nombre de cartes que la table peut stocker.
     */
    public int getTaille() {
        return h*l;
    }


    /*
     * Pre-requis : la table est pleine
     * Action : Affiche des cartes de la table sous forme de matrice
     * L'affichage des cartes doit respecter le format défini dans la classe Carte (chaque carte doit donc être colorée).
     * On ne donne volontairement pas d'exemple puisque celà depend du choix fait pour votre représentation de Carte
     */
    //EXTENSION
    public String toString() {
        String str = "\n\n  ";


        for(int x = 0; x < this.l; x++)
            str += ("|           "+Coordonnees.colonneToString(x)+"            ");

        for(int i = 0; i < this.getTaille();i++){
            if(i%l == 0)
                str += "\n\n"+Coordonnees.ligneToString(i/this.l);

            if (this.cartes[i] != null)
                str += " | " + this.cartes[i].toString();

            else
                str += " | " + "                       ";
        }

        return str+"\n";
    }


    /*
     * Résullat : Vrai la carte située aux coordonnées précisées en paramètre est une carte possible pour la table.
     */
    public boolean carteExiste(Coordonnees coordonnees) {
        return (coordonnees.getLigne() > 0 && coordonnees.getLigne() <= h
        && coordonnees.getColonne() > 0 && coordonnees.getColonne() <= l);
    }


    /*
     * Pre-requis :
     *  Il reste des cartes sur la table.
     *
     * Action : Fait sélectionner au joueur (par saisie de ses coordonnées) une carte valide (existante) de la table.
     * L'algorithme doit faire recommencer la saisie au joueur s'il ne saisit pas une carte valide.
     *
     * Résullat : Le numéro de carte sélectionné.
     *
     */
    public int faireSelectionneUneCarte() {
        Coordonnees pos = new Coordonnees("0,0");

        while (!carteExiste(pos)) {
            System.out.println("Saisir les coord de la carte");
            String input = Ut.saisirChaine();
            pos = new Coordonnees(input);
        }

        int i = pos.getLigne();
        int j = pos.getColonne();
        int numCarte = l*(i-1) + j;

        return numCarte;
    }


    /*
     * Pre-requis : 1 <=  nbCartes <= nombre de Cartes de this
     * Action : Fait sélectionner nbCartes Cartes au joueur  sur la table en le faisant recommencer jusqu'à avoir une sélection valide.
     * Il ne doit pas y avoir de doublons dans les numéros de cartes sélectionnées.
     * Résullat : Un tableau contenant les numéros de cartes sélectionnées.
     */
    public int[] selectionnerCartesJoueur(int nbCartes) {
        int[] ensCarte = new int[nbCartes];

        for(int i = 0; i < nbCartes; i++){
            int numCarte = 0;

            boolean valide = false;
            while(!valide){
                System.out.println("Carte n°"+(i+1));
                numCarte = faireSelectionneUneCarte();

                boolean doublon = false;
                int j = 0;
                while(j < i && !doublon)
                    if(ensCarte[j++] == numCarte)
                        doublon = true;

                if(!doublon)
                    valide = true;
            }

            ensCarte[i] = numCarte;
        }

        return ensCarte;
    }


    /*
     * Action : Affiche les cartes de la table correspondantes aux numéros de cartes contenus dans selection
     * Exemple de format d'affichage : "Sélection : 2-O-H 3-O-H 2-C-H"
     * Les cartes doivent être affichées "colorées"
     */
    public void afficherSelection(int[] selection) {
        System.out.println("Vous avez selectionné :");
        for(int i = 0; i < selection.length; i++)
            System.out.print(cartes[selection[i]].toString()+" | ");
        System.out.println();
    }


    /* Pré-requis : Adresses des cartes que l'on souhaite récuperer
     * Résultat : Renvoie les cartes aux adresses en parametre
     */
    public Carte[] getCartes(int[] adresses){
        int n = adresses.length;
        Carte[] tab = new Carte[n];
        for(int i=0 ; i < n ; i++)
            tab[i] = this.cartes[adresses[i]];
        
        return tab;
    }


    /* Similaire pour la fonction précédente mais pour uniquement une seule carte
     */
    public Carte getCarte(int adresses){
        return this.cartes[adresses];
    }

    /* Pré-Requis : Les adresses dans a doivent correspondre aux cartes dans c
     * Résultat : Définis les cartes c aux adresses a dans la table
     */
    public void setCartes(Carte[] c, int[] a){
        for(int i = 0; i < a.length; i++)
            this.cartes[a[i]] = c[i];
    }

    
    /* Similaire à la fonction précédente mais 
     * pour uniquement une seule carte à une seule adresse
     */
    public void setCarte(Carte c, int a){
        this.cartes[a] = c;
    }


    public static void main(String[] args) {
        Couleur[] couleurs = {Couleur.BLEU, Couleur.ROUGE, Couleur.VERT};
        int nbFiguresMax = 3;
        Figure[] figures = {Figure.CARRE,Figure.ROND,Figure.LOSANGE};
        Texture[] textures = {Texture.PLEINE,Texture.RAYE,Texture.TRANSPARENT};

        Paquet paquet = new Paquet(couleurs, nbFiguresMax, figures, textures);
        Table t = new Table(3, 3);
        t.cartes = paquet.piocher(9);

        System.out.println(t.toString());
        //System.out.println(t.faireSelectionneUneCarte());
        int[] selection = t.selectionnerCartesJoueur(3);
        t.afficherSelection(selection);
    }
}
