package co.edu.icesi.fi.tics.tssc.integration.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import co.edu.icesi.fi.tics.tssc.Taller2ThymeleafApplication;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@Nested
@RunWith(SpringJUnit4ClassRunner.class)
class TsscTopicServiceIntegrationTests {

	@Autowired
	TsscTopicService topicService;
	

	TsscTopic topic;

	@BeforeEach
	void initializeMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveTopicException() {
		topic = null;
		try {
			topicService.saveTopic(topic);
		} catch (Exception e) {
			assertTrue(true);
		}

	}

	@Test
	void testSaveTopicNoException() {
		topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		try {
			assertNotNull(topic);
			topicService.saveTopic(topic);
		} catch (Exception e) {
			assertTrue(topic.getDefaultGroups() > 0);
			assertTrue(topic.getDefaultSprints() > 0);
		}
		
	}

	@Nested
	class EditTopicIntegrationTest {

		@Test
		void testEditTopicException() {
			topic = null;

			try {
				topicService.editTopic(topic);
			} catch (Exception e) {
				assertTrue(true);
			}
		}

		@Test
		void testEditTopicNoException() {
			topic = new TsscTopic();
			topic.setDefaultSprints(2);
			topic.setDefaultGroups(2);
			try {
				assertNotNull(topic);
				topicService.editTopic(topic);
			} catch (Exception e) {
				assertTrue(topic.getDefaultGroups() > 0);
				assertTrue(topic.getDefaultSprints() > 0);
			}
			
		}
	}

}
