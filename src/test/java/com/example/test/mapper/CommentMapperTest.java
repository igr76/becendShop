package com.example.test.mapper;

import com.example.test.dto.CommentDTO;
import com.example.test.entity.AdEntity;
import com.example.test.entity.CommentEntity;
import com.example.test.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;
    @Test
    void toDTO() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1);
        commentEntity.setAd(getAdEntity());
        commentEntity.setCreatedAt(LocalDateTime.of(2023, 02, 16, 14,30, 22));
        commentEntity.setAuthor(getAuthor());
        commentEntity.setText("Test text");

        CommentDTO commentDTO = new CommentDTO( 1, "16-02-2023 14:30:22", 1, "Test text");

        CommentDTO actual = commentMapper.toDTO(commentEntity);
        assertEquals(commentDTO, actual);
    }
    private AdEntity getAdEntity() {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(1);
        adEntity.setTitle("afsdf");
        adEntity.setPrice(123);
        adEntity.setDescription("asfsdf");
        return adEntity;
    }

    private UserEntity getAuthor() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setRegDate(LocalDateTime.of(2023, 02, 16, 14,30, 22));
        userEntity.setPhone("7884643");
        userEntity.setEmail("asda@asd.re");
        userEntity.setCity("MSK");
        userEntity.setFirstName("Иван");
        userEntity.setLastName("Иванов");
        userEntity.setImage("/user/image/1");
        return userEntity;
    }
}