package utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

import model.MoveSet;

public class MazeTemplateGenerator {

    private final int size;
    private final CellHistory[][] maze;
    private final Deque<CellHistory> stack = new ArrayDeque<>();
    private final Random rand = new Random();

    private int x;
    private int y;

    public MazeTemplateGenerator(int size) {
        this.size = size;
        this.maze = new CellHistory[size][size];
    }

    public CellHistory[][] createTemplate() {
        initMaze();
        initBorders();

        // start position
        x = rand.nextInt(size);
        y = rand.nextInt(size);

        CellHistory start = maze[x][y];
        start.setVisited();
        stack.push(start);

        while (!stack.isEmpty()) {
            CellHistory current = stack.peek();
            x = current.getxCoordinate();
            y = current.getyCoordinate();

            ArrayList<MoveSet> moves = getUnvisitedNeighbours();

            if (!moves.isEmpty()) {
                MoveSet move = moves.get(rand.nextInt(moves.size()));
                carve(current, move);
            } else {
                stack.pop(); // backtrack
            }
        }

        return maze;
    }

    // ---------- CORE LOGIC ----------

    private void carve(CellHistory current, MoveSet move) {
        current.deleteFreeDirection(move);

        int nx = x;
        int ny = y;

        switch (move) {
            case UP -> ny--;
            case DOWN -> ny++;
            case LEFT -> nx--;
            case RIGHT -> nx++;
        }

        CellHistory next = maze[nx][ny];
        next.deleteFreeDirection(inverse(move));
        next.setVisited();
        stack.push(next);
    }

    private ArrayList<MoveSet> getUnvisitedNeighbours() {
        ArrayList<MoveSet> result = new ArrayList<>();

        if (y > 0 && !maze[x][y - 1].isVisited()) result.add(MoveSet.UP);
        if (y < size - 1 && !maze[x][y + 1].isVisited()) result.add(MoveSet.DOWN);
        if (x > 0 && !maze[x - 1][y].isVisited()) result.add(MoveSet.LEFT);
        if (x < size - 1 && !maze[x + 1][y].isVisited()) result.add(MoveSet.RIGHT);

        return result;
    }

    private MoveSet inverse(MoveSet m) {
        return switch (m) {
            case UP -> MoveSet.DOWN;
            case DOWN -> MoveSet.UP;
            case LEFT -> MoveSet.RIGHT;
            case RIGHT -> MoveSet.LEFT;
        };
    }

    // ---------- INIT ----------

    private void initMaze() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maze[i][j] = new CellHistory(i, j);
            }
        }
    }

    private void initBorders() {
        for (int i = 0; i < size; i++) {
            maze[i][0].deleteFreeDirection(MoveSet.UP);
            maze[i][size - 1].deleteFreeDirection(MoveSet.DOWN);
            maze[0][i].deleteFreeDirection(MoveSet.LEFT);
            maze[size - 1][i].deleteFreeDirection(MoveSet.RIGHT);
        }
    }
}