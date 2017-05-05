/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Pojo.Tblaccount;
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
public class AccountDAO {
    
    public static List<Tblaccount> getAllAccount(){
        List<Tblaccount> list = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
         try {
            String hbl = "SELECT acc FROM Tblaccount acc";
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
    
    public static Tblaccount getAccountInfor(String username){
         Session session = HibernateUtil.getSessionFactory().openSession();
        Tblaccount account = null;
        
        try{
            account = (Tblaccount) session.get(Tblaccount.class, username);
        }
        catch (HibernateException e){

        }
        finally {
            session.close();
        }
        return account;
    }
    
    public static boolean addAccount(Tblaccount account){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (AccountDAO.getAccountInfor(account.getUserName())!= null){
            return false;
        }
        else{
            Transaction transaction = null;
            try{
                
                 transaction = session.beginTransaction();
                session.saveOrUpdate(account);
                transaction.commit();  
                
            }catch (HibernateException e){

        }
        finally {
            session.close();
        }
            
        }
        return true;
    }
    
    public static boolean updateAccount(Tblaccount account){
        boolean check = true;
         Session session = HibernateUtil.getSessionFactory().openSession();        
         if (AccountDAO.getAccountInfor(account.getUserName()) == null) {             
             return false;
         }         
         Transaction transaction = null;         
         try {             
             transaction = session.beginTransaction();             
                session.update(account);             
                transaction.commit();         
            } catch (HibernateException ex) {             
                //Log the exception 
                check = false;
                transaction.rollback();             
                System.err.println(ex);         
            } finally {             
                     session.close();         
                 }         
         return check; 
    }
    
}
