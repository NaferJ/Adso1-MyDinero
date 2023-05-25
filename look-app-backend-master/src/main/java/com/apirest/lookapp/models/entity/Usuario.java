package com.apirest.lookapp.models.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, length = 20)
  private String username;

  @Column(length = 60)
  private String password;

  private Boolean enabled;

  private String nombres;

  private String telefono;

  private String apellidos;

  @Temporal(TemporalType.DATE)
  private Date fecha_nacimiento;

  @Column(unique = true)
  private String email;

  @Basic(optional = false)
  @Size(min = 1, max = 45)
  @Column(name = "identificacion")
  private String identificacion;

  @Basic(optional = false)
  @Size(max=300)
  @Column(name = "imagen")
  private String imagen;

  @Basic(optional = false)
  @Size(max=300)
  @Column(name = "direccion")
  private String direccion;

  @ManyToOne(optional = false)
  @JoinColumn(name = "id_entidad")
  private Entidad idEntidad;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
  @JoinTable(
    name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles;
  
  private static final long serialVersionUID = 1L;

}
