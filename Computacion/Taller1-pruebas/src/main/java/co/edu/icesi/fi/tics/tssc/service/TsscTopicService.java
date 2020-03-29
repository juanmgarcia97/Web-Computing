package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Service
public interface TsscTopicService {

	TsscTopic saveTopic(TsscTopic newTopic) throws Exception;
	TsscTopic editTopic(TsscTopic newTopic) throws Exception;
}
