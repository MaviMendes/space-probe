package elo7;
import java.util.ArrayList;


public class missao {
    
    private ArrayList<sonda> sondas= new ArrayList<sonda>();
    private espaco missionArea = new espaco();

    public missao(ArrayList<sonda> sondasInfo, espaco missonAreaInfo){

        for(sonda s:sondasInfo)
            sondas.add(s);
        
        missionArea.setX(missonAreaInfo.getX());
        missionArea.setY(missonAreaInfo.getY());
    }

    // mover sonda no espaco: 1- checar se eh a primeira e se colide
    // 2- checar se rompe delimitacoes do espaco, se sim, usar mod

    public void moveProbes(){

        System.out.println("Move space probes.\n");
        int i=1;
        for(sonda s: this.sondas)
        {
            System.out.println("Moving probe "+i);
            moveSpaceProbe(s);
            i++;
        }
    }

    public void moveSpaceProbe(sonda s){

        System.out.println("Move a specific space probe.\n");

        if(s.isFirst)
            s.move(s.movements,missionArea.getX(),missionArea.getY());

        else if(!s.isFirst)
            for (Character movement : s.movements)
                if(movement == 'M')
                    preventColision(s);
                else   
                    s.changeDirection(movement,s);
    }

    public void preventColision(sonda s){

        System.out.println("preventColision.\n");

        // qual seria a posicao, se movesse
        int[] newPosition = new int[2];

        switch(s.direction){ // deve ser uma funcao

            case 'N':
                newPosition[0] = s.currentPosition[0];
                newPosition[1] = s.currentPosition[1]+1;
                break;
            case 'S':
                newPosition[0] = s.currentPosition[0];
                newPosition[1] = s.currentPosition[1]-1;
                break;
            case 'W':
                newPosition[0] = s.currentPosition[0] -1;
                newPosition[1] = s.currentPosition[1];
                break;
            case 'E':
                newPosition[0] = s.currentPosition[0]+1;
                newPosition[1] = s.currentPosition[1];
                break;
        }

        System.out.println("newPosition would be: "+newPosition[0]+", "+newPosition[1]);

        for(int i=0;i<=sondas.size()-2;i++){

            sonda current = sondas.get(i);
            if(current.currentPosition[0] == newPosition[0] && current.currentPosition[1] == newPosition[1])
                s.moveAhead(s,2,missionArea.getX(),missionArea.getY()); // ir
        }
    }


    public void printSondas(){

        System.out.println("Sondas:");
        int i=1;
        for(sonda s:sondas){

            System.out.println("# Sonda "+i);
            System.out.println(s.currentPosition[0]+","+s.currentPosition[1]);
            System.out.println(s.direction);
            System.out.println("______");
            i++;
        }
    }

}


