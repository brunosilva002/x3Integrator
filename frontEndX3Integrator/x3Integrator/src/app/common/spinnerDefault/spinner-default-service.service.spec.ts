import { TestBed } from '@angular/core/testing';

import { SpinnerDefaultServiceService } from './spinner-default-service.service';

describe('SpinnerDefaultServiceService', () => {
  let service: SpinnerDefaultServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpinnerDefaultServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
