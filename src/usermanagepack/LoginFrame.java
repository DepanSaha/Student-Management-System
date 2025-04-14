package usermanagepack;

import java.awt.BorderLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginFrame extends JFrame
{
    private JComboBox cmdRole;
    private JTextField txtUid;
    private JPasswordField txtPwd;
    private JButton btnSignUp, btnSignIn, btnReset, btnExit;
    private String[] role = {"Select Your Role", "Admin","Professor","Student"};
        
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
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(temp.getSelectedIndex())
                {
                    case -1:
                        enableDisable(false);
                        break;
                    case 0:
                        enableDisable(false);
                        break;
                    case 1:
                        enableDisable(true);
                        break;
                    default:
                        enableDisable(true);
                        btnSignUp.setEnabled(false);
                        break;
                }
            }
        });
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
                Object ob = e.getSource();
                if(ob == btnSignUp)
                {
                    SignUpFrame signframe = new SignUpFrame();
                    signframe.setTitle("SIGN UP PANEL...");
                    signframe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    signframe.setResizable(false);
                    signframe.setSize(600, 300);
                    signframe.setLocationRelativeTo(null);
                    signframe.setModal(true);
                    signframe.getContentPane().setBackground(new Color(250, 250, 200));
                    signframe.setLayout(new BorderLayout());
                    signframe.setUndecorated(true);
                    signframe.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    signframe.setVisible(true);
                }
                else if(ob == btnSignIn)
                {
                    try
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project2024","root","depansaha");
                        String sql = "SELECT USERID FROM USER WHERE USERID = ? AND PASSWORD = ? AND ROLE = ?";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, txtUid.getText());
                        pst.setString(2, txtPwd.getText());
                        pst.setString(3, (String)cmdRole.getSelectedItem());
                        ResultSet result = pst.executeQuery();
                        if(!result.next())
                        {
                            JOptionPane.showMessageDialog(null, "CREDENTIAL FALIURE");
                            resetLabel();
                        }
                        else
                        {
                            MainFrame mainframe = new MainFrame();
                            mainframe.setTitle("STUDENT MANAGEMENT SYSTEM");
                            mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            mainframe.setResizable(false);
                            mainframe.setSize(900, 700);
                            mainframe.setLocationRelativeTo(null);
                            mainframe.getContentPane().setBackground(new Color(250, 250, 200));
                            mainframe.setLayout(new BorderLayout());
                            mainframe.setUndecorated(true);
                            mainframe.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                            mainframe.setVisible(true);
                            
                            if(cmdRole.getSelectedIndex() == 1)
                                mainframe.setMenuEnable(true, false, false);
                            else if(cmdRole.getSelectedIndex() == 2)
                                mainframe.setMenuEnable(false, true, false);
                            else if(cmdRole.getSelectedIndex() == 3)
                            {
                                mainframe.setMenuEnable(false, false, true);
                                System.setProperty("student_id", txtUid.getText());
                            }
                        }
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                else if(ob == btnReset)
                {
                    resetLabel();
                }
                else if(ob == btnExit)
                {
                    System.exit(0);
                }
            }
        });
        add(temp);
        return(temp);
    }
    
    private void enableDisable(Boolean state)
    {
        btnSignIn.setEnabled(state);
        btnSignUp.setEnabled(state);
        btnReset.setEnabled(state);
    }
    
    private void resetLabel()
    {
        txtUid.setText("");
        txtPwd.setText("");
        cmdRole.setSelectedIndex(-1);
    }
    
    public LoginFrame()
    {
        makeLabel("ON BOARD LOGIN", 10, 10, 570, 50, 1);
        makeLabel("SELECT ROLE/PRIVILEGE", 10, 70, 300, 30, 2);
        cmdRole = makeComboBox(role, 280, 70, 300, 30);
        makeLabel("ENTER USER ID", 10, 110, 300, 30, 2);
        txtUid = (JTextField)makeTextField(280, 110, 300, 30, 1);
        txtUid.setHorizontalAlignment(JTextField.CENTER);
        makeLabel("ENTER PASSWORD", 10, 150, 300, 30, 2);
        txtPwd = (JPasswordField)makeTextField(280, 150, 300, 30, 2);
        txtPwd.setHorizontalAlignment(JTextField.CENTER);
        txtPwd.setEchoChar('*');
        btnSignUp = makeButton("Sign Up", 10, 200, 120, 30);
        btnSignIn = makeButton("Sign In", 160, 200, 120, 30);
        btnReset = makeButton("Reset", 310, 200, 120, 30);
        btnExit = makeButton("Exit", 460, 200, 120, 30);
        enableDisable(false);
    }
}
