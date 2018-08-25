import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame{
	private int preLength = 15;
	private int row = 10;
	private int col = 10;
	
	private Container container;
	private JPanel gamePanel;
	private JPanel buttonPanel;
	private Canvas paintCanvas;
	private Button createMazeButton;
	private Button solveMazeButton;
	private ButtonClickListener clickListener;
	
	private MazeCreate mazeCreate;
	
	public Maze(int width, int height, int preLength){
		this.row = width;
		this.col = height;
		this.preLength = preLength;
		
		container = this.getContentPane();
		gamePanel = new JPanel();
		buttonPanel = new JPanel();
		paintCanvas = new Canvas();
		createMazeButton = new Button("生成迷宫");
		solveMazeButton = new Button("显示路径");
		paintCanvas.setSize(width*preLength+1, height*preLength+1);
		gamePanel.add(paintCanvas);
		buttonPanel.add(createMazeButton);
		buttonPanel.add(solveMazeButton);
		container.add(gamePanel, BorderLayout.NORTH);
		container.add(buttonPanel, BorderLayout.SOUTH);
		
		clickListener = new ButtonClickListener();
		createMazeButton.addActionListener(clickListener);
		solveMazeButton.addActionListener(clickListener);
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//	public MazeCreate(){
//		this(width, height);
//	}
	
	private class ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == createMazeButton){
				Graphics g = paintCanvas.getGraphics();
				mazeCreate = new MazeCreate(row, col,preLength, g);
				
			}else if(e.getSource() == solveMazeButton){
				Graphics g = paintCanvas.getGraphics();
				if(mazeCreate!=null){
		    	new MazeSolve(row, col, mazeCreate.getMazePoint(), g);
				}
			}
		}
		
	}
}
