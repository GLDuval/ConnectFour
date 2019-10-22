package ca.cours5b5.gabriellevesqueduval.enumerations;

public enum ETailleGrille {
    petite, moyenne, grande;

    public int getHauteur(){
        int hauteur = 0;
        if(this == petite){
            hauteur = 4;
        }else if(this == moyenne){
            hauteur = 6;
        }else if (this == grande){
            hauteur = 8;
        }
        return hauteur;
    }

    public int getLargeur(){
        int largeur = 0;
        if(this == petite){
            largeur = 4;
        }else if(this == moyenne){
            largeur = 6;
        }else if (this == grande){
            largeur = 8;
        }
        return largeur;
    }
}
