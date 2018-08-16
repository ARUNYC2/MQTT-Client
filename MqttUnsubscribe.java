/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;



import org.paho.client.mqttv3.MqttExceptions.MqttException;

/**
 *
 * @author ARUN YADAV
 */
public class MqttUnsubscribe  extends MqttWireMessage
{
 
    private String[] names;
	private int count;

	String name;
	/*public MqttUnsubscribe(String[] names) {
		super(MqttWireMessage.MESSAGE_TYPE_UNSUBSCRIBE);
		this.names = names;
	}*/
        public MqttUnsubscribe(String name) {
		super(MqttWireMessage.MESSAGE_TYPE_UNSUBSCRIBE);
		this.name = name;
	}
	
	
	public MqttUnsubscribe(byte info, byte[] data) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_UNSUBSCRIBE);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();

		count = 0;
		names = new String[10];
		boolean end = false;
		while (!end) {
			try {
				names[count] = decodeUTF8(dis);
			} catch (Exception e) {
				end = true;
			}
		}
		dis.close();
	}

	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(" names:[");
		for (int i = 0; i < count; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append("\"" + names[i] + "\"");
		}
		sb.append("]");
		return sb.toString();
	}

	protected byte getMessageInfo() {
		return (byte) (2 | (duplicate ? 8 : 0));
	}
	
	protected byte[] getVariableHeader() //throws MqttException
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			dos.writeShort(msgId);
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
                     System.out.println("IOex Unsubcribe get var");
			//throw new MqttException(ex);
                        return null;
		}
	}

	public byte[] getPayload() //throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			//for (int i=0; i<names.length; i++) {
				encodeUTF8(dos, name);
			//}
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
			System.out.println("IOex Unsubcribe get pay");
			//throw new MqttException(ex);
                        return null;
		}
	}

	public boolean isRetryable() {
		return true;
	}
    
    
    
    
    
}
