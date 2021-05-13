

abstract class Game implements Runnable{

    protected boolean running = true;
    protected double FPS = 60;
    protected long maxLoopTime = (long) (1000 / FPS);
    protected Screen screen;
    protected final int ROWS;
    protected final int COLLUMS;
    protected final int TILE_HEIGHT;
    protected final int TILE_WIDTH;
    protected Render render;
    protected int[][] map;
    protected Controls keyManager;


    Game(int row, int collumns, int tileWidth, int tileHeight, String title) {
        this.screen = new Screen(title, tileWidth * row, tileHeight * collumns);
        map = new int[row][collumns];
        ROWS = row;
        COLLUMS = collumns;
        TILE_HEIGHT = tileHeight;
        TILE_WIDTH = tileWidth;
        render = initRender();
    }

    abstract Render initRender();

    void startGame() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        long time;
        long oldTime;
        init();

        keyManager = new Controls();
        screen.getFrame().addKeyListener(keyManager);

        while (running) {
            time = System.currentTimeMillis();
            update();
            oldTime = System.currentTimeMillis();
            if (oldTime - time >= maxLoopTime ) {
                System.out.println("Wir sind zu spät!");
                continue;}
            render();
            time = System.currentTimeMillis();
            if (time - oldTime < maxLoopTime) {
                try {
                    Thread.sleep(maxLoopTime - (time - oldTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        screen.getFrame().dispose();
    }

    abstract protected void init();

    abstract protected void update();

    abstract protected void render();
}
