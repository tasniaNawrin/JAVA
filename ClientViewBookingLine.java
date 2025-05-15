package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import entities.*;
import repositories.*;

public class ClientViewBookingLine  extends JFrame implements ActionListener
{
	private JLabel label;
	private JTextField userTF;
	private JButton backBtn;
	private JTable bookingLineTable;
	private JScrollPane bookingLineTableSP;
	private JPanel panel;
	private User u;
	private Color c;
	
	public ClientViewBookingLine(User u)
	{
		super("View self-booking line");
		this.setBounds(100,100,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.c=new Color(224,255,255);
		
		this.panel=new JPanel();
		this.panel.setLayout(null);
		this.panel.setBackground(this.c);
		
		this.backBtn=new JButton("Back");
		this.backBtn.setBounds(50,50,100,30);
		this.backBtn.addActionListener(this);
		this.panel.add(backBtn);
		
	    BookingLine b1=new BookingLine();
		BookingLineRepo brp=new BookingLineRepo();
		BookingLine[] bookingLineList=brp.searchBookingLineByRoomId(b1.getRoomId());

		String bookingLineData1[][]=new String[bookingLineList.length][6];
		for(int i=0;i<bookingLineList.length;i++)
		{
			if(bookingLineList[i]!=null)
			{
				bookingLineData1[i][0]=bookingLineList[i].getBookingLineId();
				bookingLineData1[i][1]=bookingLineList[i].getRoomId();
				bookingLineData1[i][2]=String.valueOf(bookingLineList[i].getNumberOfNight());
				bookingLineData1[i][3]=bookingLineList[i].getBookingFromDate();
				bookingLineData1[i][4]=bookingLineList[i].getBookingTillDate();
				bookingLineData1[i][5]=String.valueOf(bookingLineList[i].getAmount());
			}	
		}
		
		String head1[]={"Booking ID","Room ID","No Of Nights","Booking From Date","Booking Till Date","Amount"};
	
		this.bookingLineTable=new JTable(bookingLineData1,head1);
		this.bookingLineTableSP=new JScrollPane(bookingLineTable);
		this.bookingLineTableSP.setBounds(50,110,700,450);
		this.bookingLineTable.setEnabled(false);
		this.panel.add(bookingLineTableSP);
		this.panel.revalidate();
		this.panel.repaint();
		this.add(panel);
		this.u=u;
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command=ae.getActionCommand();
		if(command.equals(backBtn.getText()))
		{
		
			ClientHomeFrame adf=new ClientHomeFrame(this.u);
			this.setVisible(false);
			adf.setVisible(true);
		}
	}
}
