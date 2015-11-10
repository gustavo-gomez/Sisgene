
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Catalogo {
    private String cat_grupo;
    private String cat_sub_grupo;
    private String cat_nombre;
    private String cat_descripcion;
    private String cat_codigo;
    
    public Catalogo(){
        //Constructor de la clase Catalogo
    }

    public String getCat_grupo() {
        return cat_grupo;
    }

    public void setCat_grupo(String cat_grupo) {
        this.cat_grupo = cat_grupo;
    }

    public String getCat_sub_grupo() {
        return cat_sub_grupo;
    }

    public void setCat_sub_grupo(String cat_sub_grupo) {
        this.cat_sub_grupo = cat_sub_grupo;
    }

    public String getCat_nombre() {
        return cat_nombre;
    }

    public void setCat_nombre(String cat_nombre) {
        this.cat_nombre = cat_nombre;
    }

    public String getCat_descripcion() {
        return cat_descripcion;
    }

    public void setCat_descripcion(String cat_descripcion) {
        this.cat_descripcion = cat_descripcion;
    }

    public String getCat_codigo() {
        return cat_codigo;
    }

    public void setCat_codigo(String cat_codigo) {
        this.cat_codigo = cat_codigo;
    }
}
