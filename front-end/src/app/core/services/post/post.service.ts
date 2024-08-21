import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostResponse } from '../../models/post/response/post-response';
import { Observable, catchError, throwError } from 'rxjs';
import { Page } from '../../models/common/page';
import { PostCreateRequest } from '../../models/post/request/post-create-request';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = 'http://localhost:8000';
  private postUrl = `${this.baseUrl}/api/post`;

  constructor(private httpClient: HttpClient) { }

  getPosts(page: number = 0, size: number = 10): Observable<Page<PostResponse>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.httpClient.get<Page<PostResponse>>(`${this.postUrl}/posts`, { params })
      .pipe(
        catchError(error => {
          console.error('Error fetching posts in service:', error);
          return throwError(() => new Error('An error occurred while fetching posts.'));
        })
      );
  }

  createPost(postCreateRequest: PostCreateRequest): Observable<PostResponse> {
    return this.httpClient.post<PostResponse>(`${this.postUrl}/create`, postCreateRequest).pipe(
      catchError(error => {
        console.error('Error creating post:', error);
        return throwError(() => new Error('An error occurred while creating the post.'));
      })
    );
  }
  
}
