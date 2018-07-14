import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLoyaltyComponent } from './admin-loyalty.component';

describe('AdminLoyaltyComponent', () => {
  let component: AdminLoyaltyComponent;
  let fixture: ComponentFixture<AdminLoyaltyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminLoyaltyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminLoyaltyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
