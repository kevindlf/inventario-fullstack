import { Component, OnInit } from '@angular/core';
import { Producto } from '../producto';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../producto-service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-editar-producto',
  imports: [FormsModule],
  templateUrl: './editar-producto.html'
})
export class EditarProducto implements OnInit {

  producto: Producto = new Producto();

  id: number;

  constructor(private productoServicio: ProductoService, 
    private enrutador: Router, private ruta: ActivatedRoute) { 
      
    }
  
  ngOnInit(){
    this.id = this.ruta.snapshot.params['id'];
    this.productoServicio.obtenerProductoPorId(this.id).subscribe(
      {
      next: (datos)=> this.producto = datos,
      error: (errores : any) => console.log(errores)
      }
    );
  }


 onSubmit(){
    this.guardarProducto();
  }

  guardarProducto(){
    this.productoServicio.editarProducto(this.id, this.producto).subscribe(
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
