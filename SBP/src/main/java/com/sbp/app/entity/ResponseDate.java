package com.sbp.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseDate {
	
	private String message;
	private Integer code = 200;
	private Object data;
	private Boolean status = true;
	
}
