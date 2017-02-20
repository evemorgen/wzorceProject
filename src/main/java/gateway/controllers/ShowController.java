package gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gateway.services.ShowService;

@Controller
@RequestMapping("/show")
public class ShowController {

	@Autowired
	private ShowService showService;
	
	@RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String show() {
        return showService.show();
    }
}
