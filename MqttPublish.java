/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

import org.paho.client.mqttv3.Ui.MqttUiMain;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.*;//ByteArrayOutputStream;
import org.paho.client.mqttv3.MqttExceptions.MqttException;
/**
 *
 * @author ARUN YADAV
 */
public class MqttPublish extends MqttWireMessage
{
        private MqttMessage message;
	private String topicName;
        private byte[] encodedPayload = null;
       
        int QoS;
        boolean Retained;
        String Message;
        //.........
        public MqttPublish(byte info, byte[] data) throws  IOException  {
		super(MqttWireMessage.MESSAGE_TYPE_PUBLISH);
                
               /*  for(int i=0;i<data.length;i++)
                 {
                    System.out.print(data[i]+" "+" "); 
                 }
                */
		message = new MqttReceivedMessage();
		message.setQos((info >> 1) & 0x03);
		if ((info & 0x01) == 0x01) {
			message.setRetained(true);
		}
		if ((info & 0x08) == 0x08) {
			((MqttReceivedMessage) message).setDuplicate(true);
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		CountingInputStream counter = new CountingInputStream(bais);
		DataInputStream dis = new DataInputStream(counter);
		topicName = decodeUTF8(dis);
		if (message.getQos() > 0) {
			msgId = dis.readUnsignedShort();
		}
		byte[] payload = new byte[data.length-counter.getCounter()];
		dis.readFully(payload);
		dis.close();
		message.setPayload(payload);
	   
                 MqttUiMain.mu.PrintReceiveMessage(topicName,message.toString(),
                         String.valueOf(message.getQos()),message.isRetained());      
          // System.out.println("topicNAme "+topicName);
           //System.out.println("Msg "+message.toString());
        
        
        }
           
        
            
        
        
        
        
        
        
        
        
        
        
        
   public MqttPublish(String topicName,String Message,int QoS,boolean Retained)
      {
          super(MqttWireMessage.MESSAGE_TYPE_PUBLISH);
          this.topicName=topicName;
          this.QoS=QoS;
          this.Retained=Retained;
          this.Message=Message;
      }
    
    
    
    public byte[] getVariableHeader() // throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			encodeUTF8(dos, topicName);
			/*if (message.getQos() > 0) {
				dos.writeShort(msgId);
			}*/
                        //dos.writeShort(10);
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
			//throw new MqttException(ex);
                        new MqttException("ClassName : MqttPublish ,getVarHead");
                       // System.out.println("IOExce var header");
                      
                        return null;
		}
	}

         public byte getMessageInfo() {
	//	byte info = (byte) (message.getQos() << 1);
	        byte info = (byte) (QoS << 1);
                if (Retained) {
                   info |= 0x01;
		}
		/*if (message.isDuplicate() || duplicate ) {
			info |= 0x08;
		} */
		
		return info;
	}
    public byte [] getPayload()// throws IOException 
    {         
             try{
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			  DataOutputStream dos = new DataOutputStream(baos);
			   encode_UTF8(dos,Message);
                            dos.flush();
                        return baos.toByteArray();
                        
             }
             catch(IOException ex)
             { new MqttException("Class Name :MqttPublish ,getPayload ");
                 //System.out.println(" IOEx payload ");
                 return null;
             }
        
    }
    
     private void encode_UTF8(DataOutputStream dos, String stringToEncode)// throws MqttException
	{
		try {

			byte[] encodedString = stringToEncode.getBytes("UTF-8");
			  dos.write(encodedString);
		}
		catch(UnsupportedEncodingException ex)
		{   
			 new MqttException("\nUnsupportedEncodingException ");
		} catch (IOException ex) {
			//throw new MqttException(ex);
                         new MqttException(ex,"ClassName :: MqttPublish ,encodeUTF8");  
                        
		}
	}
    
    
          
          
          
          
}





/* class PublishNew 
{
       public static void main(String arg[])
       {
        new MqttPublish();
      }
 }
*/