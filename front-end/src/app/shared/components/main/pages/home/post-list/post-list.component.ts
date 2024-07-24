import { Component, OnInit } from '@angular/core';
import { PostResponse } from 'src/app/core/models/post/post-response';
import { PostService } from 'src/app/core/services/post/post.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts: PostResponse[] = [];
  currentPage = 0;
  totalPages = 0;

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts() {
    this.postService.getPosts(this.currentPage).subscribe(
      response => {
        console.log('Posts data:', response);
        this.posts = response.content;
        this.totalPages = response.totalPages;
      },
      error => {
        console.error('Error fetching posts', error);
      }
    );
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadPosts();
    }
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadPosts();
    }
  }
}
