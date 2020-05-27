package co.edu.icesi.fi.tics.tssc.delegate.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import java.math.BigDecimal;
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
import co.edu.icesi.fi.tics.tssc.delegate.TsscStoryDelegateImp;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@RunWith(MockitoJUnitRunner.class)
class StoryDelegateTest {

	@Mock
	RestTemplate rest;
	
	@InjectMocks
	TsscStoryDelegateImp delegate;
	TsscStory story;
	TsscStory story2;
	
	@BeforeEach
	public void setUp()  {
		MockitoAnnotations.initMocks(this);
		story = new TsscStory();
		story.setBusinessValue(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(1));
		story.setDescription("Story1");
	}
	public void setUp2() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/stories/", story,TsscStory.class))
		.thenReturn(new ResponseEntity<>(story,HttpStatus.OK));
		
		delegate.saveStory(story);
	}
	public void setUp3() {
		setUp2();
		story2 = new TsscStory();
		story2.setBusinessValue(new BigDecimal(0));
		story2.setInitialSprint(new BigDecimal(1));
		story2.setPriority(new BigDecimal(1));
		story2.setDescription("Story2");
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/stories/", story2,TsscStory.class))
		.thenReturn(new ResponseEntity<>(story2,HttpStatus.OK));
		
		delegate.saveStory(story2);
		
	}
	
	@Test
	public void saveStoryTest() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/stories/", story,TsscStory.class))
		.thenReturn(new ResponseEntity<>(story,HttpStatus.OK));
		
		TsscStory saved = delegate.saveStory(story);
		assertEquals(saved.getDescription(), "Story1");
	}
	
	@Test
	public void findByTest() {
		
		setUp2();
		Mockito.when(rest.getForObject("http://localhost:8080/api/stories/0",TsscStory.class))
		.thenReturn(new ResponseEntity<TsscStory>(story,HttpStatus.OK).getBody());
		
		TsscStory found = delegate.findById(0);
		assertEquals(found.getDescription(), "Story1");
	}
	
	@Test
	public void deleteTest() {
		
		setUp2();
		delegate = mock(TsscStoryDelegateImp.class);
		Mockito.doNothing().when(delegate).delete(story);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/stories/0",TsscStory.class))
		.thenReturn(new ResponseEntity<TsscStory>(story,HttpStatus.OK).getBody());
		
		TsscStory found = delegate.findById(0);
		assertNull(found);
	}
	@Test
	public void editStoryTest() {
		
		setUp2();
		story.setDescription("Story2");
		
		Mockito.when(rest.patchForObject("http://localhost:8080/api/stories/", story, TsscStory.class))
		.thenReturn(new ResponseEntity<>(story,HttpStatus.OK).getBody());		
				
		delegate.editStory(story);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/stories/0",TsscStory.class))
		.thenReturn(new ResponseEntity<TsscStory>(story,HttpStatus.OK).getBody());
		
		TsscStory found = delegate.findById(0);
		assertEquals(found.getDescription(), "Story2");
	}
	
	@Test
	public void findAllTest() {
		
		setUp3();
		TsscStory[] stories = {story,story2};
		

		Mockito.when(rest.getForObject("http://localhost:8080/api/stories",TsscStory[].class))
		.thenReturn(new ResponseEntity<TsscStory[]>(stories ,HttpStatus.OK).getBody());	
		
		Iterable<TsscStory> historias = delegate.findAll();
		String names ="";
		for(TsscStory s:historias) {
			names+= s.getDescription()+" ";
		}
		assertEquals(names, "Story1 Story2 ");
	}

}
