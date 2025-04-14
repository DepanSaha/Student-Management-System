package usermanagepack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

public class LoginMain
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project2024","root","depansaha");
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet result = metadata.getTables("project2024","root","USER", new String[]{"TABLE"});
            Statement smt = con.createStatement();
            if(!result.next())
            {
                String sql = "CREATE TABLE USER(USERID VARCHAR(20) PRIMARY KEY, PASSWORD VARCHAR(20), ROLE VARCHAR(9))";
                smt.executeUpdate(sql);
                
                sql = "INSERT INTO USER VALUES('admin','admin','Admin')";
                smt.executeUpdate(sql);
                
                sql = "CREATE TABLE STUDENT_MASTER(STUDENT_ID VARCHAR(15) PRIMARY KEY, NAME VARCHAR(20), FATHER_NAME VARCHAR(20), GENDER VARCHAR(6), ADDRESS VARCHAR(50), DOB DATE, PHONE VARCHAR(12), EMAIL VARCHAR(30), COURSE VARCHAR(5), SEMESTERS CHAR(1))";
                smt.executeUpdate(sql);
                
                sql = "CREATE TABLE PROFESSOR_MASTER(PROFESSOR_ID VARCHAR(13) PRIMARY KEY, NAME VARCHAR(10), ADDRESS VARCHAR(50), GENDER VARCHAR(6), PHONE VARCHAR(12), EMAIL VARCHAR(30), DOB DATE, DOJ DATE)";
                smt.executeUpdate(sql);
                
                sql = "CREATE TABLE PROFESSOR_DEGREE(PROFESSOR_ID VARCHAR(13), DEGREE VARCHAR(10), PRIMARY KEY (PROFESSOR_ID, DEGREE), FOREIGN KEY(PROFESSOR_ID) REFERENCES PROFESSOR_MASTER(PROFESSOR_ID) ON DELETE CASCADE)";
                smt.executeUpdate(sql);
                
                sql = "CREATE TABLE STUDENT_GRADE(STUDENT_ID VARCHAR(20), SEMESTER CHAR(1), GRADE CHAR(1), PRIMARY KEY(STUDENT_ID, SEMESTER), FOREIGN KEY(STUDENT_ID) REFERENCES STUDENT_MASTER(STUDENT_ID) ON DELETE CASCADE)";
                smt.executeUpdate(sql);
            }
            con.close();
            
            LoginFrame logframe = new LoginFrame();
            logframe.setTitle("SIGN IN PANEL...");
            logframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            logframe.setResizable(false);
            logframe.setSize(600, 300);
            logframe.setLocationRelativeTo(null);
            logframe.getContentPane().setBackground(new Color(250, 250, 200));
            logframe.setLayout(new BorderLayout());
            logframe.setUndecorated(true);
            logframe.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
            logframe.setVisible(true);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
