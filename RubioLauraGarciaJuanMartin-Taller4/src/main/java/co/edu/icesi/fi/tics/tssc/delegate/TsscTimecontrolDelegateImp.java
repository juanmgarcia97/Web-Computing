package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@Component
public class TsscTimecontrolDelegateImp implements TsscTimecontrolDelegate{

	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest;
	
	public TsscTimecontrolDelegateImp() {
		// TODO Auto-generated constructor stub
		rest = new RestTemplate();
	}
	
	@Override
	public TsscTimecontrol saveTime(TsscTimecontrol newTime) {
		TsscTimecontrol time = rest.postForEntity(URI + "api/times/", newTime, TsscTimecontrol.class).getBody();
		return time;
	}

	@Override
	public TsscTimecontrol editTime(TsscTimecontrol newTime) {
		rest.put(URI + "api/times/", newTime, TsscTimecontrol.class);
		return newTime;
	}

	@Override
	public Iterable<TsscTimecontrol> findAll() {
		TsscTimecontrol[] times = rest.getForObject(URI + "api/times/", TsscTimecontrol[].class);
		List<TsscTimecontrol> tms = Arrays.asList(times);
		return tms;
	}

	@Override
	public TsscTimecontrol findById(Long id) {
		TsscTimecontrol time = rest.getForObject(URI + "api/times/" + id , TsscTimecontrol.class);
		return time;
	}

	@Override
	public void delete(TsscTimecontrol time) {
		// TODO Auto-generated method stub
		rest.delete(URI + "api/times/" + time.getId());
	}

}
