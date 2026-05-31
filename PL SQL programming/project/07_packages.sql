-- ============================================================
-- EXERCISE 7: Packages
-- ============================================================

-- Scenario 1: Customer Management Package
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(
        p_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    );
    PROCEDURE UpdateCustomerDetails(
        p_id NUMBER,
        p_name VARCHAR2 DEFAULT NULL,
        p_dob DATE DEFAULT NULL
    );
    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers(CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Customer ' || p_id || ' already exists');
    END AddCustomer;

    PROCEDURE UpdateCustomerDetails(
        p_id NUMBER,
        p_name VARCHAR2 DEFAULT NULL,
        p_dob DATE DEFAULT NULL
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = NVL(p_name, Name),
            DOB = NVL(p_dob, DOB),
            LastModified = SYSDATE
        WHERE CustomerID = p_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Customer ' || p_id || ' not found');
        END IF;
        COMMIT;
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Customers WHERE CustomerID = p_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

-- Scenario 2: Employee Management Package
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2
    );
    PROCEDURE UpdateEmployeeDetails(
        p_id NUMBER,
        p_salary NUMBER DEFAULT NULL,
        p_position VARCHAR2 DEFAULT NULL
    );
    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2
    ) IS
    BEGIN
        INSERT INTO Employees(EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_position, p_salary, p_department, SYSDATE);
        COMMIT;
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_id NUMBER,
        p_salary NUMBER DEFAULT NULL,
        p_position VARCHAR2 DEFAULT NULL
    ) IS
    BEGIN
        UPDATE Employees
        SET Salary = NVL(p_salary, Salary),
            Position = NVL(p_position, Position)
        WHERE EmployeeID = p_id;
        COMMIT;
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary
        FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

-- Scenario 3: Account Operations Package
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id NUMBER,
        p_customer_id NUMBER,
        p_account_type VARCHAR2,
        p_initial_balance NUMBER
    );
    PROCEDURE CloseAccount(p_account_id NUMBER);
    FUNCTION GetTotalCustomerBalance(p_customer_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_account_id NUMBER,
        p_customer_id NUMBER,
        p_account_type VARCHAR2,
        p_initial_balance NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts(AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_initial_balance, SYSDATE);
        COMMIT;
    END OpenAccount;

    PROCEDURE CloseAccount(p_account_id NUMBER) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;
        COMMIT;
    END CloseAccount;

    FUNCTION GetTotalCustomerBalance(p_customer_id NUMBER) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM Accounts WHERE CustomerID = p_customer_id;
        RETURN v_total;
    END GetTotalCustomerBalance;

END AccountOperations;
/
