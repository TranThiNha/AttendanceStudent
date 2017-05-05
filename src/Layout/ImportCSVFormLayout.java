/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

import DAO.StudentDAO;
import DAO.Student_SubjectDAO;
import DAO.SubjectDAO;
import static Layout.AddNewStudentLayout.subject;
import Pojo.TblestudentSubject;
import Pojo.TblestudentSubjectId;
import Pojo.Tblstudent;
import Pojo.Tblsubject;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.ERROR;
import static java.awt.image.ImageObserver.SOMEBITS;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MyPC
 */
public class ImportCSVFormLayout  extends  JFrame{
 
    Container container;
    JPanel jPanelHeader;
    JScrollPane panelTable;
    public static Tblsubject subject;
    public ImportCSVFormLayout(){
        
        container = this.getContentPane();
        container.setLayout(null);
        jPanelHeader = new JPanel();
        jPanelHeader.setBounds(0,0,800,50);
        jPanelHeader.setBackground(new Color(11,83,69));
        JLabel labelHeader = new JLabel("CSV TEMPLATE");
        labelHeader.setFont(new Font("Serif", Font.BOLD, 24));
        labelHeader.setForeground(new Color(255,255,255));
        jPanelHeader.add(labelHeader);
        
        
        //headers for the table
        String[] columns = new String[] {
                "Mã SV", "Tên SV","Tên lớp","Giới tính","Địa chỉ"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}
        };


        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class, String.class, String.class
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
                return columnClass[columnIndex];
            }
        };

        
        
        JComboBox<String> cbbClass = new JComboBox();
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
        
        JComboBox<String> cbbGender = new JComboBox<>();
        cbbGender.addItem("Nam");
        cbbGender.addItem("Nữ");
        
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.setRowHeight(30);
        table.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));

        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbbClass));
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbbGender));

        panelTable = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelTable.setBounds(20,70,750,500);
        panelTable.setBackground(new Color(247,249,249));
        
        JButton btnSave = new JButton("Lưu");
        btnSave.setBounds(620, 590, 150, 40);
        btnSave.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
        btnSave.setBackground(new Color(88, 214, 141));
        
        JLabel lblResult = new JLabel();
        lblResult.setBounds(190, 590, 350, 40);
        lblResult.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
        lblResult.setForeground(Color.red);
        
        JButton btnCancel = new JButton("Trở lại");
        btnCancel.setBounds(20, 590, 150, 40);
        btnCancel.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
        btnCancel.setBackground(new Color(250, 215, 160));
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < table.getRowCount() ; i++){
                    if (checkIfAcceptableRow(i, 5, table)==1 && checkIdNotExist(((DefaultTableModel)table.getModel()).getValueAt(i, 0).toString())){
                        Tblstudent student = getStudent(i, table);
                        try {
                            exportToCsvFile("studentlist.csv", student);
                        } catch (IOException ex) {
                            Logger.getLogger(ImportCSVFormLayout.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    else if (checkIfAcceptableRow(i, 5, table)== 0){
                        lblResult.setText("Hãy đảm bảo điền đủ thông tin!");
                    }else if(checkIfAcceptableRow(i, 5, table)==1 && !checkIdNotExist(((DefaultTableModel)table.getModel()).getValueAt(i, 0).toString())){
                        lblResult.setText("Mã Sinh viên đã tồn tại!");
                    }
                    
                }
                
                try {
                    List<Tblstudent> list = importFromCsvFile("studentlist.csv");
                    System.out.println("so luong sinh vien: " + list.size());
                    for (int i = 0 ;i<list.size() ;i++){
                        Tblstudent student = list.get(i);
                        StudentDAO.addStudent(student);
                        System.out.println("zô rồi nè: "+ list.get(i).getTenSv());
                        TblestudentSubjectId id = new TblestudentSubjectId(student.getMaSv(),subject.getMaMh());
                        TblestudentSubject studentSubject = new TblestudentSubject(id,student,subject,"nothing");                       
                        
                        if (Student_SubjectDAO.addStudent_Subject(studentSubject)){
                            lblResult.setText("Thêm không thành công!");
                            ((DefaultTableModel)table.getModel()).setNumRows(0);
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                            ((DefaultTableModel)table.getModel()).addRow(new  Object[]{});
                        }
                    }
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(ImportCSVFormLayout.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImportCSVFormLayout.this.setVisible(false);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        container.add(lblResult);
        container.add(btnSave);
        container.add(btnCancel);
        container.add(panelTable);
        container.add(jPanelHeader);
    }
    
    Tblstudent getStudent(int row, JTable table){
        String id = ((DefaultTableModel)table.getModel()).getValueAt(row, 0).toString();
        String name = ((DefaultTableModel)table.getModel()).getValueAt(row, 1).toString();
        String inClass = ((DefaultTableModel)table.getModel()).getValueAt(row, 2).toString();
        String gender = ((DefaultTableModel)table.getModel()).getValueAt(row, 3).toString();
        String address = ((DefaultTableModel)table.getModel()).getValueAt(row, 4).toString();
        return new Tblstudent(id,name,id,inClass,gender,address);
    }
         
    
                
                
    int checkIfAcceptableRow(int row,int columnCount,JTable table){
        int kq = -1;
        int count = 0;
        for (int i = 0 ; i < columnCount; i++){
            if (((DefaultTableModel)table.getModel()).getValueAt(row, i) != null){
                count ++;
            }
        }        
        if (count == 0){
            return -1;
        }else if (count < columnCount){
            return 0;
        }else{
            return 1;
        }        
    }
    
    boolean checkIdNotExist(String Id){
        
        List<Tblstudent> list = StudentDAO.getAllStudent();
        
        for (int  i = 0 ; i < list.size() ; i++){
            if (list.get(i).getMaSv().equals(Id)){
                return false;
            }
        }
        return true;
    }
    
    static void exportToCsvFile(String fileName, Tblstudent student) throws IOException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.append("id,name,class,gender,address\n");        
        writer.append(student.getMaSv()+","+student.getTenSv()+","+student.getTenLop()+","+student.getGioiTinh()+","+student.getDiaChi()+"\n");        
        writer.flush();
        writer.close();
    }
    
    static List<Tblstudent> importFromCsvFile(String fileName) throws IOException {
        List<Tblstudent> studentList = new ArrayList<>();
        String splitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        line = br.readLine();
        while(line!=null){
            String[] b = line.split(splitBy);
            Tblstudent student = new Tblstudent(b[0],b[1],b[0],b[2],b[3],b[4]);
            studentList.add(student);
            line = br.readLine();
        }
        br.close();
        return studentList;
    }

}
