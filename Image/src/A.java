import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class A extends JFrame implements ActionListener{
	//button
	JButton button;
	//panel
	JPanel jp = new JPanel();
	//Image Path
	String filepath;
	//Image
	Image image = null;
	
	JLabel jl;
	
	String[] first;
	String mofilepath;
	

	public A()
	{
		super("Image Test"); //JFrame의 생성자에 값을 입력하면 윈도우창에 표시됨.
		
		
		jp.setLayout(new BorderLayout());
		button = new JButton("click me");
		button.addActionListener(this);
		jp.add(button, "North");//jp라는 패널에 button추가
		
			
		add(jp);// JFrame에 jp라는 패널에 프레임추가
		
		setSize(600,600); //윈도우의 가로세로
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x버튼눌렀을때종료
		validate();
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(button.getText().equals("click me"))
		{
			button.setText("I've been clicked");
			JFrame window = new JFrame();
			
			JFileChooser filechooser = new JFileChooser();
			//기본 Path
			filechooser.setCurrentDirectory(new File(System.getProperty("user.home") + "//" +"Desktop"));
			//필더링될 확장자
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image 파일", "jpg","gif");
			
			//필터링될 확장자 추가.
			filechooser.addChoosableFileFilter(filter);
			
			//창열기
			int result = filechooser.showOpenDialog(window);
			
			if(result == JFileChooser.APPROVE_OPTION)
			{
				//경로반환
				File selectedFile = filechooser.getSelectedFile();
				//출력
				System.out.println(selectedFile);
				//filepath = selectedFile +"";
				//first = filepath.split("\\\\");
				//mofilepath = first[0]+"\\\\"+first[1]+"\\\\"+first[2]+"\\\\"+first[3]+"\\\\"+first[4]+"";
				//System.out.println(first[0]+"\\\\"+first[1]+"\\\\"+first[2]+"\\\\"+first[3]+"\\\\"+first[4]+"");
				
				
				ImageIcon icon = new ImageIcon(selectedFile.getPath());
				//icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH));
				
				jl = new JLabel();
				jl.setIcon(icon);
				jl.setSize(150, 150);
				jp.add(jl, "Center");
				
				repaint();
				BufferedImage img = null;
				try{
					img = ImageIO.read(new File(selectedFile.getPath()));
					RgbImage(img);
				
				}catch(IOException e)
				{
					
				}
								
		}else{ 
			button.setText("click me");
		}
	}
	}
	private void RgbImage(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
	
		System.out.println("w,h : " +w + " , "+h );
		for(int i=0; i<h; i++){
			for(int j=0;j<w; j++){
				 System.out.println("x,y: " + j + ", " + i);
				int pixel = image.getRGB(j, i);
				printPixelARGB(pixel);
				
			}
		}
	}
	public void printPixelARGB(int pixel){
		int alpha = (pixel >>24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		
		System.out.println("argb : " + alpha + ", "+"red : " + red + ", "+"green : " + green + ", "+"blue : " + blue + ", ");
	}
	public static void main(String[] args){

		new A();
		
		
	}
}