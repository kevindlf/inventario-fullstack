import { Component } from '@angular/core';
import { Producto } from '../producto';
import { Router } from '@angular/router';
import { ProductoService } from '../producto-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-agregar-producto',
  imports: [FormsModule],
  templateUrl: './agregar-producto.html',
  styleUrl: './agregar-producto.css'
})
export class AgregarProducto {

  producto : Producto = new Producto();
  
  constructor(private productoServicio: ProductoService,
    private enrutador: Router) { 
    
  }
  onSubmit(){
    this.guardarProducto();
  }

  guardarProducto(){
    this.productoServicio.agregarProducto(this.producto).subscribe(
       {
        next: (datos)=>{
          this.irListaProductos();
        },
        error: (error: any)=>{console.log(error)}
       }
    );
  }

  irListaProductos(){
    this.enrutador.navigate(['/productos']);
  }
}
