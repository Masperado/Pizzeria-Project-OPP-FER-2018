import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeOrdersDetailsComponent } from './employee-orders-details.component';

describe('EmployeeOrdersDetailsComponent', () => {
  let component: EmployeeOrdersDetailsComponent;
  let fixture: ComponentFixture<EmployeeOrdersDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeOrdersDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeOrdersDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
