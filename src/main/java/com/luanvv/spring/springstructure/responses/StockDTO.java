package com.luanvv.spring.springstructure.responses;

import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StockDTO extends ResourceSupport {

	@JsonProperty("id")
	private UUID uuid;
	
	@JsonProperty("code")
	private String stockCode;
	
	@JsonProperty("name")
	private String stockName;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonProperty("create_time")
	private String createdTime;
}
