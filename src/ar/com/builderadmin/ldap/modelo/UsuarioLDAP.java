/*
 * Copyright 2005-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.builderadmin.ldap.modelo;

import javax.naming.Name;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

/**
 * Simple class representing a single person.

 	objectClasses

	public abstract String[] objectClasses
		A list of LDAP object classes that the annotated Java class represents.
		All fields will be persisted to LDAP unless annotated Transient.
	
	public abstract String base
		The base DN of this entry. If specified, this will be prepended to all calculated distinguished names for entries of the annotated class.

 */
@Entry(objectClasses = {"top","person","organizationalPerson","user"})
public class UsuarioLDAP {
	
    @Id
    private Name dn;

    @Attribute(name = "cn")
    @DnAttribute(value = "cn")
    private String nombreCompleto;

    @Attribute(name = "sn")
    private String apellido;

    @Attribute(name = "sAMAccountName")
    private String usuario;
    
    @Attribute(name = "mail")
    private String email;
    
    @Attribute(name = "initials")
    private String iniciales;
    
    @Attribute(name = "memberOf")
    private String memberOf;
    
    @Attribute(name = "objectCategory")
    private String categoria;
    
    @Attribute(name = "thumbnailphoto")
    byte[] thumbnailphoto;
    
    @Attribute(name = "jpegPhoto")
    byte[] jpegPhoto;
    
    @Attribute(name = "Photo")
    byte[] photo;

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the iniciales
	 */
	public String getIniciales() {
		return iniciales;
	}

	/**
	 * @param iniciales the iniciales to set
	 */
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	/**
	 * @return the memberOf
	 */
	public String getMemberOf() {
		return memberOf;
	}

	/**
	 * @param memberOf the memberOf to set
	 */
	public void setMemberOf(String memberOf) {
		this.memberOf = memberOf;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(
                this, obj);
    }

    @Override
	public int hashCode() {
        return HashCodeBuilder
                .reflectionHashCode(this);
    }

    @Override
	public String toString() {
        return ToStringBuilder.reflectionToString(
                this, ToStringStyle.MULTI_LINE_STYLE);
    }

	/**
	 * @return the thumbnailphoto
	 */
	public byte[] getThumbnailphoto() {
		return thumbnailphoto;
	}

	/**
	 * @param thumbnailphoto the thumbnailphoto to set
	 */
	public void setThumbnailphoto(byte[] thumbnailphoto) {
		this.thumbnailphoto = thumbnailphoto;
	}

	/**
	 * @return the jpegPhoto
	 */
	public byte[] getJpegPhoto() {
		return jpegPhoto;
	}

	/**
	 * @param jpegPhoto the jpegPhoto to set
	 */
	public void setJpegPhoto(byte[] jpegPhoto) {
		this.jpegPhoto = jpegPhoto;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

//	/**
//	 * @return the dn
//	 */
//	public String getDn() {
//		return dn;
//	}
//
//	/**
//	 * @param dn the dn to set
//	 */
//	public void setDn(String dn) {
//		this.dn = dn;
//	}

	
}
