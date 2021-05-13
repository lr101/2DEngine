abstract public class Rules {

    protected final int ROW, COLLUMN;

    public Rules(int row, int collumn) {
        ROW = row;
        COLLUMN = collumn;
    }

    abstract void init (int[][] map);


    public abstract long setGameSpeed(double FPS);
}
