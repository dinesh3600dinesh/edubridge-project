import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from '../components/welcome/welcome.component';
import { AdminViewmenuComponent } from '../components/admin/admin-viewmenu/admin-viewmenu.component';
import { AdminloginComponent } from '../components/admin/admin-login/admin-login.component';
import { AdminhomeComponent } from '../components/admin/adminhome/adminhome.component';
import { OrderViewComponent } from '../components/admin/order-view/order-view.component';
import { AddOrderComponent } from '../components/customer/add-order/add-order.component';
import { PaymentOptionsComponent } from '../components/customer/payment-options/payment-options.component';
import { AddPaymentComponent } from '../components/customer/add-payment/add-payment.component';
import { MyPaymentComponent } from '../components/customer/my-payment/my-payment.component';
import { CustomerViewComponent } from '../components/admin/customer-view/customer-view.component';
import { PaymentViewComponent } from '../components/admin/payment-view/payment-view.component';
import { AddMenuComponent } from '../components/admin/add-menu/add-menu.component';
import { CustomersignupComponent } from '../components/customer/customer-sign-up/customer-sign-up.component';
import { CustomerhomeComponent } from '../components/customer/customerhome/customerhome.component';
import { CustomerloginComponent } from '../components/customer/customer-login/customer-login.component';
import { ViewmenuComponent } from '../components/customer/viewmenu/viewmenu.component';
import { MyProfileComponent } from '../components/customer/my-profile/my-profile.component';
import { MyOrderComponent } from '../components/customer/my-order/my-order.component';
import { ViewProductsComponent } from '../components/admin/view-products/view-products.component';

const routes: Routes = [
  {path:'',component:WelcomeComponent},
  {path: 'admin/home', component:AdminhomeComponent },
  {path:'admin/product',component:AdminViewmenuComponent},
  {path:'admin/login',component:AdminloginComponent},
  {path:'admin/customer/viewcustomer',component:CustomerViewComponent},          
  {path:'admin/order/allorders',component:OrderViewComponent},
  {path:'admin/payment/allpayment',component:PaymentViewComponent},
  {path:'admin/products/addproduct',component:AddMenuComponent},
  {path:'admin/updateProduct/:id',component:AddMenuComponent},
  {path: 'customer/home', component:CustomerhomeComponent },
  {path:'customer/signup',component:CustomersignupComponent},
  {path:'customer/login',component:CustomerloginComponent},
  //{path:'order/create/:id',component:AddOrderComponent},
   {path:'customer/placeorder/:id',component:AddOrderComponent},
  {path:'customer/product',component:ViewmenuComponent},
  {path:'customer/myprofile',component:MyProfileComponent},
  {path:'customer/myorders',component:MyOrderComponent},
  {path:'customer/mypayment',component:MyPaymentComponent},
  {path:'customer/addpayment',component:AddPaymentComponent},
  {path:'viewproduct',component:ViewProductsComponent},
  {path:'customer/paymentoptions',component:PaymentOptionsComponent}];

;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
