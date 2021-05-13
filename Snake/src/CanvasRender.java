import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class CanvasRender extends Render {

    CanvasRender(int rows, int collumns, int tileHeight, int tileWidth) {
        super(new ArrayList<>(),rows, collumns);
        list.add(new Square(tileHeight, tileWidth, new File("D:\\lukas\\Documents\\GIT\\Uni\\EigeneGit\\Semester2\\2DEngine\\Snake\\imageSrc\\blackSquare.png"),0));
        list.add(new Square(tileHeight, tileWidth, new File("D:\\lukas\\Documents\\GIT\\Uni\\EigeneGit\\Semester2\\2DEngine\\Snake\\imageSrc\\whiteSquare.png"),1));
        list.add(new Square(tileHeight, tileWidth, new File("D:\\lukas\\Documents\\GIT\\Uni\\EigeneGit\\Semester2\\2DEngine\\Snake\\imageSrc\\greenSquare.png"),2));
        list.add(new Square(tileHeight, tileWidth, new File("D:\\lukas\\Documents\\GIT\\Uni\\EigeneGit\\Semester2\\2DEngine\\Snake\\imageSrc\\head.png"),3));
    }

    @Override
    public void init(Graphics g, int[][] map) {
        for (int x = 0; x < row; x++) {
            for(int y = 0; y < collumn; y++) {
                if (map[x][y] != 0) {
                    this.renderTile(g, map[x][y], x*list.get(0).getSQUARE_HEIGTH(),y*list.get(0).getSQUARE_HEIGTH());
                }
            }
        }
    }
}
