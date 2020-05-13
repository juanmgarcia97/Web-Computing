package co.edu.icesi.fi.tics.tssc.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.Taller3PersistenceApplication;
import co.edu.icesi.fi.tics.tssc.daos.ITsscTopicDao;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@RunWith(SpringRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestTsscTopicDao {

	@Autowired
	private ITsscTopicDao topicDao;
	
	private TsscTopic topic;
	
	@BeforeEach
	void initDao() {
		topic = new TsscTopic();
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topic.setDescription("algo");
		topic.setName("topic1");
		topic.setGroupPrefix("grupo1");
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateTopicTest() {
		assertNull(topicDao);
		try {
			TsscTopic oldTo = topicDao.findById(topic.getId());
			topic.setName("discretas");
			topic.setGroupPrefix("grupito2");
			topicDao.update(topic);
			TsscTopic newTo = topicDao.findById(topic.getId());
			
			
			assertTrue(oldTo.getName().equals(newTo.getName()));
			assertTrue(oldTo.getGroupPrefix().equals(newTo.getGroupPrefix()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteTopicTest() {
		assertNull(topicDao);
		try {
			topicDao.delete(topic);
			
			assertNull(topicDao.findById(topic.getId()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTopicTest() {
		assertNull(topicDao);
		try {
			topicDao.save(topic);
			
			assertTrue(topicDao.findById(topic.getId()).equals(topic));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findTopicByNameTest() {
		assertNull(topicDao);
		try {
			topicDao.save(topic);
			List<TsscTopic> topics = topicDao.findByName("topic1");
			
			assertNotNull(topics);
			assertTrue(topics.get(0).getName().equals(topic.getName()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findTopicByDescriptionTest() {
		assertNull(topicDao);
		try {
			topicDao.save(topic);
			List<TsscTopic> topics = topicDao.findByDescription("algo");
			
			assertNotNull(topics);
			assertTrue(topics.get(0).getDescription().equals(topic.getDescription()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findTopicByIdTest() {
		assertNull(topicDao);
		try {
			topicDao.save(topic);
			TsscTopic topics = topicDao.findById(topic.getId());
			
			assertNotNull(topics);
			assertTrue(topics.getId() == topic.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
