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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SignUpFrame extends JDialog
{
    private JTextField txtUid;
    private JPasswordField txtPwd, txtCnfPwd;
    private JComboBox cmdRole;
    private JButton btnSubmit, btnReset, btnReturn;
    private String[] role = {"Admin"};
    
    private Connection con = null;
    
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
        add(temp);
        return(temp);
    }
    
    private JComponent makeTextField(int x,int y,int w,int h, int mode)
    {
        JComponent temp = null;
        if(mode==1)
            temp = new JTextField();
        else if(mode==2)
            temp = new JPasswordField();
        temp.setBounds( x, y, w, h);
        Border b1 = BorderFactory.createLineBorder(Color.BLACK, 2);
	temp.setBorder(b1);
        temp.setFont(new Font("Courier New",1,18));
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
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Object ob = e.getSource();
                    if(ob == btnSubmit)
                    {
                        String qry = "SELECT USERID FROM USER WHERE USERID = ?";
                        PreparedStatement pst1 = con.prepareStatement(qry);
                        pst1.setString(1, txtUid.getText());
                        ResultSet rst = pst1.executeQuery();
                        if(rst.next())
                        {
                            JOptionPane.showMessageDialog(null, "USER ID ALREADY PRESENT");
                        }
                        else
                        {
                            qry = "INSERT INTO USER VALUES(?,?,?)";
                            PreparedStatement pst2 = con.prepareStatement(qry);
                            pst2.setString(1, txtUid.getText());
                            pst2.setString(2, txtPwd.getText());
                            pst2.setString(3, (String)cmdRole.getSelectedItem());
                            pst2.executeUpdate();
                            JOptionPane.showMessageDialog(null, "USER REGESTERED SUCCESSFULLY");
                        }
                    }
                    else if(ob == btnReset)
                    {
                        txtUid.setText("");
                        txtPwd.setText("");
                        txtCnfPwd.setText("");
                        cmdRole.setSelectedIndex(-1);
                    }
                    else if(ob == btnReturn)
                    {
                        try
                        {
                            con.close();
                            dispose();
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                    
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        add(temp);
        return(temp);
    }
    
    public SignUpFrame()
    {
        makeLabel("NEW USER REGISTRATION", 10, 10, 570, 50, 1);
        makeLabel("ENTER USER ID", 10, 70, 300, 30, 2);
        txtUid = (JTextField)makeTextField(280, 70, 300, 30, 1);
        txtUid.setHorizontalAlignment(JTextField.CENTER);
        makeLabel("ENTER PASSWORD", 10, 110, 300, 30, 2);
        txtPwd = (JPasswordField)makeTextField(280, 110, 300, 30, 2);
        txtPwd.setEchoChar('*');
        txtPwd.setHorizontalAlignment(JTextField.CENTER);
        makeLabel("CONFIRM PASSWORD", 10, 150, 300, 30, 2);
        txtCnfPwd = (JPasswordField)makeTextField(280, 150, 300, 30, 2);
        txtCnfPwd.setEchoChar('*');
        txtCnfPwd.setHorizontalAlignment(JTextField.CENTER);
        makeLabel("SELECT ROLE/PRIVILEGE", 10, 190, 300, 30, 2);
        cmdRole = makeComboBox(role, 280, 190, 300, 30);
        btnSubmit = makeButton("Submit", 60,  230, 120, 30);
        btnReset = makeButton("Reset",   230, 230, 120, 30);
        btnReturn = makeButton("Return", 390, 230, 120, 30);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project2024","root","depansaha");
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
            
    }
}
