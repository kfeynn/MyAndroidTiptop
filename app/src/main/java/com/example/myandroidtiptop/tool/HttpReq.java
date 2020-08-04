package com.example.myandroidtiptop.tool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.example.myandroidtiptop.ui.dialog.LoadingDialog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HttpReq extends Thread {
	private List<BasicNameValuePair> parameters;
	private String uri;
	private String data;
	private Handler handler;
	private int type;
	private Dialog dialog;
	private Context context;
	private int i = 0;
	public HttpReq(List<BasicNameValuePair> parameters, String uri, Handler handler, int type) {
		this.parameters = parameters;
		this.uri = uri;
		this.handler = handler;
		this.type = type;
	}
	public HttpReq(List<BasicNameValuePair> parameters, String uri, Handler handler, int type, Dialog dialog) {
		this.parameters = parameters;
		this.uri = uri;
		this.handler = handler;
		this.type = type;
		this.dialog = dialog;
		dialog.show();
	}

	public HttpReq(List<BasicNameValuePair> parameters, String uri, Handler handler, int type, Context context) {
		this.parameters = parameters;
		this.uri = uri;
		this.handler = handler;
		this.type = type;
		this.context = context;
		dialog = LoadingDialog.createLoadingDialog(context, "嗨，断网了，快去蹭蹭网络·····");
		dialog.show();
	}
	@Override
	public void run() {
		super.run();
		toPostdata(parameters,uri);
	}



	public static String getData() throws Exception {
		 
		try {
			//HttpPath.GetGamesPath() : 网络请求路径
			URL url=new URL(HttpPath.getLoginPath());
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			
			if(conn.getResponseCode()==200){
			   InputStream ips=conn.getInputStream();
			   byte[] data=read(ips);
			   String str=new String(data);
			   System.out.println(str);
			   return str;
			}else{
			   return "网络错误 ：conn.getResponseCode() ="+conn.getResponseCode();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return "HttpService.getGamesData()发生异常！ "+e.getMessage();
		}
		
	}
	
	
	 
/*     * 读取流中的数据
     * */
	public static byte[] read(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int len=0;
		while((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
	/**
	 * httpclient post提交数据
	 * @param parameters
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	
	
	public String toPostdata(List<BasicNameValuePair> parameters, String uri){
		
		String str="获取失败";

		//初始化
		HttpClient client=new DefaultHttpClient();
		//HttpPath.GetGamesPath() : 网络请求路径
		HttpPost httpPost=new HttpPost(uri);
		//设置参数
		try {
			UrlEncodedFormEntity params=new UrlEncodedFormEntity(parameters,"UTF-8");
			httpPost.setEntity(params);	
			//执行请求
			HttpResponse response= client.execute(httpPost);
			//取得返回值
			if(response.getStatusLine().getStatusCode()==200){
				//请求成功
				HttpEntity entity=response.getEntity();
				str=EntityUtils.toString(entity, "UTF-8");
				sendMessage(str,type);
				System.out.println("cancel_dialog___"+i);
				dialog.dismiss();

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				Thread.sleep(5000);
				dialog.dismiss();
				Looper.prepare();
				Toast.makeText(context, "断网了，请检查网络连接", Toast.LENGTH_LONG).show();
				Looper.loop();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			str =  toPostdata(parameters,uri);
		}
		
		return str;
	}
	
	public  static String toPostdata(String uri){

		String str="获取失败";

		//初始化
		HttpClient client=new DefaultHttpClient();
		//HttpPath.GetGamesPath() : 网络请求路径
		HttpPost httpPost=new HttpPost(uri);
		//设置参数
		try {
			//执行请求
			HttpResponse response= client.execute(httpPost);
			//取得返回值
			if(response.getStatusLine().getStatusCode()==200){
				//请求成功
				HttpEntity entity=response.getEntity();
				str=EntityUtils.toString(entity, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				Thread.sleep(5000);

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			str =  toPostdata(uri);
		}

		return str;
	}
	
	/**
	 * httpclient get 获得数据
	 * @return
	 */
	public static String toGetData(){
		String str="获取数据失败";
		
		HttpClient client=new DefaultHttpClient();
		////HttpPath.GetGamesPath() : 网络请求路径
		HttpGet get=new HttpGet(HttpPath.getLoginPath());
	 	try {
		 
	 		HttpResponse httpResponse=client.execute(get);
	 		
		    if(httpResponse.getStatusLine().getStatusCode()==200){
		    	str=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		    }
		 
		    return str;
		    
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			return "toGetData 异常："+e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "toGetData 异常："+e.getMessage();
		}
	}

	public void sendMessage(String data, int type){
		Bundle bundle = new Bundle();
		bundle.putString("data",data);
		Message msg = new Message();
		msg.what = type;
		msg.setData(bundle);
		handler.sendMessage(msg);
	}
  

}
