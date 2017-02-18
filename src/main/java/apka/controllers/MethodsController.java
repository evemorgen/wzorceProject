package apka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import apka.services.MethodsService;

@Controller
@RequestMapping("/methods")
public class MethodsController {

	@Autowired
	private MethodsService methodsService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String show(@RequestParam(value = "name") String name) {
		return methodsService.methods(name);
	}
}
