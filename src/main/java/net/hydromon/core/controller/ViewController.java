package net.hydromon.core.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.hydromon.core.dto.SensorDTO;
import net.hydromon.core.dto.SensorValueDTO;
import net.hydromon.core.dto.StatisticsDTO;
import net.hydromon.core.dto.util.DTOUtils;
import net.hydromon.core.dto.util.TimeformatUtil;
import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.SensorValue;
import net.hydromon.core.model.User;
import net.hydromon.core.service.SensorService;
import net.hydromon.core.service.UserService;
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

	@Autowired
	private UserService userService;

	@Autowired
	private DTOUtils dtoUtil;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "redirect:/user/markos";
	}

	public static final int DAY_RESOLUTION = 1; // Select every row = 1 min
												// resolution
	public static final int WEEK_RESOLUTION = 60; // Select every 60th row = 60
													// min resolution
	public static final int MONTH_RESOLUTION = 240; // 4 hour resolution

	@RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
	public ModelAndView listSensors(@PathVariable String uid) {

		ModelAndView mav = new ModelAndView("sensor-list");

		User user = userService.getUser(uid);

		List<SensorDTO> sensorsDTO = new ArrayList<SensorDTO>();

		if (user != null) {
			mav.addObject("uid", uid);

			List<Sensor> sensors = sensorService.getSensors(user);

			for (Sensor s : sensors) {
				SensorDTO dto = dtoUtil.convert(s);
				dto.setStatus("OK");
				dto.setLatestValues(valueService.getLatestSensorValues(s, 3));
				SensorValue v = valueService.getLatestSensorValue(s);
				if (v != null) {
					dto.setLatestValue(v.getValue());
					dto.setLatestValueTime(TimeformatUtil.formatDate(v.getTimestamp()));
				}
				sensorsDTO.add(dto);
			}

		}

		mav.addObject("sensors", sensorsDTO);

		return mav;
	}

	@RequestMapping(value = "/user/{uid}/{id}", method = RequestMethod.GET)
	public ModelAndView listValues(@PathVariable String uid, @PathVariable Long id) {

		ModelAndView mav = new ModelAndView("sensor-values");
		mav.addObject("uid", uid);

		User user = userService.getUser(uid);
		List<SensorDTO> sensorsDTO = new ArrayList<SensorDTO>();

		if (user != null) {

			List<Sensor> sensors = sensorService.getSensors(user);

			for (Sensor s : sensors) {
				SensorDTO dto = dtoUtil.convert(s);
				dto.setStatus("OK");
				dto.setLatestValues(valueService.getLatestSensorValues(s, 3));
				SensorValue v = valueService.getLatestSensorValue(s);
				if (v != null) {
					dto.setLatestValue(v.getValue());
					dto.setLatestValueTime(TimeformatUtil.formatDate(v.getTimestamp()));
				}
				sensorsDTO.add(dto);
			}

		}

		mav.addObject("sensors", sensorsDTO);
		mav.addAllObjects(listValues(id).getModel());

		return mav;
	}

	@RequestMapping(value = "/sensor/{id}", method = RequestMethod.GET)
	public ModelAndView listValues(@PathVariable Long id) {
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

		SensorDTO sensorDTO = dtoUtil.convert(sensor);

		mav.addObject("sensor", sensorDTO);
		mav.addObject("dayValues", day);
		mav.addObject("dayStatistics", calculateStatistics(day));
		mav.addObject("weekValues", week);
		mav.addObject("weekStatistics", calculateStatistics(week));
		mav.addObject("monthValues", month);
		mav.addObject("monthStatistics", calculateStatistics(month));
		
		return mav;
	}

	private StatisticsDTO calculateStatistics(List<SensorValueDTO> values) {
		if (values != null && values.size() > 0) {
			StatisticsDTO s = new StatisticsDTO();
			s.setMax(Collections.max(values, new SensorValueComparator()).getValue());
			s.setMin(Collections.min(values, new SensorValueComparator()).getValue());
			Float total = 0f;
			for (SensorValueDTO dto : values) {
				total += new Float(dto.getValue());
			}
			s.setAvg(new Float(total / values.size()).toString());

			return s;

		}
		
		return null;
	}

}

class SensorValueComparator implements Comparator<SensorValueDTO> {

	public int compare(SensorValueDTO o1, SensorValueDTO o2) {
		return new Float(o1.getValue()).compareTo(new Float(o2.getValue()));
	}

}
