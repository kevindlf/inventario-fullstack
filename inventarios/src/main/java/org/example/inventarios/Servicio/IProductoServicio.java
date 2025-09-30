package org.example.inventarios.Servicio;

import org.example.inventarios.modelo.Producto;
import java.util.List;

public interface IProductoServicio {

    public List<Producto> ListarProductos();

    public Producto BuscarProductoPorId(Integer idProducto);

    public void guardarProducto(Producto producto);

    public void eliminarProductoPorId(Integer idProducto);
}
