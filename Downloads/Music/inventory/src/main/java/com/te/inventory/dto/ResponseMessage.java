package com.te.inventory.dto;

import lombok.Data;
@Data
public class ResponseMessage {
	
	public boolean error;
	public String message;
	public Object data;
	public ResponseMessage(boolean error, String message, Object data) {
		this.error = error;
		this.message = message;
		this.data = data;
	}
	public ResponseMessage() {
	}
	
}