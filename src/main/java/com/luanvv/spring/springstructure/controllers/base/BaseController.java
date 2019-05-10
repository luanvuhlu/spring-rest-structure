package com.luanvv.spring.springstructure.controllers.base;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/v1", 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE)
public class BaseController {

}
