package co.edu.icesi.fi.tics.tssc.unit.test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicServiceImp;
@Nested
@RunWith(MockitoJUnitRunner.class)
class TsscTopicServiceTests {

	@Mock
	TsscTopicRepository topicRepository;

	@InjectMocks
	TsscTopicServiceImp topicService;

	TsscTopic topic;
	
	@BeforeEach
	void initializeMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveTopicException() {
		topic = null;
		assertThrows(Exception.class, () -> {
			topicService.saveTopic(topic);
		});
	}

	@Test
	void testSaveTopicGroupsException() {
		topic = new TsscTopic();
		topic.setDefaultGroups(0);
		topic.setDefaultSprints(1);
		assertThrows(Exception.class, () -> {
			topicService.saveTopic(topic);
		});
	}

	@Test
	void testSaveTopicSprintsException() {
		topic = new TsscTopic();
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(0);
		assertThrows(Exception.class, () -> {
			topicService.saveTopic(topic);
		});
	}

	@Test
	void testSaveTopicNoException() {
		topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		assertTrue(topic.getDefaultGroups() > 0);
		assertTrue(topic.getDefaultSprints() > 0);
	}

	@Nested
	class EditTopicTest {

		@Test
		void testEditTopicException() {
			topic = null;
			assertThrows(Exception.class, () -> {
				topicService.editTopic(topic);
			});
		}

		@Test
		void testEditTopicSprintsException() throws Exception {
			topic = new TsscTopic();
			topic.setDefaultSprints(0);
			topic.setDefaultGroups(1);
			assertThrows(Exception.class, () -> {
				topicService.editTopic(topic);
			});
		}

		@Test
		void testEditTopicGroupsException() {
			topic = new TsscTopic();
			topic.setDefaultSprints(1);
			topic.setDefaultGroups(0);
			assertThrows(Exception.class, () -> {
				topicService.editTopic(topic);
			});
		}

		@Test
		void testEditTopicNoException() {
			topic = new TsscTopic();
			topic.setDefaultSprints(1);
			topic.setDefaultGroups(1);
			Optional<TsscTopic> finded = Optional.of(topic);
			when(topicRepository.findById(topic.getId())).thenReturn(finded);
		}
	}

}
