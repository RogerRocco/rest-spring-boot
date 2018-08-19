package roger.training.util;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import roger.training.model.Bankslip;
import roger.training.model.BankslipDao;
import roger.training.model.HttpStatusEnum;
import roger.training.model.Status;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BankslipHandler implements PaymentHandler{

	@Autowired
	private BankslipDao bankslipDao;

	/**
	 * Prepare the request's return to BankslipsEndpoint
	 * @param returnObj  the object that will be returned as JSONobject, JSONArray, null to
	 *                   BankslipsEndpoint
	 * @param httpStatus the object that will give httpStatus response to BankslipsEndpoint
	 */
	private static <T> ResponseEntity bankslipResponseEntity(T returnObj, HttpStatusEnum httpStatus) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Response", httpStatus.getDesc());
		return new ResponseEntity<>(returnObj, responseHeaders, HttpStatus.valueOf(httpStatus.getCode()));
	}

	/**
	 * Create a new bankslip
	 */
	public ResponseEntity<JSONArray> create(JSONObject json) {
		HttpStatusEnum httpStatus;
		try {
			if (!BankslipValidation.isValid(json.toString()))
				throw new IllegalArgumentException();

			bankslipDao.save(BankSlipConverter.fromJson(json));
			httpStatus = HttpStatusEnum.RET_CREATED_SUCCESS;

		} catch (Exception e) {
			httpStatus = HttpStatusEnum.RET_CREATE_ERROR;
		}
		return bankslipResponseEntity(null, httpStatus);
	}

	/**
	 * Get all bankslips persisted
	 */
	public ResponseEntity<JSONArray> getAll() {
		HttpStatusEnum httpStatus;
		JSONArray result = null;

		if (bankslipDao != null) {
			result = new JSONArray();
			for (Bankslip bankslipLoop : bankslipDao.getList())
				result.add(BankSlipConverter.toJson(bankslipLoop));
			httpStatus = HttpStatusEnum.RET_FIND_FOUND;
		} else {
			httpStatus = HttpStatusEnum.RET_FIND_ALL_ANYTHING_FOUND;
		}
		return bankslipResponseEntity(result, httpStatus);
	}

	/**
	 * Get a specific bankslip by id
	 * @param id the bankslip to have the status updated
	 */
	public ResponseEntity<HashMap<String, String>> getOne(@Nullable String id) {
		HttpStatusEnum httpStatus;
		HashMap obj = null;
		try {
			Bankslip bankslip = bankslipDao.find(id);
			httpStatus = HttpStatusEnum.RET_FIND_FOUND;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Response", httpStatus.getDesc());
			obj = BankSlipConverter.toJson(bankslip);
		} catch (Exception e) {
			httpStatus = HttpStatusEnum.RET_FIND_NOT_FOUND;
		}
		return bankslipResponseEntity(obj, httpStatus);
	}

	/**
	 * Update the status of bankslips
	 * @param id   to find the right bankslip and update it
	 * @param json status to replace the current bankslip status
	 */
	public ResponseEntity<JSONArray> updateStatus(String id, JSONObject json) {
		HttpStatusEnum httpStatus = null;

		try {

			if (json == null || json.get("status") == null
					|| !roger.training.util.validation.Status.isValid(json.get("status").toString()))
				throw new IllegalArgumentException();

			Bankslip bankslip = bankslipDao.find(id);
			bankslip.setStatus((String) json.get("status"));
			bankslipDao.save(bankslip);

			if (bankslip.getStatus().equals(Status.CANCELED.toString()))
				httpStatus = HttpStatusEnum.RET_UPDATE_SUCCESS_CANCELLED;
			else if (bankslip.getStatus().equals(Status.PAID.toString()))
				httpStatus = HttpStatusEnum.RET_UPDATE_SUCCESS_PAID;
			else
				httpStatus = HttpStatusEnum.RET_UPDATE_INVALID;
		} catch (Exception e) {
			httpStatus = HttpStatusEnum.RET_UPDATE_INVALID;
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Response", httpStatus.getDesc());
		return new ResponseEntity<>(responseHeaders, HttpStatus.valueOf(httpStatus.getCode()));

	}


}
