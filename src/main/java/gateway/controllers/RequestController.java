package gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gateway.model.RequestModel;
import gateway.services.RequestService;

@Controller
@RequestMapping("/request")
public class RequestController {

	@Autowired
	private RequestService requestService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String request(@RequestBody RequestModel requestModel) {
        return requestService.request(requestModel);
    }
}
