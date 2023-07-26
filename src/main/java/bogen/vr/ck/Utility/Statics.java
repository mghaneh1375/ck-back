package bogen.vr.ck.Utility;

import bogen.vr.ck.Model.Content;
import org.json.JSONObject;

import java.util.HashMap;

public class Statics {

    public static HashMap<String, Content> contents = new HashMap<>();

    public static final String JSON_OK = new JSONObject().put("status", "ok").toString();

    public static String generateSuccessMsg(String key, Object val, PairValue... pairValues) {

        JSONObject jsonObject = new JSONObject()
                .put("status", "ok")
                .put(key, val);

        for (PairValue p : pairValues)
            jsonObject.put(p.getKey().toString(), p.getValue());

        return jsonObject.toString();

    }
}
