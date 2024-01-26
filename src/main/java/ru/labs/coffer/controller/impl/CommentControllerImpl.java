package ru.labs.coffer.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommentDto> create(@Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.create(commentDto));
    }

    @Override
    @DeleteMapping("/{comment-id}")
    public ResponseEntity<Void> delete(@PathVariable("comment-id") Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/advertisement/{id}")
    public ResponseEntity<Page<CommentDto>> findAllByAdvertisementId(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(commentService.findAllByAdvertisementId(id, pageable));
    }
}
