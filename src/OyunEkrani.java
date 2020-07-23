import javax.swing.*;
import java.awt.*;

public class OyunEkrani extends JFrame {


    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args){

        OyunEkrani ekran=new OyunEkrani("Uzay Oyunu");
        ekran.setResizable(false);
        ekran.setFocusable(false);      //odağı ekrandan alıyoruz çünkü panele vericez

        ekran.setSize(800,600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Oyun oyun=new Oyun();
        oyun.requestFocus();        //klavyeden işlemleri anlaması için odağı ver şeklinde

        oyun.addKeyListener(oyun);  //klavyeden işlemlerimizi almasını sağlıyor
        oyun.setFocusable(true);    //odağı panale verdik
        oyun.setFocusTraversalKeysEnabled(false);       //klavye işlemlerini anlamamız için gereken bir method

        ekran.add(oyun);        //ekrana panelimizi ekledik

        ekran.setResizable(true);       //jframe mimiz bu panel oluştuğu zaman direkt eklensin diye true diyoruz


    }





}
