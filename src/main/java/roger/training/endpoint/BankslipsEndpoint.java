package roger.training.endpoint;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import roger.training.util.BankslipHandler;

@RestController
@RequestMapping("rest/bankslips")
public class BankslipsEndpoint {

	@Autowired
	private BankslipHandler bankslipHandler;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody(required = false) JSONObject json) {
		return bankslipHandler.create(json);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return bankslipHandler.getAll();
	}

	@RequestMapping(value = { "{id}" }, method = RequestMethod.GET)
	public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) throws Exception {
		return bankslipHandler.getOne(id);
	}

	@RequestMapping(value = { "{id}" }, method = RequestMethod.PUT)
	public ResponseEntity<?> statusUpdate(@PathVariable(value = "id") String id, @RequestBody JSONObject json) {
		return bankslipHandler.updateStatus(id, json);
	}

}
