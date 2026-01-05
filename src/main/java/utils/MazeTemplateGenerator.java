package utils;

import java.util.ArrayList;
import java.util.Random;

import model.MoveSet;

public class MazeTemplateGenerator {
	
	private final ArrayList<CellHistory> stack ;
	private int size;
	private final Random rand ;
	private final int[] cursor ;
	private final CellHistory[][] mazeTemplate ;
	
	

	public MazeTemplateGenerator(int size) {
		super();
		this.stack =  new ArrayList<>();
		this.size = size;
		this.rand = new Random();
		this.cursor = new int[]{0,0};
		mazeTemplate = new CellHistory[this.size][this.size];
	}
	
	public CellHistory[][] createTemplate() {
		MoveSet nextMove;
		initArray();
		initMazeBorder();
		setCursorStart();
		while(true) {
			nextMove = findMove();
			saveCellInStack();
			if(nextMove == null) {
				if(this.stack.isEmpty()) {
					break;
				}
				while(nextMove == null) {
					CellHistory cellFromStack = this.stack.remove(0);
					this.cursor[0]= cellFromStack.getxCoordinate();
					this.cursor[1]= cellFromStack.getyCoordinate();
					nextMove = findMove();
					saveCellInStack();
				}
			}
			step(nextMove);
		}
		//System.out.println("end");
		return this.mazeTemplate;
	}
	
	/*public void setSize(int size) {
		this.size = size;
	}*/

	private void initArray() {
		for (int i = 0; i < this.mazeTemplate.length; i++) {
			for (int j = 0; j < this.mazeTemplate.length; j++) {
				this.mazeTemplate[i][j] = new CellHistory(i,j);
			}
		}
		
	}

	private void step(MoveSet nextMove) {
		CellHistory current = getCurrent();
		current.setVisited();
		current.deleteFreeDirection(nextMove);
		moveCursor(nextMove);
		
	}
	
	private void moveCursor(MoveSet move) {
		switch(move) {
		case UP: this.cursor[1] -= 1;break;
		case DOWN: this.cursor[1] += 1;break;
		case LEFT: this.cursor[0] -= 1;break;
		case RIGHT: this.cursor[0] += 1;break;
		}
	}
	
	private void saveCellInStack() {
		MoveSet[] tmp = getPossibleMove(getAllUncheckedMove());
		if(tmp!=null && tmp.length>1) {
			this.stack.add(getCurrent());
		}
	}
	
	private MoveSet findMove() {
		MoveSet[] unchecked = getAllUncheckedMove();
		if(unchecked==null || unchecked.length == 0){
			return null;
		}
		MoveSet[] possibleMove = getPossibleMove(unchecked);
		if(possibleMove == null || possibleMove.length==0) {
			return null;
		}
		return getRandomMove(possibleMove); 
	}
	
	private void setCursorStart() {
		this.cursor[0] = rand.nextInt(this.size);
		this.cursor[1] = rand.nextInt(this.size);
	}
	
	private MoveSet[] getAllUncheckedMove() {
		CellHistory currentCell = getCurrent();
		int numberOfPossibleDirection = currentCell.countDirection();
		if(numberOfPossibleDirection>0) {
			int index = 0;
			MoveSet[] result = new MoveSet[numberOfPossibleDirection];
			for (int i = 0; i < currentCell.getFreeDirection().length; i++) {
				if(currentCell.getFreeDirection()[i]!=null) {
					result[index]=currentCell.getFreeDirection()[i];
					index++;
				}
			}
			return result;
		}
		return null;
	}
	
	private MoveSet[] getPossibleMove(MoveSet[] moveSet ) {
		int countOfPossible = 0;
		for(int i=0; i<moveSet.length; i++) {
			switch(moveSet[i]) {
			case UP:if(CheckUp()) {countOfPossible++;}break;
			
			case DOWN: if(CheckDown()) {countOfPossible++;}break;
			
			case RIGHT:if(CheckRight()) {countOfPossible++;}break;
			
			case LEFT:if(CheckLeft()) {countOfPossible++;}break;
			
			default:break;
			}
		}
		MoveSet[] result = new MoveSet[countOfPossible];
		int index = 0;
		for (int i = 0; i<moveSet.length; i++) {
			switch(moveSet[i]) {
			case UP:
				if(CheckUp()) {
					result[index] = MoveSet.UP;
					index++;
				}
				break;		
			case DOWN: 
				if(CheckDown()) {
					result[index] = MoveSet.DOWN;
					index++;
				}
				break;
			case RIGHT:
				if(CheckRight()) {
					result[index] = MoveSet.RIGHT;
					index++;
				}
				break;
			case LEFT:
				if(CheckLeft()) {
					result[index] = MoveSet.LEFT;
					index++;
				}
				break;
			default:break;
			}
		}
		return result;
		
	}
	
	private boolean CheckLeft() {
		if(cursor[0]==0) {
			return false;
		}
		return !this.mazeTemplate[cursor[0]-1][cursor[1]].isVisited();
	}
	private boolean CheckRight() {
		if(cursor[0]==this.size-1) {
			return false;
		}
		return !this.mazeTemplate[cursor[0]+1][cursor[1]].isVisited();
	}
	private boolean CheckUp() {
		if(cursor[1]==0) {
			return false;
		}
		return !this.mazeTemplate[cursor[0]][cursor[1]-1].isVisited();
	}
	private boolean CheckDown() {
		if(cursor[1]==this.size-1) {
			return false;
		}
		return !this.mazeTemplate[cursor[0]][cursor[1]+1].isVisited();
	}
	
	private MoveSet getRandomMove(MoveSet[] moveSet) {
		return moveSet[rand.nextInt(moveSet.length)];
	}
	
	private CellHistory getCurrent() {
		return this.mazeTemplate[cursor[0]][cursor[1]];
	}
	
	private void initMazeBorder() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if(i==0) {this.mazeTemplate[i][j].deleteFreeDirection(MoveSet.UP);}
				
				if(j==0) {this.mazeTemplate[i][j].deleteFreeDirection(MoveSet.LEFT);}
				
				if(i==this.size-1) {this.mazeTemplate[i][j].deleteFreeDirection(MoveSet.DOWN);}
				
				if(j==this.size-1) {this.mazeTemplate[i][j].deleteFreeDirection(MoveSet.RIGHT);				}
			}
		}
	}
}
