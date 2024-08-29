import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthInterceptor } from './core/interceptors/identity/auth-interceptor';
import { LoginComponent } from './shared/components/identity/login/login.component';
import { AuthService } from './core/services/identity/auth.service';
import { AuthGuard } from './core/guards/identity/auth-guard';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './shared/components/identity/register/register.component';
import { MainComponent } from './shared/components/main/main.component';
import { HeaderComponent } from './shared/components/main/header/header/header.component';
import { SidebarComponent } from './shared/components/main/sidebar/sidebar/sidebar.component';
import { ProfileComponent } from './shared/components/main/pages/profile/profile.component';
import { HomeComponent } from './shared/components/main/pages/home/home.component';
import { PostComponent } from './shared/components/main/pages/home/post/post.component';
import { AddCommentComponent } from './shared/components/main/pages/home/add-comment/add-comment.component';
import { PostListComponent } from './shared/components/main/pages/home/post-list/post-list.component';
import { AddPostComponent } from './shared/components/main/pages/home/add-post/add-post.component';
import { ProfilePostComponent } from './shared/components/main/pages/profile/profile-post/profile-post.component';
import { ProfileCommentComponent } from './shared/components/main/pages/profile/profile-comment/profile-comment.component';
import { ProfileMediaComponent } from './shared/components/main/pages/profile/profile-media/profile-media.component';
import { ProfileLikeComponent } from './shared/components/main/pages/profile/profile-like/profile-like.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MainComponent,
    HeaderComponent,
    SidebarComponent,
    ProfileComponent,
    HomeComponent,
    PostComponent,
    AddCommentComponent,
    PostListComponent,
    AddPostComponent,
    ProfilePostComponent,
    ProfileCommentComponent,
    ProfileMediaComponent,
    ProfileLikeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AuthService,
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
