package roger.training.model;

public enum HttpStatusEnum {

	RET_CREATED_SUCCESS("Bankslip created", 201),
	RET_CREATED_INVALID("Invalid bankslip provided.The possible reasons are: "
			+ "A field of the provided bankslip was null or with invalid values", 422),
	RET_CREATE_ERROR("Bankslip not provided in the request body", 400),

	RET_FIND_FOUND("Ok", 200), RET_FIND_ALL_ANYTHING_FOUND("No Bankslips was found", 404),

	RET_FIND_NOT_FOUND("Bankslip not found with the specified id", 404),
	RET_FIND_INVALID_ID("Invalid id provided - it must be a valid UUID", 400),

	RET_UPDATE_INVALID(
			"Bankslip not modified.The status is the same or ID of link does not corresponding the ID of body or Status was invalid",
			404),
	RET_UPDATE_SUCCESS_CANCELLED("Bankslips cancelled", 200), RET_UPDATE_SUCCESS_PAID("Bankslips paid", 200);

	private Integer code;
	private String desc;

	private HttpStatusEnum(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
