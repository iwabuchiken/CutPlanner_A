package cp.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothClientThread extends Thread {
	//�N���C�A���g���̏���
	private final BluetoothSocket clientSocket;
	private final BluetoothDevice mDevice;
	private Context mContext;
	//UUID�̐���
	public static final UUID TECHBOOSTER_BTSAMPLE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	static BluetoothAdapter myClientAdapter;
	public String myNumber;
	
	//�R���X�g���N�^��`
	public 
	BluetoothClientThread
	(Context context, String myNum , BluetoothDevice device, BluetoothAdapter btAdapter){
		
		// Log
		String msg_Log = "BluetoothClientThread => instance created";
		Log.d("BluetoothClientThread.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		//�e�평��
		mContext = context;
		BluetoothSocket tmpSock = null;
		mDevice = device;
		myClientAdapter = btAdapter;
		myNumber = myNum;
		
		try{
			//���f�o�C�X��Bluetooth�N���C�A���g�\�P�b�g�̎擾
			tmpSock = device.createRfcommSocketToServiceRecord(TECHBOOSTER_BTSAMPLE_UUID);
		}catch(IOException e){
			e.printStackTrace();
		}
		clientSocket = tmpSock;
	}
	
	public void run(){
		
		// Log
		String msg_Log = "run()";
		Log.d("BluetoothClientThread.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		//�ڑ��v�����o���O�ɁA���������𒆒f����B
		if(myClientAdapter.isDiscovering()){
			
			// Log
			msg_Log = "myClientAdapter.isDiscovering()";
			Log.d("BluetoothClientThread.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			myClientAdapter.cancelDiscovery();			
		}

		try{
			
			// Log
			msg_Log = "connecting...";
			Log.d("BluetoothClientThread.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			//�T�[�o�[���ɐڑ��v��
			clientSocket.connect();
			
			// Log
//<<<<<<< HEAD
//			msg_Log = "connected";
//=======
			msg_Log = "socket => connected";
//>>>>>>> D-6_LAB-1_bluetooth
			Log.d("BluetoothClientThread.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}catch(IOException e){
			
			// Log
			msg_Log = "catch";
			Log.e("BluetoothClientThread.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e.printStackTrace();
			
	         try {
	        	 clientSocket.close();
	        	 
	        	 // Log
				msg_Log = "closed";
				Log.d("BluetoothClientThread.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
//				e.printStackTrace();
				
	         } catch (IOException closeException) {  
	        	 
	        	 // Log
				msg_Log = "closeException";
				Log.d("BluetoothClientThread.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
	        	 e.printStackTrace();
	         }
	         return;
		}
		
		//�ڑ��������̏���
		ReadWriteModel rw = new ReadWriteModel(mContext, clientSocket, myNumber);
		rw.start();
	}

	public void cancel() {
	        try {
	        	clientSocket.close();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	  }
}
