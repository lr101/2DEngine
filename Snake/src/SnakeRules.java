import java.util.LinkedList;
import java.util.Random;

public class SnakeRules extends Rules {
    


    private LinkedList<SnakePlane> snakePlanes = new LinkedList<>();
    private SnakePlane deletetPlane;
    private SnakePlane foodPlane;
    private final int HEAD = 3;
    private final int SNAKE_BODY = 1;
    private final int BLACK = 0;
    private final int FOOD = 2;

    /**
     * 0: oben
     * 1: rechts
     * 2: unten
     * 3: links
     * 4: so wie vorher
     */
    private int direction = 0;
    
    SnakeRules(int ROW, int COLLUMN) {
        super(ROW, COLLUMN);
        deletetPlane = new SnakePlane(-1, -1);
        foodPlane = new SnakePlane(-1, -1);

    }

    @Override
    void init(int[][] map) {
        map[ROW/2][COLLUMN/2] = HEAD;
        snakePlanes.add(new SnakePlane(ROW/2, COLLUMN/2));
        foodPlane = addFood(map);
    }

    void move(int[][] map, int newDirection) {
        //printMap(map);
        //System.out.println(snakePlanes.size());


        //if no move or incorrect move, don't change direction
        if (newDirection == 4 || Math.abs(direction - newDirection) % 2 == 0) newDirection = direction;

        //Create new First
        SnakePlane first = new SnakePlane(snakePlanes.getFirst().getRow(), snakePlanes.getFirst().getCollumn());

        //Update direction
        direction = newDirection;

        //set first in direction
        if (newDirection == 0) {
            if (first.getCollumn() > 0) {
                map[first.getRow()][first.getCollumn() - 1] = HEAD;
                first.setCollumn(first.getCollumn() - 1);
            } else {
                map[first.getRow()][COLLUMN - 1] = HEAD;
                first.setCollumn(COLLUMN - 1);
            }
        } else if (newDirection == 1) {
            if (first.getRow() < ROW - 1) {
                map[first.getRow() + 1][first.getCollumn()] = HEAD;
                first.setRow(first.getRow() + 1);
            } else {
                map[0][first.getCollumn()] = HEAD;
                first.setRow(0);
            }
        } else if (newDirection == 2) {
            if (first.getCollumn() < COLLUMN - 1) {
                map[first.getRow()][first.getCollumn() +1] = HEAD;
                first.setCollumn(first.getCollumn() + 1);
            } else {
                map[first.getRow()][0] = HEAD;
                first.setCollumn(0);
            }
        } else if (newDirection == 3) {
            if (first.getRow() > 0) {
                map[first.getRow() - 1][first.getCollumn()] = HEAD;
                first.setRow(first.getRow() - 1);
            } else {
                map[ROW - 1][first.getCollumn()] = HEAD;
                first.setRow(ROW - 1);
            }
        }


        //Remove Last
        deletetPlane = new SnakePlane(snakePlanes.getLast().getRow(), snakePlanes.getLast().getCollumn());
        snakePlanes.removeLast();
        map[deletetPlane.getRow()][deletetPlane.getCollumn()] = BLACK;
        snakePlanes.addFirst(first);
        if (snakePlanes.size() > 1) {
            map[snakePlanes.get(1).getRow()][snakePlanes.get(1).getCollumn()] = SNAKE_BODY;
        }





    }

    @Override
    public long setGameSpeed(double FPS) {
        return (long) (1000/(FPS));
    }

    boolean checkFood(int[][] map) {
        if (snakePlanes.getFirst().compareTo(foodPlane) == 0) {
            snakePlanes.addLast(deletetPlane);
            foodPlane = addFood(map);
            map[deletetPlane.getRow()][deletetPlane.getCollumn()] = SNAKE_BODY;
            return true;
        }
        return false;
    }

    /*private void printMap(int[][] map) {
        for (int y = 0; y < map[0].length; y++) {
            for (int i = 0; i < map.length; i++) {

                System.out.print(map[i][y] + " ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");
    }*/

    boolean collision(int[][] map) {
        if (map[snakePlanes.getFirst().getRow()][snakePlanes.getFirst().getCollumn()] != 0) {
            for (SnakePlane s : snakePlanes) {
                if (snakePlanes.getFirst().compareTo(s) == 0 && snakePlanes.getFirst() != s) {
                    return true;
                }
            }
        }
        return false;
    }


    private SnakePlane addFood(int[][] map) {
        boolean found = false;
        int randomRow = 0;
        int randomCollumn  = 0;
        while (!found) {
            randomRow = (int) Math.floor(Math.random()*(ROW));
            randomCollumn = (int) Math.floor(Math.random()*(COLLUMN));
            if (map[randomRow][randomCollumn] == 0) {
                found = true;
                map[randomRow][randomCollumn] = FOOD;
            }
        }
        return new SnakePlane(randomRow,randomCollumn);
    }
    int rowOfDelet() {
        return deletetPlane.getRow();
    }
    int colOfDelet() {
        return deletetPlane.getCollumn();
    }
}
