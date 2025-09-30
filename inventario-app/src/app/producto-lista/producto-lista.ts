import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Producto } from '../producto';
import { ProductoService } from '../producto-service';

@Component({
  selector: 'app-producto-lista',
  imports: [CommonModule],
  templateUrl: './producto-lista.html',

})
export class ProductoLista {

  productos: Producto[];

  constructor(private productoServicio: ProductoService) {

  }

  ngOnInit() {
    //Cargar productos
    this.obtenerProductos();
  }

  private obtenerProductos() {
    //Consumir los datos del obserbable que esta en el servicio
    this.productoServicio.obtenerProductosLista().subscribe(
      (datos => {
        this.productos = datos;
      })
    );
  }
}
