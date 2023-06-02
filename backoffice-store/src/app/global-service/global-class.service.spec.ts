import { TestBed } from '@angular/core/testing';

import { GlobalClassService } from './global-class.service';

describe('GlobalClassService', () => {
  let service: GlobalClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GlobalClassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
