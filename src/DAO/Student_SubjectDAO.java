/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Pojo.TblestudentSubject;
import Pojo.TblestudentSubjectId;

/**
 *
 * @author MyPC
 */
public class Student_SubjectDAO {
    
    public static List<TblestudentSubject> getAllStudent_Subject(){
        List<TblestudentSubject> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            String hbl = "SELECT hs_mh FROM TblestudentSubject hs_mh";
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
    
    public static TblestudentSubject getStudent_SubjectInfor(TblestudentSubjectId Id){
        TblestudentSubject tblestudentSubject = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
       
         try{
             
             tblestudentSubject = (TblestudentSubject) session.get( TblestudentSubject.class, Id);
             
         }catch(HibernateException e){
             
         }finally{
             session.close();
         }
        return tblestudentSubject;
    }
    
    public static boolean addStudent_Subject(TblestudentSubject tblestudentSubject){
        boolean check = true;
        Session session = HibernateUtil.getSessionFactory().openSession();

        if (Student_SubjectDAO.getStudent_SubjectInfor(tblestudentSubject.getId())!=null){
            return false;
        }
        else{
            Transaction transaction = null;

             try{
                transaction = session.beginTransaction();
                session.saveOrUpdate(tblestudentSubject);
                transaction.commit();     
             }
            
               catch(HibernateException e){
                  transaction.rollback();
                  check = true; 

                }finally{
                    session.close();         

                }
        }
        return check;
    }
    
}
