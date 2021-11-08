
/* Versao 1: a classe Position e SpaceInformation foram criadas
Position formaliza a representacao de uma posicao em coordenadas do plano cartesiano
SpaceInformation auxilia na prevencao de colisao ao guardar informacao referente a posicao das sondas que ja se movimentaram

Maria Vitoria, 7/11/2021

*/



package spaceProbe;
import java.util.ArrayList;
import java.util.Scanner;;

public class MissionTest{

    public static void main(String[] args) {
        

        // missao: sondas e um espaco
        // criar um array list de sondas
        // criar um espaco

        /*
        
        Space: constructor needs only boundaries information
            private Position spaceBoundaries;
                Position:
                    public final int x; 
                     public final int y;
            protected SpaceInformation spaceInfo;

        SpaceProbe:
            protected Position coordinate;
            protected Character direction;
            protected ArrayList<Character> movements = new ArrayList<Character>();
            protected boolean isFirst; // metodo do espaco vai usar isso

        Mission:
            private ArrayList<SpaceProbe> sondas= new ArrayList<SpaceProbe>();
            private Space missionArea = new Space();
        
        */

        Scanner sc = new Scanner(System.in);

        Position pos;
        int x,y;
        Character dir;
        Character control = 'A';
        ArrayList<Character> arrC = new ArrayList<Character>();
        ArrayList<SpaceProbe> arrSp = new ArrayList<SpaceProbe>();
        SpaceProbe inputProbe;
        int count=1;//count probes just for output layoung sakes
        
        
        // space
        System.out.println("Space boundaries, x and then y");
        x = sc.nextInt();
        y = sc.nextInt();
        pos = new Position(x,y);

        Space missionSpace1 = new Space(pos); // space created

        // create space probes

        System.out.println("Input information about the probes, input X to stop and C to continue");
        // space probe 1
        System.out.println("Initial position of probre "+count+", insert x and then y");
        x = sc.nextInt();
        y = sc.nextInt();
        pos = new Position(x,y); 
        System.out.println("Direction:");
        dir = sc.next().charAt(0); // read Character type
        System.out.println("Movements: input movements and print X to stop.");
        control = sc.next().charAt(0);
        arrC.add(control);
        while(control != 'X'){
            control = sc.next().charAt(0);
            if(control != 'X')
                arrC.add(control);
        }

        inputProbe = new SpaceProbe(pos,dir,arrC,true); // creates probe
        arrSp.add(inputProbe); // adds probe to the list

        System.out.println("Add more probes? C to continue, X to stop");
        control = sc.next().charAt(0);

        // others

        while(control != 'X'){

            // space probe
            System.out.println("Initial position, x and then y");
            x = sc.nextInt();
            y = sc.nextInt();
            pos = new Position(x,y);

            System.out.println("Direction:");
            dir = sc.next().charAt(0); // read Character type

            // clean arrC to input new movements
            arrC.clear();
            System.out.println("Movements: input movements and print X to stop.");
            control = sc.next().charAt(0);
            arrC.add(control);
            while(control != 'X'){
                control = sc.next().charAt(0);
                if(control != 'X')
                    arrC.add(control);
            }

            inputProbe = new SpaceProbe(pos,dir,arrC,false); // creates probe
            arrSp.add(inputProbe); // adds probe to the list

            System.out.println("Add more probes? C to continue, X to stop");
            control = sc.next().charAt(0);
        }

        Mission mission1 = new Mission(arrSp,missionSpace1);

        System.out.println("Probes at the beggining:");
        mission1.printSondas();

        mission1.moveProbes();
        System.out.println("Probes at the end5:");
        mission1.printSondas();
    }


}