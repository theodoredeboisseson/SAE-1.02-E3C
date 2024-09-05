package E3CeteExt12345;
/*
 * La classe Paquet représente un paquet de cartes.
 * Les cartes sont stockées dans un tableau fixe et un indice (entier) permet de connaître le nombre de cartes
 * restantes (non piochées) dans le paquet. Quand on pioche, cet indice diminue.
 * Dans les traitements, on considère alors seulement les cartes se trouvant entre 0 et cet indice (exclus).
 * Par conséquent, on ne supprime pas vraiment les cartes piochées, on les ignore juste.
 * On a donc besoin de connaître :
 * - Le tableau stockant les cartes.
 * - Le nombre de cartes restantes dans le paquet.
 */
public class Paquet {
    private Carte[] pile;
    private int nbActuel; //représente le haut du paquet actuel

    /*
     * Pre-requis : figures.length > 0, couleurs.length > 0, textures.length > 0, nbFiguresMax > 0
     *
     * Action : Construit un paquet de cartes mélangé contenant toutes les cartes incluant 1 à nbFiguresMax figures
     * qu'il est possibles de créer en combinant les différentes figures, couleurs et textures précisées en paramètre.
     * Bien sûr, il n'y a pas de doublons.
     *
     * Exemple :
     *  - couleurs = [Rouge, Jaune]
     *  - nbFiguresMax = 2
     *  - figures = [A, B]
     *  - textures = [S, T]
     *  Génère un paquet (mélangé) avec toutes les combinaisons de cartes possibles pour ces caractéristiques : 1-A-S (rouge), 1-A-T (rouge), etc...
     */
    public Paquet(Couleur[] couleurs, int nbFiguresMax, Figure[] figures, Texture[] textures) {
        int n = getNombreCartesAGenerer(couleurs, nbFiguresMax, figures, textures);
        this.pile = new Carte[n];
        this.nbActuel = 0;
        int c, nb, f, t;
        for(c = 0; c < couleurs.length; c++)
            for(nb = 1; nb <= nbFiguresMax; nb++)
                for(f = 0; f < figures.length; f++)
                    for(t = 0; t < textures.length; t++)
                        this.pile[nbActuel++] = new Carte(couleurs[c], nb, figures[f],textures[t]);
        melanger();
    }


    /*
     * Action : Construit un paquet par recopie en copiant les données du paquet passé en paramètre.
     */
    public Paquet(Paquet paquet) {
        this.pile = new Carte[paquet.nbActuel];
        for(this.nbActuel = 0; this.nbActuel < paquet.nbActuel; this.nbActuel++)
            this.pile[this.nbActuel] = paquet.pile[this.nbActuel];
    }


    /*
     * Pre-requis : figures.length > 0, couleurs.length > 0, textures.length > 0, nbFiguresMax > 0
     *
     * Resultat : Le nombre de cartes uniques contenant entre 1 et nbFiguresMax figures qu'il est possible de générer en
     * combinant les différentes figures, couleurs et textures précisées en paramètre.
     */
    public static int getNombreCartesAGenerer(Couleur[] couleurs, int nbFiguresMax, Figure[] figures, Texture[] textures) {
        int n = couleurs.length * nbFiguresMax * figures.length * textures.length;
        return n;
    }


    /*
     * Action : Mélange aléatoirement les cartes restantes dans le paquet.
     * Attention, on rappelle que le paquet peut aussi contenir des cartes déjà piochées qu'il faut ignorer.
     */
    public void melanger() {
        for(int k = 0; k < nbActuel*2; k++){ //inverse nbActuel*2 fois, deux cartes choisies aléatoirement dans le paquet.
            int carte1 = Ut.randomMinMax(0, nbActuel-1);
            int carte2 = carte1;
            while(carte2 == carte1)
                carte2 = Ut.randomMinMax(0, nbActuel-1);
            Carte tempo = this.pile[carte2];
            this.pile[carte2] = this.pile[carte1];
            this.pile[carte1] = tempo;
        }
    }


    /*
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri selection.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées  qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=Ns4TPTC8whw&t=2s vidéo explicative
     */
    public Paquet trierSelection() {
        //Version Théodore
        /*Paquet paquet = new Paquet(this);
        int debNonTrié = 0;

        while(debNonTrié < paquet.nbActuel){
            int minActuel = debNonTrié;

            for(int i = debNonTrié+1; i < paquet.nbActuel; i++)
                if( (paquet.pile[i].compareTo(paquet.pile[minActuel])) == -1)
                    minActuel = i;
            if(minActuel != debNonTrié){
                Carte tempo = paquet.pile[minActuel];
                paquet.pile[minActuel] = paquet.pile[debNonTrié];
                paquet.pile[debNonTrié] = tempo;
            }
            debNonTrié++;
        }
        return paquet;*/

        //Version Hugo
        Paquet paquet = new Paquet(this);
        int n = paquet.nbActuel;

        int operationsElementaires = 0; //

        for (int debut=0; debut<n-1; debut++) {
            int min = debut;

            for (int actuel=debut+1; actuel<n; actuel++) {
                if ((paquet.pile[actuel].compareTo(paquet.pile[min])) == -1)
                    min = actuel;
                operationsElementaires++; //
            }

            Carte temp = paquet.pile[debut];
            paquet.pile[debut] = paquet.pile[min];
            paquet.pile[min] = temp;

            //operationsElementaires ++; //
        }

        System.out.println("Tri Sélection : " + operationsElementaires + " opérations élémentaires.");
        return paquet;
    }


    /*
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri bulles.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées  qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=lyZQPjUT5B4&embeds_referring_euri=https%3A%2F%2Fwww.developpez.com%2F&source_ve_path=Mjg2NjY&feature=emb_logo
     * vidéo explicative
     */
    public Paquet trierBulles() {
        Paquet paquet = new Paquet(this);
        int n = paquet.nbActuel;
        boolean trié = false;

        int operationsElementaires = 0; //
    
        while (!trié) {
            trié = true;
    
            for (int i=0; i<n-1; i++) {
                if ((paquet.pile[i].compareTo(paquet.pile[i + 1])) == 1) {
                    Carte temp = paquet.pile[i];
                    paquet.pile[i] = paquet.pile[i + 1];
                    paquet.pile[i + 1] = temp;
                    trié = false;

                    //operationsElementaires++; //
                }

                operationsElementaires++; //
            }
        }
        
        System.out.println("Tri Bulles : " + operationsElementaires + " opérations élémentaires."); //
        return paquet;
    }


    /*
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri insertion.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées  qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=ROalU379l3U&t=1s : vidéo explicative
     */
    public Paquet trierInsertion() {
        Paquet paquet = new Paquet(this);
        int n = paquet.nbActuel;

        int operationsElementaires = 0; //
        
        for (int i=0; i<n; i++) {
            int j = i - 1;
            
            while (j >= 0 && ((paquet.pile[j].compareTo(paquet.pile[j + 1])) == 1)) {
                Carte temp = paquet.pile[j];
                paquet.pile[j] = paquet.pile[j + 1];
                paquet.pile[j + 1] = temp;
                j = j - 1;
                operationsElementaires++; //
            }
            //operationsElementaires++; //
        }

        System.out.println("Tri Insertion : " + operationsElementaires + " opérations élémentaires.");
        return paquet;
    }


    /*
     * Action : Permet de tester les différents tris.
     * On doit s'assurer que chaque tri permette effectivement de trier un paquet mélangé.
     * Des messages d'informations devront être affichés.
     * La méthode est "static" et ne s'effectue donc pas sur la paquet courant "this".
     */
    public static void testTris() {
        Couleur[] couleurs = {Couleur.BLEU,Couleur.ROUGE,Couleur.VERT};
        int nbFiguresMax = 3;
        Figure[] figures = {Figure.CARRE ,Figure.ROND,Figure.LOSANGE};
        Texture[] textures = {Texture.PLEINE, Texture.RAYE,Texture.TRANSPARENT};
        
        Paquet paqueta = new Paquet(couleurs, nbFiguresMax, figures, textures);
        System.out.println("Pour "+getNombreCartesAGenerer(couleurs, nbFiguresMax, figures, textures)+" possibilités");

        long tempsSelection = Ut.getTempsExecution(paqueta::trierSelection);
        System.out.println("Selection :"+tempsSelection+" ms");
        long tempsBulles = Ut.getTempsExecution(paqueta::trierBulles);
        System.out.println("Bulles :"+tempsBulles+" ms");
        long tempsInsertion = Ut.getTempsExecution(paqueta::trierInsertion);
        System.out.println("Insertion :"+tempsInsertion+" ms");
        
        /*
        System.out.println(paqueta.toString() + "  //Paquet à trier");
        System.out.println(paqueta.trierSelection() + " //Tri par sélection, temps : " + tempsSelection);
        System.out.println(paqueta.trierBulles() + " //Tri à bulles, temps : " + tempsBulles);
        System.out.println(paqueta.trierInsertion() + " //Tri par insertion, temps : " + tempsInsertion);
        */
    }


    //Issu de la question 8 du sujet INCOMPLETE
    public static double proba3CR(int nbEssais) {
        Couleur[] couleurs = {Couleur.BLEU, Couleur.ROUGE, Couleur.VERT};
        int nbFiguresMax = 3;
        Figure[] figures = {Figure.CARRE,Figure.ROND,Figure.LOSANGE};
        Texture[] textures = {Texture.PLEINE,Texture.RAYE,Texture.TRANSPARENT};

        Paquet p;
        Carte[] tab;

        double proba = 0;

        for (int k = 1; k <= nbEssais; k++) {
            p = new Paquet(couleurs, nbFiguresMax, figures, textures);
            p.melanger();
            tab = p.piocher(9);
           
            int i = 0;
            int nbCR = 0;
            
            while (i < 9 && nbCR < 4)
                if (tab[i++].getCouleur() == Couleur.ROUGE)
                    nbCR++;
            if (nbCR == 3)
                proba += 1;    
        }

        return proba/nbEssais; 
    }


    /* Calcule la probabilité pour exactement 2 cartes losanges
     */
    public static double proba2CL(int nbEssais) {
        Couleur[] couleurs = {Couleur.BLEU, Couleur.ROUGE, Couleur.VERT};
        int nbFiguresMax = 3;
        Figure[] figures = {Figure.CARRE,Figure.ROND,Figure.LOSANGE};
        Texture[] textures = {Texture.PLEINE,Texture.RAYE,Texture.TRANSPARENT};

        Paquet p;
        Carte[] tab;

        double proba = 0;

        for (int k = 1; k <= nbEssais; k++) {
            p = new Paquet(couleurs, nbFiguresMax, figures, textures);
            p.melanger();
            tab = p.piocher(9);
           
            int i = 0;
            int nbCL = 0;
            
            while (i < 9 && nbCL < 3)
                if (tab[i++].getFigure() == Figure.LOSANGE)
                    nbCL++;
            if (nbCL == 2)
                proba += 1;    
        }

        return proba/nbEssais; 
    }

    public static double proba3CRet2CL(int nbEssais) {
        Couleur[] couleurs = {Couleur.BLEU, Couleur.ROUGE, Couleur.VERT};
        int nbFiguresMax = 3;
        Figure[] figures = {Figure.CARRE,Figure.ROND,Figure.LOSANGE};
        Texture[] textures = {Texture.PLEINE,Texture.RAYE,Texture.TRANSPARENT};

        Paquet p;
        Carte[] tab;

        double proba = 0;

        for (int k = 1; k <= nbEssais; k++) {
            p = new Paquet(couleurs, nbFiguresMax, figures, textures);
            p.melanger();
            tab = p.piocher(9);
           
            int i = 0;
            int nbCL = 0;
            int nbCR = 0;
            
            while (i < 9 && nbCL < 3){
                if (tab[i].getFigure() == Figure.LOSANGE)
                    nbCL++;
                if (tab[i++].getCouleur() == Couleur.ROUGE)
                    nbCR++;
            }
                
                    
            if (nbCL == 2 && nbCR == 3)
                proba += 1;
        }

        return proba/nbEssais; 
    }

    /*
     * Pre-requis : 0 < nbCartes <= nombre de cartes restantes dans le paquet.
     *
     * Action : Pioche nbCartes Cartes au dessus du Paquet this (et met à jour son état).
     *
     * Résultat : Un tableau contenant les nbCartes Cartes piochees dans le Paquet.
     *
     * Exemple :
     * Contenu paquet : [A,B,C,D,E,F,G]
     * Nombre de cartes restantes : 5. On considère donc seulement les cartes de 0 à 4.
     *
     * piocher(3)
     * Contenu paquet : [A,B,C,D,E,F,G]
     * Nombre de cartes restantes : 2
     * Renvoie [E,D,C]
     */
    //EXTENSION
    public Carte[] piocher(int nbCartes) {
        Carte[] cartePioche = new Carte[nbCartes];

        for (int i=0; i<nbCartes ; i++) {
            if(this.nbActuel > 1)
                cartePioche[i] = this.pile[this.nbActuel-1];
            else
                cartePioche[i] = null;
                
            this.nbActuel--;
        }

        return cartePioche;
    }


    /*
     * Résultat : Vrai s'il reste assez de cartes dans le paquet pour piocher nbCartes.
     */
    public boolean peutPiocher(int nbCartes) {
        if (this.nbActuel < nbCartes) 
            return false;
        return true;
    }


    /*
     * Résultat : Vrai s'il ne reste plus aucune cartes dans le paquet.
     */
    public boolean estVide() {
        if (this.nbActuel > 0) 
            return false;
        return true;
    }

    
    public int getNbActuel(){
        return this.nbActuel;
    }


    /*
     * Résultat : Une chaîne de caractères représentant le paquet sous la forme d'un tableau
     * [X, Y, Z...] représentant les cartes restantes dans le paquet.
     *
     * Exemple :
     * Contenu paquet : 1-O-P (rouge), 2-C-V (jaune), 3-L-P (jaune), 3-L-P (rouge), 1-L-V (bleu)
     * Nombre de cartes restantes : 3
     * Retourne [1-O-P, 2-C-V, 3-L-P] (et chaque représentation d'une carte est coloré selon la couleur de la carte...)
     */
    @Override
    public String toString() {
        String paquet = "[";

        for(int i = 0; i < nbActuel; i++){
            paquet += pile[i].toString();

            if(i != nbActuel-1) 
                paquet += ", ";
        }
        paquet += "]";
        
        return paquet;
    }

    
    public static void main(String[] args) {
        /*Couleur[] couleurs = {Couleur.BLEU, Couleur.ROUGE, Couleur.VERT};
        int nbFiguresMax = 3;
        Figure[] figures = {Figure.CARRE,Figure.ROND,Figure.LOSANGE};
        Texture[] textures = {Texture.PLEINE,Texture.RAYE,Texture.TRANSPARENT};

        Paquet paquet = new Paquet(couleurs, nbFiguresMax, figures, textures);*/

        //System.out.println(getNombreCartesAGenerer(couleurs, nbFiguresMax, figures, textures));

        testTris();
        //System.out.println(paquet.toString());
        //System.out.println(paquet.trierInsertion());
        //paquet.piocher(3);
        //System.out.println(paquet.peutPicoher(6));
        //System.out.println(paquet.estVide());
        //System.out.println(proba3CR(10000));
        //System.out.println(proba2CL(1000000));
        //System.out.println(proba3CRet2CL(1000000));
    }
}
