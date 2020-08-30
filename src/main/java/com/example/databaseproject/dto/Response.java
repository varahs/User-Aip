package com.example.databaseproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {

	private String message;
	private Object description;
	private int statusCode;

	public Response() {
	}

	public Response(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}

	public Response(String message, int statusCode, Object description) {
		this.message = message;
		this.statusCode = statusCode;
		this.description = description;
	}

}
