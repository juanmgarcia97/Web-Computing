package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscGameService {

	TsscGame saveGame(TsscGame newGame, TsscTopic topic) throws Exception;
	TsscGame editGame(TsscGame newGame, TsscTopic topic) throws Exception;
	TsscGame saveGame2(TsscGame newGame, TsscTopic topic) throws Exception;
}
