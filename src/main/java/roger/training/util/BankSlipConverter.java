package roger.training.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;

import roger.training.model.Bankslip;

public class BankSlipConverter {

	@SuppressWarnings("unchecked")
	public static JSONObject toJson(Bankslip bankslip) {
		JSONObject jo = new JSONObject();
		jo.put("id", bankslip.getId());
		jo.put("due_date", new SimpleDateFormat("yyyy-MM-dd").format(bankslip.getDueDate()));
		jo.put("total_in_cents", bankslip.getTotalInCents());
		jo.put("customer", bankslip.getCustomer());
		try {
			jo.put("fine", Fine.calculate(bankslip.getTotalInCents(), bankslip.getDueDate()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		jo.put("status", bankslip.getStatus());
		return jo;
	}

	public static Bankslip fromJson(JSONObject json)  {

		Bankslip bankslip = new Bankslip();
		bankslip.setCustomer(json.get("customer").toString());
		try {
			bankslip.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) json.get("due_date")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bankslip.setTotalInCents(ConvertMoney.fromCents(json.get("total_in_cents")));
		bankslip.setStatus((String) json.get("status"));

		return bankslip;
	}

}
