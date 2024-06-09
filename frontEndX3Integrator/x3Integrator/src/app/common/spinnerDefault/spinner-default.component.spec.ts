import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpinnerDefaultComponent } from './spinner-default.component';

describe('SpinnerDefaultComponent', () => {
  let component: SpinnerDefaultComponent;
  let fixture: ComponentFixture<SpinnerDefaultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SpinnerDefaultComponent]
    });
    fixture = TestBed.createComponent(SpinnerDefaultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
