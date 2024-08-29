import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileCommentComponent } from './profile-comment.component';

describe('ProfileCommentComponent', () => {
  let component: ProfileCommentComponent;
  let fixture: ComponentFixture<ProfileCommentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileCommentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
