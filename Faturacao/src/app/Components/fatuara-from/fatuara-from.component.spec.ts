import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FatuaraFromComponent } from './fatuara-from.component';

describe('FatuaraFromComponent', () => {
  let component: FatuaraFromComponent;
  let fixture: ComponentFixture<FatuaraFromComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FatuaraFromComponent]
    });
    fixture = TestBed.createComponent(FatuaraFromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
