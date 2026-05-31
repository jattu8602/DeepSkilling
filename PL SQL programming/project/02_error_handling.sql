-- ============================================================
-- EXERCISE 2: Error Handling
-- ============================================================

-- Scenario 1: Safe fund transfer with rollback on error
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account NUMBER,
    p_to_account NUMBER,
    p_amount NUMBER
) AS
    v_from_balance NUMBER;
BEGIN
    SELECT Balance INTO v_from_balance
    FROM Accounts WHERE AccountID = p_from_account FOR UPDATE;

    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds');
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account;

    UPDATE Accounts SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account;

    INSERT INTO Transactions(TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (SEQ_TRANS.nextval, p_from_account, SYSDATE, -p_amount, 'Transfer');

    INSERT INTO Transactions(TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (SEQ_TRANS.nextval, p_to_account, SYSDATE, p_amount, 'Transfer');

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
        RAISE;
END SafeTransferFunds;
/

-- Scenario 2: Update salary with employee existence check
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id NUMBER,
    p_percentage NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee ID ' || p_employee_id || ' not found');
    END IF;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Salary update failed: ' || SQLERRM);
        ROLLBACK;
END UpdateSalary;
/

-- Scenario 3: Add new customer with duplicate ID check
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id NUMBER,
    p_name VARCHAR2,
    p_dob DATE,
    p_balance NUMBER
) AS
BEGIN
    INSERT INTO Customers(CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' already exists');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
        ROLLBACK;
END AddNewCustomer;
/
