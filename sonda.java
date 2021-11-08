package elo7;

import java.util.ArrayList;

public class sonda {

    protected int[] currentPosition = new int[2];
    protected Character direction;
    protected ArrayList<Character> movements = new ArrayList<Character>();
    protected boolean isFirst; // metodo do espaco vai usar isso

    public sonda(int[] startPosition, Character startDirection, ArrayList<Character> ListOfMovements, boolean isFirstToMove){

        currentPosition[0] = startPosition[0];
        currentPosition[1] = startPosition[1];
        direction = startDirection;

        for(Character movement : ListOfMovements)
            movements.add(movement);

        isFirst = isFirstToMove;
    }
    
    public void move(ArrayList<Character> listOfMovements, int xLimit, int yLimit){

        System.out.println("move.\n");

        for(Character movement : movements){
            
            makeMove(movement, this,xLimit,yLimit);
            System.out.println("\n");
            System.out.print(this.currentPosition[0]+" , ");
            System.out.print(this.currentPosition[1]+"\n");
            System.out.println(this.direction);
            System.out.println("\n");
        }
    }

    public void makeMove(Character typeOfMovement,sonda S,int xLimit, int yLimit){

        System.out.println("make move.\n");

        if(typeOfMovement == 'R' || typeOfMovement == 'L'){
            changeDirection(typeOfMovement,S);
        }
        else{
            moveAhead(S,1,xLimit, yLimit);
        }
    }

    public void changeDirection(Character typeOfMovement,sonda S){

        System.out.println("change direction. type of movement: "+typeOfMovement+" current position: "+S.direction+"\n");

        if(typeOfMovement == 'L'){

            if(S.direction == 'N')
                S.direction = 'W';
            else if(S.direction == 'S')
                S.direction = 'E';
            else if(S.direction == 'W')
                S.direction = 'S';
            else if(S.direction == 'E')
                S.direction = 'N';
        }

        else if(typeOfMovement == 'R'){
            if(S.direction == 'N')
                S.direction = 'E';
            else if(S.direction == 'S')
                S.direction = 'W';
            else if(S.direction == 'W')
                S.direction = 'N';
            else if(S.direction == 'E')
                S.direction = 'S';
        }
    }

    public void moveAhead(sonda S, int movementExtension, int xLimit, int yLimit){

        System.out.println("move ahead.\n");
        
        Character currentDirection = S.direction;

        switch(currentDirection){

            case 'N':
                if((S.currentPosition[1] + movementExtension) <= yLimit){
                    System.out.println("N,Dentro do limite, "+(S.currentPosition[1] + movementExtension));
                    S.currentPosition[1] += movementExtension;
                }
                else  
                    S.currentPosition[1] += ((S.currentPosition[1]+movementExtension)%yLimit);
                break;
            case 'S':
                if((S.currentPosition[1] - movementExtension) >= 0){
                    System.out.println("S,Dentro do limite, "+(S.currentPosition[1] - movementExtension));
                    S.currentPosition[1] -= movementExtension;
                }
                else
                    S.currentPosition[1] = 0;

                break;
            case 'W':
                if((S.currentPosition[0] - movementExtension) >=0){
                    System.out.println("W,Dentro do limite, "+(S.currentPosition[0] - movementExtension));
                    S.currentPosition[0] -= movementExtension;
                }
                else
                    S.currentPosition[0] = 0;

                break;
            case 'E':

                if((S.currentPosition[0] + movementExtension) <= xLimit){
                    System.out.println("E,Dentro do limite, "+(S.currentPosition[0] + movementExtension));
                    S.currentPosition[0] += movementExtension; 
                }
                else  
                    S.currentPosition[0] = ((S.currentPosition[0]+movementExtension)%xLimit);
                break;
        }
    }

}
