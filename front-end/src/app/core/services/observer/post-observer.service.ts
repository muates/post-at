import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { PostResponse } from '../../models/post/response/post-response';

@Injectable({
  providedIn: 'root'
})
export class PostObserverService {

  private postSubject = new Subject<PostResponse>();
  post$ = this.postSubject.asObservable();

  addPost(post: PostResponse) {
    this.postSubject.next(post);
  }
}
