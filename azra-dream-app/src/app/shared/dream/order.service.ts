import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  public API = '//localhost:8080';
  public ORDER_API = this.API + '/order';
  
  constructor(private http:HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/api/order/findAll');
  }
  
  get(id: string) {
    return this.http.get(this.ORDER_API + '/' + id);
  }
  
  save(order: any): Observable<any> {
    let result: Observable<Object>;
    if (order['href']) {
      result = this.http.put(order.href, order);
    } else {
      result = this.http.post(this.ORDER_API, order);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
