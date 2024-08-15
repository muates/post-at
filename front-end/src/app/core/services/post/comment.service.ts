import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Page } from '../../models/common/page';
import { PostCommentResponse } from '../../models/post/response/post-comment-response';
import { CommentRequest } from '../../models/post/request/comment-request';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl = 'http://localhost:8000';
  private postUrl = `${this.baseUrl}/api/post/post-comment`;

  constructor(private httpClient: HttpClient) { }

  getComments(postId: number, page: number = 0, size: number = 10): Observable<Page<PostCommentResponse>> {
    const params = new HttpParams()
      .set('postId', postId.toString())
      .set('page', page.toString())
      .set('size', size.toString());

    return this.httpClient.get<Page<PostCommentResponse>>(`${this.postUrl}/comments`, { params })
      .pipe(
        catchError(error => {
          console.error('Error fetching posts in service:', error);
          return throwError(() => new Error('An error occurred while fetching posts.'));
        })
      );
  }

  addComment(commentRequest: CommentRequest): Observable<void> {
    return this.httpClient.post<void>(`${this.postUrl}/comment`, commentRequest)
      .pipe(
        catchError(error => {
          console.error('Error adding comment in service:', error);
          return throwError(() => new Error('An error occurred while adding the comment.'));
        })
      );
  }
}
