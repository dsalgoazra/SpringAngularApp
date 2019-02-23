import { Component, OnInit } from '@angular/core';
import { OrderService } from '../shared/dream/order.service'

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Array<any>;
  
  constructor(private orderService: OrderService) { }

  ngOnInit() {
	  this.orderService.getAll().subscribe(data => {
		  this.orders = data;
	  });
  }

}
