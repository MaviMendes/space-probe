package spaceProbe;

public class SpaceInformation {
    private SpaceProbe[][] probeMap;

    public SpaceInformation(int x, int y) {
        probeMap = new SpaceProbe[x+1][y+1];
    }

    public void addProbeToSpace(SpaceProbe newProbe){
        probeMap[newProbe.coordinate.x][newProbe.coordinate.y] = newProbe;
    }
    public boolean positionHasProbe(Position candidatePosition) {

        if(probeMap[candidatePosition.x][candidatePosition.y] == null) {
            return false;
        }

        return true;
    }

    public SpaceProbe getObjectInPosition(Position objectPosition) {
        return probeMap[objectPosition.x][objectPosition.y];
    }

}