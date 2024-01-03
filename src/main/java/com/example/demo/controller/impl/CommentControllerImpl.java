package com.example.demo.controller.impl;

import com.example.demo.controller.CommentController;
import com.example.demo.dto.CommentDto;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentControllerImpl implements CommentController {
    private final CommentService commentService;
    @Override
    @PostMapping
    public CommentDto create(CommentDto commentDto) {
        return commentService.create(commentDto);
    }

    @Override
    @GetMapping("/{id}")

    public CommentDto read(@PathVariable Long id) {
        return commentService.read(id);
    }

    @Override
    @PutMapping
    public void update(@RequestBody CommentDto dto) {
        commentService.update(dto);
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
