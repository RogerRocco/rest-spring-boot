package roger.training.util;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public interface PaymentHandler {

	public ResponseEntity<JSONArray> create(JSONObject json);

	public ResponseEntity<JSONArray> getAll();

	public ResponseEntity<HashMap<String, String>> getOne(@Nullable String id);

	public ResponseEntity<JSONArray> updateStatus(String id, JSONObject json);

}
