package ru.labs.coffer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.labs.coffer.dto.CommentDto;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Comment;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.exception.RelativeNotFoundException;
import ru.labs.coffer.mapper.CommentMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.CommentRepository;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.service.CommentService;
import ru.labs.coffer.service.security.JwtAuthorizationService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PersonRepository personRepository;
    private final AdvertisementRepository advertisementRepository;
    private final JwtAuthorizationService jwtAuthorizationService;


    @Override
    public CommentDto create(CommentDto dto) {
        dto.setId(null);
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();
        Long personId = jwtPerson.getId();
        Comment comment = commentMapper.toEntity(dto);
        Advertisement advertisement = advertisementRepository.getReferenceById(dto.getAdvertisementId());

        if (Objects.isNull(advertisement.getId())) {
            throw new RelativeNotFoundException("Adv id mist not be null");
        }
        Person person = personRepository.getReferenceById(personId);

        comment.setPerson(person);
        comment.setAdvertisement(advertisement);

        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto read(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found with id: {0}", id));
        return commentMapper.toDto(comment);
    }

    @Override
    public void update(CommentDto dto) {
        Comment newComment = commentMapper.toEntity(dto);
        Comment exComment = commentRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Comment not found with id: {0}", dto.getId()));
        commentMapper.update(exComment, newComment);
        commentRepository.save(exComment);

    }

    @Override
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found with id: {0}", id));
        comment.setDeleted(true);
        commentRepository.save(comment);

    }

    @Override
    public void deleteByAdvertisementId(Long id) {
        List<Comment> comments = commentRepository.findAllByAdvertisementId(id);
        List<Comment> forSave = comments.stream().peek(comment -> comment.setDeleted(true)).collect(Collectors.toList());
        commentRepository.saveAll(forSave);
    }

    @Override
    public Page<CommentDto> findAllByAdvertisementId(Long id, Pageable pageable) {
        return commentRepository.findAllByAdvertisementId(id, pageable).map(commentMapper::toDto);
    }
}
