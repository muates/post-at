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
import { MainComponent } from './shared/components/main/main/main.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MainComponent
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
