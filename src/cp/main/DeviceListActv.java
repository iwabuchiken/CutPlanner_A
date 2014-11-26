package cp.main;

import java.util.ArrayList;
import java.util.Set;

import cp.listeners.button.BO_CL;
import cp.listeners.button.BO_TL;
import cp.listeners.view.V_OCL;
import cp.listeners.view.V_OTL;
import cp.utils.BluetoothClientThread;
import cp.utils.CONS;
import cp.utils.Methods;
import cp.utils.Methods_dlg;
import cp.utils.Tags;
import cp.views.CV;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeviceListActv extends Activity {

	public static Vibrator vib;

//	private BluetoothAdapter mBtAdapter = CONS.BT.mBtAdapter;
	private ArrayList<BluetoothDevice> foundDeviceList = new ArrayList<BluetoothDevice>();
	static int offSet = 0;
	public static BluetoothClientThread BtClientThread;
//	public String myNumber;
	public Context mContext;

	private final int REQUEST_CODE_DISCOEVERABLE = 1;

	private boolean registered = false;

//	private BluetoothAdapter mBtAdapter = BlueToothSample.mBtAdapter;
//	private ArrayAdapter<String> pairedDeviceAdapter;
//	private ArrayAdapter<String> nonPairedDeviceAdapter;
//	private ArrayList<BluetoothDevice> foundDeviceList = new ArrayList<BluetoothDevice>();
//	
//	static int offSet = 0;
	
//	public static final String ACTION_DISCOVERY_STARTED = BluetoothAdapter.ACTION_DISCOVERY_STARTED;
//	public static final String ACTION_FOUND = BluetoothDevice.ACTION_FOUND;
//	public static final String ACTION_NAME_CHANGED = BluetoothDevice.ACTION_NAME_CHANGED;
//	public static final String ACTION_DISCOVERY_FINISHED = BluetoothAdapter.ACTION_DISCOVERY_FINISHED;
	
//	public String myNumber;
//	public Context mContext;
	
// 	public static BluetoothClientThread BtClientThread;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.devicelist);
        mContext = this;
        
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        // �d�b�ԍ����擾
        CONS.BT.myNumber = telephonyManager.getLine1Number();
        if(CONS.BT.myNumber == null){
        	CONS.BT.myNumber = "09011112222";
        }
        
        // Log
		String msg_Log = "CONS.BT.myNumber => " + CONS.BT.myNumber;
		Log.d("DeviceListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
        
        //�ڑ������̂���f�o�C�X���擾
        CONS.BT.pairedDeviceAdapter = new ArrayAdapter<String>(this, R.layout.rowdata);
        //BluetoothAdapter����A�ڑ������̂���f�o�C�X�̏����擾
        Set<BluetoothDevice> pairedDevices = CONS.BT.mBtAdapter.getBondedDevices();
        if(pairedDevices.size() > 0) {
        	
        	// Log
			msg_Log = "paired devices => found: " + pairedDevices.size();
			Log.d("DeviceListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
        	
        	//�ڑ������̂���f�o�C�X�����݂���
        	for(BluetoothDevice device:pairedDevices){
        		//�ڑ������̂���f�o�C�X�̏������Ɏ擾���ăA�_�v�^�ɋl�߂�
        		//getName()�E�E�E�f�o�C�X���擾���\�b�h
        		//getAddress()�E�E�E�f�o�C�X��MAC�A�h���X�擾���\�b�h
        		
        		CONS.BT.pairedDeviceAdapter.add(device.getName() + "\n" + device.getAddress());
        		
        		foundDeviceList.add(device);
        		
        		offSet++;
        		
        	}
        	
        	ListView deviceList = (ListView)findViewById(R.id.pairedDeviceList);
        	
        	deviceList.setAdapter(CONS.BT.pairedDeviceAdapter);
        	
        	deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        		//�f�o�C�X���X�g�I�����̏���
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					// TODO Auto-generated method stub
					ListView listView = (ListView) parent;
					BluetoothDevice device = foundDeviceList.get(position);
					BtClientThread = new BluetoothClientThread(
								mContext, 
								CONS.BT.myNumber, 
								device, 
								CONS.BT.mBtAdapter);
					
					BtClientThread.start();
					
				}
				
        	});
        	
        }//if(pairedDevices.size() > 0)
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
        ////////////////////////////////

		// listener

		////////////////////////////////
		this._Setup__SetListener();
		
    }
    
    @Override
    public void onResume() {
        super.onResume(); 
        //�T�[�o�[�X���b�h�N���A�N���C�A���g�̂���̗v���҂����J�n
		CONS.BT.BtServerThread = new BluetoothServerThread(this, CONS.BT.myNumber , CONS.BT.mBtAdapter); 
		
//<<<<<<< HEAD
		CONS.BT.BtServerThread.start();
//=======
//		////////////////////////////////
//
//		// ACTION_REQUEST_DISCOVERABLE
//
//		////////////////////////////////
////		this.finishActivity(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//		
//		this.finishActivity(CONS.Intent.REQUEST_CODE_DISCOEVERABLE);
//>>>>>>> D-6_LAB-1_bluetooth
		
		// Log
		String msg_Log = "server thread => started";
		Log.d("DeviceListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//<<<<<<< HEAD
//    }
   
//    @Override
//    public void onPause() {
//        super.onPause(); 
//        if(BtClientThread.){
//        	BtClientThread.cancel();
//        }
//    }
    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//    	super.onCreateOptionsMenu(menu);
//    	menu.add(0, Menu.FIRST, Menu.NONE, "Detect new devices");
////=======
	}//protected void onDestroy()

	@Override
	public void onBackPressed() {
		/****************************
		 * memo
			****************************/
//		Methods.stop_Player(this);

		////////////////////////////////

		// disable bt

		////////////////////////////////
		if (CONS.BT.mBtAdapter.isEnabled()) {
			
			CONS.BT.mBtAdapter.disable();
			
			// Log
			String msg_Log = "BT adapter => disabled";
			Log.d("DeviceListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		this.finish();
		
		overridePendingTransition(0, 0);
		
	}//public void onBackPressed()

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		
//		// Log
//		String msg_Log = "onKeyDown()";
//		Log.d("DeviceListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		
//		Methods.confirm_quit(this, keyCode);
//		
//		return super.onKeyDown(keyCode, event);
//	}

	@Override
	public boolean 
	onCreateOptionsMenu
	(Menu menu) {
		// 
//		MenuInflater mi = getMenuInflater();
//		mi.inflate(R.menu.menu_main, menu);

		super.onCreateOptionsMenu(menu);
		
		menu.add(0, Menu.FIRST, Menu.NONE, "Detect new device");
//>>>>>>> D-6_LAB-1_bluetooth
		return true;
    }
    
    @Override
    public boolean 
    onOptionsItemSelected(MenuItem item) {
    	//�I�v�V�������j���[���I�����ꂽ���̏���
        //���f�o�C�X�̌��o��L��ɂ���
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//        startActivity(discoverableIntent);
        
//<<<<<<< HEAD
//=======
//        startActivity(discoverableIntent);
        this.startActivityForResult(discoverableIntent, CONS.Intent.REQUEST_CODE_DISCOEVERABLE);
        
        // Log
		String msg_Log = "ACTION_REQUEST_DISCOVERABLE => started";
		Log.d("DeviceListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

//>>>>>>> D-6_LAB-1_bluetooth
    	TextView nonPairedListTitle = (TextView)findViewById(R.id.nonPairedListTitle);
    	nonPairedListTitle.setText("list of devices with no history");
    	
    	if(item.getItemId() == Menu.FIRST){
    		//�C���e���g�t�B���^�[��BroadcastReceiver�̓o�^
	        IntentFilter filter = new IntentFilter();
	        filter.addAction(CONS.BT.ACTION_DISCOVERY_STARTED);
	        filter.addAction(CONS.BT.ACTION_FOUND);
	        filter.addAction(CONS.BT.ACTION_NAME_CHANGED);
	        filter.addAction(CONS.BT.ACTION_DISCOVERY_FINISHED);
	        registerReceiver(DevieFoundReceiver, filter);
	        
	        this.registered  = true;
	        
    		CONS.BT.nonPairedDeviceAdapter = new ArrayAdapter<String>(this, R.layout.rowdata);
	        //�ڑ��\�ȃf�o�C�X�����o
	        if(CONS.BT.mBtAdapter.isDiscovering()){
	        	//�������̏ꍇ�͌��o���L�����Z������
	        	CONS.BT.mBtAdapter.cancelDiscovery();
	        }
	        //�f�o�C�X����������
	        //��莞�Ԃ̊Ԍ��o���s��
	        CONS.BT.mBtAdapter.startDiscovery();
    	}
    	
		return false;
		
    }//onOptionsItemSelected
    
    private final BroadcastReceiver DevieFoundReceiver = new BroadcastReceiver(){
    	//���o���ꂽ�f�o�C�X����̃u���[�h�L���X�g���󂯂�
    	@Override
    	public void onReceive(Context context, Intent intent){
    		String action = intent.getAction();
    		String dName = null;
    		BluetoothDevice foundDevice;

    		ListView nonpairedList = (ListView)findViewById(R.id.nonPairedDeviceList);
    		
    		nonpairedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        		//�f�o�C�X���X�g�I�����̏���
    			@Override
    			public void 
    			onItemClick
    			(AdapterView<?> parent, View v, int position, long id) {
    				// TODO Auto-generated method stub
    				ListView listView = (ListView) parent;
//<<<<<<< HEAD
//    				BluetoothDevice device = foundDeviceList.get(offSet + position);
//    				BtClientThread = new BluetoothClientThread(mContext, CONS.BT.myNumber, device, mBtAdapter);
//=======
    				
    				BluetoothDevice device = CONS.BT.foundDeviceList.get(offSet + position);
//    				BluetoothDevice device = foundDeviceList.get(offSet + position);
    				
    				BtClientThread = new BluetoothClientThread(
    							mContext, 
    							CONS.BT.myNumber, 
    							device, 
    							CONS.BT.mBtAdapter);
//>>>>>>> D-6_LAB-1_bluetooth
    				BtClientThread.start();
    			}//onItemClick
    			
        	});//nonpairedList.setOnItemClickListener
    		
    		if(CONS.BT.ACTION_DISCOVERY_STARTED.equals(action)){
//    			Log("ACTION_DISCOVERY_STARTED");
    			
    			// Log
				String msg_Log = "ACTION_DISCOVERY_STARTED";
				Log.d("DeviceListActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
    			
    		}
    		
    		if(CONS.BT.ACTION_FOUND.equals(action)){
    			//�f�o�C�X�����o���ꂽ
    			foundDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    			if((dName = foundDevice.getName()) != null){
		    		if(foundDevice.getBondState() != BluetoothDevice.BOND_BONDED){
		    			//�ڑ��������Ƃ̂Ȃ��f�o�C�X�̂݃A�_�v�^�ɋl�߂�
		        		CONS.BT.nonPairedDeviceAdapter.add(dName + "\n" + foundDevice.getAddress());  
	        			Log.d("ACTION_FOUND", dName);
	        			//�������ꂽ�f�o�C�X�����X�g�Ɋi�[���Ă���
	        			foundDeviceList.add(foundDevice);
		    		}
    			}
            	nonpairedList.setAdapter(CONS.BT.nonPairedDeviceAdapter);
    		}    
    		
    		if(CONS.BT.ACTION_NAME_CHANGED.equals(action)){
    			//���O�����o���ꂽ
    			if (dName != null) {
					
    				Log.d("ACTION_NAME_CHANGED", dName);
    				
				} else {
					
					// Log
					String msg_Log = "ACTION_NAME_CHANGED: dName => null";
					Log.d("DeviceListActv.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);

				}
    			
    			foundDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	    		if(foundDevice.getBondState() != BluetoothDevice.BOND_BONDED){
	    			//�ڑ��������Ƃ̂Ȃ��f�o�C�X�̂݃A�_�v�^�ɋl�߂�
//<<<<<<< HEAD
//	        		CONS.BT.nonPairedDeviceAdapter.add(dName + "\n" + foundDevice.getAddress());    				
//=======
	    		
	    			if (dName != null) {
						
	    				CONS.BT.nonPairedDeviceAdapter.add(dName + "\n" + foundDevice.getAddress());    				
	    				
	    				// Log
						String msg_Log = "added => " + dName + "\n" + foundDevice.getAddress();
						Log.d("DeviceListActv.java"
								+ "["
								+ Thread.currentThread().getStackTrace()[2]
										.getLineNumber() + "]", msg_Log);
					}
	    			
//>>>>>>> D-6_LAB-1_bluetooth
	    		}
	    		
            	nonpairedList.setAdapter(CONS.BT.nonPairedDeviceAdapter);
    		}

    		if(CONS.BT.ACTION_DISCOVERY_FINISHED.equals(action)){
//    			Log("�X�L�����I��");
    			// Log
				String msg_Log = "ACTION_DISCOVERY_FINISHED";
				Log.d("DeviceListActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
//<<<<<<< HEAD
				
				if (CONS.BT.nonPairedDeviceAdapter.getCount() < 1) {
					
					String msg = "No device found";
					Methods_dlg.dlg_ShowMessage((Activity) mContext, msg, R.color.gold2);
					
				}
//=======
    			
				////////////////////////////////

				// update adapter

				////////////////////////////////
				// Log
				msg_Log = "CONS.BT.nonPairedDeviceAdapter: size => "
							+ CONS.BT.nonPairedDeviceAdapter.getCount();
				Log.d("DeviceListActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				
//>>>>>>> D-6_LAB-1_bluetooth
    		}
    		
    	}//public void onReceive(Context context, Intent intent)
    };
    
	public void sockClose() {
		BtClientThread.cancel();
    }
	
    public void Log(String string){
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();    	
		
		// Log
		String msg_Log = "msg => " + string;
		Log.d("DeviceListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
    }

	
//    /** Called when the activity is first created. */
//    @Override
//     public void onCreate(Bundle savedInstanceState) {
//    	/*----------------------------
//		 * 1. super
//		 * 2. Set content
//		 * 2-2. Set title
//		 * 3. Initialize => vib
//		 * 
//		 *  4. Set list
//		 *  5. Set listener => Image buttons
//		 *  6. Set path label
//		 *  
//		 *  7. Initialize preferences
//		 *  
//		 *  8. Refresh DB
//			----------------------------*/
//		
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.devicelist);
////        setContentView(R.layout.actv_main);
//        
//        /*----------------------------
//		 * 2-2. Set title
//			----------------------------*/
//		this.setTitle(this.getClass().getName());
//    
//		mContext = this;
//		
//        vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
//
//        
//    }//public void onCreate(Bundle savedInstanceState)
//
    @Override
	protected void onDestroy() {
		/*----------------------------
		 * 1. Reconfirm if the user means to quit
		 * 2. super
		 * 3. Clear => prefs_main
		 * 4. Clear => list_root_dir
			----------------------------*/
		// Log
		Log.d("DeviceListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onDestroy()");
		
		super.onDestroy();
		
		String msg_Log;
		
		////////////////////////////////

		// unregister

		////////////////////////////////
		if (this.registered == true) {
			
			this.unregisterReceiver(this.DevieFoundReceiver);
			
			// Log
			msg_Log = "receiver => unregistered";
			Log.d("DeviceListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			this.registered = false;
			
		}
		
		////////////////////////////////

		// ACTION_REQUEST_DISCOVERABLE

		////////////////////////////////
//		this.finishActivity(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		
		this.finishActivity(this.REQUEST_CODE_DISCOEVERABLE);
		
		// Log
		msg_Log = "discoverable => finished";
		Log.d("DeviceListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// server thread

		////////////////////////////////
//<<<<<<< HEAD
		if (CONS.BT.BtServerThread != null) {
//				&& CONS.BT.BtServerThread.isAlive()) {
//=======
//		if (CONS.BT.mBtAdapter == null) {
//>>>>>>> D-6_LAB-1_bluetooth
			
			CONS.BT.BtServerThread.cancel();
			
//<<<<<<< HEAD
			// Log
			msg_Log = "CONS.BT.BtServerThread => cancelled";
			Log.d("DeviceListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
//		}
//=======
		}
		
		CONS.BT.pairedDeviceAdapter = new ArrayAdapter<String>(this, R.layout.rowdata);
		
		Set<BluetoothDevice> pairedDevices = CONS.BT.mBtAdapter.getBondedDevices();
		
        if(pairedDevices.size() > 0){

        	// Log
			msg_Log = "pairedDevices.size() > 0";
			Log.i("DeviceListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

        	for(BluetoothDevice device:pairedDevices){
        		//�ڑ������̂���f�o�C�X�̏������Ɏ擾���ăA�_�v�^�ɋl�߂�
        		//getName()�E�E�E�f�o�C�X���擾���\�b�h
        		//getAddress()�E�E�E�f�o�C�X��MAC�A�h���X�擾���\�b�h
        		
        		CONS.BT.pairedDeviceAdapter.add(device.getName() + "\n" + device.getAddress());
        		
        		CONS.BT.foundDeviceList.add(device);
        		
        		offSet++;
        		
        	}
        	ListView deviceList = (ListView)findViewById(R.id.pairedDeviceList);
        	deviceList.setAdapter(CONS.BT.pairedDeviceAdapter);
        	deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        		//�f�o�C�X���X�g�I�����̏���
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					// TODO Auto-generated method stub
					ListView listView = (ListView) parent;
					BluetoothDevice device = CONS.BT.foundDeviceList.get(position);
					BtClientThread = new BluetoothClientThread(
								mContext, 
								CONS.BT.myNumber, 
								device, 
								CONS.BT.mBtAdapter);
					
					BtClientThread.start();
				}
        	});
        	
        } else {
        	
        	// Log
			msg_Log = "pairedDevices.size() <= 0";
			Log.i("DeviceListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
        }

	}

	private void 
	_Setup__SetListener() {
		// TODO Auto-generated method stub
		
		Button bt_Options = (Button) findViewById(R.id.devicelist_bt);
		
		bt_Options.setTag(Tags.ButtonTags.ACTV_DEVLIST_BT_OPTIONS);
		
		bt_Options.setOnTouchListener(new BO_TL(this));
		bt_Options.setOnClickListener(new BO_CL(this, this.DevieFoundReceiver));
		
	}//_Setup__SetListener

	private void 
	_Setup() {
		// TODO Auto-generated method stub
//>>>>>>> D-6_LAB-1_bluetooth
		
	}//protected void onDestroy()
//
//	@Override
//	public void onBackPressed() {
//		/****************************
//		 * memo
//			****************************/
////		Methods.stop_Player(this);
//
//		////////////////////////////////
//
//		// disable bt
//
//		////////////////////////////////
//		if (this.mBtAdapter.isEnabled()) {
//			
//			this.mBtAdapter.disable();
//			
//			// Log
//			String msg_Log = "BT adapter => disabled";
//			Log.d("DeviceListActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		}
//		
//		this.finish();
//		
//		overridePendingTransition(0, 0);
//		
//	}//public void onBackPressed()
//
//	@Override
//	public boolean 
//	onCreateOptionsMenu
//	(Menu menu) {
//		// 
////		MenuInflater mi = getMenuInflater();
////		mi.inflate(R.menu.menu_main, menu);
//
//		super.onCreateOptionsMenu(menu);
//		
//		menu.add(0, Menu.FIRST, Menu.NONE, "Detect new device");
//		return true;
//		
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
////		switch (item.getItemId()) {
//
//        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//        
////        startActivity(discoverableIntent);
//        this.startActivityForResult(discoverableIntent, this.REQUEST_CODE_DISCOEVERABLE);
//        
//        // Log
//		String msg_Log = "ACTION_REQUEST_DISCOVERABLE => started";
//		Log.d("DeviceListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//
//    	TextView nonPairedListTitle = (TextView)findViewById(R.id.nonPairedListTitle);
//    	nonPairedListTitle.setText("list of devices with no history");
//
//    	if(item.getItemId() == Menu.FIRST){
//    		//�C���e���g�t�B���^�[��BroadcastReceiver�̓o�^
//	        IntentFilter filter = new IntentFilter();
//	        filter.addAction(CONS.BT.ACTION_DISCOVERY_STARTED);
//	        filter.addAction(CONS.BT.ACTION_FOUND);
//	        filter.addAction(CONS.BT.ACTION_NAME_CHANGED);
//	        filter.addAction(CONS.BT.ACTION_DISCOVERY_FINISHED);
//	        registerReceiver(DevieFoundReceiver, filter);
//	        
//    		CONS.BT.nonPairedDeviceAdapter = new ArrayAdapter<String>(this, R.layout.rowdata);
//	        //�ڑ��\�ȃf�o�C�X�����o
//	        if(mBtAdapter.isDiscovering()){
//	        	//�������̏ꍇ�͌��o���L�����Z������
//	        	mBtAdapter.cancelDiscovery();
//	        }
//	        //�f�o�C�X����������
//	        //��莞�Ԃ̊Ԍ��o���s��
//	        mBtAdapter.startDiscovery();
//    	}
//
//		
////		case R.id.opt_menu_main_db://----------------------------------
////			
////			Methods_dlg.dlg_Db_Activity(this);
////			
////			break;// case R.id.main_opt_menu_create_folder
//			
////		}//switch (item.getItemId())
//		
////		return super.onOptionsItemSelected(item);
//		
//		return false;
//		
//	}//public boolean onOptionsItemSelected(MenuItem item)
//
//    private final BroadcastReceiver DevieFoundReceiver = new BroadcastReceiver(){
//    	//���o���ꂽ�f�o�C�X����̃u���[�h�L���X�g���󂯂�
//    	@Override
//    	public void onReceive(Context context, Intent intent){
//    		String action = intent.getAction();
//    		String dName = null;
//    		BluetoothDevice foundDevice;
//
//    		ListView nonpairedList = (ListView)findViewById(R.id.nonPairedDeviceList);
//    		
//    		nonpairedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        		//�f�o�C�X���X�g�I�����̏���
//    			@Override
//    			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//    				// TODO Auto-generated method stub
//    				ListView listView = (ListView) parent;
//    				
//    				BluetoothDevice device = CONS.BT.foundDeviceList.get(offSet + position);
////    				BluetoothDevice device = foundDeviceList.get(offSet + position);
//    				
//    				BtClientThread = new BluetoothClientThread(mContext, myNumber, device, mBtAdapter);
//    				BtClientThread.start();
//    			}
//        	});
//    		
//    		if(CONS.BT.ACTION_DISCOVERY_STARTED.equals(action)){
//    			
//    			////////////////////////////////
//
//				// listview => reset
//
//				////////////////////////////////
//    			nonpairedList.setAdapter(CONS.BT.nonPairedDeviceAdapter);
//    			
//    			// Log
//				String msg_Log = "ACTION_DISCOVERY_STARTED";
//				Log.d("DeviceListActv.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", msg_Log);
//    			
//    		}
//    		
//    		if(CONS.BT.ACTION_FOUND.equals(action)){
//    			//�f�o�C�X�����o���ꂽ
//    			foundDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//    			if((dName = foundDevice.getName()) != null){
//		    		if(foundDevice.getBondState() != BluetoothDevice.BOND_BONDED){
//		    			//�ڑ��������Ƃ̂Ȃ��f�o�C�X�̂݃A�_�v�^�ɋl�߂�
//		    			CONS.BT.nonPairedDeviceAdapter.add(dName + "\n" + foundDevice.getAddress());  
////	        			Log.d("ACTION_FOUND", dName);
//		    			
//		    			// Log
//						String msg_Log = "ACTION_FOUND => " + dName;
//						Log.d("DeviceListActv.java"
//								+ "["
//								+ Thread.currentThread().getStackTrace()[2]
//										.getLineNumber() + "]", msg_Log);
//		    			
//	        			//�������ꂽ�f�o�C�X�����X�g�Ɋi�[���Ă���
//	        			CONS.BT.foundDeviceList.add(foundDevice);
////	        			foundDeviceList.add(foundDevice);
//		    		}
//    			}
//            	nonpairedList.setAdapter(CONS.BT.nonPairedDeviceAdapter);
//    		}    
//    		
//    		if(CONS.BT.ACTION_NAME_CHANGED.equals(action)){
//    			//���O�����o���ꂽ
//    			Log.d("ACTION_NAME_CHANGED", dName);
//    			foundDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//	    		if(foundDevice.getBondState() != BluetoothDevice.BOND_BONDED){
//	    			//�ڑ��������Ƃ̂Ȃ��f�o�C�X�̂݃A�_�v�^�ɋl�߂�
//	    			CONS.BT.nonPairedDeviceAdapter.add(dName + "\n" + foundDevice.getAddress());    				
//	    		}
//            	nonpairedList.setAdapter(CONS.BT.nonPairedDeviceAdapter);
//    		}
//
//    		if(CONS.BT.ACTION_DISCOVERY_FINISHED.equals(action)){
//    			
//    			if (CONS.BT.nonPairedDeviceAdapter == null) {
//					
//    				String msg = "adapter => null";
//					Methods_dlg.dlg_ShowMessage((Activity)mContext, msg, R.color.red);
//					
//				} else if (CONS.BT.nonPairedDeviceAdapter.getCount() < 1) {
//					
//					String msg = "devices => not found";
//					Methods_dlg.dlg_ShowMessage((Activity)mContext, msg, R.color.gold2);
//					
//				}
//    			
//    			// Log
//				String msg_Log = "ACTION_DISCOVERY_FINISHED";
//				Log.d("DeviceListActv.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", msg_Log);
//    			
//    		}
//    	}
//    };
//
//	@Override
//	protected void onPause() {
//		
//		super.onPause();
//
//		Log.d("DeviceListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "onPause()");
//
////		// Log
////		Log.d("DeviceListActv.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", "prefs_main: " + Methods.get_currentPath_from_prefs(this));
//		
//		
//	}
//
//	@Override
//	protected void onResume() {
//		/*********************************
//		 * 1. super
//		 * 2. Set enables
//		 *********************************/
//		super.onResume();
//
//		Log.d("DeviceListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "onResume()");
//
//		
//		/*********************************
//		 * 2. Set enables
//		 *********************************/
////		ImageButton ib_up = (ImageButton) findViewById(R.id.v1_bt_up);
////		
////		String curDirPath = Methods.get_currentPath_from_prefs(this);
////		
////		if (curDirPath.equals(dname_base)) {
////			
////			ib_up.setEnabled(false);
////			
////			ib_up.setImageResource(R.drawable.ifm8_up_disenabled);
////			
////		} else {//if (this.currentDirPath == this.dpath_base)
////		
////			ib_up.setEnabled(true);
////
////			
////			ib_up.setImageResource(R.drawable.ifm8_up);
////		
////		}//if (this.currentDirPath == this.dpath_base)
//		
//	}//protected void onResume()
//
//	@Override
//	protected void onStart() {
//		
//		super.onStart();
//		
////		_test_DrawLine();
//		
//		this._Setup();
//		
//		////////////////////////////////
//
//		// listener
//
//		////////////////////////////////
//		
//		_Setup__SetListener();
//
//		////////////////////////////////
//
//		// LAB-1: bluetooth
//
//		////////////////////////////////
//		_Bluetooth();
//		
//		////////////////////////////////
//
//		// list: nonpaired
//
//		////////////////////////////////
//		_Setup_List_Nonpaired();
//		
//		////////////////////////////////
//
//		// TelephonyManager
//
//		////////////////////////////////
//		_Setup_Telephony();
//		
//	}//protected void onStart()
//
//	private void 
//	_Setup_Telephony() {
//		// TODO Auto-generated method stub
//	
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        // �d�b�ԍ����擾
//        CONS.BT.myNumber = telephonyManager.getLine1Number();
//        
//        if(CONS.BT.myNumber == null){
//        	
//        	CONS.BT.myNumber = "09011112222";
//        	
//        }
//        
//        // Log
//		String msg_Log = "CONS.BT.myNumber => " + CONS.BT.myNumber;
//		Log.d("DeviceListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//
//	}
//
//	private void 
//	_Setup_List_Nonpaired() {
//		// TODO Auto-generated method stub
//		
//		CONS.BT.list_FoundDevices = new ArrayList<String>();
//		
//		CONS.BT.adp_FoundDeviceList = new ArrayAdapter<String>(
//				
//				this,
//				R.layout.rowdata,
//				CONS.BT.list_FoundDevices
//				
//				);
//		
//		ListView lv_NonPaired = (ListView) findViewById(R.id.nonPairedDeviceList);
//		
//		lv_NonPaired.setAdapter(CONS.BT.adp_FoundDeviceList);
//		
//		// Log
//		String msg_Log = "adapter => set";
//		Log.d("DeviceListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//	}//_Setup_List_Nonpaired
//
//	@Override
//	protected void 
//	onActivityResult
//	(int requestCode, int ResultCode, Intent date){
//	    //ダイアログ画面から結果を受けた後の処理を記述
//		
//		String msg = "requestCode => " + requestCode
//					+ "/"
//					+ "ResultCode => " + ResultCode;
//		
////		Methods_dlg.dlg_ShowMessage(this, msg);
//		
//		// Log
//		Log.d("DeviceListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg);
//		
//		if(requestCode == CONS.BT.REQUEST_ENABLE_BLUETOOTH){
//            if(ResultCode == Activity.RESULT_OK){
//                //BluetoothがONにされた場合の処理
//            	
//            	msg = "Bluetooth => turned on";
//				Methods_dlg.dlg_ShowMessage(this, msg);
//				
//            }else{
//
//            	msg = "Bluetooth => can't be turned on";
//				Methods_dlg.dlg_ShowMessage(this, msg);
//				
//            }
//            
//        }
//		
//	}//onActivityResult
//	
//	private void 
//	_Bluetooth() {
//		// TODO Auto-generated method stub
//		
//		String msg_Log;
//		
//		////////////////////////////////
//
//		// validate: adapter
//
//		////////////////////////////////
//		if (mBtAdapter == null) {
//			
//	        BluetoothAdapter Bt = BluetoothAdapter.getDefaultAdapter();
//	        
//	        if(Bt.equals(null)){
//	        	
//	        	String msg = "can't create BT adapter";
//				Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
//	        	
//	        	return;
//	        	
//	        }
//
//	        CONS.BT.mBtAdapter = Bt;
//			
//		}
//		
//		CONS.BT.pairedDeviceAdapter = new ArrayAdapter<String>(this, R.layout.rowdata);
//		
//		Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
//		
//        if(pairedDevices.size() > 0){
//
//        	// Log
//			msg_Log = "pairedDevices.size() > 0";
//			Log.i("DeviceListActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//
//        	for(BluetoothDevice device:pairedDevices){
//        		//�ڑ������̂���f�o�C�X�̏������Ɏ擾���ăA�_�v�^�ɋl�߂�
//        		//getName()�E�E�E�f�o�C�X���擾���\�b�h
//        		//getAddress()�E�E�E�f�o�C�X��MAC�A�h���X�擾���\�b�h
//        		
//        		CONS.BT.pairedDeviceAdapter.add(device.getName() + "\n" + device.getAddress());
//        		
//        		CONS.BT.foundDeviceList.add(device);
//        		
//        		offSet++;
//        		
//        	}
//        	ListView deviceList = (ListView)findViewById(R.id.pairedDeviceList);
//        	deviceList.setAdapter(CONS.BT.pairedDeviceAdapter);
//        	deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        		//�f�o�C�X���X�g�I�����̏���
//				@Override
//				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//					// TODO Auto-generated method stub
//					ListView listView = (ListView) parent;
//					BluetoothDevice device = CONS.BT.foundDeviceList.get(position);
//					BtClientThread = new BluetoothClientThread(mContext, myNumber, device, mBtAdapter);
//					BtClientThread.start();
//				}
//        	});
//        	
//        } else {
//        	
//        	// Log
//			msg_Log = "pairedDevices.size() <= 0";
//			Log.i("DeviceListActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//        }
//
//	}//_Bluetooth
//
//	private void 
//	_Setup__SetListener() {
//		// TODO Auto-generated method stub
//		
//	}//_Setup__SetListener
//
//	private void 
//	_Setup() {
//		// TODO Auto-generated method stub
//		
//	}//_Setup
	

}
