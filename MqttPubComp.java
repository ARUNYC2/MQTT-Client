/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;


import org.paho.client.mqttv3.MqttExceptions.MqttException;


/**
 *
 * @author ARUN YADAV
 */
public class MqttPubComp extends MqttAck {
	public MqttPubComp(byte info, byte[] data) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_PUBCOMP);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}
	
	public MqttPubComp(MqttPublish publish) {
		super(MqttWireMessage.MESSAGE_TYPE_PUBCOMP);
		this.msgId = publish.getMessageId();
	}
	
	public MqttPubComp(int msgId) {
		super(MqttWireMessage.MESSAGE_TYPE_PUBCOMP);
		this.msgId = msgId;
	}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
             new MqttException("Class Name : MqttPubComp ,Method : getVarHeader");
                 //System.out.println("MqttEX in PubComp ");
		//return encodeMessageId();
                return null;
	}
}
