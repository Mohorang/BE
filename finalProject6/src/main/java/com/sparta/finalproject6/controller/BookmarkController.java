package com.sparta.finalproject6.controller;


import com.sparta.finalproject6.dto.responseDto.TrueFalseDto;
import com.sparta.finalproject6.security.UserDetailsImpl;
import com.sparta.finalproject6.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/api/bookmark/{postId}")
    public TrueFalseDto addBookmark(@PathVariable Long postId , @AuthenticationPrincipal UserDetailsImpl userDetails){
        Boolean isBookmark = bookmarkService.bookmark(postId,userDetails);
        return TrueFalseDto.builder().trueOrFalse(isBookmark)
                .postId(postId)
                .build();
    }
}
