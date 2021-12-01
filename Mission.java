package spaceProbe;
import java.util.ArrayList;


public class Mission {
    
    private ArrayList<SpaceProbe> probes= new ArrayList<SpaceProbe>();
    private Space missionArea;

    public Mission(SpaceProbe probe, Space missonAreaInfo){

        addProbe(probe);
        missionArea = new Space(missonAreaInfo);
    }

    public Mission(ArrayList<SpaceProbe> probesToAdd, Space missonAreaInfo){

        addProbes(probesToAdd);
        missionArea = new Space(missonAreaInfo);

        missionArea.spaceInfo.showProbes();
    }

    public void addProbes(ArrayList<SpaceProbe> probes){

        for(SpaceProbe newProbe: probes){
            if(addProbe(newProbe))
                System.out.println("New probe added: "+newProbe);
            else{
               System.out.println("Probe "+newProbe+" not added, would colide with another probe");
            }
        }

    }

    public boolean addProbe(SpaceProbe newProbe){
        if(missionArea.spaceInfo.positionHasProbe(newProbe.coordinate))
            return false;
        else{
            probes.add(newProbe);
            missionArea.spaceInfo.addProbeToSpace(newProbe);
            return true;
        }
    }

    // mover sonda no espaco: 1- checar se eh a primeira e se colide
    // 2- checar se rompe delimitacoes do espaco, se sim, usar mod

    public void moveProbes(){

        System.out.println("Move space probes.\n");
        int i=1;
        for(SpaceProbe s: this.probes)
        {
            missionArea.spaceInfo.removeProbeFromPositon(s); // probe will be added to its final position after moving
            System.out.println("Moving probe "+i);
            moveSpaceProbe(s);
            missionArea.spaceInfo.addProbeToSpace(s);
            i++;
        }
    }

    public void moveSpaceProbe(SpaceProbe s){

        System.out.println("Move a specific space probe.\n");

        if(s.isFirst)
            s.move(s.movements,missionArea.getXCoordinate(),missionArea.getYCoordinate());

        else if(!s.isFirst){
            for (Character movement : s.movements){
                if(movement == 'M'){
                    boolean colision = willColide(s);
                    if(colision){
                        System.out.println("This probe would colide with the previous one, therefore it will not move anymore.");
                        break;
                    }
                    else continue;
                }
                else
                    s.changeDirection(movement,s);
            }
        }

    }

    public boolean willColide(SpaceProbe sp){

        System.out.println("Will a colision happen? Check this before continue moving.\n");

        Position newExpectedPosition;
        Position movementDirection = new Position(sp.getMovementDirection(sp.direction));

        // verify if new position is valid, if it is not, correct it
        newExpectedPosition = new Position(sp.simulateMovement(sp, movementDirection));
        if(!sp.consistent(newExpectedPosition, missionArea.getXCoordinate(), missionArea.getYCoordinate())){
            newExpectedPosition = sp.correctPosition(newExpectedPosition, missionArea.getXCoordinate(), missionArea.getYCoordinate());
        }

        // check if there is a probe on this position, now that the probe has a valid position
        
        if(missionArea.spaceInfo.positionHasProbe(newExpectedPosition)){
            System.out.println("A colision will happen, move not allowed");
            return true;
           /* SpaceProbe probeInDesiredPosition = new SpaceProbe(missionArea.spaceInfo.getObjectInPosition(newExpectedPosition));
            probeInDesiredPosition.moveFoward(probeInDesiredPosition, missionArea.getXCoordinate(), missionArea.getYCoordinate());*/ // move a sonda que estava parada e que iria sofrer colisao
        }
        else{
            // after avoiding colision, the current probe can be safely moved
            System.out.println("A colision will not happen, movement allowed, newExpectedPositon is "+newExpectedPosition);
            sp.moveFoward(sp,missionArea.getXCoordinate(), missionArea.getYCoordinate());
            return false;
        }
        
    }


    public void printProbes(){

        System.out.println("***Probes***");
        int i=1;
        for(SpaceProbe s:probes){

            System.out.println("# Probe "+i);
            System.out.println(s.coordinate.x+","+s.coordinate.y);
            System.out.println(s.direction);
            System.out.println("______\n");
            i++;
        }
    }

}

