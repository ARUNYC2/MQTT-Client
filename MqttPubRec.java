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
public class MqttPubRec extends MqttAck {
	public MqttPubRec(byte info, byte[] data) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_PUBREC);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}
	
	public MqttPubRec(MqttPublish publish) {
		super(MqttWireMessage.MESSAGE_TYPE_PUBREC);
		msgId = publish.getMessageId();
	}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
             new MqttException("Class Name : MqttPubRec ,Method : getVarHead");
            //System.out.println("MqttEx PubRec");
		//return encodeMessageId();
                return null;
	}
}
