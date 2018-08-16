/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;


import org.paho.client.mqttv3.Ui.MqttUiMain;
import org.paho.client.mqttv3.MqttExceptions.MqttException;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/**
 *
 * @author ARUN YADAV
 */
public class MqttConnack extends MqttAck {
    
    public static final String KEY = "Con";

	private int returnCode;
	private boolean sessionPresent;
	
	public MqttConnack(byte info, byte[] variableHeader) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_CONNACK);
		ByteArrayInputStream bais = new ByteArrayInputStream(variableHeader);
		DataInputStream dis = new DataInputStream(bais);
		sessionPresent = (dis.readUnsignedByte() & 0x01) == 0x01;
		returnCode = dis.readUnsignedByte();
		dis.close();
                
                 if(returnCode==0x00)
                  MqttUiMain.mu.connectAck("!!! Connected SuccessfullY !!!");
                 else
                 {
                   new MqttException(returnCode);  
                 }
                
	}
	
	public int getReturnCode() {
		return returnCode;
	}

	protected byte[] getVariableHeader() //throws MqttException 
        {
		// Not needed, as the client never encodes a CONNACK
		return new byte[0];
	}
	
	/**
	 * Returns whether or not this message needs to include a message ID.
	 */
	public boolean isMessageIdRequired() {
		return false;
	}
	
	public String getKey() {
		return KEY;
	}
	
	public String toString() {
		return super.toString() + " session present:" + sessionPresent + " return code: " + returnCode;
	}
	
	public boolean getSessionPresent() {
		return sessionPresent;
	}
}

    
    
    

