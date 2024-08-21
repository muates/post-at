import { TestBed } from '@angular/core/testing';

import { PostObserverService } from './post-observer.service';

describe('PostObserverServiceService', () => {
  let service: PostObserverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PostObserverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
