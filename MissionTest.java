package spaceProbe;
import java.util.ArrayList;
import java.util.Scanner;;

/* Versao 2: cometários

To do: remover impressoes (System.out.println) que estao poluindo o codigo

*/

public class MissionTest{

    public static void main(String[] args) {
        

        // missao: sondas e um espaco onde acontece a missao
        // criar um array list de sondas
        // criar um espaco
        // criar a missao com as sondas e o espaco
        // supoem-se que o usuario ira inserir dados consistentes

        /*
        
        Space: constructor needs only boundaries' information
            private Position spaceBoundaries;
                Position:
                    public final int x; 
                     public final int y;
            protected SpaceInformation spaceInfo;

        SpaceProbe:
            protected Position coordinate;
            protected Character direction;
            protected ArrayList<Character> movements = new ArrayList<Character>();
            protected boolean isFirst; 

        Mission:
            private ArrayList<SpaceProbe> sondas= new ArrayList<SpaceProbe>();
            private Space missionArea = new Space();
        
        */

        Scanner sc = new Scanner(System.in);

        Position pos; // store Space and SpaceProbe position information
        int x,y; // store Position coordinates
        Character dir; // store movement direction
        Character control = 'A'; // controls if input continues or stops
        ArrayList<Character> arrC = new ArrayList<Character>(); // array of movements for the probe
        ArrayList<SpaceProbe> arrSp = new ArrayList<SpaceProbe>(); // array of probes for the mission
        SpaceProbe inputProbe; //store probes that will be added
        int count=1;//count probes 
        
        
        // create space
        System.out.println("Space boundaries, x and then y");
        x = sc.nextInt();
        y = sc.nextInt();
        pos = new Position(x,y);

        Space missionSpace1 = new Space(pos); // space created

        // Create space probes

        System.out.println("Input information about the probes, input X to stop and C to continue");
        // Space probe 1
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

        // Create other probes

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
        System.out.println("Probes at the end:");
        mission1.printProbes();

        sc.close();
    }

}