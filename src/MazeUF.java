import java.awt.Point;
import java.util.ArrayList;

public class MazeUF {
	private int[] id; // 组别
	private int[] size;
	private int count;
	private int row;
	private int col;
	

	public MazeUF(int row,int col) {
		this.row = row;
		this.col = col;
		this.count = row*col;
		this.id = new int[this.count];
		this.size = new int[this.count];
		
		for (int i = 0; i < this.count; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	public int count() { // 组数
		return this.count;
	}

	public boolean isConnected(Point point1, Point point2) { // 是否连接
		return find(point1) == find(point2);
	}

	public int find(Point point) { // 组别
		int p = point.y*row + (point.x+1) - 1;
		while (p != id[p]) {
			id[p] = id[id[p]];  //将p节点的父节点设置为它的爷爷节点
								//压缩路径，提高效率
			p = id[p];
		}
		return id[p];
	}

	// 用树提高效率
	public void union(Point point1, Point point2) { // 连接
		int pCom = find(point1);
		int qCom = find(point2);
		if (pCom == qCom)
			return;

		if (size[pCom] < size[qCom]) {
			id[pCom] = qCom;
			size[qCom] += size[pCom];
		}else{
			id[qCom] = pCom;
			size[pCom] += size[qCom];
		}
		count--;
	}
}
