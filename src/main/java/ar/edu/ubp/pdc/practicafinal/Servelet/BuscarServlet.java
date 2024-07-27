package ar.edu.ubp.pdc.practicafinal.Servelet;

import ar.edu.ubp.pdc.practicafinal.Beans.LibroBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

@WebServlet("/buscar.jsp")
public class BuscarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LinkedList<LibroBean> items = new LinkedList<LibroBean>();

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://192.168.3.240;databaseName=pdc;encrypt=false", "pdc", "pdc");
            PreparedStatement preparedStatement = connection.prepareStatement("declare @nro_genero\tinteger = ?\n" +
                    "\n" +
                    "select nroMaterial = m.nro_material,\n" +
                    "       titulo      = m.titulo,\n" +
                    "\t   autor       = m.autor,\n" +
                    "\t   votos       = (select count(*)\n" +
                    "\t                    from dbo.votaciones_materiales_candidatos v (nolock)\n" +
                    "\t\t\t\t\t   where v.nro_material = m.nro_material)\n" +
                    "  from dbo.materiales_candidatos m (nolock)\n" +
                    " where nro_genero = @nro_genero\n" +
                    " order by m.titulo,\n" +
                    "          m.autor");

            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("genero")));

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                LibroBean item = new LibroBean();

                item.setNroMaterial(rs.getInt("nroMaterial"));
                item.setTitulo(rs.getString("titulo"));
                item.setAutor(rs.getString("autor"));
                item.setVotos(rs.getInt("votos"));
                items.add(item);
            }

            rs.close();
            preparedStatement.close();
            connection.close();

            req.setAttribute("libros", items);
            req.getRequestDispatcher("/mostrarLibros.jsp").forward(req, resp);

        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error-modal.jsp").forward(req, resp);
        }
    }
}
