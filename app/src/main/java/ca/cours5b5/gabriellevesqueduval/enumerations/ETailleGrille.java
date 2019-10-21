package ca.cours5b5.gabriellevesqueduval.enumerations;

public enum ETailleGrille {
    petite, moyenne, grande;

    public int getHauteur(){
        int hauteur = 0;
        if(this == petite){
            hauteur = 2;
        }else if(this == moyenne){
            hauteur = 4;
        }else if (this == grande){
            hauteur = 6;
        }
        return hauteur;
    }

    public int getLargeur(){
        int largeur = 0;
        if(this == petite){
            largeur = 2;
        }else if(this == moyenne){
            largeur = 4;
        }else if (this == grande){
            largeur = 6;
        }
        return largeur;
    }
}
