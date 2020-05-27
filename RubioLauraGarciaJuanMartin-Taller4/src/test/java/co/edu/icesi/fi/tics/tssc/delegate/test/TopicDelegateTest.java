package co.edu.icesi.fi.tics.tssc.delegate.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.delegate.TsscTopicDelegateImp;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@RunWith(MockitoJUnitRunner.class)
class TopicDelegateTest {


	@Mock
	RestTemplate rest;
	
	@InjectMocks
	TsscTopicDelegateImp delegate;
	
	TsscTopic topic;
	@BeforeEach
	void initiMocks(){
		MockitoAnnotations.initMocks(this);
		topic = new TsscTopic();
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		topic.setName("Topic1");
		
	}
	public void setUp2(){
		MockitoAnnotations.initMocks(this);
		topic = new TsscTopic();
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		topic.setName("Topic1");
		Mockito.when(rest.postForEntity("http://localhost:8080/api/topics/", topic,TsscTopic.class))
		.thenReturn(new ResponseEntity<>(topic,HttpStatus.OK));
		delegate.saveTopic(topic);
		
	}
	
	public void setUp3(){
		MockitoAnnotations.initMocks(this);
		topic = new TsscTopic();
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		topic.setName("Topic1");
		
		TsscTopic topic2 = new TsscTopic();
		topic2.setDefaultGroups(10);
		topic2.setDefaultSprints(10);
		topic2.setName("Topic2");
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/topics/", topic,TsscTopic.class))
		.thenReturn(new ResponseEntity<>(topic,HttpStatus.OK));
		delegate.saveTopic(topic);
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/topics/", topic2,TsscTopic.class))
		.thenReturn(new ResponseEntity<>(topic2,HttpStatus.OK));
		delegate.saveTopic(topic2);
		
	}

	@Test
	public void saveTopicTest() {
			
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/topics/", topic,TsscTopic.class))
		.thenReturn(new ResponseEntity<>(topic,HttpStatus.OK));
		
		
		TsscTopic saved = delegate.saveTopic(topic);
		assertEquals(saved.getName(), "Topic1");
		
	}
	
	@Test
	public void findByIdTest() {
		
		setUp2();
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/topics/0",TsscTopic.class))
		.thenReturn(new ResponseEntity<TsscTopic>(topic, HttpStatus.OK).getBody());
		
		
		
		TsscTopic saved = delegate.findById(0);
		assertEquals(saved.getName(), "Topic1");
		
	}
	
	
	public void deleteTest() {
		//PENDIENTE
		setUp2();
		//rest.delete("http://localhost:8080/api/topics/0");
	//	Mockito.doNothing().when(rest.delete("http://localhost:8080/api/topics/"));
		
		
		
		
		//verify(delegate,times(1)).delete(topic.getId());
		
	}
	
	@Test
	public void editTopicTest() {
		
		setUp2();
		topic.setName("Topic2");
		
		Mockito.when(rest.patchForObject("http://localhost:8080/api/topics/", topic, TsscTopic.class))
		.thenReturn(new ResponseEntity<>(topic,HttpStatus.OK).getBody());		
				
		delegate.editTopic(topic);		

		Mockito.when(rest.getForObject("http://localhost:8080/api/topics/0",TsscTopic.class))
		.thenReturn(new ResponseEntity<TsscTopic>(topic, HttpStatus.OK).getBody());		
		
		TsscTopic edited = delegate.findById(0);
		
		assertEquals(edited.getName(), "Topic2");
		
	}
	
	@Test
	public void findAllTest() {
		//PENDIENTE
		
		setUp3();
		
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/topics/",TsscTopic[].class))
		.thenReturn(new ResponseEntity<TsscTopic[]>(HttpStatus.OK).getBody());
		
		
		//then(new ResponseEntity<TsscTopic>(HttpStatus.OK));	
		 Iterable<TsscTopic> topics = delegate.findAll();
		
//		TsscTopic topic = topics.iterator().next();
//		assertEquals(topic.getName(), "Topic1");
//		topic = topics.iterator().next();
//		assertEquals(topic.getName(), "Topic2");
		
	}
	
	

}
