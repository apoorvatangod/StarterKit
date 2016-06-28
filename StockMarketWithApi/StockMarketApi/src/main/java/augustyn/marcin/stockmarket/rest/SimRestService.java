package augustyn.marcin.stockmarket.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.main.Main;

@Controller
@ResponseBody
@RequestMapping("/")
public class SimRestService {
	
	@Autowired
	private Main main;
	
	@RequestMapping(value = "/sim", method = RequestMethod.GET)
    public ResponseEntity<List<OfferTo>> simulate() {
 
		return new ResponseEntity<List<OfferTo>>(main.executeSim(), HttpStatus.OK);
    }

}
