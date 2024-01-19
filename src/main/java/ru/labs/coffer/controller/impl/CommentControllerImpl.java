package ru.labs.coffer.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.labs.coffer.controller.CommentController;
import ru.labs.coffer.dto.CommentDto;
import ru.labs.coffer.service.CommentService;

@Validated
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentControllerImpl implements CommentController {
    private final CommentService commentService;

    @Override
    @PostMapping
    public CommentDto create(@Valid @RequestBody CommentDto commentDto) {
        return commentService.create(commentDto);
    }

    @Override
    @DeleteMapping("/{comment-id}")
    public void delete(@PathVariable("comment-id") Long id) {
        commentService.delete(id);
    }

    @Override
    @DeleteMapping("/advertisement/{id}")
    public void deleteByAdvertisementId(@PathVariable Long id) {
        commentService.deleteByAdvertisementId(id);
    }

    @Override
    @GetMapping("/advertisement/{id}")
    public Page<CommentDto> findAllByAdvertisementId(@PathVariable Long id, Pageable pageable) {
        return commentService.findAllByAdvertisementId(id, pageable);
    }
}
