import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../class/customer';
import { Observable } from 'rxjs';
import { Admin } from '../class/admin';

import { Order } from '../class/order';
import { Payment } from '../class/payment';
import { Product } from '../class/product';

@Injectable({
  providedIn: 'root'
})
export class ResturantmanagemntService {
  // placeOrder(productid: number) {
  //   throw new Error('Method not implemented.');
  // }
  
  
  //SignUP-Customer
  private newuserurl = "http://localhost:8080/customer/signup";
  private viewuserurl = "http://localhost:8080/customer/viewcustomer";
  private viewuserbyidurl = "http://localhost:8080/customer/findid/id";
  private loginuserurl =  "http://localhost:8080/customer/login";
  private loginadminurl =  "http://localhost:8080/admin/login";
  private menuURL = "http://localhost:8080/products/product";
  private menuUpdateurl = "http://localhost:8080/products/update";
  private menusaveURl = "http://localhost:8080/products/product";
  private menudeleteURl = "http://localhost:8080/products/delete";
  private viewuserbyusernameurl = "http://localhost:8080/customer/username";
  private customerdeleteURl = "http://localhost:8080/customer/delete";
  private viewallorderurl = "http://localhost:8080/order"; //need to change
  private viewcustomerorderurl = "http://localhost:8080/order/customer"; 
  private deleteorderurl = "http://localhost:8080/order/delete"; 
  private viewpaybyoderidurl = "http://localhost:8080/payment/orderId";
  private viewpaybycustomeridurl = "http://localhost:8080/payment/customer";
  private viewallpayurl="http://localhost:8080/payment/allpayments";
  private deletebypayidurl ="http://localhost:8080/payment/delete";
  private addorderurl ="http://localhost:8080/order/create";
  private addpaymenturl = "http://localhost:8080/payment";

  constructor(private http:HttpClient) { }


  //SignUP-Customer
  saveUser(customer: Customer): Observable<Customer> {
    const httpOptions = { 
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'auth-token',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.post<Customer>(this.newuserurl, customer, httpOptions);
  }
  //Login-Customer
  getlogin(customer: Customer): Observable<Customer> {
    console.log(customer);
    return this.http.post<Customer>(`${this.loginuserurl}`, customer);
  }
  //GetUserById
  getuserbyid(customerId: number):Observable<Customer>  {
    const uidUrl = this.viewuserbyidurl + "/" + customerId;
    return this.http.get<Customer>(uidUrl);
  }

  

  updateUser(customer: Customer): Observable<Customer> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'auth-token',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.put<Customer>(this.viewuserurl + `/${customer}`, customer, httpOptions);
  }

  //Login-Admin
  getadminlogin(admin: Admin): Observable<Admin> {
    console.log(admin);
    return this.http.post<Admin>(`${this.loginadminurl}`, admin);
  }
  //Update Menu
  updateProduct(product:Product):Observable<any>
  {
    const headers={'content-type':'application/json'};
    const body=JSON.stringify(product);
    return this.http.put(this.menuUpdateurl,body,{'headers':headers});
  }
  //add menu
  SaveProduct(product:Product):Observable<any>
  {
    const httpOptions = {
      headers : new HttpHeaders({
          'Content-Type' : 'application/json',
          'Authorization' : 'auth-token',
          'Access-Control-Allow-Origin' : '*'
      })
    };
    return  this.http.post<Product>(this.menusaveURl,product,httpOptions);
  }
//GetMenuById

getproductbyid(productid: number):Observable<Product>  {
  const uidUrl = this.menusaveURl + "/" + productid;
  return this.http.get<Product>(uidUrl);
}
//get Product by Pname
  getProductByPname(pname:any):Observable<Product>
  {
    const searchURL =   "http://localhost:8080//products/productname/pname"+"/"+ pname;
    return  this.http.get<Product>(searchURL);
  }
//getallmenu
  getAllProduct():Observable<any>
  {
   return this.http.get(this.menuURL);
  }
//delete Menu by ID
  deleteProduct(productid: number) {
   
    const httpOptions = {
      headers : new HttpHeaders({
          'Content-Type' : 'application/json',
          'Authorization' : 'auth-token',
          'Access-Control-Allow-Origin' : '*'
      })
    };
    return  this.http.delete<Product>(this.menudeleteURl+`/${productid}`,httpOptions);
  }

//Get customer by username
getCustomerByUsername(username:String):Observable<Customer>  {
  const uidUrl = this.viewuserbyusernameurl + "/" + username;
  return this.http.get<Customer>(uidUrl);
}
//get all customers
getAllCustomer():Observable<any>
{
 return this.http.get(this.viewuserurl);
}


//delete Customer by id


deleteCustomer(id: number) {
   
  const httpOptions = {
    headers : new HttpHeaders({
        'Content-Type' : 'application/json',
        'Authorization' : 'auth-token',
        'Access-Control-Allow-Origin' : '*'
    })
  };
  return  this.http.delete<Customer>(this.customerdeleteURl+`/${id}`,httpOptions);
}
//GetOrderById
getorderbyid(customerId: number):Observable<Order>  {
  const uidUrl = this.viewallorderurl + "/" + customerId;
  return this.http.get<Order>(uidUrl);
}

//GetOrderByCustomerId
getorderbycustomerid(customerId: number):Observable<Order>  {
  const uidUrl = this.viewcustomerorderurl + "/" + customerId;
  return this.http.get<Order>(uidUrl);
}
//Get All Orders
getAllOrder():Observable<any>
{
 return this.http.get(this.viewallorderurl);
}

//delete Order by ID
deleteOrder(orderId: number): Observable<any> {
  const headers = new HttpHeaders({ 'Authorization': 'auth-token' }); 
  const url = `${this.deleteorderurl}/${orderId}`;
  return this.http.delete<Order>(url, { headers });
}
//GetpaymentById
getpaymentbyid(customerId: number):Observable<Payment>  {
  const uidUrl = this.viewpaybyoderidurl + "/" + customerId;
  return this.http.get<Payment>(uidUrl);
}

//Get All Payments
getAllPayments():Observable<any>
{
 return this.http.get(this.viewallpayurl);
}

//delete payment by ID
deletePayment(paymentId: number) {
   
  const httpOptions = {
    headers : new HttpHeaders({
        'Content-Type' : 'application/json',
        'Authorization' : 'auth-token',
        'Access-Control-Allow-Origin' : '*'
    })
  };
  return  this.http.delete<Payment>(this.deletebypayidurl+`/${paymentId}`,httpOptions);
}
//Order Menu
orderMenu(customerId:number,productid:number,quantity:any):Observable<any>
{
  console.log(productid)
  const headers={'content-type':'application/json'};
  const body=JSON.stringify(quantity);
  return this.http.post(this.addorderurl+"/"+customerId+"/"+productid,body,{'headers':headers});
}
//GetpaymentBycustomerId
getpaymentcusyomerbyid(customerId: number):Observable<Payment>  {
  const uidUrl = this.viewpaybycustomeridurl + "/" + customerId;
  return this.http.get<Payment>(uidUrl);
}

//add payments code
addPayment(payment: any, orderId: number, customerId: number): Observable<any> {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const url = `${this.addpaymenturl}/${orderId}/${customerId}`;
  return this.http.post(url, payment, { headers });
}
}