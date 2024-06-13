package uma.fitpro.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String dni;
    private RolDTO rol;
    private String nombre;
    private String apellidos;
    private boolean sexo;
    private Integer edad;
    private Float altura;
    private Float peso;
    private String contrasenya;
    private String correo;
}
