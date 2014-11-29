package cp.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class BluetoothServerThread extends Thread {
	//�T�[�o�[���̏���
	//UUID�FBluetooth�v���t�@�C�����Ɍ��߂�ꂽ�l
	private final BluetoothServerSocket servSock;
	static BluetoothAdapter myServerAdapter;
	private Context mContext;
	//UUID�̐���
	public static final UUID TECHBOOSTER_BTSAMPLE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	public String myNumber;

	//�R���X�g���N�^�̒�`
	public BluetoothServerThread(Context context,String myNum, BluetoothAdapter btAdapter){
		//�e�평��
		mContext = context;
		BluetoothServerSocket tmpServSock = null;
		myServerAdapter = btAdapter;
		myNumber = myNum;
		try{
			//���f�o�C�X��Bluetooth�T�[�o�[�\�P�b�g�̎擾
			 tmpServSock = myServerAdapter.listenUsingRfcommWithServiceRecord("BlueToothSample03", TECHBOOSTER_BTSAMPLE_UUID);
		}catch(IOException e){
			e.printStackTrace();
		}
		servSock = tmpServSock;
		
		// Log
		String msg_Log = "BluetoothServerThread => instantiated";
		Log.i("BluetoothServerThread.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
	}
	
	public void run(){
		
		String msg_Log;
		
		// Log
		msg_Log = "BluetoothServerThread => running...";
		Log.d("BluetoothServerThread.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		BluetoothSocket receivedSocket = null;		
		
		while(true){
			
			// Log
			msg_Log = "while loop => starting...";
			Log.d("BluetoothServerThread.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			try{
				//�N���C�A���g������̐ڑ��v���҂��B�\�P�b�g���Ԃ����B
				
				if (servSock != null) {
					
					// Log
					msg_Log = "servSock != null";
					Log.d("BluetoothServerThread.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
					receivedSocket = servSock.accept();
					
					// Log
					msg_Log = "servSock => accepted";
					Log.d("BluetoothServerThread.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {

					// Log
					msg_Log = "servSock => null";
					Log.e("BluetoothServerThread.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
				}
				
			}catch(IOException e){
				
				// Log
				msg_Log = "IOException";
				Log.e("BluetoothServerThread.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				e.printStackTrace();
				
				break;
			}
		
			// Log
			msg_Log = "try~catch => done";
			Log.d("BluetoothServerThread.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			if(receivedSocket != null){
				//�\�P�b�g���󂯎��Ă���(�ڑ�������)�̏���
				//RwClass��manageSocket���ڂ�
				ReadWriteModel rw = new ReadWriteModel(mContext, receivedSocket, myNumber);
				rw.start();

	            // Log
				msg_Log = "ReadWriteModel => started";
				Log.d("BluetoothServerThread.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);

				try {
					//���������������\�P�b�g�͕���B
					servSock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            break;
	            
			} else {
				
				// Log
				msg_Log = "receivedSocket => null";
				Log.d("BluetoothServerThread.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}
			
		}
	}

	public void cancel() {
	        try {
	        	servSock.close();
	        } catch (IOException e) { }
	    }
}
