package tech.getarrays.banco.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String tipoId;
    private Integer identificacion;
    private String name;
    private String email;
    private String fecha_nac;
    private String fecha_crea;
    private String estado;
    private String tipo_cuenta;
    public Integer num_cuenta;
    private String fecha_apertura;
    private Integer saldo;
    private Integer transfe;
    @Column(nullable = false, updatable = false)
    private String usuario_codigo;

    public Usuario() {
    }

    public Usuario(Long id, String tipoId, Integer identificacion, String name, String email, String fecha_nac, String fecha_crea, String estado, String tipo_cuenta, Integer num_cuenta, String fecha_apertura, Integer saldo, Integer transfe, String usuario_codigo) {
        this.id = id;
        this.tipoId = tipoId;
        this.identificacion = identificacion;
        this.name = name;
        this.email = email;
        this.fecha_nac = fecha_nac;
        this.fecha_crea = fecha_crea;
        this.estado = estado;
        this.tipo_cuenta = tipo_cuenta;
        this.num_cuenta = num_cuenta;
        this.fecha_apertura = fecha_apertura;
        this.saldo = saldo;
        this.usuario_codigo = usuario_codigo;
        this.transfe = transfe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getFecha_crea() {
        return fecha_crea;
    }

    public void setFecha_crea(String fecha_crea) {
        this.fecha_crea = fecha_crea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Integer getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(Integer num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public String getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(String fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getTransfe() {
        return transfe;
    }

    public void setTransfe(Integer transfe) {
        this.transfe = transfe;
    }

    public String getUsuario_codigo() {
        return usuario_codigo;
    }

    public void setUsuario_codigo(String usuario_codigo) {
        this.usuario_codigo = usuario_codigo;
    }
}
