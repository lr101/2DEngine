public class SnakePlane extends Plane implements Comparable<SnakePlane> {

    SnakePlane(int row, int collumn) {
        super(row, collumn);
    }

    @Override
    public int compareTo(SnakePlane o) {
        if (this.getRow() == o.getRow() && this.getCollumn() == o.getCollumn()) {
            return 0;
        } else {
            return -1;
        }
    }
}
