import { Component, OnInit } from '@angular/core';
import { PostCreateRequest } from 'src/app/core/models/post/request/post-create-request';
import { MediaType } from 'src/app/core/models/post/request/post-media-create-request';
import { UserService } from 'src/app/core/services/identity/user.service';
import { PostService } from 'src/app/core/services/post/post.service';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  postContent: string = '';
  mediaUrls: string[] = [];
  showForm: boolean = false;

  constructor(private postService: PostService, private userService: UserService) { }

  ngOnInit(): void {
  }

  addMediaUrl() {
    if (this.mediaUrls.length < 5) {
      this.mediaUrls.push('');
    } else {
      alert('You can only add up to 5 media URLs.');
    }
  }

  updateMediaUrl(index: number, event: Event) {
    const inputElement = event.target as HTMLInputElement;
    this.mediaUrls[index] = inputElement.value;
  }

  submitPost() {
    const userId = this.userService.getUserIdFromToken();

    if (userId === null) {
      console.error('User ID is null. Cannot submit post.');
      return;
    }

    const newPost: PostCreateRequest = {
      userId: userId,
      content: this.postContent,
      media: this.mediaUrls.map(url => ({
        mediaUrl: url,
        mediaType: url.startsWith('http') && (url.endsWith('.jpg') || url.endsWith('.png')) ? MediaType.IMAGE : MediaType.VIDEO
      }))
    };

    this.postService.createPost(newPost).subscribe(response => {
      console.log('Post created successfully', response);
      this.resetForm();
    });
  }

  removeMediaUrl(index: number) {
    this.mediaUrls.splice(index, 1);
  }

  resetForm() {
    this.postContent = '';
    this.mediaUrls = [];
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }
}
