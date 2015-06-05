package fr.pchab.androidrtc;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class StreamInfo {

    private String TAG = "StreamInfo";
    private StreamListener mListener;
    private Context mContext;

    public interface StreamListener{
        public void onOver();
    }

    StreamInfo(StreamListener listener, Context context){
        mListener = listener;
        mContext = context;
    }
    public void startGetStreamInfo() {
        new Thread() {
            public void run() {
                //read stream info
                String serverAddr = MyPreference.getSeverAddress(mContext);
                int port = MyPreference.getServerPort(mContext);
                String infoURL = "http://" + serverAddr + ":" + String.valueOf(port) + "/streams.json";


                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet get = new HttpGet(infoURL);
                    HttpResponse response = client.execute(get);
                    HttpEntity resEntity = response.getEntity();
                    String result = EntityUtils.toString(resEntity);
                    Log.d(TAG, "result=[" + result + "]");


                } catch (IOException e) {
                    Log.d(TAG, "http get exception");
                }
                //callback
                mListener.onOver();
            }
        }.start();
    }

}

