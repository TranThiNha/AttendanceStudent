package Layout;

import DAO.AccountDAO;
import DAO.StudentDAO;
import Helper.MD5;
import Pojo.Tblaccount;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MyPC on 4/10/2017.
 */
public class LoginLayout extends JFrame{

    JButton btnLogin;
    JPanel jPanelHeader;
    Container container;
    boolean isSV = true;
    public LoginLayout(){

        container = this.getContentPane();
        container.setLayout(null);

        //header
        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,450,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("ĐĂNG NHẬP");
        labelHeader.setFont(new Font("Serif", Font.BOLD, 24));
        labelHeader.setForeground(new Color(255,255,255));
        jPanelHeader.add(labelHeader);

        JLabel lblUserName = new JLabel("Tên đăng nhập: ");
        lblUserName.setBounds(20,120,120,40);
        lblUserName.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

        JTextArea txtUserName = new JTextArea();
        txtUserName.setFont(new Font("Serif", Font.ITALIC, 18));
        txtUserName.setBounds(140,120,200,40);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        txtUserName.setBorder(border);

        JLabel lblPassword = new JLabel("Mật khẩu: ");
        lblPassword.setBounds(60,180,80,40);
        lblPassword.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

        txtPassword.setBounds(140,180,200,40);
        txtPassword.setBorder(border);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        btnLogin.setBounds(260,240,150,40);
        btnLogin.setBackground(new Color(127, 179, 213  ));

        JButton btnChangePass = new JButton("Đổi mật khẩu");
        btnChangePass.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        btnChangePass.setBounds(20,240,150,40);
        btnChangePass.setBackground(new Color(127, 179, 213  ));

        JLabel lblNotify = new JLabel("");
        lblNotify.setFont(new Font("Serif", Font.ITALIC, 16));
        lblNotify.setBounds(20,300,250,40);
        lblNotify.setForeground(Color.red);

        
        btnChangePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtUserName.getText().equalsIgnoreCase("") && !txtPassword.getText().equalsIgnoreCase(""))
                {
                    if (AccountDAO.getAccountInfor(txtUserName.getText()) != null){
                        Tblaccount account = AccountDAO.getAccountInfor(txtUserName.getText());
                    if (!txtUserName.getText().equalsIgnoreCase("") && !txtPassword.getText().equalsIgnoreCase(""))
                    {
                        if ((account.getIsTheFisrtTime()==1 && account.getPass().equals(txtPassword.getText())) || (account.getIsTheFisrtTime()==0 && account.getPass().equals(MD5.encryptMD5(txtPassword.getText())))){
                                    
                                    ResetPasswordLayout.account = account;
                                    ResetPasswordLayout layout = null;
                                    layout = new ResetPasswordLayout();
                                    layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    layout.setVisible(true);
                                    layout.setSize(500, 500); 
                                    LoginLayout.this.setVisible(false);
                        }     
                               
                        }
                        else{
                            lblNotify.setText("password sai! vui lòng nhập lại");

                        }
                    }
                    else{
                    lblNotify.setText("Tài khoản không tồn tại!");
                        
                            
                    }
                }
                else{
                    lblNotify.setText("Nhập thiếu");
                }
 
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       
        
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtUserName.getText().equalsIgnoreCase("") && !txtPassword.getText().equalsIgnoreCase(""))
                {
                    if (AccountDAO.getAccountInfor(txtUserName.getText()) != null){
                        Tblaccount account = AccountDAO.getAccountInfor(txtUserName.getText());
                  
                        if ((account.getIsTheFisrtTime()==1 && account.getPass().equals(txtPassword.getText())) || (account.getIsTheFisrtTime()==0 && account.getPass().equals(MD5.encryptMD5(txtPassword.getText())))){

                            if (account.getIsSv() == 1){
                                if (account.getIsTheFisrtTime()==1){
                                    
                                    
                                    ResetPasswordLayout.account = account;
                                    ResetPasswordLayout layout = null;
                                    layout = new ResetPasswordLayout();
                                    layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    layout.setVisible(true);
                                    layout.setSize(500, 500); 
                                    LoginLayout.this.setVisible(false);
                                    
                                }else{
                                    AttendanceLayout.currentAccount = StudentDAO.getStudentInforFromUsername(account.getUserName());
                                    
                                    MainStudentLayout layout = null;
                                    layout = new MainStudentLayout();
                                    layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    layout.setVisible(true);
                                    layout.setSize(1000, 600); 
                                    LoginLayout.this.setVisible(false);
                                }
                             
                            }
                            else{
                                MainLayout_GVLayout layout = null;
                                layout = new MainLayout_GVLayout();
                                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                layout.setVisible(true);
                                layout.setSize(1000, 600);
                                LoginLayout.this.setVisible(false);
                            }
                            }
                        
                        else{
                            lblNotify.setText("password sai! vui lòng nhập lại");

                        }
                    }
                    
                    else{
                    lblNotify.setText("Tài khoản không tồn tại!");
                        
                            
                    }
                
                }
                else{
                    lblNotify.setText("Nhập thiếu");
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(btnChangePass);
        container.add(lblNotify);
        container.add(btnLogin);
        container.add(txtPassword);
        container.add(lblPassword);
        container.add(txtUserName);
        container.add(lblUserName);
        container.add(jPanelHeader);
    }

}
