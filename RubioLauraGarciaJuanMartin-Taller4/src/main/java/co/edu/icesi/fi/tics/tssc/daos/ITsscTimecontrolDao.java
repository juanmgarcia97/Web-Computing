package co.edu.icesi.fi.tics.tssc.daos;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;


public interface ITsscTimecontrolDao {

	public void save(TsscTimecontrol entity);
	public void update(TsscTimecontrol entity);
	public void delete(TsscTimecontrol entity);
	public TsscTimecontrol findById(long id);
	public List<TsscTimecontrol> findAll();
}
