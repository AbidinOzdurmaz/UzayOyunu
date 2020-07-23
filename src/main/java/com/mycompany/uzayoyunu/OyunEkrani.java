package com.mycompany.uzayoyunu;

import java.awt.HeadlessException;
import javax.swing.JFrame;


//Ane Ekranımız Burada olacak

public class OyunEkrani extends JFrame{             

    public OyunEkrani(String title) throws HeadlessException {      //constructer ımız oluşturduk
        super(title);
    }
    
    
    public static void main(String[] args) {
        
        OyunEkrani ekran=new OyunEkrani("SpaceX");     //Başlığımıza SpaceX dedik
        ekran.setResizable(false);                     //oluşturacağımız panel üzerinde odaklanması için bunları false yaptık
        ekran.setFocusable(false);                      //birazdan kendi panelimizi oluşturacağız

        
        ekran.setSize(800,600);                     //ekranı 800x600 yaptık
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
        
        Oyun oyun=new Oyun();                       //Panelimiz için yeni Oyun Objesi tanımladık
        
        //aşağıdaki fonskiyonların sırası çok önemli yoksa input alırken sıkıntılar olabilir
        
        oyun.requestFocus();        //klavyeden işlemleri anlaması için odağı oluşturuduğumuz panel e ekliyoruz
        
        oyun.addKeyListener(oyun);    //klavyeden işlemlerimizi almamızı sağlıyor
         
        oyun.setFocusable(true);       //odağı oluşturduğumuz oyun paneline veriyoruz
        oyun.setFocusTraversalKeysEnabled(false);       //Klavye işlemlerimiz anlaması için gerekli olan methodumuz
        
        ekran.add(oyun);                    //jframe e jpanel i ekledik
        
        ekran.setVisible(true);          //jframe jpanel eklendiği zaman oluşsun diye true dedik bu sefer
  
    }

}
