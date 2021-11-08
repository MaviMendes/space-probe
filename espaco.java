package elo7;

public class espaco {
    
    private int x;
    private int y;

 
    public espaco(int xCoordenate,int yCoordenate){

        setX(xCoordenate);
        setY(yCoordenate);
    }

    public espaco(){

        x = 0;
        y = 0;
    }

    public void setX(int xCoordenate){
        x = xCoordenate;
    }

    public void setY(int yCoordenate){
        y = yCoordenate;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}

