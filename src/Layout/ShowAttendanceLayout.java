package Layout;

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
 * Created by MyPC on 4/10/2017.
 */
public class ShowAttendanceLayout extends JFrame {

    JScrollPane jScrollPaneAttendance;
    JPanel jPanelShowAttendance;
    JPanel jPanelHeader;
    Container container;
    boolean isEdit = true;
    List<Tblsubject> listSubject;
    JLabel lblNotify;
    public ShowAttendanceLayout(){

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
        
        listSubject = SubjectDAO.getAllSubject();
        
        for (int  i = 0 ;i < listSubject.size() ;i++){
            cbbSubject.addItem(listSubject.get(i).getTenMh());
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
                "MSSV", "Tuần 1", "Tuần 2","Tuần 3","Tuần 4","Tuần 5","Tuần 6","Tuần 7",
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

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Tblattendance> tempList = new ArrayList<>();
                
                List<Tblattendance> list = AttendanceDAO.getAllAttendance();

                for (int  i = 0 ; i < list.size(); i++){
                    if (list.get(i).getId().getMaMh().equalsIgnoreCase(listSubject.get(cbbSubject.getSelectedIndex()).getMaMh())){
                        tempList.add(list.get(i));
                    }
                }
                
                List<String> studentIdList = new ArrayList<String>();
                List<TblestudentSubject> studentList = Student_SubjectDAO.getAllStudent_Subject();
                
                for (int  i = 0 ; i < studentList.size(); i++){
                    if (studentList.get(i).getId().getMaMh().equals(listSubject.get(cbbSubject.getSelectedIndex()).getMaMh())){
                        studentIdList.add(studentList.get(i).getId().getMaSv());
                    }
                }
                
                ((DefaultTableModel)table.getModel()).setNumRows(0);
                
                for (int i= 0; i <studentIdList.size() ;i++){
                    List<Integer> resultList = getAttendanceResult(tempList,studentIdList.get(i));                    
                   Object[] object = new Object[]{
                            studentIdList.get(i),resultList.get(0),resultList.get(1),resultList.get(2)
                           ,resultList.get(3),resultList.get(4),resultList.get(5),resultList.get(6)
                           ,resultList.get(7),resultList.get(8),resultList.get(9),resultList.get(10)
                           ,resultList.get(11),resultList.get(12),resultList.get(13),resultList.get(14)
                        };
                    System.out.println(studentIdList.get(i));
                    ((DefaultTableModel)table.getModel()).addRow(object);
                }
                
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JButton btnBack = new JButton("Trở lại");
        btnBack.setBounds(10,510,250,40);
        btnBack.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        
        JButton btnEdit= new JButton("Chỉnh sửa");
        btnEdit.setBounds(930,510,250,40);
        btnEdit.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        
        lblNotify = new JLabel();
        lblNotify.setBounds(270, 510, 600, 40);
        lblNotify.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblNotify.setForeground(Color.red);
       btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEdit){
                    btnEdit.setText("Lưu");
                    
                    JComboBox<Integer>cbb = new JComboBox<>();
                    cbb.addItem(0);                    
                    cbb.addItem(1);
                    table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(cbb));                    
                    table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(11).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(12).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(14).setCellEditor(new DefaultCellEditor(cbb));
                    table.getColumnModel().getColumn(15).setCellEditor(new DefaultCellEditor(cbb));
                    
                    table.setEnabled(true);
 
                    
                    isEdit = false;
                }else{
                    btnEdit.setText("Chỉnh sửa");
                    updateAttendance(table, cbbSubject);
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAttendanceLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(lblNotify);
        container.add(btnEdit);
        container.add(btnSearch);
        container.add(cbbSubject);
        container.add(btnBack);
        container.add(lblSearch);
        container.add(lblNote);
        container.add(jPanelHeader);
        container.add(jScrollPaneAttendance);
    }


    public List<Integer> getAttendanceResult (List<Tblattendance> attendanceList, String studentId){
        List<Integer> resultList = new ArrayList<>();
        int  i =0;
        //mặc định sinh viên vắng
        while(i< 15){
            resultList.add(0);
            i++;
        }
        
        List<Tbltimetable> timetableList = TimetableDAO.getAllTimetable();
        Tbltimetable tbltimetable = null;
        for (int j = 0 ;j < timetableList.size();j++){
            if (timetableList.get(j).getMonHoc().equals(attendanceList.get(0).getId().getMaMh())){
                tbltimetable = timetableList.get(j);
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
            if (attendanceList.get(k).getId().getMaSv().equals(studentId)){
                long diffTemp = (long)(attendanceList.get(k).getId().getNgayDd().getTime() - beginDate.getTime());
                
                if (diffTemp/(7 * 24 * 60 * 60 * 1000) >=0 && attendanceList.get(k).getDaDd()== Boolean.TRUE){
                    resultList.set((int)(diffTemp/(7 * 24 * 60 * 60 * 1000)),1);
                }
                
            }
        }
        
        
        
        return resultList;
    }
    
    void updateAttendance(JTable table,JComboBox<String> cbbSubject){
        String maMH =  listSubject.get(cbbSubject.getSelectedIndex()).getMaMh();
        for (int  i = 0 ;i < table.getRowCount(); i++){
            for (int j = 1 ; j < 16 ;j++){                
                if ((int)(((DefaultTableModel)table.getModel()).getValueAt(i, j)) == 0){
                    TblattendanceId id = new TblattendanceId(table.getValueAt(i,0).toString(),maMH,getDate(maMH, j));
                    
                    if (AttendanceDAO.getAttendanceInfor(id)!=null){
                        if (AttendanceDAO.updateAttendance(new Tblattendance(id, Boolean.FALSE))){
                        lblNotify.setText("Chỉnh sửa thành công");
                        System.out.println("thanh cong");
                        }
                    }else{
                    
                        if (AttendanceDAO.addAttendance(new Tblattendance(id, Boolean.FALSE))){
                            lblNotify.setText("Chỉnh sửa thành công");
                            System.out.println("thanh cong");
                        }
                    }
                }else if ((int)(((DefaultTableModel)table.getModel()).getValueAt(i, j)) == 1){
                   TblattendanceId id = new TblattendanceId(table.getValueAt(i,0).toString(),maMH,getDate(maMH, j));
                   if (AttendanceDAO.getAttendanceInfor(id)!=null){
                        if (AttendanceDAO.updateAttendance(new Tblattendance(id, Boolean.TRUE))){
                        lblNotify.setText("Chỉnh sửa thành công");
                        System.out.println("thanh cong");
                        }
                    }else{
                    
                        if (AttendanceDAO.addAttendance(new Tblattendance(id, Boolean.TRUE))){
                            lblNotify.setText("Chỉnh sửa thành công");
                            System.out.println("thanh cong");
                        }
                    }
               }
            }
        }
    }

    Date getDate(String MaMH, int week){        
        Date result = new Date();
        
        List<Tbltimetable> list = TimetableDAO.getAllTimetable();
         Date beginDate = null;
         String thu = null;
        for (int  i = 0; i< list.size();i++){
            if (list.get(i).getMonHoc().equals(MaMH)){
                beginDate = list.get(i).getNgayBd();
                thu = list.get(i).getThu();
                break;
            }
        }
        
        if (beginDate.getDay() > getDay(thu)){
           return new Date(beginDate.getTime() + ((week- 1)* 7 + 7 - beginDate.getDay() +  getDay(thu))*24*60*60*1000);
        }else if(beginDate.getDay() == getDay(thu)){
           return new Date(beginDate.getTime() + ((week- 1)* 7)*24*60*60*1000);            
        }else{
           return new Date(beginDate.getTime() + ((week- 1)* 7 - beginDate.getDay() +  getDay(thu))*24*60*60*1000);           
        }
        
    }
    
    int getDay(String thu){
        if (thu.equalsIgnoreCase("Thứ hai")){
            return 1;
        }
        if (thu.equalsIgnoreCase("Thứ ba")){
            return 2;
        }if (thu.equalsIgnoreCase("Thứ tư")){
            return 3;
        }
        if (thu.equalsIgnoreCase("Thứ năm")){
            return 4;
        }
        if (thu.equalsIgnoreCase("Thứ sáu")){
            return 5;
        }
        if (thu.equalsIgnoreCase("Thứ bảy")){
            return 6;
        }
        if (thu.equalsIgnoreCase("Chủ nhật")){
            return 0;
        }
        else{
            return -1;
        }
    }
}
