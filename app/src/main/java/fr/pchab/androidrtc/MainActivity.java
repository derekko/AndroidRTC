package fr.pchab.androidrtc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.zip.Inflater;


public class MainActivity extends Activity {

    private String TAG = "MainActivity";

    private Context mContext;

    private Button startMyStreamButton;
    private Button callOtherStreamButton;
    private Button viewOtherStreamButton;
    private Button refreshDataButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        startMyStreamButton = (Button) findViewById(R.id.start_my_stream_button);
        callOtherStreamButton = (Button) findViewById(R.id.call_other_stream_button);
        viewOtherStreamButton = (Button) findViewById(R.id.view_other_stream_button);
        refreshDataButton = (Button) findViewById(R.id.refresh_data_button);

        startMyStreamButton.setOnClickListener(buttonClickListener);
        callOtherStreamButton.setOnClickListener(buttonClickListener);
        viewOtherStreamButton.setOnClickListener(buttonClickListener);
        refreshDataButton.setOnClickListener(buttonClickListener);

    }

    protected void onResume() {
        super.onResume();
        if(checkServerSetting() == false){
            showSettingsDialog();
        }else if(checkNetworkStatus() == false){
            showNetworkErrDialog();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0, 0, Param.MENU_SETTINGS, "Settings");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == Param.MENU_SETTINGS) {
            showSettingsDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.start_my_stream_button){

            }else if(v.getId() == R.id.call_other_stream_button){

            }else if(v.getId() == R.id.view_other_stream_button){

            }else if(v.getId() == R.id.refresh_data_button){

            }else{
                Log.d(TAG, "Unknown clicked");
            }
        }
    };


    @SuppressLint("NewApi")
    public void showSettingsDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.rtc_server_setting);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View dialogLayout = layoutInflater.inflate(R.layout.rtc_server_setting, null);
        dialog.setView(dialogLayout);
        final EditText serverAddrEdit = (EditText) dialogLayout.findViewById(R.id.rtc_adddress_edit);
        final EditText serverPortEdit = (EditText) dialogLayout.findViewById(R.id.rtc_port_edit);
        final EditText deviceNameEdit = (EditText) dialogLayout.findViewById(R.id.rtc_device_name_edit);

        serverAddrEdit.setText(MyPreference.getSeverAddress(this));
        serverPortEdit.setText(String.valueOf(MyPreference.getServerPort(this)));
        deviceNameEdit.setText(MyPreference.getDeviceName(this));

        //dialog.setView(R.layout.rtc_server_setting);
        dialog.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String serverAddr = serverAddrEdit.getText().toString();
                int serverPort = Integer.valueOf(serverPortEdit.getText().toString());
                String deviceName = deviceNameEdit.getText().toString();

                MyPreference.setSeverAddress(mContext, serverAddr);
                MyPreference.setServerPort(mContext, serverPort);
                MyPreference.setDeviceName(mContext, deviceName);
            }
        });
        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void showNetworkErrDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.network);
        dialog.setMessage(R.string.no_network_connection);
        dialog.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public boolean checkServerSetting(){
        String serverAddr = MyPreference.getSeverAddress(mContext);
        int port = MyPreference.getServerPort(mContext);
        if(serverAddr.equals(Param.DEFAULT_IP) || port == 0){
            return false;
        }
        return true;
    }
    public boolean checkNetworkStatus(){
        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
