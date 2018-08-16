/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.net.*;
import java.util.Arrays;

/**
 *
 * @author ARUN YADAV
 */
public class QueueOutputStream {
    private int flag=0;
     private MqttWireMessage MWM=null;
     Socket socket=null;
     DataOutputStream dos=null;
     Vector<MqttWireMessage> VM=new Vector<MqttWireMessage>();
                    
    
    public QueueOutputStream(String ip,int port)
    { 
      
        try {
               socket = new Socket(ip, port);
            if(socket.isConnected())
                System.out.println("Socket Connected");
              } catch (UnknownHostException ex) {
                System.out.println("EXception :Unknown"+ex);
              } catch (IOException ex) {
                System.out.println("EXception :IOExc"+ex);
            }
         
    
    }
     
    public  QueueOutputStream() { }
    
    public void   SubmitInQueue(MqttWireMessage MWM) throws InterruptedException
    {
        
             VM.add(MWM);
          
           if(flag==0){
           Thread t1=new SubmitPingReq();
           Thread t2=new SocketWrite(socket,VM);
           Thread t3=new SocketRead(socket);
               t1.start();
               t2.start();
               t3.start();
                   flag=1;
             }
         //System.out.println(" "+VM.size());
           
    }
     
       
    
 
          
       
   
   
      
       
       
   
     /*  public void Write()
        {  
          try
              {
                dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("hiii");
                  MqttWireMessage mq=null;
                    if(VM.size()>0)
                       mq=remove();
                  
                System.out.println(Arrays.toString(mq.getHeader()));
                System.out.println(Arrays.toString(mq.getPayload()));
            
                dos.write(mq.getHeader(),0,mq.getHeader().length);
                dos.write(mq.getPayload(),0,mq.getPayload().length);
                dos.flush();
              }
        catch(IOException ex)
           {
              System.out.println("IOEx  in Write QueueoutputStream ");
            
           }
                   
        }*/
   
    }
