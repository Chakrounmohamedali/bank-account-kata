package com.bnp.interview.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "description",
})
public class CustomHTTPError {
	
	@JsonProperty("message")
    private String message;
    @JsonProperty("description")
    private String description;
    
	public CustomHTTPError(String message, String description) {
		super();
		this.message = message;
		this.description = description;
	}
    
    
    
}
