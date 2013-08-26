package data_access;

/**
 *
 * @author morga_000
 */
public interface EmployeeDAO {

    public void createEmployee(Employee employee);

    public Employee readEmployee(Integer E_ID);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(Employee employee);

    public Employee loginEmployee(String firstName, String lastName, String password);
}
