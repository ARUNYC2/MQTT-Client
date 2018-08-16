/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

import java.io.IOException;


import org.paho.client.mqttv3.MqttExceptions.MqttException;

/**
 *
 * @author ARUN YADAV
 */
public class MqttDisconnect extends MqttWireMessage {
	public static final String KEY="Disc";
	
	public MqttDisconnect() {
		super(MqttWireMessage.MESSAGE_TYPE_DISCONNECT);
	}

	public MqttDisconnect(byte info, byte[] variableHeader) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_DISCONNECT);
	}
	
	protected byte getMessageInfo() {
		return (byte) 0;
	}

	public  byte[] getVariableHeader() //throws MqttException
        {
		  return new byte[0];
	}
        
}