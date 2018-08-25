import java.awt.Point;
import java.util.ArrayList;

public class MazeUF {
	private int[] id; // ���
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

	public int count() { // ����
		return this.count;
	}

	public boolean isConnected(Point point1, Point point2) { // �Ƿ�����
		return find(point1) == find(point2);
	}

	public int find(Point point) { // ���
		int p = point.y*row + (point.x+1) - 1;
		while (p != id[p]) {
			id[p] = id[id[p]];  //��p�ڵ�ĸ��ڵ�����Ϊ����үү�ڵ�
								//ѹ��·�������Ч��
			p = id[p];
		}
		return id[p];
	}

	// �������Ч��
	public void union(Point point1, Point point2) { // ����
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
