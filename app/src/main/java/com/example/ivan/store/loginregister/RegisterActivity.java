package com.example.ivan.store.loginregister;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ivan.store.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Регистрация");

        final EditText regFamilyName = (EditText) findViewById(R.id.regFamilyName);
        final EditText regPhone = (EditText) findViewById(R.id.regPhone);
        final EditText regAddress = (EditText) findViewById(R.id.regAddress);
        final EditText regBankCard = (EditText) findViewById(R.id.regBankCard);
        final EditText regQiwi = (EditText) findViewById(R.id.regQiwi);
        final EditText regWebmoney = (EditText) findViewById(R.id.regWebmoney);
        final EditText regYandexMoney = (EditText) findViewById(R.id.regYandexMoney);
        final EditText regEmail = (EditText) findViewById(R.id.regEmail);
        final EditText regLogin = (EditText) findViewById(R.id.regLogin);
        final EditText regPassword = (EditText) findViewById(R.id.regPassword);

        final Button regRegister = (Button) findViewById(R.id.regRegister);

        regRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String familyName = regFamilyName.getText().toString();
                final String phone = regPhone.getText().toString();
                final String address = regAddress.getText().toString();
                final String bankCard = regBankCard.getText().toString();
                final String qiwi = regQiwi.getText().toString();
                final String webmoney = regWebmoney.getText().toString();
                final String yandexMoney = regYandexMoney.getText().toString();
                final String email = regEmail.getText().toString();
                final String login = regLogin.getText().toString();
                final String password = regPassword.getText().toString();


             /*   Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject("[" + response + "]");
                            String success = jsonResponse.getString("result");
                            if (success.equals("success")) {
                                Intent intent = new Intent(String.valueOf(RegisterActivity.this));
                                RegisterActivity.this.startActivity(intent);

                                Toast.makeText(getBaseContext(), "very success", Toast.LENGTH_LONG).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                Toast.makeText(getBaseContext(), "not success", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("HERE", "" + e.getStackTrace() );
                            Toast.makeText(getBaseContext(), "exception", Toast.LENGTH_LONG).show();
                        }
                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(familyName, phone, address, bankCard, qiwi,
                        webmoney, yandexMoney, email, login, password ,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

                    */


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://5.189.47.208:3000/register");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            conn.setRequestProperty("Accept","application/json");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);

                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("name", familyName);
                            jsonParam.put("phone", phone);
                            jsonParam.put("address", address);
                            jsonParam.put("bank_card", bankCard);
                            jsonParam.put("visa_qiwi", qiwi);
                            jsonParam.put("webmoney", webmoney);
                            jsonParam.put("yandex_money", yandexMoney);
                            jsonParam.put("email", email);
                            jsonParam.put("login", login);
                            jsonParam.put("pass", password);

                            Log.i("JSON", jsonParam.toString());
                            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                            os.writeBytes(jsonParam.toString());

                            os.flush();
                            os.close();

                            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                            Log.i("MSG" , conn.getResponseMessage());

                            conn.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }


        });

    }
}
