package spaceProbe;

import java.util.ArrayList;

public class SpaceProbe {

    protected Position coordinate;
    protected Character direction;
    protected ArrayList<Character> movements = new ArrayList<Character>();
    protected boolean isFirst; // metodo do espaco vai usar isso

    public SpaceProbe(Position startPosition, Character startDirection, ArrayList<Character> ListOfMovements, boolean isFirstToMove){

        coordinate = new Position(startPosition);
        direction = startDirection;

        for(Character movement : ListOfMovements)
            movements.add(movement);

        isFirst = isFirstToMove;
    }

    public SpaceProbe(SpaceProbe someProbe){
        this(someProbe.coordinate,someProbe.direction,someProbe.movements,someProbe.isFirst);
    }

    // execute all moves
    public void move(ArrayList<Character> listOfMovements, int xLimit, int yLimit){

        System.out.println("move.\n");

        for(Character movement : movements){
            
            makeMove(movement, this,xLimit,yLimit); // call this for each move
            System.out.println("\n");
            System.out.println("movement: "+movement);
            System.out.print(this.coordinate.x+" , ");
            System.out.println(this.coordinate.y);
            System.out.println(this.direction);
            System.out.println("\n");
        }
    }
    // what kind of move
    public void makeMove(Character typeOfMovement,SpaceProbe sp,int xLimit, int yLimit){

        System.out.println("make move.\n");

        if(typeOfMovement == 'R' || typeOfMovement == 'L'){
            changeDirection(typeOfMovement,sp);
        }
        else{
            moveFoward(sp,xLimit, yLimit);
        }
    }

    // pretty straightforward
    public void changeDirection(Character typeOfMovement,SpaceProbe sp){

        System.out.println("change direction. type of movement: "+typeOfMovement+" current position: "+sp.direction+"\n");

        if(typeOfMovement == 'L'){

            if(sp.direction == 'N')
                sp.direction = 'W';
            else if(sp.direction == 'S')
                sp.direction = 'E';
            else if(sp.direction == 'W')
                sp.direction = 'S';
            else if(sp.direction == 'E')
                sp.direction = 'N';
        }

        else if(typeOfMovement == 'R'){
            if(sp.direction == 'N')
                sp.direction = 'E';
            else if(sp.direction == 'S')
                sp.direction = 'W';
            else if(sp.direction == 'W')
                sp.direction = 'N';
            else if(sp.direction == 'E')
                sp.direction = 'S';
        }

        
    }

    // before changing the position for real, make sure the probe is in a valid place considering the constraints
    public void moveFoward(SpaceProbe sp, int xLimit, int yLimit){

        Position newPosition;
        Position movementDirection = new Position(getMovementDirection(sp.direction));

        newPosition = new Position(simulateMovement(sp, movementDirection));

        if(!consistent(newPosition, xLimit, yLimit)){
            newPosition = correctPosition(newPosition, xLimit,yLimit);
        }

        // after checking consistency and correcting it, if necessary, position is updated
        
        sp.coordinate = newPosition;

    }

    public Position simulateMovement(SpaceProbe sp,Position movementDirection){
        
        int x=0,y=0;
        Position simulated;
        

        x = sp.coordinate.x + movementDirection.x;
        y = sp.coordinate.y + movementDirection.y;

        simulated = new Position(x,y);

        return simulated;

    }

    // each direction is associated with a vector that will be used in the linear transformation

    public Position getMovementDirection(Character d){

        Position vectorIndicatingMovementDirection = new Position();

        if(d == 'N')
            vectorIndicatingMovementDirection = new Position(0,1);
        
        else if(d == 'S')
            vectorIndicatingMovementDirection = new Position(0,-1);
            
        else if(d == 'E')
            vectorIndicatingMovementDirection = new Position(1,0);

        else if (d == 'W')
            vectorIndicatingMovementDirection = new Position(-1,0);
            
        return vectorIndicatingMovementDirection;

        }

        // checks if the new position respects the boundaries

        public boolean consistent(Position position, int xLimit, int yLimit){

            if(position.x < 0)
                return false;
            else if(position.x>xLimit)
                return false;
            else if(position.y < 0)
                return false;
            else if(position.y > yLimit)
                return false;
            else   
                return true;
        }


        public Position correctPosition(Position p, int xLimit, int yLimit){

            Position consistentPosition;
            int x=0,y=0;

            if(p.x < 0)
                x = 0;
            else if(p.x>xLimit)
                x = xLimit;
            else if(p.y < 0)
                y = 0;
            else if(p.y > yLimit)
                y = yLimit;
            
            consistentPosition = new Position(x,y);

            return consistentPosition;
            
        }
}
