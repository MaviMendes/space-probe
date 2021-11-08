// Versao 0: direto do rascunho escrito, sem muito overthinking, parece funcionar so pra 1 sonda, mas precisa de mais organizacao e aplicacao de conceitos
// Maria Vitoria, 7/11/2021
/*

-> padronizar o nome das classes e metodos
-> melhorar e fazer funcionar corretamente o metodo que evita colisao

*/

package elo7;
import java.util.ArrayList;

public class modeloSonda{

    public static void main(String[] args) {
        

        // missao: sondas e um espaco
        // criar um array list de sondas
        // criar um espaco

        ArrayList<sonda> sondasDaMissao= new ArrayList<sonda>();

        int[] pos = new int[2];
        pos[0] = 1;
        pos[1]=2;
        ArrayList<Character> movements = new ArrayList<Character>(); // LML MLM LMM
        movements.add('L');
        movements.add('M');
        movements.add('L');
        movements.add('M');
        movements.add('L');
        movements.add('M');
        movements.add('L');
        movements.add('M');
        movements.add('M');

        sonda s1 = new sonda(pos,'N',movements, true);
        
        sondasDaMissao.add(s1);

        espaco e1 = new espaco(5,5);

        // sonda 2

        int[] pos2 = new int[2];
        pos2[0] = 3;
        pos2[1]=3;
        ArrayList<Character> movements2 = new ArrayList<Character>(); // MMR MMR MRRM
        movements2.add('M');
        movements2.add('M');
        movements2.add('R');
        movements2.add('M');
        movements2.add('M');
        movements2.add('R');
        movements2.add('M');
        movements2.add('R');
        movements2.add('R');
        movements2.add('M');

        sonda s2 = new sonda(pos2,'E',movements2, false);
        
        sondasDaMissao.add(s2);

        // criar missao

        missao m1 = new missao(sondasDaMissao,e1);

        m1.moveProbes();

        m1.printSondas();

        // Outra missao, outro espaco


    }

}