package com.bhs.util;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;


public class ReadwritDataFromProps {

	/**
	 * @param args
	 */
	public static Properties props;
	static{
		try{
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\bhs\\config\\bhsdata.properties");
		props= new Properties();
		props.load(fs);
		
		}
		catch(Exception e){
			
			
		}
		
	}
	
	
	public static void writeDyanamicData(String key,String value) {
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream(System.getProperty("user.dir")+"\\src\\com\\bhs\\config\\bhsdata.properties");
			props.setProperty(key, value);
			props.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
	
	/**
	 * PURPOSE: To set dynamic data int props file
	 * @author vverimadugu
	 * @param String key[]
	 * @param int value[]
	 * @return String
	 * 	 **/
	public static void writeDyanamicData(String key[],String value[]) {
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream(System.getProperty("user.dir")+"\\src\\com\\bhs\\config\\bhsdata.properties");
			for(int i=0;i<key.length;i++)
				props.setProperty(key[i], value[i]);
			
			props.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
}
