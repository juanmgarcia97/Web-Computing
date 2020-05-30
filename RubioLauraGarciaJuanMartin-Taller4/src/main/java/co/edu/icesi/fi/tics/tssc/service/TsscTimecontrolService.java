package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

public interface TsscTimecontrolService {

	TsscTimecontrol saveTime(TsscTimecontrol newTime);
	TsscTimecontrol saveTimeGame(TsscTimecontrol newTime, TsscGame game);
	TsscTimecontrol editTime(TsscTimecontrol newTime);
	TsscTimecontrol editTimeGame(TsscTimecontrol newTime, TsscGame game);
	Iterable<TsscTimecontrol> findAll();
	Optional<TsscTimecontrol> findById(Long id);
	void delete(TsscTimecontrol time);
}
