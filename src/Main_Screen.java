
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class Main_Screen extends JFrame {

    public Main_Screen(String title) throws HeadlessException {
        super(title);
    }
    
        
    
    
    public static void main(String[] args){
        
        Main_Screen ekran=new Main_Screen("  Flying Chicken Game");
        
        // bunları  yapış amacımız jfame üzerinde işlem yapmasın diye tüm işlemleri jpanel üzerinde yapsın
        ekran.setResizable(false);                      //yani Jframe focuslanmasın
        ekran.setFocusable(false);
        
        ekran.setSize(800,800);
        
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun=new Oyun();
        
        oyun.requestFocus();
        
        oyun.addKeyListener(oyun);
        
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekran.add(oyun);
        
        ekran.setVisible(true);
          
    }
}
