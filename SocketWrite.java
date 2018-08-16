/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;



import java.io.DataOutputStream;
import java.net.*;
import java.util.Vector;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.paho.client.mqttv3.MqttExceptions.MqttException;
import org.paho.client.mqttv3.Ui.MqttUiMain;

/**
 *
 * @author ARUN YADAV
 */
public class SocketWrite extends Thread {
    
        QueueOutputStream qs=null;
        MqttWireMessage mq=null;
        Vector<MqttWireMessage> VM=null;
        DataOutputStream dos=null;
        Socket socket=null;
        
      public SocketWrite(Socket socket,Vector<MqttWireMessage> VM)
        {   
             try{
                   dos = new DataOutputStream(socket.getOutputStream());
                    this.socket=socket;                   }
                catch(IOException ex)
                {
                    new MqttException(ex,"ClassName :: SocketWrite ");  
             // System.out.println(" IOEx in Socket Write "); 
                       }
                this.VM=VM;
        }
     
    public  MqttWireMessage remove()
         {
           //.........Wait util Queue have at least one element 
            while(true)
              {     if((VM.isEmpty()))
                        continue;
                    else{
                     return VM.remove(0); 
                     }
                }
         }
   
   
   public void run()
   {
       while(true)
       {
           if(MqttUiMain.Dis==true)
              {
               try {
                       socket.close();
                        break;  
                    } catch (IOException ex) {
                      Logger.getLogger(SocketWrite.class.getName()).log(Level.SEVERE, null, ex);
                      }
                   
                 }
             mq=remove(); 
          try{   
                 System.out.println(Arrays.toString(mq.getHeader()));
                 System.out.println(Arrays.toString(mq.getPayload()));
                 dos.write(mq.getHeader(),0,mq.getHeader().length);
                 dos.write(mq.getPayload(),0,mq.getPayload().length);
                 dos.flush();
              }
            catch(IOException ex){
                new MqttException("ClassNAme ::SocketWrite ,Run ");  
               // System.out.println("IOEx SocketWrite in run");
                break;
                
                }
             
           }
      }
}
