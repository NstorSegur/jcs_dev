 
package conexion_mysql;

import javax.swing.JFrame;
import views_ui.frm_login;

/**
 *
 * @author nesto
 */
public class logOut {
    public static void logOut(JFrame context, frm_login loginScreen){
        loginSession.isLoggedIn = false;
        context.setVisible(false);
        loginScreen.setVisible(true);
    }
}
