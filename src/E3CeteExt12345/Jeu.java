package E3CeteExt12345;
/**
 * La classe Jeu permet de faire des parties du jeu "E3Cète" soit avec un humain, soit avec un ordinateur.
 *
 * Règles :
 *
 *  - On possède un paquet de cartes qui représentent entre une et trois figures (losange, carre ou ovale), une texture
 *   (vide, hachuré ou plein) et une couleur (rouge, jaune ou bleu). La cardinalité des énumérations est fixée à 3 pour cette partie 2 de la SAE uniquement.
 *
 *  - Une table 3x3 permet de stocker 9 cartes. Au début de la partie, on dispose 9 cartes sur la table, piochées sur le dessus du paquet.
 *
 *  - A chaque tour, le joueur doit essayer de trouver un E3C.
 *
 *  - Le joueur doit désigner trois cartes par leurs coordonnées.
 *      - Si ces cartes forment un E3C, il gagne trois points sur son score.
 *      - Si ce n'est pas un E3C, il perd 1 point sur son score.
 *
 *   - Les trois cartes sont remplacées par de nouvelles cartes piochées dans le paquet.
 *
 *   - La partie se termine quand il n'y a plus de cartes dans le paquet (même s'il reste des cartes sur la table).
 *
 * On a donc besoin :
 *
 * - D'un paquet pour stocker toutes les cartes et avoir une pioche.
 * - D'une table.
 * - De quoi stocker le score du joueur (humain ou ordinateur).
 */
public class Jeu {
    private int scoreH;
    private int scoreO;

    private int ensembleDe;

    private Paquet paquet;
    private Table table;

    /*
     * Action :
     * - Initialise le jeu "E3Cète" en initialisant le score du joueur, le paquet et la table.
     * - La table doit être remplie.
     */
    public Jeu() {
        //Initialisation des scores
        this.scoreH = 0;
        this.scoreO = 0;

        //Initialisation des attributs
        Couleur[] couleurs = {Couleur.BLEU, Couleur.ROUGE, Couleur.VERT,Couleur.JAUNE,Couleur.VIOLET,Couleur.TURQUOISE,Couleur.BLANC,Couleur.NOIR};
        Figure[] figures = {Figure.CARRE,Figure.ROND,Figure.LOSANGE,Figure.TRIANGLE,Figure.VAGUE,Figure.TRAPEZE,Figure.RECTANGLE,Figure.RECTANGLE,Figure.CONE,Figure.ETOILE,Figure.FLECHE,Figure.COEUR,Figure.TREFLE,Figure.PIQUE,Figure.HEXAGONE,Figure.CROIX,Figure.SPIRALE,Figure.ECLAIR,Figure.EPEE,Figure.AVION,Figure.VOITURE};
        Texture[] textures = {Texture.PLEINE,Texture.RAYE,Texture.TRANSPARENT,Texture.PLEINE,Texture.TRANSPARENT,Texture.RAYE,Texture.QUADRILLE,Texture.POIS,Texture.ZIGZAG,Texture.DAMIER,Texture.TACHES,Texture.CONFETTIS,Texture.MARBRE,Texture.BRIQUE,Texture.BOIS,Texture.HERBE,Texture.PLASTIQUE,Texture.VERRE,Texture.TERRE,Texture.SABLE,Texture.GOUDRON,Texture.FER,Texture.PEAU};

        //Initialisation de la table
        System.out.print("Quelle est la hauteur de votre table : ");
        int hauteur = 0;
        while(hauteur > 20 || hauteur < 3)
            hauteur = Ut.saisirEntier();
        System.out.print("Quelle est la longueur de votre table :");
        int longueur = 0;
        while(longueur > 20 || longueur < 3)
            longueur = Ut.saisirEntier();
        this.table = new Table(hauteur, longueur);

        int max = 0;
        if(longueur > hauteur)
            max = longueur;
        else
            max = hauteur;

        //Initialisation du paquet
        Couleur[] c = new Couleur[max];
        Figure[] f = new Figure[max];
        Texture[] t = new Texture[max];
        for(int i = 0; i < max; i++){ //Recopie les tableaux jusqua l'adresse max
            c[i] = couleurs[i];
            f[i] = figures[i];
            t[i] = textures[i];
        }
        int nbFiguresMax = max;

        this.paquet = new Paquet(c, nbFiguresMax, f, t);


        int n = table.getTaille();
        int[] adresses = new int[n];
        for(int i = 0; i < n; i++)
            adresses[i] = i;

        piocherEtPlacerNouvellesCartes(adresses);
    }

    
    /*
     * Action : Pioche autant de cartes qu'il y a de numéros de cartes dans le tableau numerosDeCartes et les place
     * aux positions correspondantes sur la table.
     */
    public void piocherEtPlacerNouvellesCartes(int[] adresses) {
        Carte[] pioche = paquet.piocher(adresses.length);
        table.setCartes(pioche,adresses); 
    }


    /*
     * Action : Ré-initialise les données et variables du jeu afin de rejouer une nouvelle partie.
     */
    public void resetJeu() {
        //Reset des scores
        scoreH = 0;
        scoreO = 0;

        //Reset des attributs
        Couleur[] couleurs = {Couleur.BLEU, Couleur.ROUGE, Couleur.VERT,Couleur.JAUNE,Couleur.VIOLET,Couleur.TURQUOISE,Couleur.BLANC,Couleur.NOIR};
        Figure[] figures = {Figure.CARRE,Figure.ROND,Figure.LOSANGE,Figure.TRIANGLE,Figure.VAGUE,Figure.TRAPEZE,Figure.RECTANGLE,Figure.RECTANGLE,Figure.CONE,Figure.ETOILE,Figure.FLECHE,Figure.COEUR,Figure.TREFLE,Figure.PIQUE,Figure.HEXAGONE,Figure.CROIX,Figure.SPIRALE,Figure.ECLAIR,Figure.EPEE,Figure.AVION,Figure.VOITURE};
        Texture[] textures = {Texture.PLEINE,Texture.RAYE,Texture.TRANSPARENT,Texture.PLEINE,Texture.TRANSPARENT,Texture.RAYE,Texture.QUADRILLE,Texture.POIS,Texture.ZIGZAG,Texture.DAMIER,Texture.TACHES,Texture.CONFETTIS,Texture.MARBRE,Texture.BRIQUE,Texture.BOIS,Texture.HERBE,Texture.PLASTIQUE,Texture.VERRE,Texture.TERRE,Texture.SABLE,Texture.GOUDRON,Texture.FER,Texture.PEAU};

        //Reset de la table
        System.out.println("|Souhaitez vous redimensionner de la table ? (o/n)");
        String input = "null";
        while (!input.equals("o") && !input.equals("n")) {
            System.out.print("Choix |---->");
            input = Ut.saisirChaine();
        }

        int max = ensembleDe;
        if (input.equals("o")) {
            System.out.print("Quelle est la hauteur de votre table : ");
            int hauteur = 0;
            while(hauteur > 20 || hauteur < 3)
                hauteur = Ut.saisirEntier();
            System.out.print("Quelle est la longueur de votre table :");
            int longueur = 0;
            while(longueur > 20 || longueur < 3)
                longueur = Ut.saisirEntier();
            this.table = new Table(hauteur, longueur);

            max = 0;
            if(longueur > hauteur)
                max = longueur;
            else
                max = hauteur;
        }

        //Reset du paquet
        Couleur[] c = new Couleur[max];
        Figure[] f = new Figure[max];
        Texture[] t = new Texture[max];
        for(int i = 0; i < max; i++){ //Recopie les tableaux jusqua l'adresse max
            c[i] = couleurs[i];
            f[i] = figures[i];
            t[i] = textures[i];
        }
        int nbFiguresMax = max;

        this.paquet = new Paquet(c, nbFiguresMax, f, t);
        
        int n = table.getTaille();
        int[] adresses = new int[n];
        for(int i = 0; i < n; i++)
            adresses[i] = i;

        piocherEtPlacerNouvellesCartes(adresses);
        System.out.println("\n\n\n");
    }


    /*
     *  Résultat : Vrai si les cartes passées en paramètre forment un E3C.
     *  Vrai si trouve sur 3 cartes exactement 2 et pas plus, on des éléments égaux. 
     *  : exactement deux élements similaires
     */
    public static boolean estUnE3C(Carte[] c){
        int x=0, y=0, z=0, i=0;
        boolean estUnE3C = true;
        while(i < 3 && estUnE3C){
            if(i == 0){ x = 0; y = 1; z = 2; }//échange l'ordre de vérification des cartes
            if(i == 1){ x = 0; y = 2; z = 1; }
            if(i == 2){ x = 1; y = 2; z = 0; }
            
            if((Integer.compare(c[x].getCouleur().ordinal(),   c[y].getCouleur().ordinal())    == 0 && Integer.compare(c[x].getCouleur().ordinal(),    c[z].getCouleur().ordinal())    != 0) 
            || (Integer.compare(c[x].getFigure().ordinal(),    c[y].getFigure().ordinal())     == 0 && Integer.compare(c[x].getFigure().ordinal(),     c[z].getFigure().ordinal())     != 0)
            || (Integer.compare(c[x].getTexture().ordinal(),   c[y].getTexture().ordinal())    == 0 && Integer.compare(c[x].getTexture().ordinal(),    c[z].getTexture().ordinal())    != 0)
            || (Integer.compare(c[x].getNbFigures(),           c[y].getNbFigures())            == 0 && Integer.compare(c[x].getNbFigures(),            c[z].getNbFigures())            != 0))
                estUnE3C = false;

            i++;
        }
        return estUnE3C;
    }


    /* Crée 4 tableaux d'entiers pour chaque attributs, stocke les ordinals des enums pour chaque carte
     * Ensuite regarde si ils sont ou tous différent ou tous égaux pour chaque tableaux
     */
    public static boolean estUnEXC(Carte[] cartes) {
        int[] coul  = new int[cartes.length];
        int[] nbfig = new int[cartes.length];
        int[] fig   = new int[cartes.length];
        int[] text  = new int[cartes.length];

        for (int i=0; i<cartes.length; i++) {
            coul[i]  = cartes[i].getCouleur().ordinal();
            nbfig[i] = cartes[i].getNbFigures();
            fig[i]   = cartes[i].getFigure().ordinal();
            text[i]  = cartes[i].getTexture().ordinal();
        }

        return ((tousDiff(coul)  || tousEgaux(coul))  && 
                (tousDiff(nbfig) || tousEgaux(nbfig)) && 
                (tousDiff(fig)   || tousEgaux(fig))   && 
                (tousDiff(text)  || tousEgaux(text)));
    }


    public static boolean tousDiff(int[] tab) {
        boolean bool = true;
        int i = 0;

        while(i < tab.length - 1){
            int j = i + 1;
            while ( j < tab.length) {
                if (tab[i] == tab[j])
                    bool = false;
                j++;
            }
            i++;
        }

        return bool;
    }


    static boolean tousEgaux(int[] tab) {
        int i = 1;
        boolean bool = true;

        while (i<tab.length && bool) {
            if (tab[i] != tab[0]) bool = false;
            i++;
        }
        return bool;
    }


    /*
     * Action : Recherche un E3C parmi les cartes disposées sur la table.
     * Résullat :
     *  - Si un E3C existe, un tableau contenant les numéros de cartes (de la table) qui forment un E3C.
     *  - Sinon, la valeur null.
     */
    //EXTENSION
    public int[] chercherE3CSurTableOrdinateur() {
        boolean trouvé = false;
        Carte[] cartes = new Carte[3];
        int n = table.getTaille();

        int[] adr = new int[3];
        while (adr[0] < n-2 && !trouvé) {
                cartes[0] = table.getCarte(adr[0]++);

            adr[1] = adr[0];
            while (adr[1] < n-1 && !trouvé) {
                    cartes[1] = table.getCarte(adr[1]++);

                adr[2] = adr[1];
                while (adr[2] < n && !trouvé) {
                        cartes[2] = table.getCarte(adr[2]++);

                    if(cartes[0] != null && cartes[1] != null && cartes[2] != null)
                        if (estUnE3C(cartes))
                            trouvé = true;
                }
            }
        }

        if(!trouvé)
            return null;
        else{
            for (int i = 0; i < 3; i++)
                adr[i]--;
            return adr;
        }
    }

    /*
     * Action : Sélectionne alétoirement trois cartes sur la table.
     * La sélection ne doit pas contenir de doublons
     * Résullat : un tableau contenant les numéros des cartes sélectionnées alétaoirement
     */
    public int[] selectionAleatoireDeCartesOrdinateur() {
        int[] adr = new int[3];
        adr[0] = Ut.randomMinMax(0, 8);

        adr[2] = adr[1] = adr[0];
        while(adr[1] == adr[0])
            adr[1] = Ut.randomMinMax(0, 8);
        while(adr[2] == adr[0] || adr[2] == adr[1])
            adr[2] = Ut.randomMinMax(0, 8);

        return adr;
    }


    /*
     * Résullat : Vrai si la partie en cours est terminée.
     */
    //EXTENSION
    public boolean partieEstTerminee() {
        return (this.paquet.estVide() && chercherE3CSurTableOrdinateur() == null);
    }


    /**
     * Action : Fait jouer un tour à un joueur humain.
     * La Table et le score du joueur sont affichés.
     * Le joueur sélectionne 3 cartes.
     *  - Si c'est un E3C, il gagne trois points.
     *  - Sinon, il perd un point.
     * Les cartes sélectionnées sont remplacées.
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */
    //EXTENSION
    public void jouerTourHumain() {
        System.out.println("\n\n"+table.toString()+
                           "\nVotre score actuel est de "+scoreH+" points"+
                           "\nIl reste actuellement "+paquet.getNbActuel()+" cartes dans le paquet!"+
                           "\n-----------------------------------------"+
                           "\nSelectionnez 3 cartes (ex: A,2 B,3 C,4)");

        //////////// TRICHE
        /*
        int[] sol = chercherE3CSurTableOrdinateur();
        if(sol == null)
            System.out.println("null");
        else 
            for(int i = 0; i < 3; i++)
                System.out.println(sol[i]+1);
        */
        ////////////

        int[] adresses = table.selectionnerCartesJoueur(ensembleDe);
        for(int i = 0; i < ensembleDe; i++)
            adresses[i]--;
        table.afficherSelection(adresses);
        
        Carte[] selection = table.getCartes(adresses);
        if (estUnEXC(selection)){
            scoreH += ensembleDe;;
            System.out.println("Correct ! Vous gagnez +3 points");
        } else {
            scoreH--;
            System.out.println("Votre selection n'est pas un E3C vous perdez 1 point");
        }

        Ut.pause(1500);
        piocherEtPlacerNouvellesCartes(adresses);     
    }


    /*
     * Action : Fait jouer une partie à un joueur humain.
     * A la fin, le score final du joueur est affiché.
     */
    public void jouerHumain() {
        while(!partieEstTerminee())
            jouerTourHumain();
        System.out.println("\n\n\n\n"+table.toString()+
                           "\n\nPaquet vide et plus d'E3C -> partie terminée"+
                           "\nVous avez un total de "+scoreH+" points !");
    }


    /*
     * Action : Fait jouer un tour à l'ordinateur.
     * La Table et le score de l'ordinateur sont affichés.
     * L'ordinateur sélectionne des cartes :
     *  - L'ordinateur essaye toujours de trouver un E3C sur la table. S'il en trouve un, il gagne donc trois points.
     *  - S'il n'en trouve pas, il se rabat sur 3 cartes sélectionnées aléatoirement et perd un point.
     * Les cartes sélectionnées sont remplacées.
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */
    public void jouerTourOrdinateur() {
        System.out.println("\n\n\n\n"+table.toString()+
                           "\n\nLe score ordi actuel est de "+scoreO+" points"+
                           "\nIl reste "+paquet.getNbActuel()+" cartes dans le paquet!"+
                           "\n-----------------------------------------");
        
        int[] adresses = chercherE3CSurTableOrdinateur();

        if (adresses == null) {
            System.out.println("Erreur ordi : -1 point");
            scoreO--;
            adresses = selectionAleatoireDeCartesOrdinateur();
        } else {
            System.out.println("Réponse correcte Ordi : +3 points");
            scoreO += 3;
        }

        piocherEtPlacerNouvellesCartes(adresses);     
    }


    /*
     * Action : Fait jouer une partie à l'ordinateur.
     * Une pause est faite entre chaque tour (500 ms ou plus) afin de pouvoir observer la progression de l'ordinateur.
     * A la fin, le score final de l'ordinateur est affiché.
     * Rappel : Ut.pause(temps) permet de faire une pause de "temps" millisecondes
     */
    public void jouerOrdinateur() {
        while (!partieEstTerminee()) {
            jouerTourOrdinateur();
            Ut.pause(1000);
        }
        System.out.println("\n\n\n\n"+table.toString()+
                           "\n\nPaquet vide et plus d'E3C -> partie terminée"+
                           "\nL'Ordi à fait un score de "+scoreO+" points !");        
    }


    /*
     * Action : Permet de lancer des parties de "E3Cète" au travers d'un menu.
     * Le menu permet au joueur de sélectionner une option parmi :
     *  - humain : lance une partie avec un joueur humain
     *  - ordinateur : lance une partie avec un ordinateur
     *  - terminer : arrête le programme.
     * Après la fin de chaque partie, les données de jeu sont ré-initialisées et le menu est ré-affiché
     * (afin de faire une nouvelle sélection).
     * Les erreurs de saisie doivent être gérées (si l'utilisateur sélectionne une option inexistante).
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */
    public void jouer() {
        String input = "null";

        while (!input.equals("terminer")) {
            input = "null";
            System.out.println( "\n|-----------------------------|"+
                                "\n|- - - - -   MENU   - - - - - |"+
                                "\n|-----------------------------|"+
                                "\n|Pour lancer une partie tapez \"humain\" ou \"ordinateur\""+
                                "\n|Ou tapez \"terminer\" pour arrêter de jouer");

            while(!input.equals("humain") && !input.equals("ordinateur")   && !input.equals("terminer")){
                System.out.print("Choix |---->");
                input = Ut.saisirChaine();
            }
            if (input.equals("humain"))
                jouerHumain();
            if (input.equals("ordinateur"))
                jouerOrdinateur();
            if(!input.equals("terminer"))
                resetJeu();
        }
        System.out.println("-----------------------------"+
                        "\n     Partie terminée          "+
                        "\n      A la prochaine          ");
    }

    public static void main(String[] args) {
        //  System.out.println("■ \u25A0 \u25B2 \u25ca");

        // TEST PROBA AVOIR UN E3C SUR LA TABLE
        /*int nbEssais = 1000000;
        float proba = 0;
        for(int i = 0; i < nbEssais; i++){
            if(j.chercherE3CSurTableOrdinateur() != null)
                proba++;
            j.resetJeu();
        }
        System.out.println(proba);
        proba /= nbEssais;
        System.out.println(proba);*/
        

        // TESTEUR POUR LES CARTES DU SCHEMA
        
        Carte c1 = new Carte(Couleur.BLEU, 3, Figure.ROND, Texture.PLEINE);
        Carte c2 = new Carte(Couleur.ROUGE, 3, Figure.CARRE, Texture.PLEINE);
        Carte c3 = new Carte(Couleur.VERT, 3, Figure.LOSANGE, Texture.PLEINE);
        Carte c4 = new Carte(Couleur.BLEU, 2, Figure.CARRE, Texture.RAYE);
        Carte c5 = new Carte(Couleur.ROUGE, 2, Figure.LOSANGE, Texture.RAYE);
        Carte c6 = new Carte(Couleur.VERT, 2, Figure.ROND, Texture.RAYE);
        Carte c7 = new Carte(Couleur.BLEU, 1, Figure.LOSANGE, Texture.TRANSPARENT);
        Carte c8 = new Carte(Couleur.ROUGE, 1, Figure.ROND, Texture.TRANSPARENT);
        Carte c9 = new Carte(Couleur.VERT, 1, Figure.CARRE, Texture.TRANSPARENT);

        Carte[] test1 = new Carte[]{c1, c2, c3};
        Carte[] test2 = new Carte[]{c4, c5, c6};
        Carte[] test3 = new Carte[]{c7, c8, c9};
        Carte[] test4 = new Carte[]{c1, c4, c7};
        Carte[] test5 = new Carte[]{c2, c5, c8};
        Carte[] test6 = new Carte[]{c3, c6, c9};
        Carte[] test7 = new Carte[]{c1, c5, c9};
        Carte[] test8 = new Carte[]{c1, c6, c8};

        System.out.println("1 2 3 "+estUnEXC(test1));
        System.out.println("4 5 6 "+estUnEXC(test2));
        System.out.println("7 8 9 "+estUnEXC(test3));
        System.out.println("1 4 7 "+estUnEXC(test4));
        System.out.println("2 5 8 "+estUnEXC(test5));
        System.out.println("3 6 9 "+estUnEXC(test6));
        System.out.println("1 5 9 "+estUnEXC(test7));
        System.out.println("1 6 8 "+estUnEXC(test8));
        
    }
}
