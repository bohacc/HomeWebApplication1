/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smartgwt.client.data.Record;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

import org.yournamehere.client.GWTService;
import org.yournamehere.client.Test;

/**
 *
 * @author Martin
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    @Override
    public String myMethod(String s) {
        try {
            // Do something interesting with 's' here on the server.
            System.out.println("1");
                
                Class.forName("org.postgresql.Driver");
                System.out.println("2");
                    
                Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
                System.out.println("3");
                
                con.setAutoCommit(false);
                System.out.println("33");
                
                CallableStatement cs = con.prepareCall("{ ? = call mbsystem.gettest(?) }");
                System.out.println("4");
                
                cs.registerOutParameter(1, Types.VARCHAR);
                
                cs.setString(2, s);
                System.out.println("5");           
                
                cs.execute();
                System.out.println("6");
                
                //ResultSet rs = null;
                //rs = cs.getResultSet();
                String str = cs.getString(1);
                System.out.println("7");
                
                //System.out.println("rs: "+rs.getString(1)); 
                System.out.println("rs: "+str); 
                System.out.println("8");
                
                //session.getTransaction().commit();
                cs.close();
                System.out.println("9");
                
            return "Server says: " + s;
        } catch (SQLException ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Test[] getTests(){
        System.out.println("1");
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("2");
        session.beginTransaction();
        System.out.println("3");
        System.out.println("4");
        List list = session.createQuery("from Test").list();
        System.out.println("5");
        session.getTransaction().commit();
        System.out.println("6");
        
        Test[] results = new Test[list.size()];
        for(int i=1;i<list.size();i++){
            results[i] = (Test) list.get(i);
            System.out.println(results[i].getNazev());
        }
        System.out.println("7");
        return results;
    }
}
