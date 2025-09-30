package org.example.inventarios.Servicio;

import org.example.inventarios.Repositorio.ProductoRepository;
import org.example.inventarios.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> ListarProductos() {
        return this.productoRepository.findAll();
    }

    @Override
    public Producto BuscarProductoPorId(Integer idProducto) {
        Producto producto = this.productoRepository.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public void guardarProducto(Producto producto) {
        this.productoRepository.save(producto);
    }

    @Override
    public void eliminarProductoPorId(Integer idProducto) {
        this.productoRepository.deleteById(idProducto);
    }
}
