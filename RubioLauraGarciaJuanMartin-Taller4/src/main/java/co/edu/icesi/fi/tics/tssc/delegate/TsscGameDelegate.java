package co.edu.icesi.fi.tics.tssc.delegate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface TsscGameDelegate {

	TsscGame saveGame(TsscGame tsscGame);
	TsscGame editGame(TsscGame tsscGame);
	Iterable<TsscGame> findAll();
	TsscGame getGame(int id);
	void deleteGame(TsscGame tsscGame);
}
