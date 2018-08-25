import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class MazeSolve {
	private static int FOOT_FLAG = 1; // 已经走过的标志

	private final int EAST = 0;
	private final int WEST = 1;
	private final int NORTH = 2;
	private final int SOUTH = 3;

	private ArrayList<MazePoint> mazePoint;
	private LinkedList<FootNode> footNode;
	private Graphics gameGraphics;
	private Stack<FootNode> walkStack;
	private int row;
	private int col;
	private FootNode curPoint;

	public MazeSolve(int row, int col, ArrayList<MazePoint> positions, Graphics gameGraphics) {
		this.row = row;
		this.col = col;
		this.mazePoint = positions;
		this.gameGraphics = gameGraphics;
		walkStack = new Stack<FootNode>();
		footNode = new LinkedList<FootNode>();
		 solveMaze();
		//solveTheMaze(new FootNode(0, 0));
	}

	private void solveTheMaze(FootNode node) {
		mazePoint.get(node.y * row + node.x).stateFlag = FOOT_FLAG;
		if (node.x == (row - 1) && node.y == (col - 1)) {
			System.out.println("find the way!");
		} else {
			markFoot(node);
			if (mazePoint.get(node.y * row + node.x).isEastPass())
				if (mazePoint.get(nextPoint(node, 0).y * row + nextPoint(node, 0).x).stateFlag != FOOT_FLAG)
					solveTheMaze(nextPoint(node, 0));
			if (mazePoint.get(node.y * row + node.x).isWestPass())
				if (mazePoint.get(nextPoint(node, 1).y * row + nextPoint(node, 1).x).stateFlag != FOOT_FLAG)
					solveTheMaze(nextPoint(node, 1));
			if (mazePoint.get(node.y * row + node.x).isNorthPass())
				if (mazePoint.get(nextPoint(node, 2).y * row + nextPoint(node, 2).x).stateFlag != FOOT_FLAG)
					solveTheMaze(nextPoint(node, 2));
			if (mazePoint.get(node.y * row + node.x).isSouthPass())
				if (mazePoint.get(nextPoint(node, 3).y * row + nextPoint(node, 3).x).stateFlag != FOOT_FLAG)
					solveTheMaze(nextPoint(node, 3));

		}
		System.out.println("000000");
	}

	private int solveMaze() {
		int curStep = 1;
		int dir = EAST;
		curPoint = new FootNode(0, 0);
		curPoint.direction = EAST;
		markFoot(curPoint);
		walkStack.push(curPoint);
		do {
			if (pass(curPoint, curPoint.direction)) {
				walkStack.push(curPoint);
				curStep++;
				curPoint = nextPoint(curPoint, curPoint.direction);
				curPoint.direction = EAST;
				markFoot(curPoint);
				if ((curPoint.x == (row - 1)) && (curPoint.y == (col - 1))) {
					System.out.println("find the way!");
					while (!walkStack.isEmpty()) {
						FootNode p = walkStack.pop();
						gameGraphics.setColor(Color.black);
						gameGraphics.drawString("" + curStep, p.x + 2, p.y + 2);
						curStep--;
					}
					return 1;
				}
			} else {
				if (curPoint.direction < 4) {
					curPoint.direction++;
				} else if (curPoint.direction == 4) {
					if (!walkStack.isEmpty()) {
						FootNode node = walkStack.pop();
						while (node.direction == 4 && !walkStack.isEmpty()) {
							node = walkStack.pop();
							curStep--;
						}
						curPoint = node;
						curPoint.direction++;
					}
				}
			}
		} while (!walkStack.isEmpty());
		System.out.println("ooooooo");
		return 0;
	}

	private boolean pass(FootNode point, int direct) {
		FootNode nextPoint = nextPoint(point, direct);
		if ((nextPoint.x == -1) || (nextPoint.y == -1) || (nextPoint.x == row) || (nextPoint.y == col))
			return false;
		if (mazePoint.get(nextPoint.y * row + nextPoint.x).stateFlag == FOOT_FLAG)
			return false;

		switch (direct) {
		case EAST:
			if (mazePoint.get(point.y * row + point.x).isEastPass())
				return true;
			break;
		case WEST:
			if (mazePoint.get(point.y * row + point.x).isWestPass())
				return true;
			break;
		case NORTH:
			if (mazePoint.get(point.y * row + point.x).isNorthPass())
				return true;
			break;
		case SOUTH:
			if (mazePoint.get(point.y * row + point.x).isSouthPass())
				return true;
			break;
		default:
			break;
		}
		return false;
	}

	private void markFoot(FootNode point) {
		// System.out.println(point.x+ " " + point.y);
		mazePoint.get(point.y * row + point.x).stateFlag = FOOT_FLAG;
	}

	private FootNode nextPoint(FootNode point, int direct) {
		System.out.println(point.x + "  " + point.y);
		FootNode p = point;
		switch (direct) {
		case EAST:
			p.x += 1;
			break;
		case WEST:
			p.x -= 1;
			break;
		case NORTH:
			p.y -= 1;
			break;
		case SOUTH:
			p.y += 1;
			break;
		default:
			break;
		}
		return p;
	}

	private class FootNode {
		private int x;
		private int y;
		private int direction;

		public FootNode(int x, int y) {
			this.x = x;
			this.y = y;
			this.direction = EAST;
		}
	}
}
