package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;
@Service
public class TsscTopicServiceImp implements TsscTopicService {

	TsscTopicRepository topicRepository;

	@Autowired
	public TsscTopicServiceImp(TsscTopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	@Override
	@Transactional
	public TsscTopic saveTopic(TsscTopic newTopic) throws Exception {
		if (newTopic == null) {
			throw new Exception("Topic does not exists");
		} else if (newTopic.getDefaultSprints() <= 0) {
			throw new Exception("InvalidDefaultSprintsException");
		} else if (newTopic.getDefaultGroups() <= 0) {
			throw new Exception("InvalidDefaultGroupsException");
		} else {
			topicRepository.save(newTopic);
			return newTopic;
		}
	}

	@Override
	@Transactional
	public TsscTopic editTopic(TsscTopic newTopic) throws Exception {
		TsscTopic finded = topicRepository.findById(newTopic.getId()).get();
		if(finded == null) {
			throw new Exception("Topic does not exists");
		} else if(finded.getDefaultSprints() <= 0) {
			throw new Exception("InvalidDefaultSprintsException");
		} else if(finded.getDefaultGroups() <= 0) {
			throw new Exception("InvalidDefaultGroupsException");
		} else {
			topicRepository.save(finded);
			return finded;
		}
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		// TODO Auto-generated method stub
		return topicRepository.findAll();
	}

	@Override
	public Optional<TsscTopic> findById(Long id) {
		// TODO Auto-generated method stub
		return topicRepository.findById(id);
	}

	@Override
	@Transactional
	public void delete(TsscTopic topic) {
		// TODO Auto-generated method stub
		topicRepository.delete(topic);
	}

}
