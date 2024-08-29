import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileLikeComponent } from './profile-like.component';

describe('ProfileLikeComponent', () => {
  let component: ProfileLikeComponent;
  let fixture: ComponentFixture<ProfileLikeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileLikeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileLikeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
