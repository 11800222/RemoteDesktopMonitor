package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;
import java.awt.BorderLayout;

public class Clock extends JFrame
{
	public Clock()
	{
		setLayout(new BorderLayout(20,20));
		StillClock clocker=new StillClock();
		Border b=new LineBorder(Color.gray,2);
		clocker.setBorder(b);
		JLabel label=new JLabel(clocker.getHour()+":"+clocker.getMinute()+":"+clocker.getSecond());
		label.setBorder(b);
		clocker.t1.add(clocker,BorderLayout.CENTER);
		clocker.t2.add(label,BorderLayout.CENTER);
		add(clocker.t1,BorderLayout.NORTH);
		add(clocker.t2,BorderLayout.CENTER);
		/*
		 *setLayout(new BorderLayout(20,20));
		StillClock clocker=new StillClock();
		add(clocker,BorderLayout.CENTER);
		JLabel j=new JLabel(clocker.getHour()+":"+clocker.getMinute()+":"+clocker.getSecond(),JLabel.CENTER);
		j.setFont(new java.awt.Font("Dialog",1,15));
		add(j,BorderLayout.SOUTH);

		 */
	}
	public static void main(String[] args) 
	{
		Clock p=new Clock();
		p.setTitle("Exercise15_7");
		p.setSize(300,300);
		p.setLocationRelativeTo(null);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setVisible(true);
	}
}
class StillClock extends JPanel
{
	private int hour;
	private int minute;
	private int second;
	public JPanel t1=new JPanel();
	public JPanel t2=new JPanel();
	public StillClock()
	{
		setCurrentTime();
	}
	public StillClock(int hour,int minute,int second)
	{
		this.hour=hour;
		this.minute=minute;
		this.second=second;
	}
	public int getHour()
	{
		return hour;
	}
	public void setHour(int hour)
	{
		this.hour=hour;
		repaint();
	}
	public int getMinute()
	{
		return minute;
	}
	public void setMinute(int minute)
	{
		this.minute=minute;
		repaint();
	}
	public int getSecond()
	{
		return second;
	}
	public void setSecond(int second)
	{
		this.second=second;
		repaint();
	}
	protected void paintComponent(Graphics g)                  //一般都定义为保护类型
	{
		super.paintComponent(g);
		int clockRadius=(int)(Math.min(getWidth(),getHeight()*0.8*0.5));
		int x=getWidth()/2;
		int y=getHeight()/2;
		g.setColor(Color.blue);
		g.drawOval(x-clockRadius, y-clockRadius, 2*clockRadius, 2*clockRadius);
		g.drawString("12",x-5, y-clockRadius+12);
		g.drawString("9",x-clockRadius+3, y+5);
		g.drawString("3",x+clockRadius-10, y+3);
		g.drawString("6",x-3, y+clockRadius-3);
		int sLength=(int)(clockRadius*0.8);
		int xSecond=(int)(x+sLength*Math.sin(second*(2*Math.PI/60)));
		int ySecond=(int)(y-sLength*Math.cos(second*(2*Math.PI/60)));
		g.setColor(Color.red);
		g.drawLine(x,y,xSecond,ySecond);
		int mLength=(int)(clockRadius*0.65);
		int xMinute=(int)(x+mLength*Math.sin(minute*(2*Math.PI/60)));
		int yMinute=(int)(y-mLength*Math.cos(minute*(2*Math.PI/60)));
		g.setColor(Color.blue);
		g.drawLine(x,y,xMinute,yMinute);
		int hLength=(int)(clockRadius*0.5);
		int xHour=(int)(x+hLength*Math.sin((hour%12+minute/60.0)*(2*Math.PI/12)));
		int yHour=(int)(y-hLength*Math.cos((hour%12+minute/60.0)*(2*Math.PI/12)));
		g.setColor(Color.green);
		g.drawLine(x, y, xHour, yHour);
	}
	public void setCurrentTime()
	{
		Calendar calendar=new GregorianCalendar();
		this.hour=calendar.get(Calendar.HOUR_OF_DAY);
		this.minute=calendar.get(Calendar.MINUTE);
		this.second=calendar.get(Calendar.SECOND);
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(200,200);
	}
}
