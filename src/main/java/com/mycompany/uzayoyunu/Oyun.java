package com.mycompany.uzayoyunu;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//implements ettiğimiz Interfacelerin abstract methodlarını Ide yardımıyla oluşturuyoruz
public class Oyun extends JPanel implements KeyListener,ActionListener {        

    Timer timer=new Timer(5, this);                     //timer tanımladık 5 saniyede bir yenilencek
    
    private int gecenSure=0;                                        //oyunda geçen süre oyun bittiğinde ekrana gösterilecek
    private int harcananAtes=0;                                     //oyunda harcanan ateş oyun bittiğinde ekrana gösterilecek
    private BufferedImage image;                                    //alcağımız resmi BufferedImage değişkeni ile tanımlıyoruz
    private ArrayList<Ates> atesler=new ArrayList<Ates>();          //ateşlerin tutmak için arraylist kullanıyoruz
    private int atesdirY=2;                                         //ateşi hareket ettireceğimiz değişken
    private int topX=0;                                             //oluşturacağımız topun x ekseninde ki koordinatını 0 atadık başlangıçta
    private int topdirX=2;                                          //topu hareket ettireceğimiz değişken
    private int uzayGemisiX=0;                                      //uzay gemisinin x koordinatı
    private int dirUzayX=25;                                        //sadece x eksenin hareket ettireceğimiz hareket ettirme değerimizi tanımladık
    private int puan=100;

    
    //Çarpışma durumunu kontrol eden method
    public boolean kontrolEt(){
        for(Ates ates:atesler){
            if(new Rectangle(ates.getX(),ates.getY(),5,20).intersects(new Rectangle(topX,0,20,20))){        //Rectangle çarpışma kontrolü yapan sınıf
                return true;
            }
        }
       
       return false;

    }
    
    //Constructer ımız  ******************************************
    public Oyun()
    {
        try {
            image =ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));      //png dosyamızı ekliyoruz try/catch önerisini uyguluyoruz
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        setBackground(Color.BLACK);             //backgroundumuza siyah değerini atadık
        
        timer.start();                             // timer ımızı başlatıyoruz
    }
    

    //PAİNT METHODU   ****************************************************
    //bu methodu insert code Override methodlardan çağırıyoruz
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);     //To change body of generated methods, choose Tools | Templates.
        gecenSure+=5;       //5 saniyede bir paint çalıştığı için geçen süreyi bu şekilde hesaplayabiliriz
        
        g.setColor(Color.GREEN);                                                                           //objemizin rengini yeşil yaptık
        g.fillOval(topX,0,20,20);                                                            //topumuzu çiziyoruz
        g.drawImage(image,uzayGemisiX,500,image.getWidth()/2,image.getHeight()/2,this);      //projemize eklediğimiz resmimizin şeklini düzenleyip ekliyoruz
        
        //for-each yardımıyla ateşler arrayi üzerinde geziyoruz
        for(Ates  ates:atesler){
             if (ates.getY()<0) {
                atesler.remove(ates);       //eğer ateş ekrandan çıkarsa listeden siliyoruz oyun kasmasın diye
            }
        }
        
        g.setColor(Color.BLUE);             //ateşlerin rengini mavi yaptık
    
        for(Ates ates:atesler){             
            g.fillRect(ates.getX(),ates.getY(),5,20);           //ateşimizi tanımladık x ve y koordinatlarını tanımlamıştık başlangıçta
        }                                                                       //witdh height değerleri giriyoruz
        //burada çarpışma olduğunda olacakları yazıyoruz
        if(kontrolEt()){
            timer.stop();       //oyunu durduruyoruz nesneler hareket etmiyor artık
            puan=puan-harcananAtes-(gecenSure/1000);
            String Mesaj="Kazandınız.... \n"
                    + "Harcanan Ateş :  "+harcananAtes
                    + "\nGeçen Süre: "+gecenSure/1000
                    +"\nPuan : "+puan;
                   
            JOptionPane.showMessageDialog(this,Mesaj);       //bir mesaj yayınlıyoruz
            System.exit(0);                                               //ve oyunu kapatıyoruz
                    
        }
        
        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
       

    }

    
    //burada klavyeden alacağımız inputların ne işe yarayacağını tanımlıyoruz
    @Override
    public void keyPressed(KeyEvent e) {
        
        int c=e.getKeyCode();   //integer bir değişken oluşturup klavyeden alacağımız inputları atamak için
         
        if(c==KeyEvent.VK_LEFT){      //eğer sol ok a basarsa  
            if(uzayGemisiX<=0){       //uzay gemisi en soldaysa veya - deyse onu 0 değerine eşitliyoruz ki oyun alanımızı terketmesin
                uzayGemisiX=0;
            }           
            else{                     //yukarıdaki durumlar geçerli değilse sola bastığımızda daha önceden tanımladığımız dirUzayX kadar sola ötelenicek
                uzayGemisiX-=dirUzayX;
            }
        }
        
        else if(c==KeyEvent.VK_RIGHT){   //eğer sağ ok a basarsa
            if(uzayGemisiX>=740){        //uzay gemisi en sağdaysa veya 740 dan  büyükse onu 740 değerine eşitliyoruz ki oyun alanımızı terketmesin
                uzayGemisiX=740;
            }
            else{
                uzayGemisiX+=dirUzayX;   //yukarıdaki durumlar geçerli değilse sağa bastığımızda daha önceden tanımladığımız dirUzayX kadar sağa ötelenicek
            }
        }
        
        else if(c==KeyEvent.VK_SPACE){   //eğer space e basatsa ateş edeceğiz
            atesler.add(new Ates(uzayGemisiX+27,480));       //ateş listemize bir ateş ekliyoruz koordinat olarak uzay gemisinin oradan panel uzunluğu kadar
            harcananAtes++;                                    //kaç ateş ettiğimizi görmek için
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(Ates ates:atesler){
            ates.setY(ates.getY()-atesdirY);            //her actionPermormed çalıştığında ateşimizin y koordinatları değişecek ileri doğru gidecek
        }
        topX+=topdirX;                                  //topumuz sürekli x ekseninde pozitif yöne hareket edecek
        
        if(topX>=750){                                  //eğer panelimizin sonuna gelirse sol a doğru harekete geçecek
            topdirX=-topdirX;
        }
        if(topX<=0){                                    //eğer panelimizin başına gelirse sağ a doğru harekete geçecek
            topdirX=-topdirX;
        }
        repaint();
    }

}
