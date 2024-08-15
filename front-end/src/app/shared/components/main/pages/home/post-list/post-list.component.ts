import { Component, HostListener, OnInit } from '@angular/core';
import { PostResponse } from 'src/app/core/models/post/response/post-response';
import { PostService } from 'src/app/core/services/post/post.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts: PostResponse[] = [];
  currentPage: number = 0;
  totalPages: number = 10;
  isLoading: boolean = false;

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts() {
    if (this.isLoading) return;
    this.isLoading = true;

    this.postService.getPosts(this.currentPage).subscribe(
      (response) => {
        this.posts = [...this.posts, ...response.content];
        this.totalPages = response.totalPages;
        this.isLoading = false;
      },
      (error) => {
        console.error('Error loading posts', error);
        this.isLoading = false;
      }
    );
  }

  @HostListener('window:scroll', [])
  onScroll(): void {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight && !this.isLoading && this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadPosts();
    }
  }
}
