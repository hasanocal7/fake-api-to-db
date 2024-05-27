package com.mergen.task3.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergen.task3.dto.UserDTO;
import com.mergen.task3.entity.Address;
import com.mergen.task3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class UserHelper {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TypeReference<Iterable<UserDTO>> typeReference;

    public Iterable<UserDTO> connectJsonData() throws IOException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://dummyapi.online/api/users",
                String.class
        );
        JsonNode usersNode = objectMapper.readTree(responseEntity.getBody());
        return objectMapper.readValue(usersNode.traverse(), typeReference);
    }

    public List<User> dtoToEntityConverter() throws IOException {
        Iterable<UserDTO> userDTOIterable = connectJsonData();
        return StreamSupport.stream(userDTOIterable.spliterator(), false)
                .map(this::UserDtoToUserEntity).toList();
    }

    private User UserDtoToUserEntity(UserDTO userDTO){
        return new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                new Address(
                        null,
                        userDTO.getAddress().getStreet(),
                        userDTO.getAddress().getCity(),
                        userDTO.getAddress().getState(),
                        userDTO.getAddress().getZipcode()
                )
        );
    }
}
