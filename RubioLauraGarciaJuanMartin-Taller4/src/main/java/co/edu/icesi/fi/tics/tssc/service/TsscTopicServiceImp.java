package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.daos.ITsscTopicDao;
import co.edu.icesi.fi.tics.tssc.daos.TsscTopicDao;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;
@Service
public class TsscTopicServiceImp implements TsscTopicService {

	ITsscTopicDao topicDao;

	@Autowired
	public TsscTopicServiceImp(ITsscTopicDao topicDao) {
		this.topicDao = topicDao;
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
			topicDao.save(newTopic);
			return newTopic;
		}
	}

	@Override
	@Transactional
	public TsscTopic editTopic(TsscTopic newTopic) throws Exception {
		if(newTopic == null) {
			throw new Exception("Topic does not exists");
		} else if(newTopic.getDefaultSprints() <= 0) {
			throw new Exception("InvalidDefaultSprintsException");
		} else if(newTopic.getDefaultGroups() <= 0) {
			throw new Exception("InvalidDefaultGroupsException");
		} else {
			System.out.println(newTopic.getName() + " Service");
			topicDao.update(newTopic);
			return newTopic;
		}
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		// TODO Auto-generated method stub
		return topicDao.findAll();
	}

	@Override
	public Optional<TsscTopic> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(topicDao.findById(id));
	}

	@Override
	@Transactional
	public void delete(TsscTopic topic) {
		// TODO Auto-generated method stub
		topicDao.delete(topic);
	}

}
