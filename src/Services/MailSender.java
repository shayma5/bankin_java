/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author HP
 */


import Entities.Account;
import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;


public class MailSender {
    

     public static void sendMail(String userdes ,String etat) throws Exception{
        System.out.println("Preparing to send email");
        Properties po = new Properties();
        
        po.put("mail.smtp.auth", "true");
        po.put("mail.smtp.starttls.enable", "true");
        po.put("mail.smtp.host", "smtp.gmail.com");
             po.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        po.put("mail.smtp.port", "587");
        
        String myAccountEmail = "bankin.notifier@gmail.com";
        String password = "hgisrkcniqnkdxsh";
        
        Session session = Session.getInstance(po , new Authenticator() {
        @Override 
        protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(myAccountEmail, password);
      }
        });
        try{
        
        Message message = prepareMessage(session , myAccountEmail ,userdes ,etat);
         Transport.send(message);
             System.out.println("Message sent successfully");
         } catch(Exception ex){
            System.err.println(ex.getMessage());
        }
       
    
    
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String userdes ,String etat ) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
           // message.setRecipient(Message.RecipientType.TO, new InternetAddress(userdes.getEmail()));
             message.setRecipient(Message.RecipientType.TO, new InternetAddress(userdes));
            message.setSubject("Bankin");
            String htmlcode="  <center><a href=\"https://imgbb.com/\"><img src=\"https://i.ibb.co/vxz5b9N/image-processing20210903-16044-1mkb2s7-removebg-preview-2.png\" alt=\"image-processing20210903-16044-1mkb2s7-removebg-preview-2\" border=\"0\"  height=50%;width=50%></a></center></br>"
     +"<center><h3>bienvenue monsieur sur notre site Bankin Votre demande de compte a été :"+etat+ "</h3></center></br>";

              

            message.setContent(htmlcode,"text/html");
            return message;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }

}