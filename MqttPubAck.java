/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;


import org.paho.client.mqttv3.MqttExceptions.MqttException;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/**
 *
 * @author ARUN YADAV
 */
public class MqttPubAck extends MqttAck {
	public MqttPubAck(byte info, byte[] data) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_PUBACK);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}
	
	public MqttPubAck(MqttPublish publish) {
		super(MqttWireMessage.MESSAGE_TYPE_PUBACK);
		msgId = publish.getMessageId();
	}
	
	public MqttPubAck(int messageId) {
		super(MqttWireMessage.MESSAGE_TYPE_PUBACK);
		msgId = messageId;
	}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
            new MqttException("MqttPubAck");
           
		//return encodeMessageId();
                //
                return null;
	}
}
