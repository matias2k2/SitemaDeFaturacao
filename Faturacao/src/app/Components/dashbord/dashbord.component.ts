import { Component } from '@angular/core';

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.scss']
})
export class DashbordComponent {
  currentDate: Date = new Date();
  totalSales: number = 25024;
  totalExpenses: number = 13000;
  totalProducts: number = 350;
  salesTarget: number = 30000; // Target value for sales
  expensesTarget: number = 20000; // Target value for expenses
  productsTarget: number = 500; // Target value for products

  recentOrders = [
    { produto: 'Produto 1', marca: 'Marca 1', categoria: 'Categoria 1', cliente: 'Cliente 1' },
    // More orders here
  ];

  ngOnInit() {
    this.updateTime();
  }

  updateTime() {
    setInterval(() => {
      this.currentDate = new Date();
    }, 1000); // Update every second
  }

  calculateOffset(value: number): number {
    const radius = 30;
    const circumference = 2 * Math.PI * radius;
    const progress = value / 100;
    return circumference * (1 - progress);
  }
  showModal: boolean = false;
  selectedOrder: any = null;

  showDetails(order: any) {
    this.selectedOrder = order;
    this.showModal = true;
  }

  hideDetails() {
    this.showModal = false;
    this.selectedOrder = null;
  }
}
