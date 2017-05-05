/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Tblstudent;
import Pojo.Tblsubject;
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
public class SubjectDAO {
     public static List<Tblsubject> getAllSubject(){
       List<Tblsubject> subjectList = new ArrayList<>();
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
            String hbl = "SELECT sv FROM Tblsubject sv";
            Query query = session.createQuery(hbl);
            subjectList = query.list();
        }
        catch (HibernateException e){

        }
        finally {
            session.close();
        }
        return subjectList;
    }
     
     public static Tblsubject getSubjectInfor(String Id){
         Tblsubject subject = null;
         Session session = HibernateUtil.getSessionFactory().openSession();
       
         try{
             
             subject = (Tblsubject) session.get( Tblsubject.class, Id);
             
         }catch(HibernateException e){
             
         }finally{
             session.close();
         }
         
         return subject;
     }
     
     public static boolean addSubject(Tblsubject subject){
                  boolean kq = true; 

         Session session = HibernateUtil.getSessionFactory().openSession();
         if (SubjectDAO.getSubjectInfor(subject.getMaMh())!= null){
             return false;
         }
         else{
                Transaction transaction = null;

             try{
                transaction = session.beginTransaction();
                session.saveOrUpdate(subject);
                transaction.commit();     
             }
            
               catch(HibernateException e){
                  transaction.rollback();
                  kq = true; 

                }finally{
                    session.close();         

                }
        }
        return kq;

     }
  
    
}
