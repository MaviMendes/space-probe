# space-probe
Elo7 challenge
#  Movimentação de uma sonda espacial em um planalto

Desafio Elo7<br>
## Descrição da implementação do projeto

A partir da leitura do enunciado, decidi representar a situação da seguinte forma:<br>
<ul>
  <li>Uma sonda pode se mover respeitando as delimitações do espaço em que está (o planalto).</li>
  <li>O planalto/espaço tem informações específicas sobre ele (limites, sondas que estão se movendo nele).</li>
  <li>Uma missão espacial consiste de 1 ou mais sondas se movendo em um dado espaço/planalto.</li>
  <li>Os dados a serem inseridos pelo usuário serão consistentes.</li>
</ul>

### Descrição visual da organização

![alt text](https://github.com/MaviMendes/space-probe/blob/master/img/classes.jpg?raw=true)
![alt text](https://github.com/MaviMendes/space-probe/blob/master/img/mission.jpg?raw=true)
![alt text](https://github.com/MaviMendes/space-probe/blob/master/img/spaceprobe.jpg?raw=true)
![alt text](https://github.com/MaviMendes/space-probe/blob/master/img/space.jpg?raw=true)
![alt text](https://github.com/MaviMendes/space-probe/blob/master/img/spaceInformation.jpg?raw=true)
![alt text](https://github.com/MaviMendes/space-probe/blob/master/img/position.jpg?raw=true)
    
### Descrição detalhada
    
* Início:
* Input:
coordenadas do espaço
Instancia:
espaço
* Input:
Primeira sonda
Instancia objeto do tipo sonda
Adiciona à lista de sondas da missao
Adicionar mais sondas?
Input, instancia, adiciona à lista de sondas até decidir parar (digitar 'X')
Não entra no while
-> Com o espaço e as sondas instanciados:
Cria objeto do tipo missão que tem como parâmetros a lista de sondas e o espaço
Chama o método que moverá as sondas
Imprime o estado inicial e final das sondas
-> Missão
* Percorre a lista de sondas, movimentando-as em sequência
* Se a sonda é a primeira, chama o método move<br>
   * Para cada movimento na lista de movimentos da sonda, o método move chama o método makeMove, que chama o método para mudar a direção (changeDIrection) ou o método para andar para frente (moveFoward), a depender do tipo do movimento.<br>
    O método changeDirection realiza a mudança de direção do vetor da sonda.<br>
    O método moveFoward simula a nova posição da sonda antes de mudá-la, de fato, para verificar se esta consistente. Caso esteja, move, caso não esteja, corrige.
    A depender da direção, o vetor de movimento da sonda é o seguinte:<br>
        S = (0,-1), N = (0,1), E = (1,0), W = (-1,0)<br>
    Ao somar a coordenada atual com o vetor relacionado à direção, obtém-se a coordenada final.<br>
    A verificação de consistência realizada pelo método consistent é a seguinte: a sonda não pode ultrapassar o limite inferior (0,0) e nem o limite superior (ponto superior direito da malha do planalto). <br>
    Caso a sonda tenda a ultrapassar o limite inferior, ela ficará na posição inferior limite (x=0 ou y=0). <br>
    Caso a sonda tenda a ultrapassar o limite superior, ela ficará na posição superior limite.<br>
    As coordenadas esperadas são geradas pela função simulateMovement, e isso é feito a fim de evitar que a sonda receba uma posição inválida (o que equivaleria, na vida real, a causa um acidente, por exemplo).<br>
    Após verificar a consistência e, caso necessário, corrigir o valor, o método irá atualizar as coordenadas da sonda.<br>

* Se a sonda não é a primeira, há risco de choque. Por causa disso, primeiro é verificado, na classe Mission, se o movimento é de rotação ou de movimentação. Caso seja de rotação, o respectivo método é chamado.<br>
Caso a sonda precise se movimentar, é chamado o método preventColision.<br>
 * Em preventColision é calculada a posição esperada para a sonda.<br>
* Primeiro, verifica-se se essa posição é consistente e, caso não seja, seu valor é corrigido, conforme já explicado acima.<br>
Em seguida, é verificado se há uma sonda nessa posição. Se houver, é necessário mover a sonda que está na posição desejada, visto que ela já terminou seus movimentos e, assim, supõe-se que esteja inutilizada.<br>
* Caso exista uma sonda na posição para qual queremos mover outra sonda, movemos era para frente, chamando o método moveFoward.<br>
Nesse método de preventColision é utilizada  a classe SpaceInformation, que tem uma matriz que guarda na respectiva coordenada a sonda que está estacionada ali.<br>
* Após terminar a sua série de movimentações, as sondas são adicionadas a essa matriz. Isso acontece no método moveProbes de Mission:<br> missionArea.spaceInfo.addProbeToSpace(s);
