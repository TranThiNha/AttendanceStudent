/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

import DAO.StudentDAO;
import DAO.Student_SubjectDAO;
import Pojo.TblestudentSubject;
import Pojo.TblestudentSubjectId;
import Pojo.Tblstudent;
import Pojo.Tblsubject;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import java.util.List;

/**
 *
 * @author MyPC
 */
public class AddNewStudentLayout  extends JFrame{
    Container container;
    JPanel jPanelHeader;
    public static Tblsubject subject;   
    
    public AddNewStudentLayout(){
        container = this.getContentPane();
        container.setLayout(null);        
        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,600,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("THÊM SINH VIÊN MỚI");
        labelHeader.setFont(new Font("Serif", Font.BOLD, 24));
        labelHeader.setForeground(new Color(255,255,255));
        jPanelHeader.add(labelHeader);
        
        JLabel lblID = new JLabel("Nhập mã số SV: ");
        lblID.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblID.setBounds(20,100,250,40);
        
        JTextArea txtID = new JTextArea();
        txtID.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        txtID.setBounds(230,100,300,40);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        txtID.setBorder(border);
        
        JLabel lblName = new JLabel("Nhập Tên SV: ");
        lblName.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblName.setBounds(20,160,250,40);
        
        JTextArea txtName = new JTextArea();
        txtName.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        txtName.setBounds(230,160,300,40);
        txtName.setBorder(border);
       
        
        JLabel lblClass = new JLabel("Chọn tên lớp của SV: ");
        lblClass.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblClass.setBounds(20,220,250,40);
        
         JComboBox<String> cbbClass = new JComboBox<>();
        cbbClass.addItem("16CTT1");
        cbbClass.addItem("16CTT2");
        cbbClass.addItem("16CTT3");
        cbbClass.addItem("16CNTN");
        cbbClass.addItem("15CTT1");
        cbbClass.addItem("15CTT2");
        cbbClass.addItem("15CTT3");
        cbbClass.addItem("15CNTN");
        cbbClass.addItem("14CTT1");
        cbbClass.addItem("14CTT2");
        cbbClass.addItem("14CTT3");
        cbbClass.addItem("14CNTN");
        cbbClass.addItem("13CTT1");
        cbbClass.addItem("13CTT2");
        cbbClass.addItem("13CTT3");
        cbbClass.addItem("13CNTN");
        
        cbbClass.setBounds(230,220,300,40);
        cbbClass.setBackground(new Color(247,249,249));
       
        
        JLabel lblGender = new JLabel("Chọn giới tính của SV: ");
        lblGender.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblGender.setBounds(20,280,250,40);
        
         JComboBox<String> cbbGender = new JComboBox<>();
        cbbGender.addItem("Nam");
        cbbGender.addItem("Nữ");
        cbbGender.setBounds(230,280,300,40);
        cbbGender.setBackground(new Color(247,249,249));
        
       
        JLabel lblAddress = new JLabel("Nhập địa chỉ SV: ");
        lblAddress.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblAddress.setBounds(20,340,250,40);
        
        JTextArea txtAddress= new JTextArea();
        txtAddress.setWrapStyleWord(true);
        txtAddress.setLineWrap(true);
        txtAddress.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        txtAddress.setBounds(230,340,300,80);
        txtAddress.setBorder(border);        
        
        JButton btnAdd = new JButton("Thêm");
        btnAdd.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
        btnAdd.setBounds(20,440,245,50);
        
        JButton btnCancel = new JButton("Trở lại");
        btnCancel.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
        btnCancel.setBounds(285,440,245,50);
        
        JLabel lblResult = new JLabel("");
        lblResult.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblResult.setBounds(20,520,400,40);
        lblResult.setForeground(Color.red);
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewStudentLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (StudentDAO.getStudentInfor(txtID.getText())!=null){
                    lblResult.setText("ID đã tồn tại, nhập ID khác!");
                }else{
                    if (!txtID.getText().equals("") && !txtName.getText().equals("") && !txtAddress.getText().equals("")){
                        Tblstudent student = new Tblstudent(txtID.getText(),txtName.getText(),txtID.getText(),cbbClass.getSelectedItem().toString(),cbbGender.getSelectedItem().toString(),txtAddress.getText());
                        TblestudentSubjectId id = new TblestudentSubjectId(student.getMaSv(),subject.getMaMh());
                        TblestudentSubject studentSubject = new TblestudentSubject(id,student,subject,"nothing");
                        StudentDAO.addStudent(student);
                        System.out.println(subject.getTenMh());
                        
                        if (Student_SubjectDAO.addStudent_Subject(studentSubject)){
                            lblResult.setText("Thêm thành công!");
                        }
                        else{
                            lblResult.setText("Thêm không thành công!");
                        } 
                    }
                    else{
                        lblResult.setText("Dữ liệu điền bị thiếu!");
                    }
                }
                
                
               
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(lblResult);
        container.add(btnCancel);
        container.add(btnAdd);
        container.add(jPanelHeader);
        container.add(lblID);
        container.add(txtID);
        container.add(lblName);
        container.add(txtName);
        container.add(lblClass);
        container.add(cbbClass);
        container.add(lblGender);
        container.add(cbbGender);
        container.add(lblAddress);
        container.add(txtAddress);
    }
    
}
