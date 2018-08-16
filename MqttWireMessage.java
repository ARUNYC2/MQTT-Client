/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

/**
 *
 * @author ARUN YADAV
 */
 

 import java.io.*;
 import java.lang.*;
  
import org.paho.client.mqttv3.MqttExceptions.MqttException;




public abstract class MqttWireMessage 
{
    
        public static final byte MESSAGE_TYPE_CONNECT = 1;
	public static final byte MESSAGE_TYPE_CONNACK = 2;
	public static final byte MESSAGE_TYPE_PUBLISH = 3;
	public static final byte MESSAGE_TYPE_PUBACK = 4;
	public static final byte MESSAGE_TYPE_PUBREC = 5;
	public static final byte MESSAGE_TYPE_PUBREL = 6;
	public static final byte MESSAGE_TYPE_PUBCOMP = 7;
	public static final byte MESSAGE_TYPE_SUBSCRIBE = 8;
	public static final byte MESSAGE_TYPE_SUBACK = 9;
	public static final byte MESSAGE_TYPE_UNSUBSCRIBE = 10;
	public static final byte MESSAGE_TYPE_UNSUBACK = 11;
	public static final byte MESSAGE_TYPE_PINGREQ = 12;
	public static final byte MESSAGE_TYPE_PINGRESP = 13;
	public static final byte MESSAGE_TYPE_DISCONNECT = 14;

	protected static final String STRING_ENCODING = "UTF-8";
	
	private static final String PACKET_NAMES[] = { "reserved", "CONNECT", "CONNACK", "PUBLISH",
			            "PUBACK", "PUBREC", "PUBREL", "PUBCOMP", "SUBSCRIBE", "SUBACK",
			             "UNSUBSCRIBE", "UNSUBACK", "PINGREQ", "PINGRESP", "DISCONNECT" };

     private byte type;
     protected int msgId ;
     protected boolean duplicate =false;
     
   
     
     public MqttWireMessage(byte type) {
		this.type = type;
	        this.msgId = 0;
	}
      
       public byte getType()
       {
           return this.type;
       }
       
       
      
       public int getMessageId() {
		return msgId;
       }
       
	public void setMessageId(int msgId) {
		this.msgId = msgId;
	}
      
     public byte[] getHeader() //throws MqttException 
     {
	    try {
	        int first = ((getType() & 0x0f) << 4) ^ (getMessageInfo() & 0x0f);
	        byte[] varHeader = getVariableHeader();
	        int remLen = varHeader.length + getPayload().length;
                     
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        DataOutputStream dos = new DataOutputStream(baos);
	        dos.writeByte(first);
	        dos.write(encodeMBI(remLen));
	        dos.write(varHeader);
	        dos.flush();
	        return baos.toByteArray();
	    } catch(IOException ioe) {
               System.out.println("error in get header");
	       // throw new MqttException(ioe);
               return null;
	    }
	}
                protected abstract byte[] getVariableHeader();
                protected abstract byte getMessageInfo();
     
      public byte[] getPayload() //throws MqttException
            {
               return  new byte[0];
               }
                    
     
      public static MqttWireMessage createWireMessage(InputStream inputStream) //throws MqttException 
      {
		try {
			CountingInputStream counter = new CountingInputStream(inputStream);
			DataInputStream in = new DataInputStream(counter);
			int first = in.readUnsignedByte();
			byte type = (byte) (first >> 4);
                        if((type<1)  ||  (type >14))
                           new MqttException("Getting MSG from Server is Wrong not in(1-14)"); 
			byte info = (byte) (first &= 0x0f);
			long remLen = readMBI(in).getValue();
			long totalToRead = counter.getCounter() + remLen;

			MqttWireMessage result;
			long remainder = totalToRead - counter.getCounter();
			byte[] data = new byte[0];
			// The remaining bytes must be the payload...
			if (remainder > 0) {
				data = new byte[(int) remainder];
				in.readFully(data, 0, data.length);
			}
				
			if (type == MqttWireMessage.MESSAGE_TYPE_CONNECT) {
				result = new MqttConnect(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_PUBLISH) {
				result = new MqttPublish(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_PUBACK) {
				result = new MqttPubAck(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_PUBCOMP) {
				result = new MqttPubComp(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_CONNACK) {
				result = new MqttConnack(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_PINGREQ) {
				result = new MqttPingReq(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_PINGRESP) {
				result = new MqttPingResp(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_SUBSCRIBE) {
				result = new MqttSubscribe(info, data);}
			else if (type == MqttWireMessage.MESSAGE_TYPE_SUBACK) {
				result = new MqttSuback(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_UNSUBSCRIBE) {
				result = new MqttUnsubscribe(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_UNSUBACK) {
				result = new MqttUnsubAck(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_PUBREL) {
				//result = new MqttPubRel(info, data);
                                result=null;
                        }
			else if (type == MqttWireMessage.MESSAGE_TYPE_PUBREC) {
				result = new MqttPubRec(info, data);
			}
			else if (type == MqttWireMessage.MESSAGE_TYPE_DISCONNECT) {
				result = new MqttDisconnect(info, data);
			}
			else {
			    new MqttException("UNEXPECTED_ERROR IN \n ClassNAme ::MqttWireMesssage ,creatWireMsg");  
                           //	throw ExceptionHelper.createMqttException(MqttException.REASON_CODE_UNEXPECTED_ERROR);
			 return null;
                        }
			return result;
		} catch(IOException io) {
		  new MqttException(io," ClassNAme ::MqttWireMsg,creatWireMsg");
                  //System.out.println("Mqtt Exc in CreatWireMsg");
                  return null;
                   //throw new MqttException(io);
		}
	}
		
      protected static MultiByteInteger readMBI(DataInputStream in) throws IOException {
		byte digit;
		long msgLength = 0;
		int multiplier = 1;
		int count = 0;
		
		do {
			digit = in.readByte();
			count++;
			msgLength += ((digit & 0x7F) * multiplier);
			multiplier *= 128;
		} while ((digit & 0x80) != 0);
		
		return new MultiByteInteger(msgLength, count);
	}
      
      
      
      
      
      
      
      
      
      
      ///..........encode Message ID.....
      protected byte[] encodeMessageId() //throws MqttException 
      {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			dos.writeShort(msgId);
			dos.flush();
			return baos.toByteArray();
		}
		catch (IOException ex) {
			
                     new MqttException(ex,"ClassName :: MqttWireMessage ,encodedMsgId");  
                      // throw new MqttException(ex);
                      return null;
		}
	}

      //..........UTF 8 encode...
      
      protected void encodeUTF8(DataOutputStream dos, String stringToEncode)// throws MqttException
	{
		try {

			byte[] encodedString = stringToEncode.getBytes("UTF-8");
			byte byte1 = (byte) ((encodedString.length >>> 8) & 0xFF);
			byte byte2 =  (byte) ((encodedString.length >>> 0) & 0xFF);  
			

			dos.write(byte1);
			dos.write(byte2);
			dos.write(encodedString);
		}
		catch(UnsupportedEncodingException ex)
		{
			//throw new MqttException(ex);
		} catch (IOException ex) {
			//throw new MqttException(ex);
                         new MqttException(ex,"ClassName :: MqttWireMsg ,encodeUTF8");  
                        
		}
	}
	
	
      
      
      //...............UtF 8 Decode
	protected String decodeUTF8(DataInputStream input) //throws MqttException
	{
		int encodedLength;
		try {
			encodedLength = input.readUnsignedShort();

			byte[] encodedString = new byte[encodedLength];
				input.readFully(encodedString);

			return new String(encodedString, "UTF-8");
		} catch (IOException ex) {
			 new MqttException(ex,"ClassName MqttWireMsg ,decodeUTF8");  
                       //throw new MqttException(ex);
                        return null;
		}
	}

           //...........encodeMBI

        protected static byte[] encodeMBI( long number) {
		int numBytes = 0;
		long no = number;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// Encode the remaining length fields in the four bytes
		do {
			byte digit = (byte)(no % 128);
			no = no / 128;
			if (no > 0) {
				digit |= 0x80;
			}
			bos.write(digit);
			numBytes++;
		} while ( (no > 0) && (numBytes<4) );
		
		return bos.toByteArray();
	}
        
     public String toString() {
		return PACKET_NAMES[type];
	}

     
    
    
}
