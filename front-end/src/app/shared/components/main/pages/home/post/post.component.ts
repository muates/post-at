import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { PostResponse } from 'src/app/core/models/post/post-response';

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

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {}

  toggleLike() {
    this.isLiked = !this.isLiked;
  }

  toggleComments() {
    this.showComments = !this.showComments;
  }

  prev() {
    this.currentIndex = (this.currentIndex > 0) ? this.currentIndex - 1 : this.post.media.length - 1;
  }

  next() {
    this.currentIndex = (this.currentIndex < this.post.media.length - 1) ? this.currentIndex + 1 : 0;
  }
}
