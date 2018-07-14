import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {CoreModule} from './core/core.module';
import {AppRoutingModule} from './app-routing.module';
import {RouterModule} from '@angular/router';
import {PizzaService} from './services/pizza.service';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './services/user.service';
import {FormsModule} from '@angular/forms';
import {OrderService} from './services/order.service';
import { OrderDetailsComponent } from './core/order-details/order-details.component';
import {GradeService} from './services/grade.service';
import {AdminService} from './services/admin.service';
import {EmployeeService} from './services/employee.service';
import {OfferPipe} from './services/offer.pipe';


@NgModule({
  declarations: [
    AppComponent,
    OrderDetailsComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    CoreModule,
    AppRoutingModule,
    RouterModule,
    FormsModule
  ],
  providers: [PizzaService, UserService, OrderService, AdminService, EmployeeService, GradeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
