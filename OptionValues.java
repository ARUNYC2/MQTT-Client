/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.client.mqttv3.Ui;

/**
 *
 * @author ARUN YADAV
 */
import java.io.*;
import java.lang.*;
import java.util.*;

public class OptionValues 
   {
        public static String  ClientId;
        public static boolean CleanSession ;
        public  static int KeepAlive;
        public static String UserName;
        public static char [] Password;

     public  OptionValues( String  ClientId,boolean CleanSession ,int KeepAlive,
                           String UserName,char[] Password)
        {
        this.ClientId=ClientId;
        this.CleanSession=CleanSession;
        this.KeepAlive=KeepAlive;
        this.UserName=UserName;
        this.Password=Password;
       // System.out.println(""+ UserName);
        }
     
     
     
     
   
     
}
