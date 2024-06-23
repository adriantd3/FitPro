package uma.fitpro.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UsuarioDTO {
    private Integer id;
    private String dni;
    private RolDTO rol;
    private String nombre;
    private String apellidos;
    private Byte sexo;
    private Integer edad;
    private Float altura;
    private Float peso;
    private String contrasenya;
    private String correo;
    private List<Integer> dietasCliente;
    private Set<UsuarioDTO> diestistasCliente;
    private Set<UsuarioDTO> entrenadoresCliente;
    private List<Integer> clientesDietista;
    private List<Integer> rutinasCliente;
    private List<Integer> clientesEntrenador;
}
