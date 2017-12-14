package com.example.shivani.jsonparsing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String JSON_STRING="{\"employee\":{\"name\":\"Robert\",\"salary\":\"500000\",\"age\":\"25\",\"country\":\"England\",\"dob\":\"23 Oct 1992\"}}";
    public static final String JSON_ARRAY="{\"employee\":[{\"name\":\"Robert\",\"salary\":\"500000\",\"age\":\"25\",\"country\":" +
            "\"England\",\"dob\":\"23 Oct 1993\"},{\"name\":\"Srikant\",\"salary\":\"450000\",\"age\":\"24\",\"country\":" +
            "\"India\",\"dob\":\"15 May 1993\"},{\"name\":\"Milenda\",\"salary\":\"550000\",\"age\":\"25\",\"country\":" +
            "\"USA\",\"dob\":\"07 July 1994\"}]}";
    public MainActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button)findViewById(R.id.button);
        TextView textView = (TextView)findViewById(R.id.text);
        TextView textView2 = (TextView)findViewById(R.id.text2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,JSONList.class);
                startActivity(intent);
            }
        });
        
        try{
            JSONObject object = (new JSONObject(JSON_STRING)).getJSONObject("employee");
            String empName=object.getString("name");
            int empSalary=object.getInt("salary");
            int empAge=object.getInt("age");
            String empCountry=object.getString("country");
            String empDob=object.getString("dob");
            String string = "Employee Name :"+"\t"+empName+"\n"+"Salary :"+"\t"+empSalary+"\n"+"Age :"+"\t"+
                    empAge+"\n"+"Country :"+"\t"+empCountry+"\n"+ "DOB :"+"\t"+empDob;
            textView.setText(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    
   String data="";
        try {
            JSONObject empArray = new JSONObject(JSON_ARRAY);
            JSONArray jsonArray = empArray.optJSONArray("employee");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.optString("name").toString();
                int salary = Integer.parseInt(String.valueOf(obj.optInt("salary")));
                int age =Integer.parseInt(String.valueOf(obj.optInt("age")));
                String country = obj.optString("country").toString();
                String dob = obj.optString("dob").toString();
                data+="Employee Name :" + "\t" + name + "\n" + "Salary :" + "\t" + salary + "\n" + "Age :" + "\t" +
                        age + "\n" + "Country :" + "\t" + country + "\n" + "DOB :" + "\t" + dob+"\n\n";
            
            textView2.setText(data);
        }}
        catch (JSONException e) {
            e.printStackTrace();
        }
    
}
}
