package Layout;

import DAO.TimetableDAO;
import Pojo.Tbltimetable;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanel_Calendar;
import net.sourceforge.jdatepicker.impl.JDatePicker_Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by MyPC on 4/9/2017.
 */
public class AddTimeTableLayout extends JFrame {
    Container container;
    JPanel jPanelHeader;
    Tbltimetable timetable;
    public AddTimeTableLayout(){
        timetable = new Tbltimetable();
        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(247,249,249));
        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,800,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("THÊM THỜI KHÓA BIỂU");
        labelHeader.setFont(new Font("Serif", Font.BOLD, 24));
        labelHeader.setForeground(new Color(255,255,255));
        jPanelHeader.add(labelHeader);


        JLabel jLabelStartDate = new JLabel("Ngày bắt đầu: ");
        jLabelStartDate.setBounds(10,70,140,30);
        jLabelStartDate.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
         JDatePanel_Calendar jDatePanelStartDate = new JDatePanel_Calendar();
        jDatePanelStartDate.setBounds(140,60,200,180);
        Date temp = new Date();
       jDatePanelStartDate.setValue(jDatePanelStartDate.getValue());
        temp = jDatePanelStartDate.getValue().getTime();
        timetable.setNgayBd(temp);
        jDatePanelStartDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date temp = new Date();
                jDatePanelStartDate.setValue(jDatePanelStartDate.getValue());
                temp = jDatePanelStartDate.getValue().getTime();
                timetable.setNgayBd(temp);
                System.out.println(temp.toString());
            }
        });

        
        JLabel jLabelEndDate = new JLabel("Ngày kết thúc: ");
        jLabelEndDate.setBounds(380,70,140,30);
        jLabelEndDate.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        JDatePanel_Calendar jDatePanelEndDate = new JDatePanel_Calendar();
        jDatePanelEndDate.setBounds(510,60,200,180);
        jDatePanelEndDate.setValue(jDatePanelEndDate.getValue());
        temp = jDatePanelEndDate.getValue().getTime();
        timetable.setNgayKt(temp);
        jDatePanelEndDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date temp = new Date();
                jDatePanelEndDate.setValue(jDatePanelEndDate.getValue());
                temp = jDatePanelEndDate.getValue().getTime();
                timetable.setNgayKt(temp);
                System.out.println(temp.toString());
            }
        });

        JLabel labelThu = new JLabel("Nhập thứ:");
        labelThu.setBounds(10,280,100,30);
        labelThu.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));


        JComboBox<String> cbbDate = new JComboBox<>();
        cbbDate.addItem("Thứ hai");
        cbbDate.addItem("Thứ ba");
        cbbDate.addItem("Thứ tư");
        cbbDate.addItem("Thứ năm");
        cbbDate.addItem("Thứ sáu");
        cbbDate.addItem("Thứ bảy");
        cbbDate.addItem("Chủ nhật");
        cbbDate.setBounds(110,280,150,30);
        cbbDate.setBackground(new Color(247,249,249));

        JLabel labelPhong = new JLabel("Nhập phòng học:");
        labelPhong.setBounds(280,280,150,30);
        labelPhong.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));


        JComboBox<String> cbbPhong = new JComboBox<>();
        cbbPhong.addItem("C22");
        cbbPhong.addItem("C23");
        cbbPhong.addItem("C32");
        cbbPhong.addItem("C33");
        cbbPhong.addItem("C42");
        cbbPhong.addItem("C43");
        cbbPhong.addItem("E101");
        cbbPhong.addItem("E102");
        cbbPhong.addItem("E103");
        cbbPhong.addItem("E104");
        cbbPhong.addItem("E201");
        cbbPhong.addItem("E202");
        cbbPhong.addItem("E203");
        cbbPhong.addItem("E204");
        cbbPhong.addItem("E301");
        cbbPhong.addItem("E302");
        cbbPhong.addItem("E303");
        cbbPhong.addItem("E304");
        cbbPhong.addItem("E401");
        cbbPhong.addItem("E402");
        cbbPhong.addItem("E403");
        cbbPhong.addItem("E404");
        cbbPhong.addItem("F101");
        cbbPhong.addItem("F102");
        cbbPhong.addItem("F103");
        cbbPhong.addItem("F104");
        cbbPhong.addItem("F201");
        cbbPhong.addItem("F202");
        cbbPhong.addItem("F203");
        cbbPhong.addItem("F204");
        cbbPhong.addItem("F301");
        cbbPhong.addItem("F302");
        cbbPhong.addItem("F303");
        cbbPhong.addItem("F304");

        cbbPhong.setBounds(430,280,150,30);
        cbbPhong.setBackground(new Color(247,249,249));

        JLabel labelStartTime = new JLabel("Nhập Giờ bắt đầu:");
        labelStartTime.setBounds(10,350,150,30);
        labelStartTime.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        SpinnerNumberModel spinnerHourModel = new SpinnerNumberModel(0,0,23,1);
        JSpinner spinnerHour = new JSpinner(spinnerHourModel);
        spinnerHour.setBounds(160,350,60,30);
        spinnerHour.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        SpinnerNumberModel spinnerMinuteModel = new SpinnerNumberModel(0,0,59,1);
        JSpinner spinnerMinute = new JSpinner(spinnerMinuteModel);
        spinnerMinute.setBounds(225,350,60,30);
        spinnerMinute.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        SpinnerNumberModel spinnerSecondModel = new SpinnerNumberModel(0,0,59,1);
        JSpinner spinnerSecond = new JSpinner(spinnerSecondModel);
        spinnerSecond.setBounds(290,350,60,30);
        spinnerSecond.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        JLabel labelEndTime = new JLabel("Nhập Giờ kết thúc:");
        labelEndTime.setBounds(380,350,150,30);
        labelEndTime.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        SpinnerNumberModel spinnerHourMode2 = new SpinnerNumberModel(0,0,23,1);
        JSpinner spinnerHour2 = new JSpinner(spinnerHourMode2);
        spinnerHour2.setBounds(530,350,60,30);
        spinnerHour2.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        SpinnerNumberModel spinnerMinuteModel2 = new SpinnerNumberModel(0,0,59,1);
        JSpinner spinnerMinute2 = new JSpinner(spinnerMinuteModel2);
        spinnerMinute2.setBounds(595,350,60,30);
        spinnerMinute2.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        SpinnerNumberModel spinnerSecondModel2 = new SpinnerNumberModel(0,0,59,1);
        JSpinner spinnerSecond2 = new JSpinner(spinnerSecondModel2);
        spinnerSecond2.setBounds(660,350,60,30);
        spinnerSecond2.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        JLabel lblID = new JLabel("Nhập ID:");
        lblID.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        lblID.setBounds(80,400,80,40);
        
        JTextArea txtID = new JTextArea();
        txtID.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        txtID.setBounds(160,400,300,40);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        txtID.setBorder(border);
        
        JButton btnOk = new JButton("OK");
        btnOk.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        btnOk.setBounds(350,470,150,40);

        JLabel lblResult = new JLabel();
        lblResult.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblResult.setBounds(20,470,320,40);
        lblResult.setForeground(Color.red);
        
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               long diff =  (long) (timetable.getNgayKt().getTime() - timetable.getNgayBd().getTime());
                if ((long)diff / (7 * 24 * 60 * 60 * 1000) < 15){
                     lblResult.setText((long)diff / (7 * 24 * 60 * 60 * 1000)+ " tuần -> Khoảng cách thời gian chưa đủ 15 tuần!");
                }
                else{
                    
                    lblResult.setText("");
                if (MainLayout_GVLayout.addedSubject.getMaMh()!=null && !txtID.getText().equals("")){
                   
                    List<Tbltimetable> list = new ArrayList<>();
                    list = TimetableDAO.getAllTimetable();
                    boolean check = true;
                    for (int  i =0; i< list.size();i++){
                        if (list.get(i).getMaTkb().equalsIgnoreCase(txtID.getText())){
                            check = false;
                            lblResult.setText("ID đã tồn tại, vui lòng nhập lại");
                        }
                    }
                    
                    if (check){
                        timetable.setThu(cbbDate.getSelectedItem().toString());
                        timetable.setMaTkb(txtID.getText());
                        timetable.setMonHoc(MainLayout_GVLayout.addedSubject.getMaMh());
                        Date beginDate = new Date();
                        beginDate.setHours((Integer)spinnerHour.getValue());
                        beginDate.setMinutes((Integer)spinnerMinute.getValue());
                        beginDate.setSeconds((Integer)spinnerSecond.getValue());
                        
                        timetable.setGioBd(beginDate);
                        
                        Date EndDate = new Date();
                        EndDate.setHours((Integer)spinnerHour2.getValue());
                        EndDate.setMinutes((Integer)spinnerMinute2.getValue());
                        EndDate.setSeconds((Integer)spinnerSecond2.getValue());
                        
                        timetable.setGioKt(EndDate);
                        
                        timetable.setPhongHoc(cbbPhong.getSelectedItem().toString());
                        timetable.setSoTuan(15);
                        
                        if (timetable.getGioKt().getHours() < timetable.getGioBd().getHours() ){
                            lblResult.setText("Thời gian bắt đầu không được trễ hơn thời gian kết thúc");                         
                        }
                        else if (timetable.getGioKt().getHours() == timetable.getGioBd().getHours() && timetable.getGioKt().getMinutes() <timetable.getGioBd().getMinutes() ){
                            lblResult.setText("Thời gian bắt đầu không được trễ hơn thời gian kết thúc");                             
                        }
                        else{
                            TimetableDAO.addTimetable(timetable); 
                            lblResult.setText("Thêm thành công");
                        }
                        
                    }
                    else{
                        lblResult.setText("");

                    }

              
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
                else{
                    lblResult.setText("Dữ liệu rỗng");

                }
            }
            }
        });
        
        
        JButton btnCANCEL = new JButton("Trở lại");
        btnCANCEL.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        btnCANCEL.setBounds(530,470,150,40);

        btnCANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTimeTableLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(lblResult);        
        container.add(lblID);
        container.add(txtID);
        container.add(btnOk);
        container.add(btnCANCEL);
        container.add(spinnerSecond2);
        container.add(spinnerMinute2);
        container.add(spinnerHour2);
        container.add(labelEndTime);
        container.add(spinnerSecond);
        container.add(spinnerMinute);
        container.add(spinnerHour);
        container.add(labelStartTime);
        container.add(cbbDate);
        container.add(labelThu);
        container.add(cbbPhong);
        container.add(labelPhong);
        container.add(jLabelEndDate);
        container.add(jDatePanelStartDate);
        container.add(jDatePanelEndDate);
        container.add(jPanelHeader);
        container.add(jLabelStartDate);
    }
}
