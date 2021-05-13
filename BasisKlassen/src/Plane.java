abstract class Plane {

    private  int row;
    private  int collumn;


    Plane(int row, int collumn) {
        this.row = row;
        this.collumn = collumn;
    }

    public void setCollumn(int collumn) {
        this.collumn = collumn;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getCollumn() {
        return collumn;
    }
}
