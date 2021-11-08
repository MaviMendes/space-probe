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
            
            System.out.println("movement: "+movement);
            makeMove(movement, this,xLimit,yLimit); // call this for each move
            System.out.println("\n");
            System.out.println("After movement, direction is: "+this.direction);
            System.out.print("coordinates are: "+this.coordinate.x+" , ");
            System.out.println(this.coordinate.y);
            System.out.println("\n");
        }
    }
    // what kind of move
    public void makeMove(Character typeOfMovement,SpaceProbe sp,int xLimit, int yLimit){

        System.out.println("make move.\n");

        if(typeOfMovement == 'R' || typeOfMovement == 'L'){
            System.out.println("make move calls change direction");
            changeDirection(typeOfMovement,sp);
        }
        else{
            System.out.println("make move calls moveFoward");
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

        System.out.println("move foward");

        Position newPosition;
        Position movementDirection = new Position(getMovementDirection(sp.direction));

        newPosition = new Position(simulateMovement(sp, movementDirection));

        if(!consistent(newPosition, xLimit, yLimit)){
            System.out.println("not consistent, on move foward, call correctPosition");
            System.out.println("newPosition before: "+newPosition.x+","+newPosition.y);
            newPosition = correctPosition(newPosition, xLimit,yLimit);
            System.out.println("newPosition after correcting it: "+newPosition.x+","+newPosition.y);
        }

        // after checking consistency and correcting it, if necessary, position is updated
        
        sp.coordinate = newPosition;

    }

    public Position simulateMovement(SpaceProbe sp,Position movementDirection){
        
        System.out.println("simulate movement");

        int x=0,y=0;
        Position simulated;
        
        System.out.println("movementDirection: "+movementDirection.x+","+movementDirection.y);

        x = sp.coordinate.x + movementDirection.x;
        y = sp.coordinate.y + movementDirection.y;

        simulated = new Position(x,y);

        System.out.println("simulated coordinate: "+simulated.x+","+simulated.y);

        return simulated;

    }

    // each direction is associated with a vector that will be used in the linear transformation

    public Position getMovementDirection(Character d){

        System.out.println("get movement direction");

        Position vectorIndicatingMovementDirection = new Position();

        if(d == 'N')
            vectorIndicatingMovementDirection = new Position(0,1);
        
        else if(d == 'S')
            vectorIndicatingMovementDirection = new Position(0,-1);
            
        else if(d == 'E')
            vectorIndicatingMovementDirection = new Position(1,0);

        else if (d == 'W')
            vectorIndicatingMovementDirection = new Position(-1,0);
            
        System.out.println("Movement direction for direction "+d+" is: "+vectorIndicatingMovementDirection.x+","+vectorIndicatingMovementDirection.y);
        
        return vectorIndicatingMovementDirection;

        }

        // checks if the new position respects the boundaries

        public boolean consistent(Position position, int xLimit, int yLimit){

            System.out.println("check if it is consistent");

            if(position.x < 0){

                System.out.println("pos x < 0, posx = "+position.x);
                return false;
            }
            else if(position.x>xLimit){
                System.out.println("pos x > limit, posx = "+position.x+", limit = "+xLimit);
                return false;
            }
            else if(position.y < 0){
                System.out.println("pos y < 0, posy = "+position.y);
                return false;
            }
            else if(position.y > yLimit){
                System.out.println("pos y > limit, posy = "+position.y+", limit = "+yLimit);
                return false;
            }
            else{
                System.out.println("consistency ok");
                return true;
            }   
        }


        public Position correctPosition(Position p, int xLimit, int yLimit){

            System.out.println("correct position");

            Position consistentPosition;
            int x=p.x,y=p.y;

            if(p.x < 0){
                System.out.println("px < 0");
                x = 0;
            }
            else if(p.x>xLimit){
                System.out.println("px > limit");
                x = xLimit;
            }
            else if(p.y < 0){
                System.out.println("py < 0");
                y = 0;
            }
            else if(p.y > yLimit){
                System.out.println("px > 0");
                y = yLimit;
            }
            
            consistentPosition = new Position(x,y);

            return consistentPosition;
            
        }
}
