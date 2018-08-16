/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3.MqttExceptions;



import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author ARUN YADAV
 */
 public class MqttException extends Throwable{
    
        public static final short REASON_CODE_CLIENT_EXCEPTION  = 0x00;
        public static final short REASON_CODE_INVALID_PROTOCOL_VERSION	= 0x01;
	public static final short REASON_CODE_INVALID_CLIENT_ID   = 0x02;
	public static final short REASON_CODE_BROKER_UNAVAILABLE  = 0x03;
	public static final short REASON_CODE_FAILED_AUTHENTICATION = 0x04; 
	public static final short REASON_CODE_NOT_AUTHORIZED = 0x05;
       /** An unexpected error has occurred. */
        public static final short REASON_CODE_UNEXPECTED_ERROR	= 0x06;
	/** Error from subscribe - returned from the server. */
	public static final short REASON_CODE_SUBSCRIBE_FAILED	= 0x80;
	
	
	public static final short REASON_CODE_CLIENT_TIMEOUT = 32000;
        public static final short REASON_CODE_NO_MESSAGE_IDS_AVAILABLE   = 32001;
        public static final short REASON_CODE_WRITE_TIMEOUT     = 32002;
	public static final short REASON_CODE_CLIENT_CONNECTED  = 32100;
        public static final short REASON_CODE_CLIENT_ALREADY_DISCONNECTED   = 32101;
        public static final short REASON_CODE_CLIENT_DISCONNECTING          = 32102;
	/** Unable to connect to server */
	public static final short REASON_CODE_SERVER_CONNECT_ERROR  = 32103;
        public static final short REASON_CODE_CLIENT_NOT_CONNECTED    = 32104;
        public static final short REASON_CODE_SOCKET_FACTORY_MISMATCH    = 32105;
	public static final short REASON_CODE_SSL_CONFIG_ERROR = 32106;
        public static final short REASON_CODE_CLIENT_DISCONNECT_PROHIBITED  = 32107;
        public static final short REASON_CODE_INVALID_MESSAGE = 32108;
        public static final short REASON_CODE_CONNECTION_LOST  = 32109;
        public static final short REASON_CODE_CONNECT_IN_PROGRESS  = 32110;
        public static final short REASON_CODE_CLIENT_CLOSED   = 32111;
	public static final short REASON_CODE_TOKEN_INUSE = 32201;
	public static final short REASON_CODE_MAX_INFLIGHT  = 32202;
        public static final short REASON_CODE_DISCONNECTED_BUFFER_FULL	= 32203;

	
        
        private int reasonCode;
	private Throwable cause;
        private String reason=null;
    
    
    
    
  public MqttException(int reasonCode) {
		//super();
		this.reasonCode = reasonCode;
                if(reasonCode==0x00)
                    reason="REASON_CODE_CLIENT_EXCEPTION";
                else if(reasonCode==0x01)
                   reason="REASON_CODE_INVALID_PROTOCOL_VERSION"; 
                 else if(reasonCode==0x02)
                   reason="REASON_CODE_INVALID_CLIENT_ID"; 
                 else if(reasonCode==0x03)
                   reason="REASON_CODE_BROKER_UNAVAILABLE "; 
                 else if(reasonCode==0x04)
                   reason="REASON_CODE_FAILED_AUTHENTICATION "; 
                 else if(reasonCode==0x05)
                   reason="REASON_CODE_NOT_AUTHORIZED"; 
                else if(reasonCode==0x06)
                   reason="REASON_CODE_UNEXPECTED_ERROR"; 
                else if(reasonCode==0x80)
                   reason="REASON_CODE_SUBSCRIBE_FAILED"; 
                else 
                   reason="!!!    SOMETHING_WRONG   !!!"; 
                
               JOptionPane.showMessageDialog(null," Mqtt Exception Message ::  "+reason );
             
                
	}

    public MqttException(IOException ex,String msg)
    {
        JOptionPane.showMessageDialog(null," !!!   ##  _Mqtt IOException_   ## !!! \n"
                + msg);
       
    }
    
    public MqttException(Throwable cause) {
		super();
		this.reasonCode = REASON_CODE_CLIENT_EXCEPTION;
		this.cause = cause;
	}
     public MqttException(String msg)
     {
       JOptionPane.showMessageDialog(null,"!!  Exception  IN !!   \n "+msg);  
     }
    
    
}

    
    

