package ar.edu.ubp.pdc.practicafinal.Servelet;

import ar.edu.ubp.pdc.practicafinal.Beans.VotoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;

@WebServlet("/save.jsp")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc;encrypt=false", "sa", "123456");

            VotoBean voto = new VotoBean();
            voto.setCorreo(req.getParameter("correo"));
            voto.setTelefono(req.getParameter("telefono"));
            if (req.getParameter("nroLegAlumno").isEmpty()) {
                voto.setNroLegAlumno(1);
            }else {
                voto.setNroLegAlumno(Integer.parseInt(req.getParameter("nroLegAlumno")));
            }
            voto.setNroMaterial(Integer.parseInt(req.getParameter("nroMaterial")));

            Gson gson = new Gson();
            Type type = new TypeToken<VotoBean>(){}.getType();

            String json = gson.toJson(voto, type);

            connection.setAutoCommit(false);

            CallableStatement callableStatement = connection.prepareCall("{call dbo.ins_voto_material(?, ?)}");
            callableStatement.setString(1, json);
            callableStatement.registerOutParameter(2, Types.INTEGER);

            callableStatement.execute();
            connection.commit();

            int nroVotos = callableStatement.getInt(2);

            callableStatement.close();
            connection.close();

            req.setAttribute("votos", nroVotos);
            req.setAttribute("nroMaterial", req.getParameter("nroMaterial"));
            req.getRequestDispatcher("/success.jsp").forward(req, resp);

        }catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error-modal.jsp").forward(req, resp);
        }
    }
}
