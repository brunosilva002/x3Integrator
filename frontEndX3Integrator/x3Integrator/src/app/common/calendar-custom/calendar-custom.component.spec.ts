import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarCustomComponent } from './calendar-custom.component';

describe('CalendarCustomComponent', () => {
  let component: CalendarCustomComponent;
  let fixture: ComponentFixture<CalendarCustomComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CalendarCustomComponent]
    });
    fixture = TestBed.createComponent(CalendarCustomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
