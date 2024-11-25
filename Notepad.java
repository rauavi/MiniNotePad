import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Notepad extends JFrame implements ActionListener{
	Container cr;
	JTextArea ta;
	JMenu file,edit,app;
	JMenuBar mbar;
	JMenuItem NW,ON,SV,EX,CP,CT,PS,FC,BG;
	
	public Notepad(String t)
	{
		super(t);
		cr=getContentPane();
		ta=new JTextArea();
		cr.add(ta);
		
		mbar=new JMenuBar();
		setJMenuBar(mbar);
		
		file=new JMenu("File");
		edit=new JMenu("Edit");
		app=new JMenu("Apperance");
		mbar.add(file);
		mbar.add(edit);
		mbar.add(app);
		
		NW=new JMenuItem("New");
		ON=new JMenuItem("Open");
		SV=new JMenuItem("Save");
		EX=new JMenuItem("Exit");
		CP=new JMenuItem("Copy");
		CT=new JMenuItem("Cut");
		PS=new JMenuItem("Paste");
		FC=new JMenuItem("font Color");
		BG=new JMenuItem("Background Color");
		
		file.add(NW);
		file.add(ON);
		file.add(SV);
		file.add(EX);
		
		edit.add(CP);
		edit.add(CT);
		edit.add(PS);
		
		app.add(FC);
		app.add(BG);
		
		NW.addActionListener(this);
		PS.addActionListener(this);
		ON.addActionListener(this);
		EX.addActionListener(this);
		SV.addActionListener(this);
		CP.addActionListener(this);
		FC.addActionListener(this);
		CT.addActionListener(this);
		BG.addActionListener(this);

		
		setSize(400, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Notepad("Notepad");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc=new JFileChooser();
		int ch;
		String s="";
		try {
		if(e.getSource()==CP)
			ta.copy();
		else if(e.getSource()==CT)
			ta.cut();
		else if(e.getSource()==PS)
			ta.paste();
		else if(e.getSource()==NW)
			ta.setText("");
		else if(e.getSource()==EX)
			this.dispose();
		else if(e.getSource()==ON)
		{
			fc.showOpenDialog(this);
			File f=fc.getSelectedFile();
			FileReader fr;
			
			fr = new FileReader(f);
			 
			while((ch=fr.read())!=-1)
			{
				s+=(char)ch;
			}
			ta.setText(s);
			fr.close();
		}
		else if(e.getSource()==SV)
		{
			fc.showSaveDialog(this);
			File f=fc.getSelectedFile();
			FileWriter fw=new FileWriter(f);
			fw.write(ta.getText());
			fw.close();
		}
		else if(e.getSource()==BG)
		{
			Color c=JColorChooser.showDialog(this, "Select Color", null);
			ta.setBackground(c);
		}
		else if(e.getSource()==FC)
		{
			Color p=JColorChooser.showDialog(this, "Select Color", null);
			ta.setForeground(p);
		}
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
}
}
