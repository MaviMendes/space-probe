package spaceProbe;

public class Space {
    
    private Position spaceBoundaries;
    protected SpaceInformation spaceInfo;
 
    public Space(){

        spaceBoundaries = new Position();
    }

    public Space(Position spaceBoundariesInformation){

        spaceBoundaries = new Position(spaceBoundariesInformation);
        spaceInfo = new SpaceInformation(spaceBoundariesInformation.x, spaceBoundariesInformation.y);
    }

    public Space(Space someSpace){
        spaceBoundaries = new Position(someSpace.getXCoordinate(),someSpace.getYCoordinate());
        spaceInfo = new SpaceInformation(this.getXCoordinate(), this.getYCoordinate());
    }

    public Space(int x, int y){
        spaceBoundaries = new Position(x,y);
        spaceInfo = new SpaceInformation(x,y);
    }

    public int getXCoordinate(){

        return spaceBoundaries.x;
    }

    public int getYCoordinate(){

        return spaceBoundaries.y;
    }
}

