/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3;

/**
 *
 * @author ARUN YADAV
 * 
 */
public class MqttPingResp extends MqttAck {
	public static final String KEY = "Ping";
	
	public MqttPingResp(byte info, byte[] variableHeader) {
		super(MqttWireMessage.MESSAGE_TYPE_PINGRESP);}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
		// Not needed, as the client never encodes a PINGRESP
		return new byte[0];
	}
	
	
        
        public boolean isMessageIdRequired() {
		return false;
	}
	
	
        
        public String getKey() {
		return KEY;
	}


}
