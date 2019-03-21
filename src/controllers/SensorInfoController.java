package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sensors.objects.ServiceInformation;
import sensors.services.SensorInterface;

@Controller
@RequestMapping({"/sensorinfo"})
public class SensorInfoController {
	
	private Map<String,Object> sensorInfoMap;
	
	@Autowired
	@Qualifier("airLyInfo")
	private SensorInterface info;
	
	public SensorInfoController() {
		sensorInfoMap = new HashMap<>();
	}
	
	private Map<String,Object> getInformations() {
		sensorInfoMap.put("airLy", info.getSensor(new ServiceInformation()));
		return sensorInfoMap;
	}
	
	@RequestMapping(method=GET)
	public String settings(Model model) {	
		model.addAttribute("sensorMap", getInformations());
		return "sensorinfo";
	}

}
