import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginfromComponent } from './loginfrom.component';

describe('LoginfromComponent', () => {
  let component: LoginfromComponent;
  let fixture: ComponentFixture<LoginfromComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginfromComponent]
    });
    fixture = TestBed.createComponent(LoginfromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
