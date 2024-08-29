import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './shared/components/identity/login/login.component';
import { MainComponent } from './shared/components/main/main.component';
import { HomeComponent } from './shared/components/main/pages/home/home.component';
import { ProfileComponent } from './shared/components/main/pages/profile/profile.component';
import { AuthGuard } from './core/guards/identity/auth-guard';
import { ProfilePostComponent } from './shared/components/main/pages/profile/profile-post/profile-post.component';
import { ProfileCommentComponent } from './shared/components/main/pages/profile/profile-comment/profile-comment.component';
import { ProfileMediaComponent } from './shared/components/main/pages/profile/profile-media/profile-media.component';
import { ProfileLikeComponent } from './shared/components/main/pages/profile/profile-like/profile-like.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'main', component: MainComponent, canActivate: [AuthGuard], children: [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard], 
      children: [
        { path: '', redirectTo: 'profile-posts', pathMatch: 'full' },
        { path: 'profile-posts', component: ProfilePostComponent },
        { path: 'profile-comments', component: ProfileCommentComponent },
        { path: 'profile-media', component: ProfileMediaComponent },
        { path: 'profile-likes', component: ProfileLikeComponent }
      ]
    }
  ]},
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }