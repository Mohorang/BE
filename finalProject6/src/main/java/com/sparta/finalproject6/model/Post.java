package com.sparta.finalproject6.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.finalproject6.dto.requestDto.PostRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

//    @ElementCollection
//    @CollectionTable(name = "postImagesUrl",joinColumns = {@JoinColumn(name = "post_id",referencedColumnName = "POST_ID")})
//    private List<String> imgUrl;
//
//    //TODO : 20220701
//    //S3에서 기존 파일을 삭제하기 위해 추가한 파일네임.
//    @Column
//    @ElementCollection
//    @CollectionTable(name = "postImagesFileName",joinColumns = {@JoinColumn(name = "post_id",referencedColumnName = "POST_ID")})
//    private List<String> imgFileName;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    private List<Place> place;

    @Column(nullable = false)
    private String content;

    @Column
    private int loveCount;

    @Column
    private int bookmarkCount;

    @Column
    private int viewCount;

    @Column
    private int commentCount;

    @Column(nullable = false)
    private String regionCategory;
    @Column(nullable = false)
    private String priceCategory;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<ThemeCategory> themeCategories;

    @OneToMany(mappedBy = "post", orphanRemoval = true) // 부모 객체 삭제시 하위 객첵도 삭제
    @JsonManagedReference //직렬화 허용
    private List<Comment> comments;

    //isLove는 게시글 조회에서 좋아요 상태를 요청할때 유저별로 좋아요 상태를 반환해주기 위한
    //그저 하나의 변수로서 사용하기 때문에 DB에 저장하지 않는다.
    @Transient
    @Builder.Default
    private Boolean isLove = false;

    @Transient
    @Builder.Default
    private Boolean isBookmark = false;

//    @OneToMany(mappedBy = "post", orphanRemoval = true)
//    private List<Love> loves;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column
    private String restroom;

    @Column
    @ElementCollection
    @CollectionTable(name = "restroomOption",joinColumns ={@JoinColumn(name = "post_id",referencedColumnName = "POST_ID")})
    private List<String> restroomOption;


    public Post(User user, PostRequestDto requestDto) {
        this.user = user;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.regionCategory = requestDto.getRegionCategory();
        this.priceCategory = requestDto.getPriceCategory();
    }

    public void update(PostRequestDto postRequestDto) {
//        this.user = user;
        this.title = postRequestDto.getTitle();;
        this.content = postRequestDto.getContent();
        this.regionCategory = postRequestDto.getRegionCategory();
        this.priceCategory = postRequestDto.getPriceCategory();
        this.restroom = postRequestDto.getRestroom();
        this.restroomOption = postRequestDto.getRestroomOption();
    }

    //좋아요 수 업데이트
    public void updateLikeCount(boolean countUp){
        if (countUp) {
            ++this.loveCount;
        } else {
            --this.loveCount;
        }
    }

    public void updateBookmarkCount(boolean countUp){
        if(countUp){
            ++this.bookmarkCount;
        }
        else{
            --this.bookmarkCount;
        }
    }

    public void viewCountUp(){
        this.viewCount++;
    }

    public void updateCommentCount(int commentCount) {

        this.commentCount = commentCount;
    }
}
