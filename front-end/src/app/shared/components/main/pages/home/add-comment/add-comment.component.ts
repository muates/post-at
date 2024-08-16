import { Component, Input, OnInit } from '@angular/core';
import { CommentRequest } from 'src/app/core/models/post/request/comment-request';
import { UserService } from 'src/app/core/services/identity/user.service';
import { CommentService } from 'src/app/core/services/post/comment.service';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  @Input() postId!: number;
  @Input() postOwnerId!: number;

  userId: number | null = 0;
  commentContent: string = '';
  

  constructor(
    private commentService: CommentService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.userId = this.userService.getUserIdFromToken();
  }

  addComment() {
    if (!this.commentContent.trim()) {
      return;
    }

    const commentRequest: CommentRequest = {
      postId: this.postId,
      userId: this.userId!,
      content: this.commentContent,
      postOwnerId: this.postOwnerId
    };

    this.commentService.addComment(commentRequest).subscribe(
      () => {
        this.commentContent = '';
      },
      error => {
        console.error('Error adding comment:', error);
      }
    );
  }

}
