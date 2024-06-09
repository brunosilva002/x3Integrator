import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditHeaderDivComponent } from './edit-header-div.component';

describe('EditHeaderDivComponent', () => {
  let component: EditHeaderDivComponent;
  let fixture: ComponentFixture<EditHeaderDivComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditHeaderDivComponent]
    });
    fixture = TestBed.createComponent(EditHeaderDivComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
