import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MemberResponse } from 'src/app/core/models/member/response/member-response';
import { UserService } from 'src/app/core/services/identity/user.service';
import { MemberService } from 'src/app/core/services/member/member.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  member!: MemberResponse;
  defaultProfilePicture: string = 'assets/images/default-avatar.png';

  constructor(
    private memberService: MemberService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const memberId = this.userService.getUserIdFromToken();

    if (memberId !== null) {
      this.memberService.getMember(memberId).subscribe(data => {
        console.log(data);
        this.member = data;
      });
    }

  }

  viewPosts() {
    this.router.navigate(['main/profile/profile-posts']);
  }

  viewComments() {
    this.router.navigate(['main/profile/profile-comments']);
  }

  viewMedia() {
    this.router.navigate(['main/profile/profile-media']);
  }

  viewLikes() {
    this.router.navigate(['main/profile/profile-likes']);
  }
}
