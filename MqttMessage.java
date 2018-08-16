
package org.paho.client.mqttv3;

/**
 *
 * @author ARUN YADAV
 */
public class MqttMessage {
    
        private boolean mutable = true;
	private byte[] payload;
	private int qos = 1;
	private boolean retained = false;
	private boolean dup = false;
	private int messageId;
        
        
        public static void validateQos(int qos) {
		if ((qos < 0) || (qos > 2)) {
			throw new IllegalArgumentException();
		}
	}
        
        
        public MqttMessage() {
		setPayload(new byte[]{});
	}

	
	public MqttMessage(byte[] payload) {
		setPayload(payload);
	}

	
	public byte[] getPayload() {
		return payload;
	}

	
	public void clearPayload() {
		checkMutable();
		this.payload = new byte[] {};
	}

	
	public void setPayload(byte[] payload) {
		checkMutable();
		if (payload == null) {
			throw new NullPointerException();
		}
		this.payload = payload;
	}

	
	public boolean isRetained() {
		return retained;
	}

	
	public void setRetained(boolean retained) {
		checkMutable();
		this.retained = retained;
	}

	
	public int getQos() {
		return qos;
	}

	
	public void setQos(int qos) {
		checkMutable();
		validateQos(qos);
		this.qos = qos;
	}

	
	public String toString() {
		return new String(payload);
	}

	
	protected void setMutable(boolean mutable) {
		this.mutable = mutable;
	}

	protected void checkMutable() throws IllegalStateException {
		if (!mutable) {
			throw new IllegalStateException();
		}
	}

	protected void setDuplicate(boolean dup) {
		this.dup = dup;
	}

	  // This will only be set on messages received from  the server.

	public boolean isDuplicate() {
		return this.dup;
	}
	
	
	//  This is only to be used internally to provide the MQTT id of a message
	 // received from the server. 
	public void setId(int messageId) {
		this.messageId = messageId;
	}

	// Returns the MQTT id of the message. 
	public int getId() {
		return this.messageId;
    }
}
