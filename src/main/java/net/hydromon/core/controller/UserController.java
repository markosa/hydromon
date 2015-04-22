package net.hydromon.core.controller;

import javax.validation.Valid;

import net.hydromon.core.dto.AddSensor;
import net.hydromon.core.dto.AddSensorValue;
import net.hydromon.core.dto.SensorDTO;
import net.hydromon.core.service.SensorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private SensorService sensorService;


	@RequestMapping(value = "add", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody SensorDTO addUser(@Valid @RequestBody AddSensor sensor) {

		return null;
	}
}
