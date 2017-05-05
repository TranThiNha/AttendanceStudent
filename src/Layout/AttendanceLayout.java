package Layout;

import DAO.AttendanceDAO;
import DAO.Student_SubjectDAO;
import DAO.SubjectDAO;
import Pojo.Tblsubject;
import net.sourceforge.jdatepicker.impl.JDatePanel_Calendar;
import java.util.List;
import DAO.SubjectDAO;
import DAO.TimetableDAO;
import Pojo.Tblattendance;
import Pojo.TblattendanceId;
import Pojo.TblestudentSubject;
import Pojo.Tblstudent;
import Pojo.Tbltimetable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;

/**
 * Created by MyPC on 4/10/2017.
 */
public class AttendanceLayout extends JFrame {

    Container container;
    JPanel jPanelHeader;
    public static Tblstudent currentAccount;
    public AttendanceLayout(){
        container = this.getContentPane();
        container.setLayout(null);

        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,600,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("ĐIỂM DANH");
        labelHeader.setFont(new Font("Serif", Font.BOLD, 24));
        labelHeader.setForeground(new Color(255,255,255));
        jPanelHeader.add(labelHeader);

         //headers for the table
        String[] columns = new String[] {
                "Môn học", "Điểm danh"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                
        };

        final Class[] columnClass = new Class[] {
                String.class, Boolean.class
        };

        //create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                if (column == 1)
                    return true;
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setMaxWidth(150);
        table.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));
        
        JLabel label = new JLabel("Thời gian hiện tại: ");
        label.setBounds(20, 100,150,40);
        label.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

        JLabel lblTime = new JLabel();
        lblTime.setBounds(180,100,350,40);
        lblTime.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
        lblTime.setForeground(new Color(231, 76, 60));
        ActionListener actionDate = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datePattern = "dd/MM/yyyy";
                SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
                Date currentDate = new Date();
                lblTime.setText(currentDate.getHours() + ":" +currentDate.getMinutes() + ":" + currentDate.getSeconds()
                        + " Ngày "+dateFormatter.format(currentDate));             
                
                    
                }
                //model.fireTableDataChanged();
            
        };
        new Timer(1000, actionDate).start();
        Date currentDate = new Date();

        List<Tbltimetable> list = TimetableDAO.getAllTimetable();
        List<Tblsubject> currentlist = new ArrayList<>();
                ((DefaultTableModel)table.getModel()).setRowCount(0);
                for (int  i = 0; i <list.size();i++){
                    long beginTime = list.get(i).getGioBd().getHours()*60 +list.get(i).getGioBd().getMinutes();
                    long endTime = list.get(i).getGioKt().getHours()*60 +list.get(i).getGioKt().getMinutes();
                    long currentTime = currentDate.getHours()*60 + currentDate.getMinutes();
                    
                   if (currentTime >= beginTime && currentTime <= endTime && currentDate.getDay() == getThu(list.get(i).getThu())){
                        System.out.println("zô r nè");
                        Tblsubject subject = SubjectDAO.getSubjectInfor(list.get(i).getMonHoc());
                        currentlist.add(subject);
                        Object[] object = new Object[]{
                            subject.getTenMh()
                        };
                        System.out.println("size: "+currentlist.size());
                        ((DefaultTableModel)table.getModel()).addRow(object);
                        TblattendanceId tblattendanceId = new TblattendanceId(currentAccount.getMaSv(),list.get(i).getMonHoc(),currentDate);
                        
                        if (AttendanceDAO.getAttendanceInfor(tblattendanceId)!=null){
                            ((DefaultTableModel)table.getModel()).setValueAt(Boolean.TRUE, ((DefaultTableModel)table.getModel()).getRowCount()-1, 1);
                        }
                    }
                       //model.addRow(object); 
                    }
        
        JLabel label1 = new JLabel("Những môn đang được học: ");
        label1.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        label1.setBounds(20,150,300,40);        

        JScrollPane jPanelShowList = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanelShowList.setBounds(20,200,550,400);
        jPanelShowList.setBackground(new Color(247,249,249));

        JButton btnAttendance = new JButton("Submit");
        btnAttendance.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        btnAttendance.setBounds(240,610,200,40);
        
        JButton btnCancel = new JButton("Trở lại");
        btnCancel.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        btnCancel.setBounds(20,610,200,40);
        
        btnAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberRow = ((DefaultTableModel)table.getModel()).getRowCount();
                for (int  i = 0; i <numberRow ;i++){
                    if (((DefaultTableModel)table.getModel()).getValueAt(i,1)!=null){
                        TblattendanceId tblattendanceId = new TblattendanceId(currentAccount.getMaSv(),currentlist.get(i).getMaMh(),currentDate);
                        Tblattendance tblattendance = new Tblattendance(tblattendanceId,Boolean.TRUE);
                        if (AttendanceDAO.addAttendance(tblattendance)){
                            System.out.println("thêm điểm danh thành công");
                        }
                        
                    }
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendanceLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(btnCancel);
        container.add(btnAttendance);
        container.add(jPanelShowList);
        container.add(label1);
        container.add(lblTime);
        container.add(label);
        container.add(jPanelHeader);
    }

    int getThu(String thu){
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
