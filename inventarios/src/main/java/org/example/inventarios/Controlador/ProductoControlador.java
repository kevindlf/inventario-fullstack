package org.example.inventarios.Controlador;

import org.example.inventarios.Servicio.ProductoServicio;
import org.example.inventarios.exepciones.RecursosNoEncontradosExepcion;
import org.example.inventarios.modelo.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")//Permitir peticiones desde el front en este caso con Angular si lo saco anda en postman pero no en el front
public class ProductoControlador {

   private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

   @Autowired
    private ProductoServicio productoServicio;

   @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
       List<Producto> productos = this.productoServicio.ListarProductos();
       logger.info("Productos obtenidos: ");
       productos.forEach((producto -> logger.info(producto.toString())));
       return productos;
   }

   @PostMapping("/productos")
    public Producto agregarPorducto(@RequestBody Producto producto){
         logger.info("Producto agregado: " + producto);
         return this.productoServicio.guardarProducto(producto);
   }

   @GetMapping("/productos/{id}")
    public ResponseEntity<Producto>obtenerProductoPorId(@PathVariable int id){
         Producto producto = this.productoServicio.BuscarProductoPorId(id);
         if(producto != null){
             return ResponseEntity.ok(producto);
         }else{
             throw new RecursosNoEncontradosExepcion("No se encontró el Id"+ id);
         }
   }
    @PutMapping("/productos/{id}")
     public ResponseEntity<Producto> actualizarProducto(
             @PathVariable int id,@RequestBody Producto productoRecibido){
            Producto producto = this.productoServicio.BuscarProductoPorId(id);
            if(producto != null){
                 producto.setDescripcion(productoRecibido.getDescripcion());
                 producto.setPrecio(productoRecibido.getPrecio());
                 producto.setExistencia(productoRecibido.getExistencia());
                 this.productoServicio.guardarProducto(producto);
                 logger.info("Producto actualizado: " + producto);
                 return ResponseEntity.ok(producto);
            }else{
                 throw new RecursosNoEncontradosExepcion("No se encontró el Id"+ id);
            }
    }

    @DeleteMapping("/productos/{id}")
     public ResponseEntity<Void> eliminarProducto(@PathVariable int id){
            Producto producto = this.productoServicio.BuscarProductoPorId(id);
            if(producto != null){
                 this.productoServicio.eliminarProductoPorId(id);
                 logger.info("Producto eliminado: " + producto);
                 return ResponseEntity.noContent().build();
            }else{
                 throw new RecursosNoEncontradosExepcion("No se encontró el Id"+ id);
            }
    }
}
