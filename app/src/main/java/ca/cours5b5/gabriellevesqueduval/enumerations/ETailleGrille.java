package ca.cours5b5.gabriellevesqueduval.enumerations;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public enum ETailleGrille {
    petite, moyenne, grande;

    public int getHauteur(){
        GLog.appel(this);
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
        GLog.appel(this);
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
