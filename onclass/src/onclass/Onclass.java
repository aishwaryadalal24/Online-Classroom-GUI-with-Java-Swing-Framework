
package onclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Onclass extends JFrame implements ActionListener{

JLabel lblusername, lblpassword;
JTextField txtusername;
JPasswordField txtpassword;
JButton btnlogin, btnreset;
    
public Onclass(){
   
        lblusername=new JLabel("User Name:");
        lblpassword=new JLabel("password:");
        txtusername=new JTextField(10);
        txtpassword=new JPasswordField(10);
        btnlogin=new JButton("Login");
        btnreset=new JButton("Reset");
        
        Container contentpane=this.getContentPane();

        contentpane.setLayout(new FlowLayout());

        contentpane.add(lblusername);
        contentpane.add(txtusername);
        contentpane.add(lblpassword);
        contentpane.add(txtpassword);
        contentpane.add(btnlogin);
        contentpane.add(btnreset);

        btnlogin.addActionListener(this);
        btnreset.addActionListener(this);
}


    @Override
    public void actionPerformed(ActionEvent e) {
        
    String btnpressed=e.getActionCommand();
            if(btnpressed.equals("Reset"))
            {

                    txtusername.setText("");
                    txtpassword.setText("");
            }
            else if (btnpressed.equals("Login")) {
                                JFrame f=new JFrame();
                                Container c=f.getContentPane();
                               JFrame ft1=new JFrame();
                                Container ct1=ft1.getContentPane();
                                c.setLayout(new FlowLayout());
                                
                                 
                                String username=txtusername.getText();
                                char pass[]=txtpassword.getPassword();
                                String password=new String(pass);
                                Connection con=null;
                                PreparedStatement ps=null;
                                try {
                                        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                                        System.out.println("Driver registered successfully");
                                        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/classroom", "root", "Kalpana@24");


                                        ps=con.prepareStatement("select * from user_details where username=?");

                                        ps.setString(1, username);

                                        ResultSet r=ps.executeQuery();

                                        if(r==null){
                                                    JLabel invalid= new JLabel("Invalid username or password \n please try again");
                                                    c.add(invalid);
                                                    f.setTitle("Login");
                                                    f.setSize(500,500);
                                                    f.setVisible(true);
                                        }

                                        else{
                                                String result = "ki";
                                                while(r.next()){

                                                result=r.getString(2);
                                        }
                                        if(result.equals(password)){
                                            
                                            
                                            ps=con.prepareStatement("select * from users where username=?");

                                            ps.setString(1, username);
                                        
                                            r=ps.executeQuery();
                                            if(r==null){
                                                    JLabel invalid= new JLabel("Invalid username or password \n please try again");
                                                    c.add(invalid);
                                                    f.setTitle("Login");
                                                    f.setSize(500,500);
                                                    f.setVisible(true);
                                                }
                                            else{
                                                result = "ki";
                                                while(r.next()){

                                                result=r.getString(2);
                                        }
                                            
                                            
                                                JLabel u=new JLabel("Welcome "+username+" to Online Classroom");
                                                c.add(u);
                                                JButton OS,Java,DBMS;
                                                JLabel cl=new JLabel("Here are all your classrooms");
                                                OS=new JButton("OS");
                                                Java=new JButton("Java");
                                                DBMS=new JButton("DBMS");
                                                JButton AddStudent=new JButton("AddStudent");
                                                 JButton AddAssignment=new JButton("AddAssignment");
                                                 JButton RemoveStudent=new JButton("RemoveStudent");
                                                c.add(cl);
                                                c.add(OS);
                                                c.add(Java);
                                                c.add(DBMS);
                                                
                                                if(result.equals("Teacher")){
                                                
                                                    
                                                    
                                                    c.add(AddStudent);
                                                    c.add(AddAssignment);
                                                    c.add(RemoveStudent);
                                                    
                                                
                                            }
                                                f.setTitle("Online Classroom");
                                                f.setSize(500,500);
                                                f.setVisible(true);
                                                
                                                OS.addActionListener(new ActionListener(){
                                                    @Override
                                                    public void actionPerformed(ActionEvent e){
                                                        JFrame cls=new JFrame();
                                                        Container op=cls.getContentPane();
                                                        op.setLayout(new FlowLayout());
                                                                JLabel os1=new JLabel("Welcome to os class");
                                                                JLabel hyperlink1= new JLabel();
                                                                JButton assn1=new JButton("Assignments of OS");
                                                                hyperlink1.setText("<html><a href='https://meet.google.com/lookup/etqwcsw7kh'>Google Meet Link for OS</a></html>");
                                                                op.add(os1);
                                                                op.add(hyperlink1);
                                                                op.add(assn1);
                                                                
                                                                cls.setTitle("OS Classroom");
                                                                cls.setSize(500,500);
                                                                cls.setVisible(true);
                                                    assn1.addActionListener(new ActionListener(){
                                                    @Override
                                                    public void actionPerformed(ActionEvent e){
                                                        
                                                             JFrame osassn=new JFrame();
                                                             Container osa=osassn.getContentPane();
                                                             osa.setLayout(new FlowLayout());
                                                              JLabel v1=new JLabel("Here are the all OS assignments ");
                                                              osa.add(v1);
                                                               
                                                        
                                                            try {
                                                                Connection con4=DriverManager.getConnection("jdbc:mysql://127.0.0.1/classroom", "root", "Kalpana@24");
                                                                 Statement st1=con4.createStatement();
                                                                 ResultSet rs=st1.executeQuery("select * from assignments where sub='OS'");
                                                                 while(rs.next()){
                                                                            JLabel q1=new JLabel(rs.getString(2));
                                                                            osa.add(q1);

                                                                        }
                                                                 
                                                               osassn.setTitle("OS Assignments");
                                                               osassn.setSize(500,500);
                                                               osassn.setVisible(true);
                                                                con4.close();
                                                                
                                                            } 
                                                            catch (SQLException ex) {
                                                                Logger.getLogger(Onclass.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                    }
                                                 });
                                                    
                                                    
                                                    
                                                    }
                                                });
                                                Java.addActionListener(new ActionListener(){   
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    JFrame cls2=new JFrame();
                                                    Container op2=cls2.getContentPane();
                                                    op2.setLayout(new FlowLayout());
                                                                JLabel os2=new JLabel("Welcome to Java class");
                                                                JLabel hyperlink2 = new JLabel();
                                                                JButton assn2=new JButton("Assignments of Java");
                                                                hyperlink2.setText("<html><a href='https://meet.google.com/lookup/etqwcsw7kh'>Google Meet Link for Java</a></html>");
                                                                op2.add(os2);
                                                                op2.add(hyperlink2);
                                                                op2.add(assn2);
                                                                cls2.setTitle("Java Classroom");
                                                                cls2.setSize(500,500);
                                                                cls2.setVisible(true);
                                                    assn2.addActionListener(new ActionListener(){
                                                    @Override
                                                    public void actionPerformed(ActionEvent e){
                                                                JFrame javaassn=new JFrame();
                                                             Container javaa=javaassn.getContentPane();
                                                             javaa.setLayout(new FlowLayout());
                                                              JLabel v1=new JLabel("Here are the all Java assignments ");
                                                              javaa.add(v1);
                                                               
                                                        
                                                            try {
                                                                Connection con5=DriverManager.getConnection("jdbc:mysql://127.0.0.1/classroom", "root", "Kalpana@24");
                                                                 Statement st2=con5.createStatement();
                                                                 ResultSet rs2=st2.executeQuery("select * from assignments where sub='Java'");
                                                                 while(rs2.next()){
                                                                            JLabel q2=new JLabel(rs2.getString(2));
                                                                            javaa.add(q2);

                                                                        }
                                                                 
                                                               javaassn.setTitle("Java Assignments");
                                                               javaassn.setSize(500,500);
                                                               javaassn.setVisible(true);
                                                               con5.close();
                                                                
                                                            } 
                                                            catch (SQLException ex) {
                                                                Logger.getLogger(Onclass.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                    }
                                                 });
                                                }
                                                
                                                });
                                                
                                                DBMS.addActionListener(new ActionListener(){   
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    JFrame cls3=new JFrame();
                                                    Container op3=cls3.getContentPane();
                                                                op3.setLayout(new FlowLayout());
                                                                JLabel os3=new JLabel("Welcome to DBMS class");
                                                                JLabel hyperlink3 = new JLabel();
                                                                JButton assn3=new JButton("Assignments of DBMS");
                                                                hyperlink3.setText("<html><a href='https://meet.google.com/lookup/gbpwnbaj36'>Google Meet Link for DBMS</a></html>");
                                                                op3.add(os3);
                                                                op3.add(hyperlink3);
                                                                op3.add(assn3);
                                                                cls3.setTitle("DBMS Classroom");
                                                                cls3.setSize(500,500);
                                                                cls3.setVisible(true);
                                                                
                                                    assn3.addActionListener(new ActionListener(){
                                                    @Override
                                                    public void actionPerformed(ActionEvent e){
                                                            JFrame dbmsassn=new JFrame();
                                                             Container dbmsa=dbmsassn.getContentPane();
                                                             dbmsa.setLayout(new FlowLayout());
                                                              JLabel v1=new JLabel("Here are the all DBMS assignments ");
                                                              dbmsa.add(v1);
                                                               
                                                        
                                                            try {
                                                                Connection con6=DriverManager.getConnection("jdbc:mysql://127.0.0.1/classroom", "root", "Kalpana@24");
                                                                 Statement st3=con6.createStatement();
                                                                 ResultSet rs3=st3.executeQuery("select * from assignments where sub='DBMS'");
                                                                 while(rs3.next()){
                                                                            JLabel q3=new JLabel(rs3.getString(2));
                                                                            dbmsa.add(q3);

                                                                        }
                                                                 
                                                               dbmsassn.setTitle("DBMS Assignments");
                                                               dbmsassn.setSize(500,500);
                                                               dbmsassn.setVisible(true);
                                                                con6.close();
                                                                
                                                            } 
                                                            catch (SQLException ex) {
                                                                Logger.getLogger(Onclass.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                    }
                                                 });
                                                                
                                                }
                                                
                                                });
                                                
                                                 
                                                AddAssignment.addActionListener(new ActionListener(){   
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    JFrame assign=new JFrame();
                                                    Container assignc=assign.getContentPane();
                                                    assignc.setLayout(new FlowLayout());
                                                    JLabel s1=new JLabel("Enter Subject of Assignment: ");
                                                    JLabel a1=new JLabel("Enter Assignment question: ");
                                                    JTextField sub,asn;
                                                    sub=new JTextField(50);
                                                    asn=new JTextField(50);
                                                    JButton addasn=new JButton("ADD ASSIGNMENT");
                                                    
                                                    assignc.add(s1);
                                                    assignc.add(sub);
                                                    assignc.add(a1);
                                                    assignc.add(asn);
                                                    assignc.add(addasn);
                                                    assign.setTitle("New Assignments");
                                                    assign.setSize(500,500);
                                                    assign.setVisible(true);
                                                    
                                                    
                                                    
                                                    addasn.addActionListener(new ActionListener(){
                                                    @Override
                                                    public void actionPerformed(ActionEvent e){
                                                        String subject1=sub.getText(),ques1=asn.getText();
                                                        
                                                        
                                                            try {
                                                                Connection con1=DriverManager.getConnection("jdbc:mysql://127.0.0.1/classroom", "root", "Kalpana@24");
                                                                PreparedStatement ps1=null;
           
                                                                ps1=con1.prepareStatement("insert into assignments values (?,?)");
                                                                
                                                                ps1.setString(1, subject1);
                                                                ps1.setString(2,ques1);
                                                                ps1.executeUpdate();
                                                                System.out.println("values added");
                                                                con1.close();
                                                                
                                                            } 
                                                            catch (SQLException ex) {
                                                                Logger.getLogger(Onclass.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                        
                                                    }
                                                    });
                                                    
                                                    
                                                }
                                                
                                                });
                                                
                                                AddStudent.addActionListener(new ActionListener(){   
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    JFrame addstu=new JFrame();
                                                    Container addstudent=addstu.getContentPane();
                                                    addstudent.setLayout(new FlowLayout());
                                                    JLabel stuname=new JLabel("Enter Name of student:  ");
                                                    JLabel stureg=new JLabel("Enter Registration number of student: ");
                                                    JTextField stun,stur;
                                                    stun=new JTextField(50);
                                                    stur=new JTextField(50);
                                                    JButton addnewstu=new JButton("ADD STUDENT");
                                                    
                                                    addstudent.add(stuname);
                                                    addstudent.add(stun);
                                                    addstudent.add(stureg);
                                                    addstudent.add(stur);
                                                    addstudent.add(addnewstu);
                                                    addstu.setTitle("New Student");
                                                    addstu.setSize(500,500);
                                                    addstu.setVisible(true);
                                                    
                                                addnewstu.addActionListener(new ActionListener(){   
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    String stu1=stun.getText(),reg1=stur.getText();
                                                        
                                                        
                                                            try {
                                                                Connection con2=DriverManager.getConnection("jdbc:mysql://127.0.0.1/classroom", "root", "Kalpana@24");
                                                                PreparedStatement ps2=null;
           
                                                                ps2=con2.prepareStatement("insert into users values (?,?,?,?)");
                                                                
                                                                ps2.setString(1, stu1);
                                                                ps2.setString(2,"Student");
                                                                ps2.setString(3,reg1);
                                                                ps2.setString(4,null);
                                                                ps2.executeUpdate();
                                                                
                                                                ps2=con2.prepareStatement("insert into user_details values (?,?)"); 
                                                                ps2.setString(1, stu1);
                                                                ps2.setString(2,reg1);
                                                                ps2.executeUpdate();
                                                                System.out.println("New Student added Successfully!!");
                                                                con2.close();
                                                                
                                                            } 
                                                            catch (SQLException ex) {
                                                                Logger.getLogger(Onclass.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                }
                                                
                                                }); 
                                                    
                                     }
                                                
                                                }); 
                                                
                                                RemoveStudent.addActionListener(new ActionListener(){   
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    JFrame removestu=new JFrame();
                                                    Container removestudent=removestu.getContentPane();
                                                    removestudent.setLayout(new FlowLayout());
                                                    
                                                    JLabel rstureg=new JLabel("Enter Registration number of student: ");
                                                    JTextField removes;
                                                    removes=new JTextField(50);
                                                    
                                                    JButton removeystu=new JButton("REMOVE STUDENT");
                                                    
                                                    removestudent.add(rstureg);
                                                    removestudent.add(removes);
                                                    removestudent.add(removeystu);
                                                   
                                                    removestu.setTitle("Remove Student");
                                                    removestu.setSize(500,500);
                                                    removestu.setVisible(true);
                                                    
                                                removeystu.addActionListener(new ActionListener(){   
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    String stu2=removes.getText();
                                                        
                                                        
                                                            try {
                                                                Connection con3=DriverManager.getConnection("jdbc:mysql://127.0.0.1/classroom", "root", "Kalpana@24");
                                                                PreparedStatement ps3=null;
           
                                                                ps3=con3.prepareStatement("delete from users where reg_no=?");
                                                                ps3.setString(1,stu2);
                                                                ps3.executeUpdate();
                                                                ps3=con3.prepareStatement("delete from user_details where password=?");
                                                                ps3.setString(1,stu2);
                                                                ps3.executeUpdate();
                                                                System.out.println("Student Removed Successfully!!");
                                                                con3.close();
                                                                
                                                            } 
                                                            catch (SQLException ex) {
                                                                Logger.getLogger(Onclass.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                }
                                                
                                                }); 
                                                }
                                                
                                                }); 
                                                
                                            }
                                            
                                            }
                                        else{
                                                        JLabel invalid= new JLabel("Invalid username or password \n please try again");
                                                        c.add(invalid);
                                                        f.setTitle("Login");
                                                        f.setSize(500,500);
                                                        f.setVisible(true);
                                        }
                                        }
                                        

                                        con.close();

            }
            catch(Exception e2)
                    { System.out.println(e2);
                    }
            
         

} 
            
}
    
     public static void main(String[] args) {
                    Onclass jf=new Onclass();
                    jf.setTitle("User Login");
                    jf.setSize(500,500);
                    jf.setVisible(true);
                    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
