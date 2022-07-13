package com.sparta.finalproject6.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    private Long postId;
    private Long id;
    @NotNull(message = "댓글을 입력해 주세요.")
    private String comment;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
