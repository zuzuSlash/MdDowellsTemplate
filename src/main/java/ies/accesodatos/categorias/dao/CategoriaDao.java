package ies.accesodatos.categorias.dao;

import ies.accesodatos.categorias.model.Categoria;
import ies.accesodatos.commons.dao.ADao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDao extends ADao<Categoria,Integer> {

    private String tableName = "categorias";
    private final String selectall = "select * from APP." + this.tableName;
    private String selectbyid = "select * from APP." + this.tableName + " where ID=?";
    private String deletebyid = "delete from APP." + this.tableName + " where ID=?";
    private String insertsql = "insert into APP." + this.tableName + "(NOMBRE,DESCRIPCION,ACTIVO,IMG_SRC)VALUES(?,?,?,?)";
    private String updatesql = "update APP." + this.tableName + " set NOMBRE=?, DESCRIPCION=?, ACTIVO=?, IMG_SRC=? WHERE ID=?";
    public CategoriaDao() {
        super();
    }
    @Override
    public Categoria getById(Integer id) {
        Categoria categoria = new Categoria();
        try {
            PreparedStatement pst = this.getConnection().getConnection().prepareStatement(this.selectbyid);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                categoria = this.registerToObject(rs);
            pst.close();
        } catch (SQLException ex) {

            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return categoria;
    }
    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement pst = this.getConnection().getConnection().prepareStatement(this.deletebyid);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {

            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public List<Categoria> getAll() {
        ArrayList<Categoria> scl = new ArrayList<>();
        Categoria tempo;
        PreparedStatement pst = null;
        try {
            try {
                pst = this.getConnection().getConnection().prepareStatement(this.selectall);
            } catch (SQLException ex) {
                ex.printStackTrace();

                Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tempo = this.registerToObject(rs);
                scl.add(tempo);
            }
            pst.close();
        } catch (SQLException ex) {

            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return scl;
    }
    @Override
    public void update(Categoria item) {
        try {
            PreparedStatement pst = this.getConnection().getConnection().prepareStatement(this.updatesql);
            pst.setString(1, item.getNombre());
            pst.setString(2, item.getDescripcion());
            pst.setBoolean(3, item.isActivo());
            pst.setString(4, item.getImg_src());
            pst.setInt(5, item.getId());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {

            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    @Override
    public void delete(Categoria item) {
        try {
            PreparedStatement pst = this.getConnection().getConnection().prepareStatement(this.deletebyid);
            pst.setInt(1, item.getId());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {

            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    @Override
    public void insert(Categoria item) {
        PreparedStatement pst;
        item.setImg_src("C:\\Users\\Zzz\\Desktop\\Trabajos\\MdDowellsTemplate\\src\\main\\resources\\images\\foodAdd.png");
        try {
            pst = this.getConnection().getConnection().prepareStatement(this.insertsql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getNombre());
            pst.setString(2, item.getDescripcion());
            pst.setBoolean(3, item.isActivo());
            pst.setString(4, item.getImg_src());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {

            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //pasar de registro a objeeto
    private Categoria registerToObject(ResultSet r) {
        Categoria item = new Categoria();
        try {
            item.setId(r.getInt("ID"));
            item.setNombre(r.getString("NOMBRE"));
            item.setDescripcion(r.getString("DESCRIPCION"));
            item.setImg_src(r.getString("IMG_SRC"));
            item.setActivo(r.getBoolean("ACTIVO"));
        } catch (SQLException ex) {

            Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return item;
    }
}