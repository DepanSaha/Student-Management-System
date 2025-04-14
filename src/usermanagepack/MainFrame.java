package usermanagepack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;

public class MainFrame extends JFrame
{
    private JMenuBar menubar = null;
    private JMenu menuAdmin, menuProfessor, menuStudent;
    private JMenuItem[] adminItems = new JMenuItem[7];
    private JMenuItem[] profItems = new JMenuItem[4];
    private JMenuItem[] stuItems = new JMenuItem[3];
    private String[] adminItemCap = {"Add Student", "Add Professor", "Edit Student", "Edit Professor", "Delete Student", "Delete Professor", "Exit"};
    private String[] profItemCap = {"Assign Grade", "Edit Grade", "View Student", "Exit"};
    private String[] stuItemCap = {"View Detail", "View Grade", "Exit"};
    private Font fnt = new Font("Verdana", 1, 12);
        
    private JMenu makeMenu(String cap)
    {
        JMenu temp = new JMenu(cap);
        temp.setFont(fnt);
        menubar.add(temp);
        return temp;
    }
    
    private JMenuItem makeMenuItem(String cap, JMenu menu, String image)
    {
        JMenuItem temp = new JMenuItem(cap, new ImageIcon(image));
        temp.setFont(fnt);
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object ob = e.getSource();
                if(ob == adminItems[0]) //Add new Students
                {
                    StudentAddFrame stuAddframe = new StudentAddFrame();
                    stuAddframe.setTitle("ADD NEW STUDENT");
                    stuAddframe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    stuAddframe.setResizable(false);
                    stuAddframe.setSize(630, 580);
                    stuAddframe.setLocationRelativeTo(null);
                    stuAddframe.setModal(true);
                    stuAddframe.getContentPane().setBackground(new Color(250, 250, 200));
                    stuAddframe.setLayout(new BorderLayout());
                    stuAddframe.setUndecorated(true);
                    stuAddframe.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    stuAddframe.setVisible(true);
                }
                else if(ob == adminItems[1]) //Add new Professors
                {
                    ProfessorAddFrame proAddframe = new ProfessorAddFrame();
                    proAddframe.setTitle("ADD NEW PROFESSOR");
                    proAddframe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    proAddframe.setResizable(false);
                    proAddframe.setSize(630, 500);
                    proAddframe.setLocationRelativeTo(null);
                    proAddframe.setModal(true);
                    proAddframe.getContentPane().setBackground(new Color(250, 250, 200));
                    proAddframe.setLayout(new BorderLayout());
                    proAddframe.setUndecorated(true);
                    proAddframe.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    proAddframe.setVisible(true);
                }
                else if(ob == adminItems[2]) //Edit existing student
                {
                    StudentEditFrame stuEditFrame = new StudentEditFrame();
                    stuEditFrame.setTitle("EDIT EXISTING STUDENT");
                    stuEditFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    stuEditFrame.setResizable(false);
                    stuEditFrame.setSize(530,550);
                    stuEditFrame.setLocationRelativeTo(null);
                    stuEditFrame.getContentPane().setBackground(new Color(250,200,150));
                    stuEditFrame.setModal(true);
                    stuEditFrame.setLayout(new BorderLayout());
                    stuEditFrame.setUndecorated(true);
                    stuEditFrame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    stuEditFrame.setVisible(true);
                }
                else if(ob == adminItems[3]) //Edit existing professor
                {
                    ProfessorEditFrame proEditFrame = new ProfessorEditFrame();
                    proEditFrame.setTitle("EDIT EXISTING PROFESSOR");
                    proEditFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    proEditFrame.setResizable(false);
                    proEditFrame.setSize(530,500);
                    proEditFrame.setLocationRelativeTo(null);
                    proEditFrame.getContentPane().setBackground(new Color(250,200,150));
                    proEditFrame.setModal(true);
                    proEditFrame.setLayout(new BorderLayout());
                    proEditFrame.setUndecorated(true);
                    proEditFrame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    proEditFrame.setVisible(true);
                }
                else if(ob == adminItems[4]) //Delete existing student
                {
                    StudentDeleteFrame stuDelFrame = new StudentDeleteFrame();
                    stuDelFrame.setTitle("DELETE EXISTING STUDENT");
                    stuDelFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    stuDelFrame.setResizable(false);
                    stuDelFrame.setSize(530,550);
                    stuDelFrame.setLocationRelativeTo(null);
                    stuDelFrame.getContentPane().setBackground(new Color(250,200,150));
                    stuDelFrame.setModal(true);
                    stuDelFrame.setLayout(new BorderLayout());
                    stuDelFrame.setUndecorated(true);
                    stuDelFrame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    stuDelFrame.setVisible(true);
                }
                else if(ob == adminItems[5]) //Delete existing professor
                {
                    ProfessorDeleteFrame proDelFrame = new ProfessorDeleteFrame();
                    proDelFrame.setTitle("DELETE EXISTING PROFESSOR");
                    proDelFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    proDelFrame.setResizable(false);
                    proDelFrame.setSize(530,500);
                    proDelFrame.setLocationRelativeTo(null);
                    proDelFrame.getContentPane().setBackground(new Color(250,200,150));
                    proDelFrame.setModal(true);
                    proDelFrame.setLayout(new BorderLayout());
                    proDelFrame.setUndecorated(true);
                    proDelFrame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    proDelFrame.setVisible(true);
                }
                else if(ob == adminItems[6])
                {
                    System.exit(0);
                }
                else if(ob == profItems[0]) //Assigning Grade
                {
                    System.setProperty("gradeassign", "1");
                    GradeAssign frame = new GradeAssign();
                    frame.setTitle("ASSIGNING OF GRADE");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setResizable(false);
                    frame.setSize(540,270);
                    frame.setLocationRelativeTo(null);
                    frame.getContentPane().setBackground(new Color(250,225,200));
                    frame.setModal(true);
                    frame.setLayout(new BorderLayout());
                    frame.setUndecorated(true);
                    frame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    frame.setVisible(true);
                }
                else if(ob == profItems[1]) // Editing Grade
                {
                    System.setProperty("gradeassign", "2");
                    GradeAssign frame = new GradeAssign();
                    frame.setTitle("MODIFICATION OF GRADE");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setResizable(false);
                    frame.setSize(540,270);
                    frame.setLocationRelativeTo(null);
                    frame.getContentPane().setBackground(new Color(250,225,200));
                    frame.setModal(true);
                    frame.setLayout(new BorderLayout());
                    frame.setUndecorated(true);
                    frame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    frame.setVisible(true);
                }
                else if(ob == profItems[2]) // View Student
                {
                    StudentSnapshot stuSnap = new StudentSnapshot();
                    stuSnap.setTitle("STUDENTS' SEMESTER WISE GRADE SNAPSHOT");
                    stuSnap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    stuSnap.setResizable(false);
                    stuSnap.setSize(900,400);
                    stuSnap.setLocationRelativeTo(null);
                    stuSnap.getContentPane().setBackground(new Color(250,225,200));
                    stuSnap.setModal(true);
                    stuSnap.setLayout(new BorderLayout());
                    stuSnap.setUndecorated(true);
                    stuSnap.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    stuSnap.setVisible(true);
                }
                else if(ob == profItems[3])
                {
                    System.exit(0);
                }
                else if(ob == stuItems[0]) //View Detail
                {
                    ViewDetailByStudent viewStuframe = new ViewDetailByStudent();
                    viewStuframe.setTitle("PERSONAL DETAIL");
                    viewStuframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    viewStuframe.setResizable(false);
                    viewStuframe.setSize(530,550);
                    viewStuframe.setLocationRelativeTo(null);
                    viewStuframe.getContentPane().setBackground(Color.WHITE);
                    viewStuframe.setModal(true);
                    viewStuframe.setUndecorated(true);
                    viewStuframe.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    viewStuframe.setModal(true);
                    viewStuframe.setLayout(new BorderLayout());
                    viewStuframe.setVisible(true);
                }
                else if(ob == stuItems[1]) //View Grade
                {
                    ViewGradeByStudent stuGrade = new ViewGradeByStudent();
                    stuGrade.setTitle("GRADE DETAIL");
                    stuGrade.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    stuGrade.setResizable(false);
                    stuGrade.setSize(530,500);
                    stuGrade.setLocationRelativeTo(null);
                    stuGrade.getContentPane().setBackground(Color.WHITE);
                    stuGrade.setModal(true);
                    stuGrade.setLayout(new BorderLayout());
                    stuGrade.setUndecorated(true);
                    stuGrade.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    stuGrade.setVisible(true);
                }
                else if(ob == stuItems[2])
                {
                    System.exit(0);
                }
            }
        });
        menu.add(temp);
        return temp;
    }
    
    public void setMenuEnable(boolean b1, boolean b2, boolean b3)
    {
        menuAdmin.setEnabled(b1);
        menuProfessor.setEnabled(b2);
        menuStudent.setEnabled(b3);
    }
    
    public MainFrame()
    {
        menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        menuAdmin     = makeMenu("Administration");
        menuProfessor = makeMenu("Professor");
        menuStudent   = makeMenu("Student");
        
        for(int idx = 0; idx < 7; idx++)
        {
            if(idx != 0 && idx % 2 == 0) menuAdmin.addSeparator();
            adminItems[idx] = makeMenuItem(adminItemCap[idx], menuAdmin, "menuicon.gif");
        }
        for(int idx = 0; idx < 4; idx++)
        {
            if(idx == 3) menuProfessor.addSeparator();
            profItems[idx] = makeMenuItem(profItemCap[idx], menuProfessor, "menuicon.gif");
        }
        for(int idx = 0; idx < 3; idx++)
        {
            if(idx == 2) menuStudent.addSeparator();
            stuItems[idx] = makeMenuItem(stuItemCap[idx], menuStudent, "menuicon.gif");
        }
    }
}
