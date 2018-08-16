/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author ARUN YADAV
 */
public class MqttUnsubAck extends MqttAck {
	
	public MqttUnsubAck(byte info, byte[] data) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_UNSUBACK);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
		// Not needed, as the client never encodes an UNSUBACK
		return new byte[0];
	}
}
