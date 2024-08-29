import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { MemberResponse } from '../../models/member/response/member-response';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private baseUrl = 'http://localhost:8000';
  private memberUrl = `${this.baseUrl}/api/member`;

  constructor(private httpClient: HttpClient) { }

  getMember(memberId: number): Observable<MemberResponse> {
    return this.httpClient.get<MemberResponse>(`${this.memberUrl}/${memberId}`)
      .pipe(
        catchError(error => {
          console.error('Error fetching member data in service:', error);
          return throwError(() => new Error('An error occurred while fetching member data.'));
        })
      );
  }
}
