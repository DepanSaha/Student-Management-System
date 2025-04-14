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

public class StudentAddFrame extends JDialog
{
    private JTextField txtID, txtName, txtFather, txtAddress, txtDOB, txtPhone, txtEmail;
    private JComboBox cmbGender, cmbCourse, cmbSem;
    private JButton btnAdd, btnUpdate, btnCancel, btnReturn;
    
    private String[] gender = {"Male", "Female", "Trans","Other"};
    private String[] course = {"BTech", "BCA", "BBA", "BSC", "MTech", "MCA", "MBA", "MSC"};
    private String[] sem    = {"1", "2", "3", "4", "5", "6", "7", "8"};
    
    private int sIDSequence = 0;
    private Connection con = null;
    private PreparedStatement pst1 = null;
    private PreparedStatement pst2 = null;
    
    private JLabel makeLabel(String s,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(s);
        temp.setBounds(x, y, w, h);
	if(mode == 1)
	{
            Border b1 = BorderFactory.createLineBorder(Color.RED, 2);
            Border b2 = BorderFactory.createLineBorder(Color.WHITE, 2);
            Border b3 = BorderFactory.createCompoundBorder(b1, b2);
            temp.setFont(new Font("Verdana", 1, 32));
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
                try
                {
                    Object ob = e.getSource();
                    if(ob == btnAdd)
                    {
                        enableDiasable(true);
                        Date date = new Date();
                        int mn = date.getMonth()+1;
                        int yr = date.getYear() + 1900;
                        String uid = String.format("S-%4d-%02d-%05d",yr, mn, sIDSequence+1);
                        txtID.setText(uid);
                        txtName.grabFocus();
                    }
                    else if(ob == btnUpdate)
                    {
                        pst1.setString(1, txtID.getText());
			pst1.setString(2, txtName.getText());
			pst1.setString(3, txtFather.getText());
			pst1.setString(4, (String)cmbGender.getSelectedItem());
			pst1.setString(5, txtAddress.getText());
			pst1.setString(6, txtDOB.getText());
			pst1.setString(7, txtPhone.getText());
			pst1.setString(8, txtEmail.getText());
			pst1.setString(9, (String)cmbCourse.getSelectedItem());
			pst1.setString(10, (String)cmbSem.getSelectedItem());
			pst1.executeUpdate();
			
                        String pass = txtName.getText();
                        pass = pass.substring(0, pass.indexOf(" "));
                        System.out.print(pass);
			pst2.setString(1, txtID.getText());
			pst2.setString(2, pass);
			pst2.setString(3, "Student");
			pst2.executeUpdate();
                        sIDSequence++;
			JOptionPane.showMessageDialog(null, "Student Registered Successfully");
                        enableDiasable(false);
                        reset();
                    }
                    else if(ob == btnCancel)
                    {
                        enableDiasable(false);
                        reset();
                    }
                    else if(ob == btnReturn)
                    {
                        dispose();
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        add(temp);
        return temp;
    }
    
    private void enableDiasable(boolean state)
    {
        txtID.setEditable(state);
        txtName.setEditable(state);
        txtFather.setEditable(state);
        txtAddress.setEditable(state);
        txtDOB.setEditable(state);
        txtEmail.setEditable(state);
        txtPhone.setEditable(state);
        cmbGender.setSelectedIndex(0);
        cmbCourse.setSelectedIndex(0);
        cmbSem.setSelectedIndex(0);
        btnUpdate.setEnabled(state);
        btnCancel.setEnabled(state);
    }
    
    private void reset()
    {
        txtID.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtFather.setText("");
        txtAddress.setText("");
        txtDOB.setText("");
        txtEmail.setText("");
    }
    
    public StudentAddFrame()
    {
        try
        {
            makeLabel("NEW STUDENT REGISTRATION", 10,  10, 600, 70, 1);
            makeLabel("STUDENT ID GENERATED",     10,  100, 300, 30, 2);
            txtID = makeTextField(               300,  100, 310, 30);
            txtID.setEditable(false);
            makeLabel("ENTER STUDENT NAME",       10, 140, 300, 30, 2);
            txtName = makeTextField(             300, 140, 310, 30);
            makeLabel("ENTER FATHER'S NAME",      10, 180, 300, 30, 2);
            txtFather = makeTextField(           300, 180, 310, 30);
            makeLabel("ENTER GENDER STATUS",      10, 220, 300, 30, 2);
            cmbGender = makeComboBox(gender,     300, 220, 310, 30);
            makeLabel("ENTER LOCAL ADDRESS",      10, 260, 300, 30, 2);
            txtAddress = makeTextField(          300, 260, 310, 30);
            makeLabel("ENTER DATE OF BIRTH",      10, 300, 300, 30, 2);
            txtDOB = makeTextField(              300, 300, 310, 30);
            makeLabel("ENTER PHONE NUMBER",       10, 340, 300, 30, 2);
            txtPhone = makeTextField(            300, 340, 310, 30);
            makeLabel("ENTER EMAIL ADDRESS",      10, 380, 300, 30, 2);
            txtEmail = makeTextField(            300, 380, 310, 30);
            makeLabel("ENTER COURSE ENROLLED",    10, 420, 300, 30, 2);
            cmbCourse = makeComboBox(course,     300, 420, 310, 30);
            makeLabel("NUMBER OF SEMESTERS",      10, 460, 300, 30, 2);
            cmbSem = makeComboBox(sem,           300, 460, 310, 30);
            btnAdd = makeButton("Add New",        25, 500, 100, 30);
            btnAdd.setEnabled(true);
            btnUpdate = makeButton("Update",     180, 500, 100, 30);
            btnCancel = makeButton("Cancel",     330, 500, 100, 30);
            btnReturn = makeButton("Return",     480, 500, 100, 30);
            btnReturn.setEnabled(true);
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2024","root","depansaha");
            pst1 = con.prepareStatement("insert into student_master values(?,?,?,?,?,?,?,?,?,?)");
	    pst2 = con.prepareStatement("insert into user values(?,?,?)");
            Statement smt = con.createStatement();
            String sql = "SELECT STUDENT_ID FROM STUDENT_MASTER ORDER BY STUDENT_ID DESC LIMIT 1";
            ResultSet rst = smt.executeQuery(sql);
            if(!rst.next())
            {
                sIDSequence = 0;
            }
            else
            {
                String sid = rst.getString(1);
                sIDSequence = Integer.parseInt(sid.substring(sid.lastIndexOf("-")+1));
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
