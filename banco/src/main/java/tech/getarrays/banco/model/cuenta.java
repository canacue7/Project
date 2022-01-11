package tech.getarrays.banco.model;

import javax.persistence.*;

@Table(name="cuenta")
public class cuenta {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String email;
    private String fecha_nac;
    private String fecha_crea;
    private String estado;
    private String tipo_cuenta;
    private Integer num_cuenta;
    private String password;
    private Integer saldo;
    @Column(nullable = false, updatable = false)
    private String usuario_codigo;

}
