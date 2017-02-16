package apka.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import apka.services.UnregisterService;

@Controller
@RequestMapping("/unregister")
public class UnregisterController {

	@Autowired
	private UnregisterService unregisterService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String unregister(@RequestBody Map<String, String> id) {
        return unregisterService.unregister(id);
    }
}
