/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

import DAO.Student_SubjectDAO;
import DAO.SubjectDAO;
import DAO.TimetableDAO;
import Pojo.TblestudentSubject;
import Pojo.Tblstudent;
import Pojo.Tblsubject;
import Pojo.Tbltimetable;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MyPC
 */
public class MainStudentLayout extends JFrame{
    
    JPanel jPanelHeader;
    JPanel jpanelLogOut;
    Container container;
    JPanel jPanelHome;
    JScrollPane jPanelShowList;
    public  MainStudentLayout(){
        
        container = this.getContentPane();
        container.setLayout(null);
        
        //header
        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,800,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("SINH VIÊN");
        labelHeader.setFont(new Font("Serif", Font.BOLD, 24));
        labelHeader.setPreferredSize(new Dimension(700,50));
        labelHeader.setHorizontalAlignment(SwingConstants.LEFT);
        labelHeader.setForeground(new Color(255,255,255));
        jPanelHeader.add(labelHeader);

        
        jpanelLogOut = new JPanel();   
        jpanelLogOut.setBounds(800,0,200,50);
        jpanelLogOut.setBackground(new Color(11,83,69));
        
        JButton btnLogOut = new JButton("Đăng xuất");
        btnLogOut.setFont(new Font("Serif", Font.BOLD, 18));
        btnLogOut.setBackground(new Color(11,83,69));
        btnLogOut.setPreferredSize(new Dimension(150,25));
        btnLogOut.setVerticalAlignment(SwingConstants.CENTER);
        btnLogOut.setForeground(Color.WHITE);
        jpanelLogOut.add(btnLogOut);
        
         //home button
        ImageIcon iconHome = new ImageIcon("Home.png");
        Image image = iconHome.getImage();
        Image newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconHome = new ImageIcon(newImage);

        JButton btnHome= new JButton("Trang chủ");
        btnHome.setBackground(new Color(178, 223, 219));
        btnHome.setPreferredSize((new Dimension(210, 30)));
        btnHome.setIcon(iconHome);
        btnHome.setHorizontalAlignment(SwingConstants.LEFT);
        btnHome.setBounds(5,55,210,40);
        
        ImageIcon iconAttendanceOnline = new ImageIcon("AttendanceOnline.png");
        image = iconAttendanceOnline.getImage();
        newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconAttendanceOnline = new ImageIcon(newImage);
        
        JButton btnAttendance = new JButton("Điểm danh online");
        btnAttendance.setBackground(new Color(178, 223, 219));
        btnAttendance.setBounds(5,115,210,40);
        btnAttendance.setPreferredSize((new Dimension(200, 30)));
        btnAttendance.setIcon(iconAttendanceOnline);
        btnAttendance.setHorizontalAlignment(SwingConstants.LEFT);
        
        ImageIcon iconTimetable= new ImageIcon("timetable.png");
        image = iconTimetable.getImage();
        newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconTimetable = new ImageIcon(newImage);
        
        JButton btnShowTimetable= new JButton("Xem thời khóa biểu");
        btnShowTimetable.setBackground(new Color(178, 223, 219));
        btnShowTimetable.setBounds(5,175,210,40);
        btnShowTimetable.setPreferredSize((new Dimension(200, 30)));
        btnShowTimetable.setIcon(iconTimetable);
        btnShowTimetable.setHorizontalAlignment(SwingConstants.LEFT);
        
        //attendance button
        ImageIcon iconShowAttendance = new ImageIcon("Attendance.png");
        image = iconShowAttendance.getImage();
        newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconShowAttendance = new ImageIcon(newImage);

        JButton btnShowAttendance = new JButton("Xem kết quả điểm danh");
        btnShowAttendance.setBackground(new Color(178, 223, 219));
        btnShowAttendance.setBounds(5,235,210,40);
        btnShowAttendance.setPreferredSize((new Dimension(200, 30)));
        btnShowAttendance.setIcon(iconShowAttendance);
        btnShowAttendance.setHorizontalAlignment(SwingConstants.LEFT);
        
        jPanelHome = new JPanel();
        jPanelHome.setBounds(250,55,700,450);
        jPanelHome.setBackground(new Color(247,249,249));

        ImageIcon cover = new ImageIcon("cover.png");
        image = cover.getImage();
        newImage = image.getScaledInstance(150,120,Image.SCALE_SMOOTH);
        cover = new ImageIcon(newImage);

        JLabel label1 = new JLabel();
        label1.setIcon(cover);
        label1.setBounds(5,15, 500, 100);

        JLabel label2 = new JLabel("Chào mừng bạn đến với ứng dụng điểm danh");
        label2.setForeground(new Color(198, 40, 20));
        label2.setFont(new Font("Serif", Font.BOLD, 36));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.TOP);
        label2.setBounds(5,200, 700, 100);

        jPanelHome.add(label1);
        jPanelHome.add(label2);
        
        
        String[] columns = new String[] {
            "Mã thời khóa biểu", "Môn học","Ngày bắt đầu","Ngày kết thúc","Giờ bắt đầu","Giờ kết thúc","Phòng học"
        };

                //actual data for the table in a 2d array
                Object[][] data = new Object[][] {

                };


                final Class[] columnClass = new Class[] {
                        String.class,String.class,String.class,String.class,String.class,String.class,String.class
                };

                //create table model with data
                DefaultTableModel model = new DefaultTableModel(data, columns) {

                    @Override
                    public boolean isCellEditable(int row, int column)
                    {
                        return false;
                    }

                    @Override
                    public Class<?> getColumnClass(int columnIndex)
                    {
                        return columnClass[columnIndex];
                    }
                };

               
                List<Tbltimetable>list = getTimetableList(); 
                
                for (int i = 0 ;i < list.size();i++){
                     Object[] object = new Object[]{
                        list.get(i).getMaTkb(),SubjectDAO.getSubjectInfor(list.get(i).getMonHoc()).getTenMh(),new SimpleDateFormat("MM-dd-yyyy").format(list.get(i).getNgayBd()),new SimpleDateFormat("MM-dd-yyyy").format(list.get(i).getNgayKt()),new SimpleDateFormat("hh:mm:ss").format(list.get(i).getGioBd()),new SimpleDateFormat("hh:mm:ss").format(list.get(i).getGioKt()),list.get(i).getPhongHoc()
                     };
                      model.addRow(object);
                }             

                JTable table = new JTable(model);
                table.getColumnModel().getColumn(0).setMaxWidth(200);
                table.setRowHeight(30);
                table.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));

                jPanelShowList = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jPanelShowList.setBounds(250,55,700,450);
                jPanelShowList.setBackground(new Color(247,249,249));
                
        
        btnAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendanceLayout layout = null;
                layout = new AttendanceLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(600, 700); 
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnShowTimetable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {           
                
                jPanelShowList.setVisible(true);
                jPanelHome.setVisible(false);
                container.add(jPanelShowList);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginLayout layout = null;
                layout = new LoginLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(450, 400);
                MainStudentLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                jPanelHome.setVisible(true);
                container.add(jPanelHome);
               jPanelShowList.setVisible(false);
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnShowAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAttendanceOfStudentLayout layout = null;
                layout = new ShowAttendanceOfStudentLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(1200, 600);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(btnShowAttendance);
        container.add(jPanelHome);
        container.add(btnHome);
        container.add(btnAttendance);
        container.add(btnShowTimetable);
        container.add(jPanelHeader);
        container.add(jpanelLogOut);
    }
    
    List<Tbltimetable> getTimetableList(){
        List<Tbltimetable> resultList= new ArrayList<>();
        List<Tbltimetable> timetableList = new ArrayList<>();
        timetableList = TimetableDAO.getAllTimetable();
        
        for (int  i = 0; i < timetableList.size();i++){
            if (checkExist(timetableList.get(i))){
                resultList.add(timetableList.get(i));
            }
        }
        
        return resultList;
    }
    
    boolean checkExist(Tbltimetable tbltimetable){
        List<TblestudentSubject> list= getListStudentSubjectOfStudent();
        
        for (int  i = 0; i <list.size() ;i++){
            if (list.get(i).getId().getMaMh().equals(tbltimetable.getMonHoc())){
                return true;
            }
        }
        
        return false;
    }
    
    List<TblestudentSubject> getListStudentSubjectOfStudent(){
        List<TblestudentSubject> result = new ArrayList<>();
        Tblstudent student = AttendanceLayout.currentAccount;
        List<TblestudentSubject> studentSubjectList = Student_SubjectDAO.getAllStudent_Subject();
        for (int  i = 0 ; i <studentSubjectList.size() ; i ++){
            if (studentSubjectList.get(i).getId().getMaSv().equals(student.getMaSv())){
                result.add(studentSubjectList.get(i));
            }
        }
        return result;
    }
}
