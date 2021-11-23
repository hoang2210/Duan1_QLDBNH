/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JPasswordField;

/**
 *
 * @author Dieu Le
 */
public class utilityHelper {
     public static boolean checkNullPass(JPasswordField txt) {
      
        if (txt.getPassword().length > 0) {
            return true;
        } else {
           
            DialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }
}
