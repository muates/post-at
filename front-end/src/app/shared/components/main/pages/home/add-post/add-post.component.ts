import { Component, OnInit } from '@angular/core';
import { PostCreateRequest } from 'src/app/core/models/post/request/post-create-request';
import { MediaType } from 'src/app/core/models/post/request/post-media-create-request';
import { UserService } from 'src/app/core/services/identity/user.service';
import { PostObserverService } from 'src/app/core/services/observer/post-observer.service';
import { PostService } from 'src/app/core/services/post/post.service';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  postContent: string = '';
  mediaUrls: { mediaUrl: string, mediaType: MediaType }[] = [];
  showForm: boolean = false;

  constructor(
    private postService: PostService,
    private userService: UserService,
    private postObserverService: PostObserverService) { }

  ngOnInit(): void {
  }

  addImageUrl() {
    if (this.mediaUrls.length < 5) {
      this.mediaUrls.push({ mediaUrl: '', mediaType: MediaType.IMAGE });
    } else {
      alert('You can only add up to 5 media URLs.');
    }
  }

  addVideoUrl() {
    if (this.mediaUrls.length < 5) {
      this.mediaUrls.push({ mediaUrl: '', mediaType: MediaType.VIDEO });
    } else {
      alert('You can only add up to 5 media URLs.');
    }
  }

  addGifUrl() {
    if (this.mediaUrls.length < 5) {
      this.mediaUrls.push({ mediaUrl: '', mediaType: MediaType.GIF });
    } else {
      alert('You can only add up to 5 media URLs.');
    }
  }

  updateMediaUrl(index: number, event: Event) {
    const inputElement = event.target as HTMLInputElement;
    this.mediaUrls[index].mediaUrl = inputElement.value;
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
      media: this.mediaUrls
    };

    this.postService.createPost(newPost).subscribe(response => {
      this.postObserverService.addPost(response);
      this.resetForm();
      this.showForm = false;
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

  getMediaIconClass(mediaType: MediaType): string {
    switch (mediaType) {
      case MediaType.IMAGE:
        return 'fa-image';
      case MediaType.VIDEO:
        return 'fa-video';
      case MediaType.GIF:
        return 'fa fa-film';
      default:
        return '';
    }
  }
}
