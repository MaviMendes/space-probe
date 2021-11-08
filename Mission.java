package spaceProbe;
import java.util.ArrayList;


public class Mission {
    
    private ArrayList<SpaceProbe> sondas= new ArrayList<SpaceProbe>();
    private Space missionArea;

    public Mission(ArrayList<SpaceProbe> sondasInfo, Space missonAreaInfo){

        for(SpaceProbe s:sondasInfo)
            sondas.add(s);
        
        missionArea = new Space(missonAreaInfo);
    }

    // mover sonda no espaco: 1- checar se eh a primeira e se colide
    // 2- checar se rompe delimitacoes do espaco, se sim, usar mod

    public void moveProbes(){

        System.out.println("Move space probes.\n");
        int i=1;
        for(SpaceProbe s: this.sondas)
        {
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

        else if(!s.isFirst)
            for (Character movement : s.movements)
                if(movement == 'M')
                    preventColision(s);
                else   
                    s.changeDirection(movement,s);
    }

    public void preventColision(SpaceProbe sp){

        System.out.println("preventColision.\n");

        Position newExpectedPosition;
        Position movementDirection = new Position(sp.getMovementDirection(sp.direction));

        // verify if new position is valid
        newExpectedPosition = new Position(sp.simulateMovement(sp, movementDirection));
        if(!sp.consistent(newExpectedPosition, missionArea.getXCoordinate(), missionArea.getYCoordinate())){
            newExpectedPosition = sp.correctPosition(newExpectedPosition, missionArea.getXCoordinate(), missionArea.getYCoordinate());
        }

        // check if there is a probe on this position
        
        if(missionArea.spaceInfo.positionHasProbe(newExpectedPosition)){
            SpaceProbe probeInDesiredPosition = new SpaceProbe(missionArea.spaceInfo.getObjectInPosition(newExpectedPosition));
            probeInDesiredPosition.moveFoward(probeInDesiredPosition, missionArea.getXCoordinate(), missionArea.getYCoordinate()); // move a sonda que estava parada e que iria sofrer colisao
        }

        // after avoiding colision, the current probe can be safely moved
        sp.moveFoward(sp,missionArea.getXCoordinate(), missionArea.getYCoordinate());
        
    }


    public void printProbes(){

        System.out.println("***Probes***");
        int i=1;
        for(SpaceProbe s:sondas){

            System.out.println("# Probe "+i);
            System.out.println(s.coordinate.x+","+s.coordinate.y);
            System.out.println(s.direction);
            System.out.println("______\n");
            i++;
        }
    }

}

