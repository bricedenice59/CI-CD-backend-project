package com.openclassrooms.bobapp.service;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.bobapp.data.JsonReader;
import com.openclassrooms.bobapp.model.Joke;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JokeServiceTest {

    @Mock
    private JsonReader jsonReader;

    @InjectMocks
    private JokeService jokeService;

    @BeforeEach
    public void setUp() {
        // Mock the JsonReader to return a list of jokes
        when(jsonReader.getJokes()).thenReturn(createMockJokes());
    }

    private List<Joke> createMockJokes() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        ObjectNode joke1 = mapper.createObjectNode();
        joke1.put("joke", "Why did the chicken cross the playground? To get to the other slide!");
        arrayNode.add(joke1);
        ObjectNode joke2 = mapper.createObjectNode();
        joke2.put("joke", "Why don't skeletons fight each other? They don't have the guts.");
        arrayNode.add(joke2);
        return mapper.convertValue(arrayNode, new TypeReference<>() {});
    }

    @Test
    public void testGetRandomJoke() {
        Joke randomJoke = jokeService.getRandomJoke();
        Assertions.assertNotNull(randomJoke, "The random joke should not be null");
    }
}
