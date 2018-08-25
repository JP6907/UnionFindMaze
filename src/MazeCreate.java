import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class MazeCreate {
	private int row;
	private int col;
	private Graphics g;
	private int preLength;
	private ArrayList<MazePoint> positions;  //储存迷宫点的信息，四个方向是否可行
	
	public MazeCreate(int row,int col,int preLength, Graphics g){
		this.row = row;
		this.col = col;
		this.preLength = preLength;
		this.g = g;
		positions = new ArrayList<MazePoint>(row*col);
		for(int i = 0;i<row*col;i++){
			positions.add(new MazePoint());
		}
		
		createMaze(new Point(0,0),new Point(row-1, col-1));
	}
	public Point createRamdomPoint(int row,int col){
		Random random = new Random();
		int r = Math.abs(random.nextInt())%row;
		int c = Math.abs(random.nextInt())%col;
		Point p = new Point(r,c);
		return p;
	}
	public Point randomArroundPoint(Point point){
		Random random = new Random();
		Point newPoint = new Point(0,0);
		int a;
		do{
		a = Math.abs(random.nextInt())%4;
		switch(a){
		case 0:
			newPoint = new Point(point.x + 1,point.y);
			break;
		case 1:
			newPoint = new Point(point.x - 1,point.y);
			break;
		case 2:
			newPoint = new Point(point.x,point.y + 1);
			break;
		case 3:
			newPoint = new Point(point.x,point.y - 1);
			break;
		}
		}while(newPoint.x==-1||newPoint.x==row||newPoint.y==-1||newPoint.y==col);
		return newPoint;
	}
	public void createMaze(Point start,Point end){
		MazeUF uf = new MazeUF(row, col);
		Point point1;
		Point point2;
		g.setColor(Color.white);
		g.fillRect(0, 0, (row+1)*preLength, (col+1)*preLength);
		g.setColor(Color.black);
		//初始化全部是墙，为黑色
		for(int i = 0;i<=row;i++){
			g.drawLine(i*preLength, 0, i*preLength, col*preLength);
		}
		for(int j = 0;j<=col;j++){
			g.drawLine(0, j*preLength, row*preLength, j*preLength);
		}
		g.setColor(Color.white);
		while(!uf.isConnected(start, end)){
			point1 = createRamdomPoint(row,col);
			point2 = randomArroundPoint(point1);
			if(uf.isConnected(point1, point2))
				continue;
			//如果未相通，则把两点之间的墙打通(画成白色)
			uf.union(point1, point2);
			if(point1.y+1 == point2.y){
				g.drawLine(point2.x*preLength, point2.y*preLength, (point2.x+1)*preLength, point2.y*preLength);
				positions.get(point1.y*row + point1.x).setSouthPass();
				positions.get(point2.y*row + point2.x).setNorthPass();
			}else if(point2.y+1 == point1.y){
				g.drawLine(point1.x*preLength, point1.y*preLength, (point1.x+1)*preLength, point1.y*preLength);
				positions.get(point2.y*row + point2.x).setSouthPass();
				positions.get(point1.y*row + point1.x).setNorthPass();
			}else if(point1.x+1 == point2.x){
				g.drawLine(point2.x*preLength, point2.y*preLength, point2.x*preLength, (point2.y+1)*preLength);
				positions.get(point1.y*row + point1.x).setEastPass();
				positions.get(point2.y*row + point2.x).setWestPass();;
			}else if(point2.x+1 == point1.x){
				g.drawLine(point1.x*preLength, point1.y*preLength, point1.x*preLength, (point1.y+1)*preLength);
				positions.get(point1.y*row + point1.x).setWestPass();;
				positions.get(point2.y*row + point2.x).setEastPass();;
			}
		}
		System.out.println(positions.get(0).east);
		System.out.println(positions.get(0).west);
		System.out.println(positions.get(0).north);
		System.out.println(positions.get(0).south);
		System.out.println(positions.get(10).east);
		System.out.println(positions.get(10).west);
		System.out.println(positions.get(10).north);
		System.out.println(positions.get(10).south);
		System.out.println(positions.get(row*col-1).east);
		System.out.println(positions.get(row*col-1).west);
		System.out.println(positions.get(row*col-1).north);
		System.out.println(positions.get(row*col-1).south);
		System.out.println("out");
	}
	public ArrayList<MazePoint> getMazePoint(){
		return this.positions;
	}
}
