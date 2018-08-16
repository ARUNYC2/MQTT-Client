/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

import org.paho.client.mqttv3.MqttExceptions.MqttException;
import org.paho.client.mqttv3.Ui.MqttUiMain;
        

/**
 *
 * @author ARUN YADAV
 */
public class SubmitPingReq extends Thread {
    
       MqttWireMessage mw=null;
    QueueOutputStream nqs=MqttUiMain.qs;
       
    public SubmitPingReq(){}
    
    
    public void  run()
    {
        
        while(true){
            try{
            mw=new MqttPingReq();
           nqs. SubmitInQueue(mw);
         
           Thread.sleep(1500);  
           }
          catch(InterruptedException ex )
          {
             
               new MqttException("InterruptedException Exc. ClassNAme :: SubmitPingReq ,Run");  
              //System.out.println(" InterrExce in submitPingReq in RUN "); 
             }
        
         }
     
    }
    
    
              



                
    
    
}
