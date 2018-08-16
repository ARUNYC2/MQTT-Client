/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

import org.paho.client.mqttv3.MqttExceptions.MqttException;
/**
 *
 * @author ARUN YADAV
 */
import java.io.IOException;

public class MqttPingReq extends MqttWireMessage
{
   public static final String KEY = "Ping";

	public MqttPingReq() {
		super(MqttWireMessage.MESSAGE_TYPE_PINGREQ);
	}

	public MqttPingReq(byte info, byte[] variableHeader) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_PINGREQ);
	}
	
	/**
	 * Returns <code>false</code> as message IDs are not required for MQTT
	 * PINGREQ messages.
	 */
	public boolean isMessageIdRequired() {
		return false;
	}

	protected byte[] getVariableHeader() //throws MqttException 
        {
		return new byte[0];
	}
	
	protected byte getMessageInfo() {
		return 0;
	}
	
	public String getKey() {
		return KEY;
	}
}

