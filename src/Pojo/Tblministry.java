package Pojo;
// Generated Apr 14, 2017 6:01:21 PM by Hibernate Tools 4.3.1



/**
 * Tblministry generated by hbm2java
 */
public class Tblministry  implements java.io.Serializable {


     private String maGv;
     private String tenGv;
     private String userName;

    public Tblministry() {
    }

	
    public Tblministry(String maGv) {
        this.maGv = maGv;
    }
    public Tblministry(String maGv, String tenGv, String userName) {
       this.maGv = maGv;
       this.tenGv = tenGv;
       this.userName = userName;
    }
   
    public String getMaGv() {
        return this.maGv;
    }
    
    public void setMaGv(String maGv) {
        this.maGv = maGv;
    }
    public String getTenGv() {
        return this.tenGv;
    }
    
    public void setTenGv(String tenGv) {
        this.tenGv = tenGv;
    }
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }




}


