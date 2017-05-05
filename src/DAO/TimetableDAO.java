/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Tbltimetable;
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
public class TimetableDAO {
   
    public static List<Tbltimetable> getAllTimetable(){
        List<Tbltimetable> tbltimetables = new ArrayList<>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
         try {
            String hbl = "SELECT t FROM Tbltimetable t";
            Query query = session.createQuery(hbl);
            tbltimetables = query.list();
        }
        catch (HibernateException e){

        }
        finally {
            session.close();
        }
        
        return tbltimetables;
    }
    
    public static Tbltimetable getTimetableInfor(String Id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tbltimetable timetable = null;
        
        try{
            timetable = (Tbltimetable) session.get(Tbltimetable.class, Id);
        }
        catch (HibernateException e){

        }
        finally {
            session.close();
        }
        return timetable;
    }
    
    public static boolean addTimetable(Tbltimetable timetable ){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (TimetableDAO.getTimetableInfor(timetable.getMaTkb())!= null){
            return false;
        }
        else{
            Transaction transaction = null;
            try{
                
                 transaction = session.beginTransaction();
                session.save(timetable);
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
