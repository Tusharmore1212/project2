/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginpage;

/**
 *
 * @author hp123
 */
import java.awt.*;
import java.awt.event.*;
import java.lang.System.Logger;
import javax.swing.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
 Label Id, Password, caption;
  TextField txtfd, txtfd2;
  Button b1,b2,b3,b4,b5;

  public LoginPage() {
    // Set up the window
   
    caption = new Label("LOGIN PAGE");
    add(caption);
    caption.setBounds(180, 40, 200, 60);
    caption.setFont(new Font("serif", Font.BOLD, 25));

    
    
    Id = new Label("User Id");
    add(Id);
    
    Id.setBounds(70, 140, 120, 30);
    txtfd = new TextField();
    add(txtfd);
    txtfd.setBounds(300, 140, 120, 30);
    Password = new Label("PassWord");
    add(Password);
    Password.setBounds(70, 230, 120, 30);
    txtfd2 = new TextField();
    add(txtfd2);
    txtfd2.setBounds(300, 230, 120, 30);
    b5 = new Button("LOGIN");
    add(b5);
    
    b5.setBounds(170, 300, 120, 30);
    Id.setForeground(Color.BLACK);
    txtfd.setForeground(Color.BLACK);
    Password.setForeground(Color.BLACK);
    txtfd2.setForeground(Color.BLACK);
    b5.setForeground(Color.BLACK);
                            
    Id.setBackground(Color.WHITE);
    txtfd.setBackground(Color.WHITE);
    Password.setBackground(Color.WHITE);
    txtfd2.setBackground(Color.WHITE);
    b5.setBackground(Color.WHITE);
    //Id.addActionListener(this);
    //b2.addActionListener(this);
   // b3.addActionListener(this);
    //b4.addActionListener(this);
    b5.addActionListener(this);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("Display Text");
    
    setSize(500, 400);
    //setBackground(Color.RED);
    setLayout(null);
    setVisible(true);
    
  }
  Connection con ;
  
   @Override
  public void actionPerformed(ActionEvent e) {
      //AudioConverter  a = new AudioConverter();
     
         String id = txtfd.getText();
         String paassword = txtfd2.getText();
         if (e.getSource() == b5) {
           try {
         //   Class.forName("con.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","admin");
             System.out.println("connect");
             Statement st = con.createStatement();
             String sql = " select * from student where password = 'MORE'";
             ResultSet s= st.executeQuery(sql);
             if(s.next())
             {
                 dispose();
                 new NEWLOGIN().setVisible(true);
             }   
             else {
                                       JOptionPane.showMessageDialog(this,"invalid details");
                                         txtfd.setText("");
            txtfd2.setText("");

             }
             } catch (SQLException ex) {
          //  Logger.getLogger(registrationpage.class.getName()).log(Level.SEVERE, null, ex);
                      JOptionPane.showMessageDialog(this,"connection failed");
                txtfd.setText("");
            txtfd2.setText("");
        } 

        }     
      }
      
    
    public static void main(String[] args)
    {
         new NEWLOGIN().setVisible(true);

        
    }
}
