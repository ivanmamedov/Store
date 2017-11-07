package com.example.ivan.store.loginregister;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 06.11.2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://5.189.47.208:3000/register";
    private Map<String, String> params;

    public RegisterRequest(String userName, String numberPhone, String address, String bankCard,
                           String visaQiwi, String webmoney, String yandexMoney,
                           String eMail, String login, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", userName);
        params.put("phone", numberPhone);
        params.put("address", address);
        params.put("bank_card", bankCard);
        params.put("visa_qiwi", visaQiwi);
        params.put("webmoney", webmoney);
        params.put("yandex_money", yandexMoney);
        params.put("email", eMail);
        params.put("login", login);
        params.put("pass", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
