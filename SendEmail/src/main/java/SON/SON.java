package SON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SON {

    public static class Uye{

        public String isim;
        public String soyisim;
        public String mail;
       
        public Uye(String isim, String soyisim, String mail) {
            this.isim = isim;
            this.soyisim = soyisim;
            this.mail = mail;
        }
       
    }
    public static class ElitUye extends Uye{


        public ElitUye(String isim, String soyisim, String mail) {
            super(isim, soyisim, mail);
        }
    }
    public static class GenelUye extends Uye{


        public GenelUye(String isim, String soyisim, String mail) {
            super(isim, soyisim, mail);
        }
    }

    public static void mailgonder(String to , String yazi)
    {

               //mail islemleri
                String from = "beyzacar5441@gmail.com";
                String host = "smtp.gmail.com";
                Properties properties = System.getProperties();
                properties.put("mail.smtp.host", host);
                properties.put("mail.smtp.port", "465");
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.auth", "true");

                //gonderen  mail bilgileri
                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("beyzacar5441@gmail.com", "qysxvrhxfbnqsqtq");

                    }

                });


                session.setDebug(true);

                try {
                   
                    MimeMessage message = new MimeMessage(session);

                    message.setFrom(new InternetAddress(from));

                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                    
                    message.setSubject("MAİL");
                    
                    message.setText(yazi);

                    System.out.println("sending to: " + to);
                    // mesaj gonderimi
                    Transport.send(message);
                    System.out.println("mail gönderildi....");
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
    }

    public static void main(String[] args) {
    	boolean cont=false;
    	
    //islem secimi	
        System.out.println("######	 MENU	######");
        System.out.println("HANGİ İŞLEMİ YAPMAK İSTİYORSUNUZ?");
        System.out.println("Genel Uye Eklemek için 1e");
        System.out.println("Elit Uye Eklemek için 2ye");
        System.out.println("Mail Gondermek için 3e basınız.");
        Scanner scan= new Scanner(System.in);
        int islem=scan.nextInt();
       
        String dosya = "beyza" + "proje.txt";

      
     
        if(islem==1) { 
        		System.out.println("isim girin");
            	 Scanner scan2= new Scanner(System.in);
                 String isim1=scan2.nextLine();
                 System.out.println("soyisim girin");
                 Scanner scan3= new Scanner(System.in);
                 String soyisim1=scan3.nextLine();
                 System.out.println("mail girin");
                 Scanner scan4= new Scanner(System.in);
                 String mail1=scan4.nextLine();
              
                 //dosya varlıgı kontrolu
                 try {
                     File dosyam = new File(dosya);
                     if (!dosyam.exists()) {
                         dosyam.createNewFile();
                         cont=true;
                     }
                     // yeni dosya oluşturulursa başlık ekleme
                     if(cont==true) {
         	          String giris="	GENEL ÜYELER\n\n\n\n\nELİT ÜYELER\n";
         	          FileWriter f= new FileWriter(dosyam,false);
       	            BufferedWriter b= new BufferedWriter(f);
       	            b.write(giris);
       	            b.close();
                     }
                     
                     BufferedReader br = new BufferedReader(new FileReader(dosyam));
                            
                     //dosyaya genel uye ekleme
                     GenelUye user = new GenelUye(isim1,soyisim1,mail1);
                     String metin = "";
                     String st;
                     String temp = user.isim + "\t" + user.soyisim + "\t" + user.mail;
                     while ((st = br.readLine()) != null) {
                         if(st.contains("GENEL ÜYELER")) {
                             metin = metin + st + "\n";
                             metin = metin + temp + "\n";
                         }
                         else {
                             metin = metin + st + "\n";
                         }

                       
                     }
                     System.out.println("*************************");
                     System.out.println(metin);
                     
                     FileWriter myWriter = new FileWriter(dosyam);
                     myWriter.write(metin);
                     myWriter.close();

                 }
                 catch (IOException e) {
                     System.out.println("IOException Occured");
                     e.printStackTrace();
                 }
                 
        }
                 
                 else if(islem==2) {
                System.out.println("isim girin");
                Scanner scan5= new Scanner(System.in);
                String isim2=scan5.nextLine();
                System.out.println("isim girin");
                Scanner scan6= new Scanner(System.in);
                String soyisim2=scan6.nextLine();
                System.out.println("isim girin");
                Scanner scan7= new Scanner(System.in);
                String mail2=scan7.nextLine();
 
                //dosya varlıh-ğı kontrolu
                try {
                    File dosyam = new File(dosya);
                    if (!dosyam.exists()) {
                        dosyam.createNewFile();
                        cont=true;
                    }
                    //yeni dosyaya baslık ekleme
                    if(cont==true) {
           	          String giris="	GENEL ÜYELER\n\n\n\n	ELİT ÜYELER\n";
           	          FileWriter f= new FileWriter(dosyam,false);
         	            BufferedWriter b= new BufferedWriter(f);
         	            b.write(giris);
         	            b.close();
                       }
                    BufferedReader br  = new BufferedReader(new FileReader(dosyam));

                    //elit üye ekleme
                    ElitUye user = new ElitUye(isim2,soyisim2,mail2);
                    String metin = "";
                    String st;
                    String temp = user.isim + "\t" + user.soyisim + "\t" + user.mail;
                    while ((st = br.readLine()) != null) {
                        if(st.contains("ELİT ÜYELER")) {
                            metin = metin + st + "\n";
                            metin = metin + temp + "\n";
                        }
                        else {
                            metin = metin + st + "\n";
                        }

                    }
                    System.out.println("##############################");
                    System.out.println(metin);

                    FileWriter myWriter = new FileWriter(dosyam);
                    myWriter.write(metin);
                    myWriter.close();

                }
                catch (IOException e) {
                    System.out.println("IOException Occured");
                    e.printStackTrace();
                }}

               
                else if(islem==3) {
            	
                
                // Recipient's email ID needs to be mentioned.
                

            }
      
        System.out.println("Mail Atmak İstediğiniz Üyeleri Seçin");
        System.out.println("Elit Uyeler için 1e");
        System.out.println("Genel Uyeler için 2ye");
        System.out.println("Tum Uyeler için 3e basın.");
        Scanner scan9= new Scanner(System.in);
        int secim=scan9.nextInt();
                switch(secim) {
                    case 1:
                    	//dosya olusturma
                    	try {
            	            File dosyam = new File(dosya);
            	            if (!dosyam.exists()) {
            	                dosyam.createNewFile();
            	                cont=true;
            	            }
            	            //dosya baslangıc bilgileri
            	            if(cont==true) {
                   	          String giris="	GENEL ÜYELER\nn\n\n\n	ELİT ÜYELER\n";
                   	          FileWriter f= new FileWriter(dosyam,false);
                 	            BufferedWriter b= new BufferedWriter(f);
                 	            b.write(giris);
                 	            b.close();
                               }
            	           
            	         
            	            BufferedReader buff= new BufferedReader(new FileReader(dosyam));
            	            
            	 
            	            String bilgi = "";
            	            Boolean e = false;
            		        String st;
            		        ArrayList<String> mail = new ArrayList<String>();
                            
                            System.out.println("Atacıgınız maili girin:");
                            Scanner scan8= new Scanner(System.in);
                            String yazi=scan8.nextLine();

            		        
            		        while ((st = buff.readLine()) != null) {
            		        	
            		        	if(st.contains("GENEL ÜYELER")) {
            		        		e = false;
            		        	}
            		        	
            		        	if(e == true) {
            		        		bilgi = bilgi + st + "\n";
            		        	}
            		        	
            		        	if(st.contains("ELİT ÜYELER")) {
            		        		e = true;
            		        	}
            		        }
            		        
            		        System.out.println("Elit kullanıcılara mail attınız. Mail atılan kullanıcılar: ");
            		        System.out.println(bilgi);
            		        
            		        	
            	        }
            	        catch (IOException e) {
            	            System.out.println("IOException Occured");
            	            e.printStackTrace();
            	        }
                        break;
                    case 2:
                    	try {
            	            File dosyam = new File(dosya);
            	            if (!dosyam.exists()) {
            	                dosyam.createNewFile();
            	            }
            	            BufferedReader buff  = new BufferedReader(new FileReader(dosyam));

            	 
            	            String  bilgi = "";
            	            Boolean c = false;
            		        String st;
                            ArrayList<String> mail = new ArrayList<String>();
                            
                            System.out.println("Atacıgınız maili girin:");
                            Scanner scan8= new Scanner(System.in);
                            String yazi=scan8.nextLine();

            		        while ((st = buff.readLine()) != null) {
            		        	
            		        	if(st.contains("ELİT ÜYELER")) {
            		        		c = false;
            		        	}
            		        	
            		        	if(c == true) {
                                    StringTokenizer st1 = new StringTokenizer(st , "\t");
                                    String strr = null;
                                    for (int i = 1; st1.hasMoreTokens(); i++){
                                        String str = st1.nextToken();
                                        if(str.contains("@")){
                                            strr = str;
                                            break;
                                        }
                                    }

                                    if(strr != null)
                                    {
                                        mailgonder(strr, yazi);   
                                    }
                                    
            		        		bilgi = bilgi + st + "\n";
            		        	}
            		        	
            		        	if(st.contains("GENEL ÜYELER")) {
            		        		c = true;
            		        	}
            		        }
            		        
            		        System.out.println("Genel kullanıcılara mail attınız. Mail atılan kullanıcılar: ");
            		        System.out.println(bilgi);
            		        
            		        	
            	        }
            	        catch (IOException e) {
            	            System.out.println("IOException Occured");
            	            e.printStackTrace();
            	        }
                        break;
                    case 3:
                    	try {
            	            File dosyam = new File(dosya);
            	            if (!dosyam.exists()) {
            	                dosyam.createNewFile();
            	            }
            	            BufferedReader buff = new BufferedReader(new FileReader(dosyam));

            	            boolean c=false;
            	            String bilgi = "";
            		        String st;
            		        ArrayList<String> mail = new ArrayList<String>();
                            
                            System.out.println("Atacıgınız maili girin:");
                            Scanner scan8= new Scanner(System.in);
                            String yazi=scan8.nextLine();

                            while ((st = buff.readLine()) != null) {
            		        
            		        
                            			StringTokenizer st1 = new StringTokenizer(st , "\t");
                                    String strr = null;
                                    for (int i = 1; st1.hasMoreTokens(); i++){
                                        String str = st1.nextToken();
                                        if(str.contains("@")){
                                            strr = str;
                                            break;
                                        }
                                    }

                                    if(strr != null)
                                    {
                                        mailgonder(strr, yazi);   
                                    }
                                    
            		        		bilgi = bilgi + st + "\n";
            		        	}
            		        	
            	
                    	
            		        
            		        
            		        System.out.println("Tüm kullanıcılara mail attınız. Mail atılan kullanıcılar: ");
            		        System.out.println(bilgi);
            		        
            		        	
            	        }
            	        catch (IOException e) {
            	            System.out.println("IOException Occured");
            	            e.printStackTrace();
            	        }
                        break;
                }

                



        }

    }



