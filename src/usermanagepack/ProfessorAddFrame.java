package usermanagepack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ProfessorAddFrame extends JDialog
{
    private JTextField txtID, txtName, txtAddress, txtPhone, txtEmail, txtDOJ, txtDegree;
    private JComboBox  cmbGender;
    private JButton btnAdd, btnUpdate, btnCancel, btnReturn;
    
    private String[] gender = {"Male", "Female", "Trans","Other"};
    private int pIDSequence = 0;
    
    private Connection con = null;
    private Statement  smt = null;
    private ResultSet  rst = null;
    private PreparedStatement pst1 = null;
    private PreparedStatement pst2 = null;
    private PreparedStatement pst3 = null;
    
    private JLabel makeLabel(String s,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(s);
        temp.setBounds(x, y, w, h);
	if(mode == 1)
	{
            Border b1 = BorderFactory.createLineBorder(Color.RED, 2);
            Border b2 = BorderFactory.createLineBorder(Color.WHITE, 2);
            Border b3 = BorderFactory.createCompoundBorder(b1, b2);
            temp.setFont(new Font("Verdana", 1, 30));
	    temp.setOpaque(true);
	    temp.setBackground(Color.BLUE);
            temp.setForeground(Color.WHITE);
            temp.setBorder(b3);
	    temp.setHorizontalAlignment(JLabel.CENTER);
	}
        else if(mode == 2)
        {
            temp.setFont(new Font("Courier New", 1, 18));
            temp.setHorizontalAlignment(JLabel.LEFT);
        }
        else if(mode == 3)
        {
            temp.setFont(new Font("Courier New", 1, 18));
            temp.setOpaque(true);
            temp.setBackground(Color.WHITE);
            temp.setHorizontalAlignment(JLabel.CENTER);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
        add(temp);
        return(temp);
    }
    
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = null;
        temp = new JTextField();
        temp.setBounds( x, y, w, h);
        Border b1 = BorderFactory.createLineBorder(Color.BLACK, 2);
	temp.setBorder(b1);
        temp.setFont(new Font("Courier New",1,18));
        temp.setEditable(false);
        add(temp);
        return(temp);
    }
    
    private JComboBox makeComboBox(String[] item,int x,int y,int w,int h)
    {
        JComboBox temp = new JComboBox(item);
        temp.setFont(new Font("Courier New",1,18));
        temp.setBounds(x, y, w, h);
        add(temp);
        return temp;
    }
    
    private JButton makeButton (String s,int x,int y,int w,int h)
    {
        JButton temp = new JButton(s);
        temp.setBounds( x, y, w, h);
        temp.setOpaque(true);
        temp.setFont(new Font("Courier New",1,16));
	temp.setMargin(new Insets(0,0,0,0));
        temp.setEnabled(false);
        temp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object ob = e.getSource();
                if(ob == btnAdd)
                {
                    setReset();
                    Date dt = new Date();
                    int y = dt.getYear()+1900;
                    int m = dt.getMonth()+1;
                    pIDSequence++;
                    String pid = String.format("P-%4d-%02d-%03d", y,m,pIDSequence);
                    txtID.setText(pid);
                    txtName.grabFocus();
                }
                else if(ob == btnUpdate)
                {
                    try
                    {
                        pst1.setString(1, txtID.getText());
                        pst1.setString(2, txtName.getText());
                        pst1.setString(3, txtAddress.getText());
                        pst1.setString(4, (String)cmbGender.getSelectedItem());
                        pst1.setString(5, txtPhone.getText());
                        pst1.setString(6, txtEmail.getText());
                        pst1.setString(7, txtDOJ.getText());
                        pst1.setString(8, txtDOJ.getText());
                        pst1.executeUpdate();
                        String degree[] = txtDegree.getText().split(",");
                        for(String temp:degree)
                        {
                            pst2.setString(1, txtID.getText());
                            pst2.setString(2, temp);
                            pst2.executeUpdate();
                        }
                        pst3.setString(1, txtID.getText());
                        pst3.setString(2, txtID.getText());
                        pst3.setString(3, "Professor");
                        pst3.executeUpdate();
                        
                        setReset();
                        btnAdd.grabFocus();
                        JOptionPane.showMessageDialog(null, "Professor Registered Successfully");
                        
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                else if(ob == btnCancel)
                {
                    setReset();
                    btnAdd.grabFocus();
                    pIDSequence--;
                }
                else if(ob == btnReturn)
                {
                    dispose();
                }
            }
        });
        add(temp);
        return temp;
    }
    
private void setReset()
    {
        txtID.setEditable(!txtID.isEditable());
        txtID.setText("");
        txtName.setEditable(!txtName.isEditable());
        txtName.setText("");
        txtDOJ.setEditable(!txtDOJ.isEditable());
        txtDOJ.setText("");
        txtAddress.setEditable(!txtAddress.isEditable());
        txtAddress.setText("");
        txtEmail.setEditable(!txtEmail.isEditable());
        txtEmail.setText("");
        txtPhone.setEditable(!txtPhone.isEditable());
        txtPhone.setText("");
        txtDegree.setEditable(!txtDegree.isEditable());
        txtDegree.setText("");
        cmbGender.setSelectedIndex(0);
        btnUpdate.setEnabled(!btnUpdate.isEnabled());
        btnCancel.setEnabled(!btnCancel.isEnabled());
    }
        
    public ProfessorAddFrame()
    {
        try
        {
            makeLabel("NEW PROFESSOR REGISTRATION", 10,   10, 600, 70, 1);
            makeLabel("PROFESSOR ID GENERATED",     10,  100, 300, 30, 2);
            txtID = makeTextField(                 300,  100, 310, 30);
            txtID.setEditable(false);
            makeLabel("ENTER PROFESSOR NAME",       10, 140, 300, 30, 2);
            txtName = makeTextField(               300, 140, 310, 30);
            makeLabel("ENTER GENDER STATUS",        10, 180, 300, 30, 2);
            cmbGender = makeComboBox(gender,       300, 180, 310, 30);
            makeLabel("ENTER LOCAL ADDRESS",        10, 220, 300, 30, 2);
            txtAddress = makeTextField(            300, 220, 310, 30);
            makeLabel("ENTER DATE OF JOIN",         10, 260, 300, 30, 2);
            txtDOJ = makeTextField(                300, 260, 310, 30);
            makeLabel("ENTER PHONE NUMBER",         10, 300, 300, 30, 2);
            txtPhone = makeTextField(              300, 300, 310, 30);
            makeLabel("ENTER EMAIL ADDRESS",        10, 340, 300, 30, 2);
            txtEmail = makeTextField(              300, 340, 310, 30);
            makeLabel("DEGREE COMMA SEPERATED",     10, 380, 300, 30, 2);
            txtDegree = makeTextField(             300, 380, 310, 30);
            btnAdd = makeButton("Add New",          25, 420, 100, 30);
            btnAdd.setEnabled(true);
            btnUpdate = makeButton("Update",       180, 420, 100, 30);
            btnCancel = makeButton("Cancel",       330, 420, 100, 30);
            btnReturn = makeButton("Return",       480, 420, 100, 30);
            btnReturn.setEnabled(true);
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2024", "root", "depansaha");
            pst1 = con.prepareStatement("INSERT INTO PROFESSOR_MASTER VALUES(?,?,?,?,?,?,?,?)");
            pst2 = con.prepareStatement("INSERT INTO PROFESSOR_DEGREE VALUES(?,?)");
            pst3 = con.prepareStatement("INSERT INTO USER VALUES(?,?,?)");
            smt = con.createStatement();
            rst = smt.executeQuery("SELECT PROFESSOR_ID FROM PROFESSOR_MASTER ORDER BY PROFESSOR_ID DESC LIMIT 1");
            if(rst.next())
            {
                String sid = rst.getString(1);
                pIDSequence = Integer.parseInt(sid.substring(sid.lastIndexOf("-")+1));
            }
            else
            {
                pIDSequence = 0;
            }
            smt.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
