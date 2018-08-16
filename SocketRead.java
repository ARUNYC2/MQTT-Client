/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.paho.client.mqttv3.MqttExceptions.MqttException;
import org.paho.client.mqttv3.Ui.MqttUiMain;

/**
 *
 * @author ARUN YADAV
 */
public class SocketRead extends Thread {
     Socket socket;
     DataInputStream dis=null; 
      public SocketRead(Socket socket)
       {
           try{
             dis=new DataInputStream(socket.getInputStream());
            }
           catch(IOException ex)
           {
                new MqttException(ex,"ClassName ::SocketRead Constractor");  
            //   System.out.println("IOExc  Socket Input stream");
           }
       }
    
      
      public void run()
      {
          byte firstByte;
          MqttWireMessage msg=null;
          while(true)
          {
                 if(MqttUiMain.Dis==true)
                  {
                     try {
                          socket.close();  
                           break;
                        } catch (IOException ex) {
                        
                            Logger.getLogger(SocketRead.class.getName()).log(Level.SEVERE, null, ex);
                           break;
                        }
                   }  
                 else{  
              
                       msg=MqttWireMessage.createWireMessage(dis);
                         if(msg==null)
                             {
                                new MqttException("ClassName ::SocketRead,method Run ");  
                                  break;
                             }   
             
                       }
              }
           /*   try{
             // firstByte=dis.readByte();
              
             // System.out.println(""+(char)firstByte);
             
              
            //new SocketInputStream(firstByte,dis);
              
              }
              
              
              catch(IOException ex , )
              {
                  System.out.println("IOEx in Socket Write"+ex);
              }
              */
           
          
      }
}
      
      
      

