import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Square implements Comparable<Square> {

    private final int SQUARE_HEIGTH, SQUARE_WIDTH;
    private BufferedImage img;
    private int imgID;


    Square(int height, int width, File file, int imgID) {
        SQUARE_HEIGTH = height;
        SQUARE_WIDTH = width;
        this.imgID = imgID;
        this.img = getImage(file);
    }

    private BufferedImage getImage(File file) {
        BufferedImage newImg;
        try {
            newImg = ImageIO.read(file);
        } catch (IOException e) {
            newImg = new BufferedImage(SQUARE_WIDTH, SQUARE_HEIGTH, BufferedImage.TYPE_INT_ARGB);
            newImg.getGraphics().setColor(Color.BLACK);
            e.printStackTrace();
        }
        return newImg;

    }

    public int getSQUARE_HEIGTH() {
        return SQUARE_HEIGTH;
    }

    public int getSQUARE_WIDTH() {
        return SQUARE_WIDTH;
    }

    public int getImgID() {
        return imgID;
    }

    @Override
    public int compareTo(Square s) {
        return Integer.compare(this.imgID, s.getImgID());
    }

    public BufferedImage getImg() {
        return img;
    }
}
