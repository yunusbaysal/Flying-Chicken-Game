
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//For Game components                                        
public class Oyun extends JPanel implements KeyListener,ActionListener {
        
        Timer timer=new Timer(6,this);      // timer her çalıstıgında action performu calıstırır
        private BufferedImage imagetavuk,imagetarget,imageegg,imagecat,imagebg;
        private Tavuk tavuk=new Tavuk(330,0);
        private ArrayList<Ates> atesler=new ArrayList<Ates>();
        private ArrayList<Ates> atesler2=new ArrayList<Ates>();
        private Ates temp,tempL,temp2,tempR;
        private Hedef hedef=new Hedef(300);
        private Hedef hedef2=new Hedef(715,1); 
        private int puan=0;
        Random rand = new Random();
        Random rand1=new Random();
        private Kedi kedi=new Kedi(490,380);  // sag taraftaki Kedi
        private Kedi kedi2=new Kedi(280,250);  // sol taraftaki kedi
        private int level=1;
        private  int hedef_sayisi=5;
        private boolean kontrol=false;
        private Kedi kedi3=new Kedi(-800,-800);
        private int vurulma=0,vurulma2=0; // vurulma:combo icin sol taraf  vurulma2: combo icin sol taraf
        
    public boolean kontrolcarpisma()
    {
           for(Ates at: atesler)
           {
                if(new Rectangle(at.getX(),at.getY(),42,41).intersects(new Rectangle(hedef.getX(),hedef.getY(),51,45)))
                {
                     temp=at;
                     return true;
                }
                   
           }
           
           return false;
    }
    public boolean kontrolcarpisma2()
    {
           for(Ates at: atesler2)
           {
                if(new Rectangle(at.getX(),at.getY(),42,41).intersects(new Rectangle(hedef2.getX(),hedef2.getY(),51,45)))
                {
                     temp2=at;
                     return true;
                }
                   
           }
           
           return false;
    }
    public boolean kontrolcarpismakedi(Kedi a)
    {
           if(new Rectangle(tavuk.getX(),tavuk.getY(),40,80).intersects(new Rectangle(a.getX(),a.getY(),46,60)))
           {
               return true;
           }
           return false;
    }
    public boolean kontrolcarpismacombo()    // sol taraf icin
    {
           for(Ates at: atesler)
           {
                if(new Rectangle(at.getX(),at.getY(),42,41).intersects(new Rectangle(hedef.getX(),hedef.getY(),80,74)))
                {
                     tempL=at;
                     return true;
                }
                   
           }
           
           return false;
    }
     public boolean kontrolcarpismacombo2()
    {
           for(Ates at: atesler2)
           {
                if(new Rectangle(at.getX(),at.getY(),42,41).intersects(new Rectangle(hedef2.getX(),hedef2.getY(),80,74)))
                {
                     tempR=at;
                     return true;
                }
                   
           }
           
           return false;
    }
   
    public Oyun(){
        
            try {
                imagebg=ImageIO.read(new FileImageInputStream(new File("pic/Sky.jpg")));
                imagetavuk=ImageIO.read(new FileImageInputStream(new File("pic/Chicken.png")));
                imagetarget=ImageIO.read(new FileImageInputStream(new File("pic/Target.png")));
                imageegg=ImageIO.read(new FileImageInputStream(new File("pic/Golden.png")));
                imagecat=ImageIO.read(new FileImageInputStream(new File("pic/Cat.png")));
            } catch (IOException ex) {
                Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
            }
           
                
            setBackground(Color.GREEN);
            
            timer.start();
              
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(imagebg,0,0,this);
        g.setColor(Color.BLACK);
        g.drawString("SCORE:"+puan,670,15);
        g.drawString("LEVEL  "+level,10,15 );   // level eklenmedi eklenecek!
        g.drawString("KALAN HEDEF SAYISI:"+hedef_sayisi,540,750);
        if(vurulma==3)
        {
            g.drawString("COMBO X3 SOL'DA",200,15);
        }
        if(vurulma2==3)
        {
            g.drawString("COMBO X3 SAG'DA",300,15);
        }  
            for(Ates at:atesler)
            {
                if(at.getX()<0)
                {
                    atesler.remove(at);
                }
                if(tavuk.getY()>=590)
                {
                    for(Ates at1: atesler)
                    {
                        atesler.remove(at1);
                    }
                }
            }
            for(Ates at:atesler2)
            {
                if(at.getX()>800)
                {
                    atesler2.remove(at);
                }
                if(tavuk.getY()>=590)
                {
                    for(Ates at1: atesler2)
                    {
                        atesler2.remove(at1);
                    }
                }
            }
            g.drawImage(imagetavuk,tavuk.getX(),tavuk.getY(),imagetavuk.getWidth()/8,imagetavuk.getHeight()/8,this);
            if(vurulma==3)
             g.drawImage(imagetarget,hedef.getX(),hedef.getY(),imagetarget.getWidth()/3,imagetarget.getHeight()/3, this);
            else
            g.drawImage(imagetarget,hedef.getX(),hedef.getY(),imagetarget.getWidth()/5,imagetarget.getHeight()/5, this);
            
            if(vurulma2==3)
              g.drawImage(imagetarget,hedef2.getX(),hedef2.getY(),imagetarget.getWidth()/3,imagetarget.getHeight()/3, this);
            else
              g.drawImage(imagetarget,hedef2.getX(),hedef2.getY(),imagetarget.getWidth()/5,imagetarget.getHeight()/5, this);
            
            for(Ates at:atesler)
            {
                g.drawImage(imageegg,at.getX(),at.getY(),imageegg.getWidth()/8,imageegg.getHeight()/8,this);
            }
            for(Ates at:atesler2)
            {
                g.drawImage(imageegg,at.getX(),at.getY(),imageegg.getWidth()/8,imageegg.getHeight()/8,this);
            }
            g.drawImage(imagecat,kedi.getX(),kedi.getY(),imagecat.getWidth()/14,imagecat.getHeight()/14,this);
            g.drawImage(imagecat,kedi2.getX(),kedi2.getY(),imagecat.getWidth()/14,imagecat.getHeight()/14,this);
            if(kontrol)
            {
                g.drawImage(imagecat,kedi3.getX(),kedi3.getY(),imagecat.getWidth()/14,imagecat.getHeight()/14,this);
            }
          
            
            if(level == 1 && puan>=500)
            {  
                timer.stop();
                String message="Tebrikler Level 1 tamamladin(Score:"+puan+"...Level 2 ye devam etmek icin space'e bas.";
                puan=0;
                vurulma=0; //combo için
                vurulma2=0; // combo sag taraf
                JOptionPane.showMessageDialog(this,message);
                hedef_sayisi=5;
                level++;
                tavuk.setY(600);
                tavuk.moveY=4;
                Ates.moveX=8;
                timer.start();    
            }
            if(level == 2 && puan>=500)
            {  
                timer.stop();
                String message="Tebrikler Level 2 tamamladin(Score:"+puan+"...Level 3 ye devam etmek icin space'e bas.";
                puan=0;
                vurulma=0; //combo için
                vurulma2=0; // combo sag taraf
                JOptionPane.showMessageDialog(this,message);
                hedef_sayisi=8;
                level++;
                tavuk.setY(600);
                Ates.moveX=8;
                tavuk.moveY=3;
                kontrol=true;
                timer.start();
            }
            if(level == 3 && puan>=800)
            {   
                timer.stop();
                String message="Tebrikler Level 3 tamamladin(Score:"+puan+"...Oyunu basariyla bitirdin...)";
                JOptionPane.showMessageDialog(this,message);
                kontrol=false;
                System.exit(0);
            }
            if(kontrolcarpismakedi(kedi))
            {
                timer.stop();
                puan=0;
                vurulma=0; //combo için
                vurulma2=0; // combo sag taraf
                if(level==1)
                hedef_sayisi=5;
                else if(level==2)
                hedef_sayisi=5;
                else if(level==3)
                hedef_sayisi=8;    
                String message="Ooops! Kedi seni yakaladi.Tekrar denemek icin Space tusuna bas...)";
                JOptionPane.showMessageDialog(this,message);
                 tavuk.setY(600);
                timer.start();
            }
            if(kontrolcarpismakedi(kedi2))
            {
                timer.stop();
                puan=0;
                vurulma=0; //combo için
                vurulma2=0; // combo sag taraf
                if(level==1)
                hedef_sayisi=5;
                else if(level==2)
                hedef_sayisi=5;
                else if(level==3)
                hedef_sayisi=8;    
                String message="Ooops! Kedi seni yakaladi.Tekrar denemek icin Space tusuna bas...)";
                JOptionPane.showMessageDialog(this,message);
                 tavuk.setY(600);
                timer.start();
            }
            if(kontrolcarpismakedi(kedi3))
            {
                timer.stop();
                puan=0;
                vurulma=0; //combo için
                vurulma2=0; // combo sag taraf
                if(level==1)
                hedef_sayisi=5;
                else if(level==2)
                hedef_sayisi=5;
                else if(level==3)
                hedef_sayisi=8;    
                String message="Ooops! Kedi seni yakaladi.Tekrar denemek icin Space tusuna bas...)";
                JOptionPane.showMessageDialog(this,message);
                 tavuk.setY(600);
                timer.start();
            }
            
           
           
            if(kontrolcarpismacombo() && hedef.combo(vurulma))
            {
                hedef_sayisi-=2;
                puan+=200;
                atesler.remove(tempL);
                hedef.setY(-100);
                if(level==3)
                {
                    vurulma=0;
                }
            }else
            {
             if(kontrolcarpisma())
            {   hedef_sayisi--;
                puan+=100;
                atesler.remove(temp);
                hedef.setY(-100);
                vurulma++;
            }
            }
           
            if(kontrolcarpismacombo2() && hedef2.combo(vurulma2))
            {
                hedef_sayisi-=2;
                puan+=200;
                atesler2.remove(tempR);
                hedef2.setY(-100);
                 if(level==3)
                {
                    vurulma2=0;
                }
            }
            else
            {
              if(kontrolcarpisma2())
            {   hedef_sayisi--;
                puan+=100;
                atesler2.remove(temp2);
                hedef2.setY(-100);
                vurulma2++;
            }
            }
            
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
       
    @Override
    public void keyTyped(KeyEvent e) {
       
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
            
               int c=e.getKeyCode();
               
               if(c == KeyEvent.VK_LEFT)
               {
                   if(tavuk.getX()<=50)
                   {
                       tavuk.setX(50);
                   }
                   else
                   {
                       tavuk.setX(tavuk.getX()-tavuk.moveX);
                   }  
               }
               else if(c == KeyEvent.VK_RIGHT)
               {
                   if(tavuk.getX()>=650)
                   {
                       tavuk.setX(650);
                   }
                   else
                   {
                       tavuk.setX(tavuk.getX()+tavuk.moveX);
                   }
               }
               else if(c == KeyEvent.VK_Q)
               {
                   atesler.add(new Ates(tavuk.getX()+30,tavuk.getY()));
               }
               else if(c == KeyEvent.VK_W)
               {
                    atesler2.add(new Ates(tavuk.getX()+30,tavuk.getY()));
               }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(Ates ates:atesler)
        {   
            ates.setX(ates.getX()-ates.moveX);
             
        }
        for(Ates ates:atesler2)
        {
           ates.setX(ates.getX()+ates.moveX);
        }
        if(hedef.getY()<20 && hedef.getY()!=-100)
        {   
            hedef.setY(650);
        }
        if(hedef2.getY()<20 && hedef2.getY()!= -100)
        {
            hedef2.setY(650);
        }
        tavuk.setY(tavuk.getY()+tavuk.moveY);
        if(tavuk.getY()>=600)
        {   
            tavuk.setY(0);
           
            hedef.setY(18+rand.nextInt(510));
            hedef2.setY(18+rand.nextInt(510));
            kedi.setX((400+rand1.nextInt(210)));
            kedi.setY((120+rand1.nextInt(480)));
            kedi2.setX((60+rand1.nextInt(340)));
            kedi2.setY((120+rand1.nextInt(480)));
            if(kontrol)
            {
                kedi3.setX(250+rand1.nextInt(230));
                kedi3.setY(200+rand1.nextInt(340));
            }   
                    
                
            
        }
                
        repaint();
    }
    
}
