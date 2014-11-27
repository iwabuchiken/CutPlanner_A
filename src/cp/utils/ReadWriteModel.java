package cp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import cp.main.StreamActivity;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class ReadWriteModel extends Thread {
	//�\�P�b�g�ɑ΂���I/O����
	
	public static InputStream in;
	public static OutputStream out;
	private String sendNumber;
	private Context mContext;
		
	//�R���X�g���N�^�̒�`
	public ReadWriteModel(Context context, BluetoothSocket socket, String string){
		sendNumber = string;
		mContext = context;
		
		try {
			//�ڑ��ς݃\�P�b�g����I/O�X�g���[�������ꂼ��擾
			in = socket.getInputStream();
			out = socket.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	public void write(byte[] buf){
		//Output�X�g���[���ւ̃f�[�^��������
		try {
			out.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		byte[] buf = new byte[1024];
		String rcvNum = null;
		int tmpBuf = 0;
		
		try {
			write(sendNumber.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
			try {
				tmpBuf = in.read(buf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(tmpBuf!=0){
				try {
					rcvNum = new String(buf, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// Log
			String msg_Log = "intent starting => StreamActivity";
			Log.d("ReadWriteModel.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Intent i = new Intent(mContext, StreamActivity.class);
			i.putExtra("NUMBER", rcvNum);
			mContext.startActivity(i);
		}

	}
}
