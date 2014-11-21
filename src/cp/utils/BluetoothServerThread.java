package cp.utils;

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
	}
	
	public void run(){
		BluetoothSocket receivedSocket = null;		
		while(true){
			try{
				//�N���C�A���g������̐ڑ��v���҂��B�\�P�b�g���Ԃ����B
				receivedSocket = servSock.accept();
			}catch(IOException e){
				break;
			}
		
			if(receivedSocket != null){
				//�\�P�b�g���󂯎��Ă���(�ڑ�������)�̏���
				//RwClass��manageSocket���ڂ�
				ReadWriteModel rw = new ReadWriteModel(mContext, receivedSocket, myNumber);
				rw.start();
			
				try {
					//���������������\�P�b�g�͕���B
					servSock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            break;
			}		
		}
	}

	public void cancel() {
	        try {
	        	servSock.close();
	        } catch (IOException e) { }
	    }
}
