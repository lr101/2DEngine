import java.awt.*;
import java.awt.image.BufferStrategy;

public class Snake extends Game {

    private SnakeRules rules;


    Snake() {
        super(40,20,25,25,"Snake");
        rules = new SnakeRules(ROWS, COLLUMS);
        startGame();
    }

    @Override
    Render initRender() {
        return new CanvasRender(this.ROWS, COLLUMS, TILE_HEIGHT, TILE_WIDTH);
    }

    @Override
    protected void init() {
        rules.init(map);

        BufferStrategy bs = screen.getCanvas().getBufferStrategy();
        if (bs == null) {
            screen.getCanvas().createBufferStrategy(1);
            bs = screen.getCanvas().getBufferStrategy();
        }
        screen.getCanvas().setBackground(Color.BLACK);

        Graphics g = bs.getDrawGraphics();
        render.init(g, map);
        bs.show();
        g.dispose();
        FPS = 7;
        maxLoopTime = rules.setGameSpeed(FPS);
    }

    @Override
    protected void update() {
        keyManager.update();
        int direction = 4;
        if (keyManager.up) direction = 0;
        if (keyManager.right) direction = 1;
        if (keyManager.down) direction = 2;
        if (keyManager.left) direction = 3;
        rules.move(map, direction);

        if (rules.checkFood(map))  {
            FPS *= 1.03;
            maxLoopTime = rules.setGameSpeed(FPS);
        }

        running = !rules.collision(map);
    }

    @Override
    protected void render() {
        BufferStrategy bs = screen.getCanvas().getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        render.renderTile(g, 0, rules.rowOfDelet()  * TILE_HEIGHT, rules.colOfDelet()  * TILE_WIDTH);
        render.init(g, map);
        bs.show();
        g.dispose();
    }
}
