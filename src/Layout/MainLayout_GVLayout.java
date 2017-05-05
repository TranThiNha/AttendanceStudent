package Layout;

import DAO.StudentDAO;
import DAO.Student_SubjectDAO;
import DAO.SubjectDAO;
import Pojo.TblestudentSubject;
import Pojo.TblestudentSubjectId;
import Pojo.Tblstudent;
import Pojo.Tblsubject;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;

/**
 * Created by MyPC on 4/8/2017.
 */
public class MainLayout_GVLayout extends JFrame {
    Container container;
    JPanel jPanelHome;
    JPanel jPanelHeader;
    JScrollPane jPanelShowList;
    JPanel jPanelAddSubject;
    JPanel jPanelAddStudent;
    JScrollPane jScrollPaneShowStudentList;
    JPanel jpanelLogOut;
    public static Tblsubject addedSubject;
    public MainLayout_GVLayout(){
        container = this.getContentPane();
        container.setLayout(null);
        addedSubject = new Tblsubject();
        //-----------------------------SUB JPANEL------------------------------------------

        //header
        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,800,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("GIÁO VỤ");
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
 //-------------------------------------HOME---------------------------------------------
        jPanelHome = new JPanel();
        jPanelHome.setBounds(250,55,700,450);
        jPanelHome.setBackground(new Color(247,249,249));

        ImageIcon cover = new ImageIcon("cover.png");
        Image image = cover.getImage();
        Image newImage = image.getScaledInstance(150,120,Image.SCALE_SMOOTH);
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


 //---------------------------------ADD SUBJECT -----------------------------------------
        jPanelAddSubject = new JPanel();
        jPanelAddSubject.setBounds(250,55,700,450);
        jPanelAddSubject.setBackground(new Color(247,249,249));

        JLabel labelTitle = new JLabel("Tạo một môn học mới");
        labelTitle.setPreferredSize((new Dimension(600, 100)));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setForeground(new Color(21, 67, 96));
        labelTitle.setFont(new Font("Serif", Font.BOLD, 32));

        jPanelAddSubject.add(labelTitle);

        JLabel labelID = new JLabel("Nhập ID của môn học:");
        labelID.setForeground(new Color(33, 33, 33));
        labelID.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
        JTextArea textAreaID = new JTextArea();
        textAreaID.setPreferredSize(new Dimension(400,40));
        textAreaID.setMargin(new Insets(10,50,50,10));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textAreaID.setBorder(border);
        textAreaID.setFont(new Font("Serif", Font.ITALIC, 24));
        jPanelAddSubject.add(labelID);
        jPanelAddSubject.add(textAreaID);

        JLabel labelName = new JLabel("Nhập tên của môn học:");
        labelName.setForeground(new Color(33, 33, 33));
        labelName.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
        JTextArea textAreaName = new JTextArea();
        textAreaName.setPreferredSize(new Dimension(400,40));
        textAreaName.setBorder(border);
        textAreaName.setFont(new Font("Serif", Font.ITALIC, 24));
        jPanelAddSubject.add(labelName);
        jPanelAddSubject.add(textAreaName);    
        
        JButton btnAdd = new JButton("Thêm");
        jPanelAddSubject.add(btnAdd);
        btnAdd.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
                
        
        JButton btnAddTimeTable = new JButton("Thêm thời khóa biểu");
        jPanelAddSubject.add(btnAddTimeTable);
        btnAddTimeTable.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));     
        
        JLabel lblResult = new JLabel();
        lblResult.setPreferredSize(new Dimension(400,50));
        lblResult.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblResult.setForeground(Color.red);
        jPanelAddSubject.add(lblResult);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                if (textAreaID.getText().toString() !="" && textAreaName.getText().toString()!="")
                {
                    addedSubject.setMaMh(textAreaID.getText().toString());
                    addedSubject.setTenMh(textAreaName.getText().toString());
                    List<Tblsubject> temp = new ArrayList<>();
                    temp = SubjectDAO.getAllSubject();
                    boolean check = true;
                    for (int  i =0 ; i< temp.size();i++){
                        
                            if (temp.get(i).getMaMh().equalsIgnoreCase(textAreaID.getText()))
                            {
                                check = false;
                                lblResult.setText("Id đã tồn tại! vui lòng nhập id khác");
                            }
                       
                    }
                    
                    if (check == true)
                    {
                        lblResult.setText("");

                        if (SubjectDAO.addSubject(addedSubject)){
                         System.out.print("thành công");                        
                        }else{
                         System.out.print("không thành công");                        
                        
                        }
                    }
                    
                    
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        btnAddTimeTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTimeTableLayout layout = null;
                layout = new AddTimeTableLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(800, 600);
            }
        });

//--------------------------------------SHOW LIST SUBJECT------------------------------------
        //headers for the table
        String[] columns = new String[] {
                "Mã môn học", "Tên môn học"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                
        };


        final Class[] columnClass = new Class[] {
                Integer.class, String.class
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

        List<Tblsubject> subjectlist = new ArrayList<>();
        subjectlist = SubjectDAO.getAllSubject();
       
        for (int  i =0 ; i< subjectlist.size();i++){
            Object[] object = new Object[]{
                subjectlist.get(i).getMaMh(),subjectlist.get(i).getTenMh()
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

  //-----------------------------------ADD STUDENT------------------------------------------


        jPanelAddStudent = new JPanel();
        jPanelAddStudent.setBounds(250,55,700,450);
        jPanelAddStudent.setBackground(new Color(247,249,249));

        JLabel jLabelChooseSubject = new JLabel("Chọn môn học:");
        jLabelChooseSubject.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        jLabelChooseSubject.setPreferredSize((new Dimension(200, 30)));
        jLabelChooseSubject.setHorizontalAlignment(SwingConstants.RIGHT);

        JComboBox<String> cbbSubjectList = new JComboBox<>();
        cbbSubjectList.setBackground(new Color(247,249,249));
        cbbSubjectList.setPreferredSize(new Dimension(400,30));

        
        for (int  i =0 ; i< subjectlist.size();i++){
            
            cbbSubjectList.addItem(subjectlist.get(i).getTenMh());
            
        }
        
        
        
        JButton btnCheck = new JButton("Check từ list");
        btnCheck.setBackground(new Color(0, 151, 167));
        btnCheck.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        btnCheck.setPreferredSize(new Dimension(200,40));

        JButton btnCreateNew = new JButton("Tạo SV mới");
        btnCreateNew.setBackground(new Color(77, 208, 225));
        btnCreateNew.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        btnCreateNew.setPreferredSize(new Dimension(200,40));

        JButton btnImport = new JButton("Import từ CSV");
        btnImport.setBackground(new Color(144, 202, 249  ));
        btnImport.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        btnImport.setPreferredSize(new Dimension(200,40));


        //headers for the table
        columns = new String[] {
                "Mã số sinh viên", "Tên sinh viên","Lớp","Chọn"
        };

        //actual data for the table in a 2d array
        data = new Object[][] {           
        };

        final Class[] columnClass2 = new Class[] {
                String.class, String.class, String.class, Boolean.class
        };

        
        //create table model with data
        DefaultTableModel model2 = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                if (column == 3)
                return true;
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass2[columnIndex];
            }
        };
        List<Tblstudent> studentList = StudentDAO.getAllStudent();
        for (int  i = 0; i < studentList.size();i++){
             Object[] object = new Object[]{
                studentList.get(i).getMaSv(),studentList.get(i).getTenSv(),studentList.get(i).getTenLop()
            };
            model2.addRow(object); 
        }
        table = new JTable(model2);
        table.setRowHeight(30);
        table.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));

        jScrollPaneShowStudentList =  new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneShowStudentList.setPreferredSize(new Dimension(600,300));

        
        JButton btnAddCheckList = new JButton("Thêm");
        btnAddCheckList.setPreferredSize(new Dimension(100,40));
        btnAddCheckList.setForeground(new Color(41, 128, 185));
        
        btnAddCheckList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbSubjectList.getSelectedItem() != null)
                {
                    System.out.println("number: "+model2.getRowCount());
                    for (int  i = 0 ; i < model2.getRowCount(); i++){
                        System.out.println("row:" + i+ "------"+model2.getValueAt(i,3));
                        if (model2.getValueAt(i,3)!=null){
                            int index = cbbSubjectList.getSelectedIndex();
                            List<Tblsubject> subjectlistTemp = new ArrayList<>();
                            subjectlistTemp = SubjectDAO.getAllSubject();
                            Tblsubject sub = subjectlistTemp.get(index);
                            System.out.println(sub.getMaMh());
                            System.out.println(i + "-----"+ studentList.get(i).getTenSv());

                            TblestudentSubjectId id = new TblestudentSubjectId(studentList.get(i).getMaSv(),sub.getMaMh());
                            TblestudentSubject studentSubject = new TblestudentSubject(id,studentList.get(i),sub,"nothing");
                            if (Student_SubjectDAO.addStudent_Subject(studentSubject)){
                                System.out.println("thanh cong");
                            }
                            else{
                                System.out.println("khong thanh cong");
                            }

                        }
                    }
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        int index = cbbSubjectList.getSelectedIndex();
                List<Tblsubject> subjectlistTemp = new ArrayList<>();
                subjectlistTemp = SubjectDAO.getAllSubject();
                Tblsubject sub = subjectlistTemp.get(index);
                
        btnCreateNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                
                AddNewStudentLayout.subject = sub;
                AddNewStudentLayout layout = null;
                layout = new AddNewStudentLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(600, 600);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ImportCSVFormLayout.subject = sub;
                ImportCSVFormLayout layout = null;
                layout = new ImportCSVFormLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(800, 700);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jPanelAddStudent.add(jLabelChooseSubject);
        jPanelAddStudent.add(cbbSubjectList);
        jPanelAddStudent.add(btnCheck);
        jPanelAddStudent.add(btnCreateNew);
        jPanelAddStudent.add(btnImport);
        jPanelAddStudent.add(jScrollPaneShowStudentList);
        jPanelAddStudent.add(btnAddCheckList);

  //-----------------------------------Show Attendance---------------------------------------



//----------------------------------------BUTTON---------------------------------------------
        //home button
        ImageIcon iconHome = new ImageIcon("Home.png");
        image = iconHome.getImage();
        newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconHome = new ImageIcon(newImage);

        JButton btnHome= new JButton("Trang chủ");
        btnHome.setBackground(new Color(178, 223, 219));
        btnHome.setPreferredSize((new Dimension(210, 30)));
        btnHome.setIcon(iconHome);
        btnHome.setHorizontalAlignment(SwingConstants.LEFT);
        btnHome.setBounds(5,55,210,40);


        //add subject button
        ImageIcon iconAddSubject = new ImageIcon("AddSubject.png");
        image = iconAddSubject.getImage();
        newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconAddSubject = new ImageIcon(newImage);

        JButton btnAddSubject = new JButton("Thêm môn");
        btnAddSubject.setBackground(new Color(178, 223, 219));
        btnAddSubject.setBounds(5,115,210,40);
        btnAddSubject.setPreferredSize((new Dimension(200, 30)));
        btnAddSubject.setIcon(iconAddSubject);
        btnAddSubject.setHorizontalAlignment(SwingConstants.LEFT);


        //show list subject button
        ImageIcon iconShowListSubject = new ImageIcon("ListSubject.png");
        image = iconShowListSubject.getImage();
        newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconShowListSubject = new ImageIcon(newImage);

        JButton btnShowListSubject = new JButton("Xem Danh sách môn học");
        btnShowListSubject.setBackground(new Color(178, 223, 219));
        btnShowListSubject.setPreferredSize((new Dimension(200, 30)));
        btnShowListSubject.setIcon(iconShowListSubject);
        btnShowListSubject.setHorizontalAlignment(SwingConstants.LEFT);
        btnShowListSubject.setBounds(5,175,210,40);


        //add student button
        ImageIcon iconAddStudent = new ImageIcon("AddStudent.png");
        image = iconAddStudent.getImage();
        newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconAddStudent = new ImageIcon(newImage);


        JButton btnAddStudent = new JButton("Thêm Sinh viên vào môn học");
        btnAddStudent.setBackground(new Color(178, 223, 219));
        btnAddStudent.setBounds(5,235,210,40);
        btnAddStudent.setPreferredSize((new Dimension(200, 30)));
        btnAddStudent.setIcon(iconAddStudent);
        btnAddStudent.setHorizontalAlignment(SwingConstants.LEFT);


        //attendance button
        ImageIcon iconShowAttendance = new ImageIcon("Attendance.png");
        image = iconShowAttendance.getImage();
        newImage = image.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        iconShowAttendance = new ImageIcon(newImage);

        JButton btnShowAttendance = new JButton("Xem kết quả điểm danh");
        btnShowAttendance.setBackground(new Color(178, 223, 219));
        btnShowAttendance.setBounds(5,295,210,40);
        btnShowAttendance.setPreferredSize((new Dimension(200, 30)));
        btnShowAttendance.setIcon(iconShowAttendance);
        btnShowAttendance.setHorizontalAlignment(SwingConstants.LEFT);


        btnShowAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAttendanceLayout layout = null;
                layout = new ShowAttendanceLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(1200, 600);
            }
        });

        //-------------------------MAIN CONTAINER------------------------------------------

        
        
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginLayout layout = null;
                layout = new LoginLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(450, 400);
                MainLayout_GVLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnShowListSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanelShowList.setVisible(true);
                jPanelHome.setVisible(false);
                container.add(jPanelShowList);
                jPanelAddSubject.setVisible(false);
                jPanelAddStudent.setVisible(false);

            }
        });
        
        btnAddSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanelAddSubject.setVisible(true);
                container.add(jPanelAddSubject);
                jPanelHome.setVisible(false);
                jPanelShowList.setVisible(false);
                jPanelAddStudent.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                jPanelAddStudent.setVisible(true);
                jPanelAddSubject.setVisible(false);
                jPanelHome.setVisible(false);              
                jPanelShowList.setVisible(false);
                container.add(jPanelAddStudent);
               
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanelHome.setVisible(true);
                jPanelAddSubject.setVisible(false);
                jPanelAddStudent.setVisible(false);
                jPanelShowList.setVisible(false);
                container.add(jPanelHome);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        //container.add(jPanelAddStudent);
        container.add(jpanelLogOut);
        container.add(jPanelHeader);
        container.add(btnHome);
        container.add(btnAddSubject);
        container.add(btnShowListSubject);
        container.add(btnAddStudent);
        container.add(btnShowAttendance);
        container.add(jPanelHome);
    }
}
