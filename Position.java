package spaceProbe;

public class Position {
    public final int x; // imutavel
    public final int y;

    public Position() {
        this(0, 0);
    }

    public Position(Position posicao) { // ?
        this(posicao.x, posicao.y);
    }

    public Position(int coordX, int coordY) {
        x = coordX;
        y = coordY;
    }

    
}