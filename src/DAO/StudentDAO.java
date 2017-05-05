/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Pojo.Tblstudent;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author MyPC
 */
public class StudentDAO {
    
    public static List<Tblstudent> getAllStudent(){
        List<Tblstudent> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            String hbl = "SELECT hs FROM Tblstudent hs";
            Query query = session.createQuery(hbl);
            list = query.list();
        }
        catch (HibernateException e){

        }
        finally {
            session.close();
        }
        return list;
    }
    
    public static Tblstudent getStudentInfor (String Id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tblstudent student = null;
        
        try{
            student = (Tblstudent) session.get(Tblstudent.class, Id);
        }
        catch (HibernateException e){

        }
        finally {
            session.close();
        }
        return student;
    }
    
    public static Tblstudent getStudentInforFromUsername (String username){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tblstudent student = null;
        
        try{
            student = (Tblstudent) session.get(Tblstudent.class, username);
        }
        catch (HibernateException e){

        }
        finally {
            session.close();
        }
        return student;
    }
    
    public static boolean addStudent(Tblstudent student){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (TimetableDAO.getTimetableInfor(student.getMaSv())!= null){
            return false;
        }
        else{
            Transaction transaction = null;
            try{
                
                transaction = session.beginTransaction();
                session.save(student);
                transaction.commit();  
                
            }catch (HibernateException e){

            }
            finally {
                session.close();
            }
        }
        return true;    
    }
    
}
