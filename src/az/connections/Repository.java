package az.connections;

import java.sql.SQLException;
import java.util.List;

public interface Repository<O> {

    public O find(O o);
    public List<O> findAll();
    public boolean save(O o) throws SQLException;
    public boolean update(O o);
    public boolean delete(O o);
}
