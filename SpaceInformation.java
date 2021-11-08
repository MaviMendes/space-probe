package spaceProbe;

public class SpaceInformation {
    private SpaceProbe[][] probeMap;

    public SpaceInformation(int x, int y) {
        probeMap = new SpaceProbe[x+1][y+1];
    }

    // after a probe finishes moving, it is added to this list in order to be possible to know if
    // there is a probe in its final coordinate or not
    // this is useful to prevent colision

    public void addProbeToSpace(SpaceProbe newProbe){
        probeMap[newProbe.coordinate.x][newProbe.coordinate.y] = newProbe;
    }

    // check if there is a probe at some position

    public boolean positionHasProbe(Position candidatePosition) {

        if(probeMap[candidatePosition.x][candidatePosition.y] == null) {
            return false;
        }

        return true;
    }

    // returns a probe in a given position
    
    public SpaceProbe getObjectInPosition(Position objectPosition) {
        return probeMap[objectPosition.x][objectPosition.y];
    }

}