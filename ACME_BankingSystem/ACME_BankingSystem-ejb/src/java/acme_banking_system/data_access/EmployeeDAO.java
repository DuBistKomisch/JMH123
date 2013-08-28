package acme_banking_system.data_access;

import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;

/**
 *
 * @author morga_000
 */
public interface EmployeeDAO {

    public void createEmployee(Employee employee) throws DataLayerException;

    public Employee readEmployee(Integer E_ID) throws DataLayerException;

    public void updateEmployee(Employee employee) throws DataLayerException;

    public void deleteEmployee(Employee employee) throws DataLayerException;

    public Employee loginEmployee(String firstName, String lastName, String password) throws BusinessException, DataLayerException;
}
