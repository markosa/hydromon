package net.hydromon.core.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.hydromon.core.dto.SensorDTO;
import net.hydromon.core.dto.SensorValueDTO;
import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.SensorValue;
import net.hydromon.core.service.SensorService;
import net.hydromon.core.service.ValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class ViewController {

	@Autowired
	private SensorService sensorService;

	@Autowired
	private ValueService valueService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");

		mav.addObject("sensors", sensorService.getSensors());

		return mav;
	}

	@RequestMapping(value = "/sensor/{id}", method = RequestMethod.GET)
	public ModelAndView listSensors(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("sensor-values");

		Sensor sensor = sensorService.getSensor(id);

		Timestamp now = new Timestamp(new Date().getTime());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Timestamp tsDay = new Timestamp(cal.getTimeInMillis());

		cal.setTimeInMillis(now.getTime());
		cal.add(Calendar.DAY_OF_YEAR, -7);
		Timestamp tsWeek = new Timestamp(cal.getTimeInMillis());

		cal.setTimeInMillis(now.getTime());
		cal.add(Calendar.MONTH, -1);
		Timestamp tsMonth = new Timestamp(cal.getTimeInMillis());

		List<SensorValue> values = valueService.getValues(sensor, tsMonth, now);

		List<SensorValueDTO> day = new ArrayList<SensorValueDTO>();
		List<SensorValueDTO> week = new ArrayList<SensorValueDTO>();
		List<SensorValueDTO> month = new ArrayList<SensorValueDTO>();

		for (SensorValue value : values) {
			if (value.getTimestamp().after(tsDay))
				day.add(new SensorValueDTO(id, value.getValue(), value.getTimestamp().getTime()));

			if (value.getTimestamp().after(tsWeek))
				week.add(new SensorValueDTO(id, value.getValue(), value.getTimestamp().getTime()));

			if (value.getTimestamp().after(tsMonth))
				month.add(new SensorValueDTO(id, value.getValue(), value.getTimestamp().getTime()));
		}

		SensorDTO sensorDTO = new SensorDTO();
		sensorDTO.setDescription(sensor.getDescription());
		sensorDTO.setName(sensor.getName());
		sensorDTO.setLocation(sensor.getLocation());
		sensorDTO.setType(sensor.getType());
		sensorDTO.setChart(sensor.getChart());

		mav.addObject("sensor", sensorDTO);

		mav.addObject("dayValues", day);
		mav.addObject("weekValues", week);
		mav.addObject("monthValues", month);

		return mav;
	}

}
