/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Pojo.Tblattendance;
import Pojo.TblattendanceId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author MyPC
 */
public class AttendanceDAO {
    
   public static List<Tblattendance> getAllAttendance(){
        List<Tblattendance> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            String hbl = "SELECT dd FROM Tblattendance dd";
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
    
   public static Tblattendance getAttendanceInfor(TblattendanceId Id){
         Session session = HibernateUtil.getSessionFactory().openSession();
        Tblattendance attendance = null;
        
        try{
            attendance = (Tblattendance) session.get(Tblattendance.class, Id);
        }
        catch (HibernateException e){

        }
        finally {
            session.close();
        }
        return attendance;
    }
   
   
   
   public static boolean addAttendance(Tblattendance attendance){
       Session session = HibernateUtil.getSessionFactory().openSession();
        if (AttendanceDAO.getAttendanceInfor(attendance.getId())!= null){
            return false;
        }
        else{
            Transaction transaction = null;
            try{
                
                 transaction = session.beginTransaction();
                session.saveOrUpdate(attendance);
                transaction.commit();  
                
            }catch (HibernateException e){

        }
        finally {
            session.close();
        }
            
        }
        return true;
   }
   
   public static boolean updateAttendance(Tblattendance attendance){
       Session session = HibernateUtil.getSessionFactory().openSession();
        if (AttendanceDAO.getAttendanceInfor(attendance.getId())== null){
            return false;
        }
        else{
            Transaction transaction = null;
            try{
                
                transaction = session.beginTransaction();
                session.update(attendance);
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
