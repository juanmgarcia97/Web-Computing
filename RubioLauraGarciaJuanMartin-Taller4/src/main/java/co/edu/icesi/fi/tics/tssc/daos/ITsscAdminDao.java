package co.edu.icesi.fi.tics.tssc.daos;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;


public interface ITsscAdminDao {

	public void save(TsscAdmin entity);
	public void update(TsscAdmin entity);
	public void delete(TsscAdmin entity);
	public TsscAdmin findById(long id);
	public List<TsscAdmin> findAll();
}
