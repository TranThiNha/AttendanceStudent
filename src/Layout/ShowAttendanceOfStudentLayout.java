/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

import javax.swing.JFrame;
import DAO.AttendanceDAO;
import DAO.Student_SubjectDAO;
import DAO.SubjectDAO;
import DAO.TimetableDAO;
import Pojo.Tblattendance;
import Pojo.TblattendanceId;
import Pojo.TblestudentSubject;
import Pojo.Tblstudent;
import Pojo.Tblsubject;
import Pojo.Tbltimetable;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author MyPC
 */
public class ShowAttendanceOfStudentLayout extends JFrame{
JScrollPane jScrollPaneAttendance;
    JPanel jPanelShowAttendance;
    JPanel jPanelHeader;
    Container container;
    boolean isEdit = true;
    List<TblestudentSubject> listSubject;
    JLabel lblNotify;
    public ShowAttendanceOfStudentLayout(){

        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(254, 249, 231  ));
        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,1200,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("KẾT QUẢ ĐIỂM DANH");
        labelHeader.setFont(new Font("Serif", Font.BOLD, 24));
        labelHeader.setForeground(new Color(255,255,255));
        jPanelHeader.add(labelHeader);


        JLabel lblSearch = new JLabel("Chọn môn học: ");
         lblSearch.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        lblSearch.setBounds(300,100,150,40);
        
        JComboBox<String>cbbSubject = new  JComboBox<>();
        List<TblestudentSubject> studentSubjectList = Student_SubjectDAO.getAllStudent_Subject();
        
        listSubject = new ArrayList<>();
        
        for (int  i = 0 ;i < studentSubjectList.size() ;i++){
            if (studentSubjectList.get(i).getId().getMaSv().equals(AttendanceLayout.currentAccount.getMaSv())){
                cbbSubject.addItem(SubjectDAO.getSubjectInfor(studentSubjectList.get(i).getId().getMaMh()).getTenMh());                
                listSubject.add(studentSubjectList.get(i));
            }
        }
        
        cbbSubject.setBounds(450, 100, 400, 40);
        cbbSubject.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        
        JButton btnSearch = new JButton("Thống kê");
        btnSearch.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        btnSearch.setBounds(900,100,150,40);
        
        
        
        JLabel lblNote = new JLabel("Chú thích: -1: không có dữ liệu, 0: vắng, 1: có đi học");
        lblNote.setBounds(200,150,600,30);
        lblNote.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblNote.setForeground(new Color(231, 76, 60 ));
        //headers for the table
        String[] columns = new String[] {
                "Mã môn học", "Tuần 1", "Tuần 2","Tuần 3","Tuần 4","Tuần 5","Tuần 6","Tuần 7",
                "Tuần 8","Tuần 9","Tuần 10","Tuần 11","Tuần 12","Tuần 13","Tuần 14","Tuần 15"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                
                };

        final Class[] columnClass3 = new Class[] {
                String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
                , Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
        };

        //create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return true;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass3[columnIndex];
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setEnabled(false);
        table.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

        jScrollPaneAttendance =  new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneAttendance.setBounds(10,200,1170,300);

        
        List<Tblattendance> tempList = new ArrayList<>();
                
                List<Tblattendance> list = AttendanceDAO.getAllAttendance();

                for (int  i = 0 ; i < list.size(); i++){
                    if (list.get(i).getId().getMaSv().equalsIgnoreCase(AttendanceLayout.currentAccount.getMaSv())){
                        tempList.add(list.get(i));
                    }
                }
                
                List<String> subjectIdList = new ArrayList<String>();
                List<TblestudentSubject> List = Student_SubjectDAO.getAllStudent_Subject();
                
                for (int  i = 0 ; i < List.size(); i++){
                    if (List.get(i).getId().getMaSv().equals(AttendanceLayout.currentAccount.getMaSv())){
                        subjectIdList.add(List.get(i).getId().getMaMh());
                    }
                }
                
                ((DefaultTableModel)table.getModel()).setNumRows(0);
                
                for (int i= 0; i <subjectIdList.size() ;i++){
                    List<Integer> resultList = getAttendanceResult(tempList,subjectIdList.get(i));                    
                   Object[] object = new Object[]{
                            subjectIdList.get(i),resultList.get(0),resultList.get(1),resultList.get(2)
                           ,resultList.get(3),resultList.get(4),resultList.get(5),resultList.get(6)
                           ,resultList.get(7),resultList.get(8),resultList.get(9),resultList.get(10)
                           ,resultList.get(11),resultList.get(12),resultList.get(13),resultList.get(14)
                        };
                    System.out.println(subjectIdList.get(i));
                    ((DefaultTableModel)table.getModel()).addRow(object);
                }
                
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                System.out.println("----------zô ne-------------");
                ((DefaultTableModel)table.getModel()).setNumRows(0);
                
                  System.out.println("cbbSubject.getSelectedIndex(): "+cbbSubject.getSelectedIndex());
                
                  List<Integer> resultList = getAttendanceResult(tempList,listSubject.get(cbbSubject.getSelectedIndex()).getId().getMaMh());                    
                  System.out.println("cbbSubject.getSelectedIndex(): "+cbbSubject.getSelectedIndex());
                   Object[] object = new Object[]{
                            listSubject.get(cbbSubject.getSelectedIndex()).getId().getMaMh(),resultList.get(0),resultList.get(1),resultList.get(2)
                           ,resultList.get(3),resultList.get(4),resultList.get(5),resultList.get(6)
                           ,resultList.get(7),resultList.get(8),resultList.get(9),resultList.get(10)
                           ,resultList.get(11),resultList.get(12),resultList.get(13),resultList.get(14)
                        };
                    ((DefaultTableModel)table.getModel()).addRow(object);
                
                
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JButton btnBack = new JButton("Trở lại");
        btnBack.setBounds(10,510,250,40);
        btnBack.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        
         
        
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAttendanceOfStudentLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(btnSearch);
        container.add(cbbSubject);
        container.add(btnBack);
        container.add(lblSearch);
        container.add(lblNote);
        container.add(jPanelHeader);
        container.add(jScrollPaneAttendance);
    }


    public List<Integer> getAttendanceResult (List<Tblattendance> attendanceList, String subjectId){
        List<Integer> resultList = new ArrayList<>();
        int  i =0;
        //mặc định sinh viên vắng
        while(i< 15){
            resultList.add(0);
            i++;
        }
        System.out.println("ID: "+ subjectId);
        
        List<Tbltimetable> timetableList = TimetableDAO.getAllTimetable();
        Tbltimetable tbltimetable = null;
        for (int j = 0 ;j < timetableList.size();j++){
            if (timetableList.get(j).getMonHoc().equals(subjectId)){
                tbltimetable = timetableList.get(j);
                System.out.println("có nè");
                break;
            }
        }
        
        
        Date beginDate = tbltimetable.getNgayBd();
        Date endDate = tbltimetable.getNgayKt();
        Date currentDate = new Date();
        long diff = (long)(currentDate.getTime() - beginDate.getTime());
        
        //những tuần chưa tới xem như chưa có dữ liệu
        if ( (long)diff / (7 * 24 * 60 * 60 * 1000) <= 14 && (long)diff / (7 * 24 * 60 * 60 * 1000) >= 0){
            for (int  j = (int) (diff / (7 * 24 * 60 * 60 * 1000)) ;j < 15; j++){
                resultList.set(j,-1);
            }
        }
        
        //kiểm tra tuần nào điểm danh để đánh dấu có đi học
        for (int  k =0 ; k < attendanceList.size() ;k++){
            if (attendanceList.get(k).getId().getMaSv().equals(AttendanceLayout.currentAccount.getMaSv()) && attendanceList.get(k).getId().getMaMh().equals(subjectId)){
                long diffTemp = (long)(attendanceList.get(k).getId().getNgayDd().getTime() - beginDate.getTime());               
                if (diffTemp/(7 * 24 * 60 * 60 * 1000) >=0 && attendanceList.get(k).getDaDd()== Boolean.TRUE){
                    resultList.set((int)(diffTemp/(7 * 24 * 60 * 60 * 1000)),1);
                }
                
            }
        }
        
        
        
        return resultList;
    }
    
   
}    
    

