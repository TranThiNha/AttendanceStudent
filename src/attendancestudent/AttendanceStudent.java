/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancestudent;

import Layout.LoginLayout;
import javax.swing.JFrame;

/**
 *
 * @author MyPC
 */
public class AttendanceStudent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginLayout layout = null;
        layout = new LoginLayout();
        layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layout.setVisible(true);
        layout.setSize(450, 400);
    }
    
}
