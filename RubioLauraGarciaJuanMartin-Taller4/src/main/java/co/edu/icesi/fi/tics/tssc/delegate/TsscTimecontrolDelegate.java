package co.edu.icesi.fi.tics.tssc.delegate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

public interface TsscTimecontrolDelegate {

	TsscTimecontrol saveTime(TsscTimecontrol newTime);
	TsscTimecontrol editTime(TsscTimecontrol newTime);
	Iterable<TsscTimecontrol> findAll();
	TsscTimecontrol findById(Long id);
	void delete(TsscTimecontrol time);
}
