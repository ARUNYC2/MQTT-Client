/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

import org.paho.client.mqttv3.MqttExceptions.MqttException;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ARUN YADAV
 */

public class MqttSubscribe extends MqttWireMessage
{
    
    
    private String[] names;
     private  int [] qos;
     private int count;
    private String name1;
    private int qos1;
    
    public MqttSubscribe(byte info, byte[] data) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_SUBSCRIBE);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
                      
             
               // name=decodeUTF8(dis);
                //qos=dis.readByte();
                
		count = 0;
		names = new String[10];
		qos = new int[10];
		boolean end = false;
		while (!end) {
			try {
				names[count] = decodeUTF8(dis);
				qos[count++] = dis.readByte();
			} catch (Exception e) {
				end = true;
			}
		}
		dis.close();
	}
    
   
    
 public MqttSubscribe(String  name,int qos)
    {super(MqttWireMessage.MESSAGE_TYPE_SUBSCRIBE);
       /* 
      super(MqttWireMessage.MESSAGE_TYPE_SUBSCRIBE);
		this.names = names;
		this.qos = qos;
		
		if (names.length != qos.length) {
		throw new IllegalArgumentException();
		}
		this.count = names.length;
		
		for (int i=0;i<qos.length;i++) {
			MqttMessage.validateQos(qos[i]);
		}
                 */
              this.name1=name;
              this.qos1=qos;
        
        
    }
    
    protected byte getMessageInfo() {
		return (byte) (2 | (duplicate ? 8 : 0));
               // return (byte) (2 & 0x0f);
	}
    
    public byte[] getVariableHeader() //throws MqttException 
    {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			dos.writeShort(msgId);
			dos.flush();
			return baos.toByteArray();
		    } catch (IOException ex) {
		         new MqttException("Class Name :: MqttSubscribe ,getVarHead");
                        //System.out.println("IOEx get var subscribe");	
                        return null;
                        //throw new MqttException(ex);
		}
    }
	
	public byte[] getPayload() //throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			
                        
                    
                        encodeUTF8(dos,name1);
                       dos.writeByte(qos1);
                        
                        
                       /*for (int i=0; i<names.length; i++) {
				encodeUTF8(dos,names[i]);
				dos.writeByte(qos[i]);
			}*/
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
                    
                      //new MqttException(ex,"ClassName :: MqttSubscribe getPayload");  
			System.out.println("IOEx get pay subscribe ");
                          //throw new MqttException(ex);
                          return null;
		}
               
	}
  }

/*public class SubscribeNew {
    public static void main(String arg[])
    {
        
    }
    
}*/
