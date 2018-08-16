/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

import org.paho.client.mqttv3.MqttExceptions.MqttException;
import java.io.IOException;
/**
 *
 * @author ARUN YADAV
 */
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
 

 

public class MqttConnect extends MqttWireMessage
{
   /*   for socket connecting to broker eclipse
      Connect(){
    Socket socket=null;
    try
         {
           socket=new Socket("iot.eclipse.org",8883);
               
          }
         catch(UnknownHostException ex)
          {
            System.out.println(ex);
              }
           catch(IOException ex)
           {
            System.out.println(ex);
            }
          
             
                  System.out.println("\nSocket connected");
                  
                  while(true)
                  {
                      if(socket.isConnected())
                          System.out.println("Connected");
                      else{ System.out.println("not connected");break;}
                     
                     try{ //wait(100);
                      Thread.sleep(5000);
                     }
                     catch(InterruptedException ex){
                         
                         
                     }
                  }
               }*/
    
public static final String KEY = "Con";

	private String clientId;
	private boolean cleanSession;
	private MqttMessage willMessage;
	private String userName;
	private char[] password;
	private int keepAliveInterval;
	private String willDestination;
	private int MqttVersion;
        
        public MqttConnect(byte info, byte[] data) throws IOException{ //MqttException {
		super(MqttWireMessage.MESSAGE_TYPE_CONNECT);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);

		String protocol_name = decodeUTF8(dis);
		int protocol_version = dis.readByte();
		byte connect_flags = dis.readByte();
		keepAliveInterval = dis.readUnsignedShort();
		clientId = decodeUTF8(dis);
		dis.close();
	}

       public MqttConnect(String clientId, int MqttVersion, boolean cleanSession,
                              int keepAliveInterval, String userName, char[] password,
                             MqttMessage willMessage, String willDestination) {
		super(MqttWireMessage.MESSAGE_TYPE_CONNECT);
		this.clientId = clientId;
		this.cleanSession = cleanSession;
		this.keepAliveInterval = keepAliveInterval;
		this.userName = userName;
		this.password = password;
		this.willMessage = willMessage;
		this.willDestination = willDestination;
		this.MqttVersion = MqttVersion;
	}
      

        protected byte getMessageInfo() {
		return (byte) 0;
	      }

       
         public byte[] getVariableHeader()// throws MqttException 
          {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			
			if (MqttVersion == 3) {
				encodeUTF8(dos,"MQIsdp");			
			}
			else if (MqttVersion == 4) {
				encodeUTF8(dos,"MQTT");			
			}
			dos.write(MqttVersion);

			byte connectFlags = 0;
			
			if (cleanSession) {
				connectFlags |= 0x02;
			}
			
			if (willMessage != null ) {
				connectFlags |= 0x04;
				connectFlags |= (willMessage.getQos()<<3);
				if (willMessage.isRetained()) {
					connectFlags |= 0x20;
				}
			}
			
			if (userName != null) {
				connectFlags |= 0x80;
				if (password != null) {
					connectFlags |= 0x40;
				}
			}
			dos.write(connectFlags);
			dos.writeShort(keepAliveInterval);
			dos.flush();
			return baos.toByteArray();
		} catch(IOException ioe) {
			
                        System.out.println("exception IO");
                       // throw new MqttException(ioe);
                       return null;
		}
	}

      public byte[] getPayload() //throws MqttException 
           {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			encodeUTF8(dos,clientId);
			
			if (willMessage != null) {
				encodeUTF8(dos,willDestination);
				dos.writeShort(willMessage.getPayload().length);
				dos.write(willMessage.getPayload());
			}
			
			if (userName != null) {
				encodeUTF8(dos,userName);
				if (password != null) {
					encodeUTF8(dos,new String(password));
				}
			}
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
			//throw new MqttException(ex);
                        return null;
		}
	}




}

