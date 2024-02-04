import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule} from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule , } from '@angular/material/sidenav';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AdminhomeComponent } from '../components/admin/adminhome/adminhome.component';
import { AdminloginComponent } from '../components/admin/admin-login/admin-login.component';
import { AdminViewmenuComponent } from '../components/admin/admin-viewmenu/admin-viewmenu.component';
import { CustomerloginComponent } from '../components/customer/customer-login/customer-login.component';
import { CustomersignupComponent } from '../components/customer/customer-sign-up/customer-sign-up.component';
import { CustomerViewComponent } from '../components/admin/customer-view/customer-view.component';
import { CustomerhomeComponent } from '../components/customer/customerhome/customerhome.component';
import { OrderViewComponent } from '../components/admin/order-view/order-view.component';
import { MyProfileComponent } from '../components/customer/my-profile/my-profile.component';
import { PaymentViewComponent } from '../components/admin/payment-view/payment-view.component';
import { AddMenuComponent } from '../components/admin/add-menu/add-menu.component';
import { PaymentOptionsComponent } from '../components/customer/payment-options/payment-options.component';
import { MyOrderComponent } from '../components/customer/my-order/my-order.component';
import { MyPaymentComponent } from '../components/customer/my-payment/my-payment.component';
import { AddOrderComponent } from '../components/customer/add-order/add-order.component';
import { ViewmenuComponent } from '../components/customer/viewmenu/viewmenu.component';
import { AddPaymentComponent } from '../components/customer/add-payment/add-payment.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { WelcomeComponent } from '../components/welcome/welcome.component';
import { ViewProductsComponent } from '../components/admin/view-products/view-products.component';

@NgModule({
  declarations: [
    AppComponent,
    
    WelcomeComponent,
    AdminhomeComponent,
    AdminloginComponent,
    AdminViewmenuComponent,
    CustomerloginComponent,
    CustomersignupComponent,
    CustomerhomeComponent,
    CustomerViewComponent,
    OrderViewComponent,
    PaymentViewComponent,
    AddMenuComponent,
    AddOrderComponent,
    ViewmenuComponent,
    AddPaymentComponent,
    MyProfileComponent,
    MyOrderComponent,
    MyPaymentComponent,
    PaymentOptionsComponent,
    ViewProductsComponent,


    
 
  
 
    
    
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatSidenavModule,
    MatToolbarModule,

    MatButtonModule,
    MatIconModule,
    BrowserModule,
    NgxPaginationModule
    
  ],
  providers: [],
  bootstrap: [AppComponent],
  
  

})
export class AppModule { }
