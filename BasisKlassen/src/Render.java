import java.awt.*;
import java.util.ArrayList;

abstract public class Render {

    protected int row, collumn;
    protected ArrayList<Square> list;

    Render(ArrayList<Square> squares, int row, int collumn) {
        this.collumn = collumn;
        this.row = row;
        list = squares;
    }

    abstract public void init(Graphics g, int[][] map);

    public void renderTile(Graphics g, int imgID, int x, int y){
        Square sq = null;
        for (Square s : list) {
            if (s.getImgID() == imgID) {
                sq = s;
            }
        }
        //TODO Nullpointer Exception
        g.drawImage(sq.getImg() , x, y, sq.getSQUARE_WIDTH(), sq.getSQUARE_HEIGTH(), null);
    }
}
