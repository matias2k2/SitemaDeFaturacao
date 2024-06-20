import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FromularioComponent } from './fromulario.component';

describe('FromularioComponent', () => {
  let component: FromularioComponent;
  let fixture: ComponentFixture<FromularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FromularioComponent]
    });
    fixture = TestBed.createComponent(FromularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
