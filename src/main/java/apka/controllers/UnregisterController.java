package apka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import apka.services.UnregisterService;

@Controller
@RequestMapping("/unregister")
public class UnregisterController {

	@Autowired
	private UnregisterService unregisterService;
	
	@RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String unregister(@RequestParam(value="id") String id) {
        return unregisterService.unregister(id);
    }
}
