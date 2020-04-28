package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Service
public interface TsscAdminService {

	public TsscAdmin save(TsscAdmin admin);
	public TsscAdmin edit(TsscAdmin admin);
	public void delete(TsscAdmin admin);
}
