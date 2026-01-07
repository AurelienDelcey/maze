package utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import model.MoveSet;

public class MazeTemplateGenerator {
	
	private CellHistory current ;
	private final int size;
	private final Random rand ;
	private final int[] cursor ;
	private final CellHistory[][] mazeTemplate ;
	private final Deque<CellHistory> stack;
	
	

	public MazeTemplateGenerator(int size) {
		this.stack = new ArrayDeque<>();
		this.size = size;
		this.rand = new Random();
		this.cursor = new int[]{0,0};
		this.current = null;
		mazeTemplate = new CellHistory[this.size][this.size];
	}
	
	public CellHistory[][] createTemplate() {
		
		MoveSet[] possibleMove = null;
		MoveSet nextMove = null;
		initArray();
		
		
		setCursorStart();
		this.current = getCurrent();
		stack.push(this.current);
		while (true) {
			this.current.setVisited();
			possibleMove = searchPossibleMoves();
			while (possibleMove == null) {
				if (stack.isEmpty()) {
					return this.mazeTemplate;
				}
				CellHistory popCell = this.stack.peek();
				cursor[0] = popCell.getxCoordinate();
				cursor[1] = popCell.getyCoordinate();
				this.current = getCurrent();
				possibleMove = searchPossibleMoves();
				if(possibleMove==null) {
					this.stack.pop();
				}
			}
			nextMove = getRandomMove(possibleMove);
			openWay(nextMove);
			moveCursor(nextMove);
			this.current = getCurrent();
			stack.push(this.current);
		}
	}
	
	private void openWay(MoveSet move) {
		MoveSet moveInvers = getInverseMove(move);
		this.mazeTemplate[this.cursor[0]][this.cursor[1]].deleteFreeDirection(move);
		moveCursor(move);
		this.mazeTemplate[this.cursor[0]][this.cursor[1]].deleteFreeDirection(moveInvers);
		moveCursor(moveInvers);
		
	}
	
	private void initArray() {
		for (int i = 0; i < this.mazeTemplate.length; i++) {
			for (int j = 0; j < this.mazeTemplate.length; j++) {
				this.mazeTemplate[i][j] = new CellHistory(i,j);
			}
		}
		
	}
	
	private MoveSet getInverseMove(MoveSet move) {
		return switch(move){
			case UP -> MoveSet.DOWN;
			case DOWN -> MoveSet.UP;
			case LEFT -> MoveSet.RIGHT;
			case RIGHT -> MoveSet.LEFT;
		};
	}
	
	private void moveCursor(MoveSet move) {
		switch(move) {
		case UP ->this.cursor[1] -= 1;
		case DOWN-> this.cursor[1] += 1;
		case LEFT-> this.cursor[0] -= 1;
		case RIGHT-> this.cursor[0] += 1;
		}
	}
	
	private MoveSet[] searchPossibleMoves() {
		int counter = 0;
		MoveSet[] possibleMoves = null;
		if(checkLeft()) {counter++;}
		if(checkRight()) {counter++;}
		if(checkUp()) {counter++;}
		if(checkDown()) {counter++;}
		if(counter==0) {return null;}
		possibleMoves = new MoveSet[counter];
		counter = 0;
		if(checkLeft()) {possibleMoves[counter]=MoveSet.LEFT;counter++;}
		if(checkRight()) {possibleMoves[counter]=MoveSet.RIGHT;counter++;}
		if(checkUp()) {possibleMoves[counter]=MoveSet.UP;counter++;}
		if(checkDown()) {possibleMoves[counter]=MoveSet.DOWN;counter++;}
		return possibleMoves;
	}
	
	private void setCursorStart() {
		this.cursor[0] = rand.nextInt(this.size);
		this.cursor[1] = rand.nextInt(this.size);
	}
	
	private boolean checkLeft() {
		if(cursor[0]==0) {
			return false;
		}
		return !this.mazeTemplate[cursor[0]-1][cursor[1]].isVisited();
	}
	private boolean checkRight() {
		if(cursor[0]==this.size-1) {
			return false;
		}
		return !this.mazeTemplate[cursor[0]+1][cursor[1]].isVisited();
	}
	private boolean checkUp() {
		if(cursor[1]==0) {
			return false;
		}
		return !this.mazeTemplate[cursor[0]][cursor[1]-1].isVisited();
	}
	private boolean checkDown() {
		if(cursor[1]==this.size-1) {
			return false;
		}
		return !this.mazeTemplate[cursor[0]][cursor[1]+1].isVisited();
	}
	
	private MoveSet getRandomMove(MoveSet[] moveSet) {
		if(moveSet.length==1) {return moveSet[0];}
		return moveSet[rand.nextInt(moveSet.length)];
	}
	
	private CellHistory getCurrent() {
		return this.mazeTemplate[cursor[0]][cursor[1]];
	}
}
