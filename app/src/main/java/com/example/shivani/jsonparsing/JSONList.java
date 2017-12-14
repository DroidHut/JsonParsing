package com.example.shivani.jsonparsing;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONList extends AppCompatActivity {
ArrayList<HashMap<String,String>> list;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        list=new ArrayList<>();
         lv=(ListView)findViewById(R.id.list);
        new GetContacts().execute();
        
    }
    private class GetContacts extends AsyncTask<Void,Void,Void>{
        
        @Override
        protected Void doInBackground(Void... params) {
            HTTPHandler handler =  new HTTPHandler();
            String url="http://api.androidhive.info/contacts/";
            String json=handler.makeServiceCall(url);
            if(json!=null)
            {
                try {
                    JSONObject jsonObject=new JSONObject(json);
                    JSONArray array =  jsonObject.getJSONArray("contacts");
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        String id=object.getString("id");
                        String name=object.getString("name");
                        String gender=object.getString("gender");
                        String email=object.getString("email");
                        String address=object.getString("address");
                        
                        JSONObject phone=object.getJSONObject("phone");
                        String mobile=phone.getString("mobile");
                        String home=phone.getString("home");
                        String office=phone.getString("office");
                        
                        
                        HashMap<String ,String> contacts=new HashMap<>();
                        contacts.put("id",id);
                        contacts.put("name",name);
                        contacts.put("gender",gender);
                        contacts.put("address",address);
                        contacts.put("email",email);
                        contacts.put("mobile",mobile);
                        contacts.put("home",home);
                        contacts.put("office",office);


                        list.add(contacts);   
                    }
                } catch (JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            
                        }
                    });
                    e.printStackTrace();
                }
                

            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
            return null;
        }
        @Override
        protected  void onPostExecute(Void result){
            super.onPostExecute(result);
            ListAdapter adapter =new SimpleAdapter(JSONList.this,list,R.layout.list_items,new String[]
                    {"id","name","gender","address","email","mobile","home","office"}
            ,new int[]{R.id.id,R.id.name,R.id.gender,R.id.address,R.id.email,R.id.mobile,R.id.home,R.id.office});
                    lv.setAdapter(adapter);
        }
    }
}
