package com.example.pengxiang.appproject.tool;

import android.util.Log;

import com.example.pengxiang.appproject.beans.CompanyInfo;
import com.example.pengxiang.appproject.beans.ConnectionInfo;
import com.example.pengxiang.appproject.beans.ContractBean;
import com.example.pengxiang.appproject.beans.InformBean;
import com.example.pengxiang.appproject.beans.NoticeBean;
import com.example.pengxiang.appproject.beans.Policy;
import com.example.pengxiang.appproject.beans.RecruitInfo;
import com.example.pengxiang.appproject.beans.ResourceInfo;
import com.example.pengxiang.appproject.beans.ServiceInfo;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengxiang on 07/27 0027.
 */

public class Connect {

    public static String ipAddr = "10.0.2.2";
    public static String path = "http://" + ipAddr + ":8080/Android";

    private String connWeb(String url){
        String str = "";
        try{
            HttpGet request = new HttpGet(url);
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200){
                str = EntityUtils.toString(response.getEntity(),"gbk");
            }
        }catch (IOException e){
            Log.v("network","not 200");
            e.printStackTrace();
        }
        return str;
    }

    //访问数据库

    //获取公告信息
    public List<NoticeBean> getNoticePopList(){
        List<NoticeBean> noticelist = new ArrayList<NoticeBean>();
        //url的flag信息
        String url = path + "/Servlet/AnnouncementViewServlet?";
        String str = connWeb(url);

//        str = "{\"list\":" + str + "}";

        //手机客户端解析服务器端的json数据格式
        try{
            Log.v("json",str);

//            JSONObject job = new JSONObject(str);
//            Log.v("length",job.length()+"");

//            JSONArray jay = job.getJSONArray("list");

            JSONArray jay = new JSONArray(str);
            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                NoticeBean noticebean = new NoticeBean();
                noticebean.setId(temp.getInt("annID"));
                noticebean.setTitle(temp.getString("annTitle"));
                noticebean.setType(temp.getString("annType"));
                noticebean.setContent(temp.getString("annContent"));
                noticebean.setDate(temp.getString("annDate"));
                noticelist.add(noticebean);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return noticelist;
    }

    //获取公司信息
    public List<CompanyInfo> getCompanyPopList(){
        List<CompanyInfo> companyInfoList = new ArrayList<>();

        String url = path + "/Servlet/DisComListServlet";
        String str = connWeb(url);

//        str = "{\"list\":" + str + "}";

        //手机客户端解析服务器端的json数据格式
        try{
            Log.v("json",str);

//            JSONObject job = new JSONObject(str);
//            Log.v("length",job.length()+"");

            JSONArray jay = new JSONArray(str);

            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                CompanyInfo companyInfo = new CompanyInfo();

                companyInfo.setId(temp.getString("comID"));
                companyInfo.setName(temp.getString("comName"));
                companyInfo.setIntrodoce(temp.getString("comContent"));

                companyInfoList.add(companyInfo);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return companyInfoList;
    }

    //获取招聘信息
    public List<RecruitInfo> getRecruitPopList(){
        List<RecruitInfo> recruitInfoList = new ArrayList<>();

        String url = path + "/Servlet/ComRecruitServlet";
        String str = connWeb(url);

//        str = "{\"list\":" + str + "}";

        //手机客户端解析服务器端的json数据格式
        try{
            Log.v("json",str);

//            JSONObject job = new JSONObject(str);
//            Log.v("length",job.length()+"");

            JSONArray jay = new JSONArray(str);

            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                RecruitInfo recruitInfo = new RecruitInfo();

                recruitInfo.setId(temp.getInt("comRID"));
                recruitInfo.setCom_name(temp.getString("comRName"));
                recruitInfo.setDate(temp.getString("comRDate"));
                recruitInfo.setJob(temp.getString("comRJob"));
                recruitInfo.setRequire(temp.getString("comRReq"));
                recruitInfo.setMoney(temp.getString("comRMon"));
                recruitInfo.setPlace(temp.getString("comRPlace"));
                recruitInfo.setContact(temp.getString("comRCon"));

                recruitInfoList.add(recruitInfo);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return recruitInfoList;
    }

    //获取政策信息
    public List<Policy> getPolicyPopList(){
        List<Policy> policyList = new ArrayList<>();

        String url = path + "/Servlet/NationalPolicyServlet";
        String str = connWeb(url);

//        str = "{\"list\":" + str + "}";

        //手机客户端解析服务器端的json数据格式
        try{
            Log.v("json",str);

//            JSONObject job = new JSONObject(str);
//            Log.v("length",job.length()+"");

            JSONArray jay = new JSONArray(str);

            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                Policy policy = new Policy();

                policy.setId(temp.getInt("npid"));
                policy.setTitle(temp.getString("nptitle"));
                policy.setDate(temp.getString("npdate"));
                policy.setContent(temp.getString("npcontent"));

                policyList.add(policy);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return policyList;
    }

    //发送投诉
    public boolean sendComplain(String title,String content){

        String use_url = path + "/Servlet/FeedbackServlet" +
//                "feedTitle=" + "\"" + title + "\"" + "&" + "feedContent=" + "\"" + content + "\"";
              "?feedTitle=" + title + "&feedContent=" + content;
        String responseStr = "";
        try{

            HttpGet httpRequest = new HttpGet(use_url);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params,3000);
            HttpConnectionParams.setConnectionTimeout(params,5000);
            HttpConnectionParams.setSoTimeout(params,5000);
            httpRequest.setParams(params);

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            final int ret = httpResponse.getStatusLine().getStatusCode();
            Log.v("ret.....",ret+"");
            if (ret == HttpStatus.SC_OK){
                responseStr = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
            }else{
                responseStr = "-1";
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.v("return.........",responseStr);
        if (responseStr.equals("true")){
            return true;
        }else
            return false;
    }

    //登陆判断
    public int Logincheck(String username, String password) {

        String url = path + "/Servlet/LoginCheckServlet" + "?username=" + username + "&password=" + password;
        String role = "";
        int result = -1;
        System.out.println(url);
        try {
            HttpGet httpRequest = new HttpGet(url);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params, 3000);
            HttpConnectionParams.setConnectionTimeout(params, 5000);
            HttpConnectionParams.setSoTimeout(params, 5000);
            httpRequest.setParams(params);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            final int ret = httpResponse.getStatusLine().getStatusCode();
            Log.v("ret.....", ret + "");
            if (ret == HttpStatus.SC_OK) {
                role = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
            } else {
                role = "-1";
            }

            JSONObject job = new JSONObject(role);
            result = job.getInt("role");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (result == 0) {
            return 0;//企业账号
        } else if (result == 1) {
            return 1;//物业管理
        }else
            return -1;
    }

    public boolean Register(String username,String password) {
        String url = path + "/Servlet/LoginRegisterServlet" + "?username=" + username + "&password=" + password;
        String flag="";
        boolean result = false;
        try{
            HttpGet httpRequest = new HttpGet(url);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params,3000);
            HttpConnectionParams.setConnectionTimeout(params,5000);
            HttpConnectionParams.setSoTimeout(params,5000);
            httpRequest.setParams(params);

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            final int ret = httpResponse.getStatusLine().getStatusCode();
            Log.v("ret.....",ret+"");
            if (ret == HttpStatus.SC_OK){
                flag = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
            }else{
                flag = "-1";
            }
            JSONObject job = new JSONObject(flag);
            result = job.getBoolean("flag");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("return.........",flag);
        if (result == true) {
            return true;
        } else {
            return false;
        }
    }

    //发送联系人
    public boolean sendContacts(String name,String mail,String phone){

        String use_url = path + "/Servlet/LinkManInsertServlet" +
                "?linkName=" + name + "&linkPhone=" + phone+"&linkEmail="+mail;
        String responseStr = "";
        try{

            HttpGet httpRequest = new HttpGet(use_url);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params,3000);
            HttpConnectionParams.setConnectionTimeout(params,5000);
            HttpConnectionParams.setSoTimeout(params,5000);
            httpRequest.setParams(params);

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            final int ret = httpResponse.getStatusLine().getStatusCode();
            Log.v("ret.....",ret+"");
            if (ret == HttpStatus.SC_OK){
                responseStr = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
            }else{
                responseStr = "-1";
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.v("return.........",responseStr);
        if (responseStr.equals("true")){
            return true;
        }else
            return false;
    }

    //发送招聘信息
    public boolean sendRecuit(String company, String require, String position, String slary,
                              String connection, String time,String place){

        String use_url = path + "/Servlet/ComRecruitInsertServlet" +
                "?comRName=" + company + "&comRReq=" + require+"&comRCon="+connection+
                "&comRJob="+position+"&comRMon="+slary+"&comRDate="+time+"&comRPlace="+place;

        Log.v("url.......",use_url);
        String responseStr = "";
        try{

            HttpGet httpRequest = new HttpGet(use_url);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params,3000);
            HttpConnectionParams.setConnectionTimeout(params,5000);
            HttpConnectionParams.setSoTimeout(params,5000);
            httpRequest.setParams(params);

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            final int ret = httpResponse.getStatusLine().getStatusCode();
            Log.v("ret.....",ret+"");
            if (ret == HttpStatus.SC_OK){
                responseStr = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
            }else{
                responseStr = "-1";
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.v("return.........",responseStr);
        if (responseStr.equals("true")){
            return true;
        }else
            return false;
    }

    //资源预定
    public boolean sendResource(String id,String kind,String time){

        String use_url = path + "/Servlet/RoomCarBookServlet" +
                "?genUID=" + id + "&resType=" + kind+"&resUseDay="+time;
        String responseStr = "";
        try{

            HttpGet httpRequest = new HttpGet(use_url);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params,3000);
            HttpConnectionParams.setConnectionTimeout(params,5000);
            HttpConnectionParams.setSoTimeout(params,5000);
            httpRequest.setParams(params);

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            final int ret = httpResponse.getStatusLine().getStatusCode();
            Log.v("ret.....",ret+"");
            if (ret == HttpStatus.SC_OK){
                responseStr = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
            }else{
                responseStr = "-1";
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.v("return.........",responseStr);
        if (responseStr.equals("true")){
            return true;
        }else
            return false;
    }

    //发送服务记录
    public boolean sendService(String title,String content,String time){

        String use_url = path + "Servlet/ServerRecordServlet" +
                "?serverTitle=" + title + "&serverContent=" + content + "&serverDate=" + time;
        String responseStr = "";
        try{

            HttpGet httpRequest = new HttpGet(use_url);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params,3000);
            HttpConnectionParams.setConnectionTimeout(params,5000);
            HttpConnectionParams.setSoTimeout(params,5000);
            httpRequest.setParams(params);

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            final int ret = httpResponse.getStatusLine().getStatusCode();
            Log.v("ret.....",ret+"");
            if (ret == HttpStatus.SC_OK){
                responseStr = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
            }else{
                responseStr = "-1";
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.v("return.........",responseStr);
        if (responseStr.equals("true")){
            return true;
        }else
            return false;
    }

    //获取联系人信息
    public List<ConnectionInfo> getConnectionPopList(){
        List<ConnectionInfo> companyInfoList = new ArrayList<>();

        String url = path + "/Servlet/LinkManViewServlet";
        String str = connWeb(url);

        try{
            Log.v("json",str);
            JSONArray jay = new JSONArray(str);

            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                ConnectionInfo connectionInfo = new ConnectionInfo();

                connectionInfo.setName(temp.getString("linkName"));
                connectionInfo.setmail(temp.getString("linkEmail"));
                connectionInfo.setPhone(temp.getString("linkPhone"));

                companyInfoList.add(connectionInfo);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return companyInfoList;
    }

    //获取服务信息
    public List<ServiceInfo> getServicePopList(){
        List<ServiceInfo> serviceInfoList = new ArrayList<>();

        String url = path + "/Servlet/ServerRecordViewServlet";
        String str = connWeb(url);

        try{
            Log.v("json",str);
            JSONArray jay = new JSONArray(str);

            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                ServiceInfo serviceInfo = new ServiceInfo();

                serviceInfo.setContent(temp.getString("serverContent"));
                serviceInfo.setTime(temp.getString("serverDate"));
                serviceInfo.setTitle(temp.getString("serverTitle"));

                serviceInfoList.add(serviceInfo);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return serviceInfoList;
    }

    //获取资源预定信息
    public List<ResourceInfo> getResourcePopList(){
        List<ResourceInfo> resourceInfoList = new ArrayList<>();

        String url = path + "/Servlet/RoomCarBookServlet";
        String str = connWeb(url);



        //手机客户端解析服务器端的json数据格式
        try{
            Log.v("json",str);


            JSONArray jay = new JSONArray(str);

            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                ResourceInfo resourceInfo = new ResourceInfo();

                resourceInfo.setId(temp.getString("genUID"));
                resourceInfo.setKind(temp.getString("resType"));
                resourceInfo.setTime(temp.getString("resUseDay"));

                resourceInfoList.add(resourceInfo);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return resourceInfoList;
    }

    //获取通知信息
    public List<InformBean> getInformPopList(){
        List<InformBean>informlist = new ArrayList<>();
        //url的flag信息
        String url = path + "/Servlet/AnnouncementViewServlet";
        // String url = path + "com/Servlet/AnnouncementViewServlet";//原来代码
        String str = connWeb(url);
//      str = "{\"list\":" + str + "}";

        //手机客户端解析服务器端的json数据格式
        try{
            Log.v("json",str);
//           JSONObject job = new JSONObject(str);
//           Log.v("length",job.length()+"");
//          JSONArray jay = job.getJSONArray("list");
            JSONArray jay = new JSONArray(str);
//            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                InformBean informbean = new InformBean();
                informbean.setId(temp.getInt("annID"));
                informbean.setTitle(temp.getString("annTitle"));
                informbean.setContent(temp.getString("annContent"));
                informbean.setDate(temp.getString("annDate"));
                informlist.add(informbean);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return informlist;
    }


    //获取合同信息
    public List<ContractBean> getContractPopList(){
        List<ContractBean>contractlist = new ArrayList<>();
        //url的flag信息
        String url = path + "/Servlet/ContractServlet";
        String str = connWeb(url);
        //        str = "{\"list\":" + str + "}";

        //手机客户端解析服务器端的json数据格式
        try{
            Log.v("json",str);
//            JSONObject job = new JSONObject(str);
//            Log.v("length",job.length()+"");
//            JSONArray jay = job.getJSONArray("list");
            JSONArray jay = new JSONArray(str);
//            Log.v("jay",jay.length()+"");
            for (int i = 0; i<jay.length();i++){
                JSONObject temp = (JSONObject)jay.get(i);
                ContractBean contractbean = new ContractBean();
                contractbean.setId(temp.getInt("contractID"));
                contractbean.setName(temp.getString("contractName"));
                contractbean.setContent(temp.getString("contractCon"));
                contractbean.setsDate(temp.getString("contractDate"));
                contractbean.seteDate(temp.getString("contractEDate"));
                contractlist.add(contractbean);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return contractlist;
    }
//    public void connWeb(String url){
//
//        new HttpUtils().send(HttpRequest.HttpMethod.GET, url,
//                new RequestCallBack<String>() {
//                    @Override
//                    public void onSuccess(ResponseInfo<String> responseInfo){
//                        String string = responseInfo.result;
//                        noticelist = new ArrayList<NoticeBean>();
//                        try{
//                            str = new String(string.getBytes("gbk"),"gbk");
//                            //解析json
//                            try{
//                                JSONObject job = new JSONObject(str);
//                                JSONArray jay = job.getJSONArray("list");
//                                for (int i = 0;i < jay.length();i++){
//                                    JSONObject temp = (JSONObject)jay.get(i);
//                                    NoticeBean noticebean = new NoticeBean();
//                                    noticebean.setId(temp.getInt("annID"));
//                                    noticebean.setTitle(temp.getString("annTitle"));
//                                    noticebean.setType(temp.getString("annType"));
//                                    noticebean.setContent(temp.getString("annContent"));
//                                    noticebean.setDate(temp.getString("annDate"));
//
//                                    noticelist.add(noticebean);
//                                }
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//                        }catch (UnsupportedEncodingException e){
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(HttpException e, String s) {
//
//                    }
//                });
//
//    }
}
