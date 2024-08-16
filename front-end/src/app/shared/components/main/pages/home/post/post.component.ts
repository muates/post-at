import { AfterViewInit, Component, HostListener, Input, OnInit } from '@angular/core';
import { PostCommentResponse } from 'src/app/core/models/post/response/post-comment-response';
import { PostResponse } from 'src/app/core/models/post/response/post-response';
import { CommentService } from 'src/app/core/services/post/comment.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit, AfterViewInit {

  @Input() post!: PostResponse;

  isLiked: boolean = false;
  showComments: boolean = false;
  defaultProfilePicture: string = 'assets/images/default-avatar.png';
  currentIndex: number = 0;
  comments: PostCommentResponse[] = [];
  isLoadingComments: boolean = false;
  pageSize: number = 10;
  currentPage: number = 0;
  totalComments: number = 0;
  noMoreComments: boolean = false;

  constructor(private commentService: CommentService) { }

  ngOnInit(): void { }

  ngAfterViewInit(): void { }

  toggleLike() {
    this.isLiked = !this.isLiked;
  }

  toggleComments() {
    this.showComments = !this.showComments;
    if (this.showComments && this.comments.length === 0) {
      this.loadComments();
    }
  }

  loadComments() {
    if (this.isLoadingComments) return;
    this.isLoadingComments = true;

    this.commentService.getComments(this.post.id, this.currentPage, this.pageSize)
      .subscribe(response => {
        this.comments = [...this.comments, ...response.content];

        this.totalComments = response.totalElements;
        this.currentPage++;

        this.noMoreComments = this.comments.length >= this.totalComments;

        this.isLoadingComments = false;
      }, error => {
        this.isLoadingComments = false;
      });
  }

  loadMoreComments() {
    if (!this.noMoreComments && !this.isLoadingComments) {
      this.loadComments();
    }
  }

  prev() {
    this.currentIndex = (this.currentIndex > 0) ? this.currentIndex - 1 : this.post.media.length - 1;
  }

  next() {
    this.currentIndex = (this.currentIndex < this.post.media.length - 1) ? this.currentIndex + 1 : 0;
  }
}
