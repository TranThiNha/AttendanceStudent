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
public class ResetPasswordLayout extends JFrame {

    Container container;
    JPanel jPanelHeader;
    public static Tblaccount account;
    public ResetPasswordLayout(){
        container = this.getContentPane();
        container.setLayout(null);

        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,500,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("RESET MẬT KHẨU");
        labelHeader.setFont(new Font("Serif", Font.BOLD, 24));
        labelHeader.setForeground(new Color(255,255,255));
        jPanelHeader.add(labelHeader);

        JLabel lblNewPassword = new JLabel("Nhập mật khẩu mới: ");
        lblNewPassword.setBounds(20,120,200,40);
        lblNewPassword.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

        JPasswordField txtNewPassword = new JPasswordField();
        txtNewPassword.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        txtNewPassword.setBounds(220,120,200,40);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        txtNewPassword.setBorder(border);

        JLabel lblConfirmPassword = new JLabel("Nhập lại mật khẩu mới: ");
        lblConfirmPassword.setBounds(20,180,200,40);
        lblConfirmPassword.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

        txtPassword.setBounds(220,180,200,40);
        txtPassword.setBorder(border);

        JButton btnLogin = new JButton("RESET");
        btnLogin.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        btnLogin.setBounds(210,240,150,40);
        btnLogin.setBackground(new Color(127, 179, 213  ));
        
        JButton btnCancel = new JButton("Trở lại");
        btnCancel.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        btnCancel.setBounds(40,240,150,40);
        btnCancel.setBackground(new Color(127, 179, 213  ));
        
        JLabel lblNotify = new JLabel("");
        lblNotify.setFont(new Font("Serif", Font.ITALIC, 16));
        lblNotify.setBounds(20,300,400,40);
        lblNotify.setForeground(Color.red);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtNewPassword.getText().equalsIgnoreCase(account.getPass())){
                    lblNotify.setText("Password trùng với password cũ!");
                }else if (!txtNewPassword.getText().equalsIgnoreCase(txtPassword.getText())){
                    lblNotify.setText("không trùng Password!");           
                }else{
                    account.setIsTheFisrtTime(0);
                    account.setPass(MD5.encryptMD5(txtNewPassword.getText()));
                    if (AccountDAO.updateAccount(account)){
                        AttendanceLayout.currentAccount = StudentDAO.getStudentInforFromUsername(account.getUserName());
                        System.out.println( AttendanceLayout.currentAccount.getTenSv());
                                    
                        System.out.println("Update thành công");
                        lblNotify.setText("Reset Password thành công!");           
                        MainStudentLayout layout = null;
                        layout = new MainStudentLayout();
                        layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        layout.setVisible(true);
                        layout.setSize(1000, 600);
                        ResetPasswordLayout.this.setVisible(false);
                    }
                    
                }
        
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginLayout layout = null;
                layout = new LoginLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(450, 400);
                ResetPasswordLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(btnCancel);
        container.add(lblNotify);
        container.add(btnLogin);
        container.add(txtPassword);
        container.add(lblConfirmPassword);
        container.add(txtNewPassword);
        container.add(lblNewPassword);
        container.add(jPanelHeader);
    }
}
