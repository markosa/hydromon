package net.hydromon.core.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import net.hydromon.core.dto.AddSensor;
import net.hydromon.core.dto.AddSensorValue;
import net.hydromon.core.dto.BaseResponseDTO;
import net.hydromon.core.dto.GetSensorValues;
import net.hydromon.core.dto.GetSensorValuesRange;
import net.hydromon.core.dto.SensorDTO;
import net.hydromon.core.dto.SensorValueDTO;
import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.SensorValue;
import net.hydromon.core.model.User;
import net.hydromon.core.service.PermissionService;
import net.hydromon.core.service.SensorService;
import net.hydromon.core.service.UserService;
import net.hydromon.core.service.ValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/sensor")
public class SensorController {

	@Autowired
	private SensorService sensorService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private UserService userService;

	@Autowired
	private ValueService valueService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<SensorDTO> getSensor(@PathVariable Long id) {

		SensorDTO sensorDTO = new SensorDTO();

		Sensor sensor = sensorService.getSensor(id);

		if (sensor != null) {
			sensorDTO.setDescription(sensor.getDescription());
			sensorDTO.setId(sensor.getId());
			sensorDTO.setName(sensor.getName());
			sensorDTO.setStatus("OK");
		} else {
			sensorDTO.setStatus("NOT FOUND");
			return new ResponseEntity<SensorDTO>(sensorDTO, HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<SensorDTO>(sensorDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}/addvalue", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResponseDTO> addValue(@PathVariable String id, @Valid @RequestBody AddSensorValue addValue) {
		BaseResponseDTO response = new BaseResponseDTO();

		if (permissionService.canProceed(addValue.getUid(), addValue.getApikey())) {
			Sensor sensor = sensorService.getSensor(new Long(id));

			if (sensor != null && sensor.getUser() != null && sensor.getUser().getUid().equals(addValue.getUid())) {
				SensorValue value = new SensorValue();
				value.setTimestamp(new Timestamp(addValue.getTime()));
				value.setSensor(sensor);
				value.setValue(addValue.getValue());
				value = valueService.addValue(value);

				response.setStatus("OK");

			} else {
				response.setStatus("ACCESS DENIED OR SENSOR NOT FOUND");
				return new ResponseEntity<BaseResponseDTO>(response, HttpStatus.FORBIDDEN);

			}
		} else {
			response.setStatus("DENIED");
			return new ResponseEntity<BaseResponseDTO>(response, HttpStatus.FORBIDDEN);

		}

		return new ResponseEntity<BaseResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<SensorDTO> addSensor(@Valid @RequestBody AddSensor addSensorDTO) {
		SensorDTO sensorDTO = new SensorDTO();

		if (permissionService.canProceed(addSensorDTO.getUid(), addSensorDTO.getApikey())) {
			User user = userService.getUser(addSensorDTO.getUid());
			Sensor sensor = new Sensor();
			sensor.setDescription(addSensorDTO.getDescription());
			sensor.setName(addSensorDTO.getName());
			sensor.setUser(user);
			sensor = sensorService.addSensor(sensor);

			if (sensor != null) {
				sensorDTO.setDescription(sensor.getDescription());
				sensorDTO.setName(sensor.getName());
				sensorDTO.setId(sensor.getId());
				sensorDTO.setStatus("OK");
			} else {
				sensorDTO.setStatus("FAIL");
				return new ResponseEntity<SensorDTO>(sensorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} else {
			sensorDTO.setStatus("DENIED");
			return new ResponseEntity<SensorDTO>(sensorDTO, HttpStatus.FORBIDDEN);

		}

		return new ResponseEntity<SensorDTO>(sensorDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}/listvalues", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<List<SensorValueDTO>> listValues(@PathVariable Long id, @Valid @RequestBody GetSensorValues getSensorValues) {
		List<SensorValueDTO> valuesDTO = new ArrayList<SensorValueDTO>();

		Sensor sensor = sensorService.getSensor(id);
		List<SensorValue> values = valueService.getValues(sensor);

		if (values != null) {
			for (SensorValue v : values)
				valuesDTO.add(new SensorValueDTO(v.getSensor().getId(), v.getValue(), v.getTimestamp().getTime()));
		} else {
			return new ResponseEntity<List<SensorValueDTO>>(valuesDTO, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<List<SensorValueDTO>>(valuesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}/listvaluesrange", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<List<SensorValueDTO>> listValues(@PathVariable Long id, @Valid @RequestBody GetSensorValuesRange getSensorValues) {
		List<SensorValueDTO> valuesDTO = new ArrayList<SensorValueDTO>();

		Sensor sensor = sensorService.getSensor(id);
		List<SensorValue> values = valueService.getValues(sensor, new Timestamp(getSensorValues.getFrom()), new Timestamp(getSensorValues.getTo()));

		if (values != null) {
			for (SensorValue v : values)
				valuesDTO.add(new SensorValueDTO(v.getSensor().getId(), v.getValue(), v.getTimestamp().getTime()));
		} else {
			return new ResponseEntity<List<SensorValueDTO>>(valuesDTO, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<List<SensorValueDTO>>(valuesDTO, HttpStatus.OK);
	}
}
