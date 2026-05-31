-- ============================================================
-- EXERCISE 3: Stored Procedures
-- ============================================================

-- Scenario 1: Monthly interest for all savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 1 / 100),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    DBMS_OUTPUT.PUT_LINE('Interest applied to ' || SQL%ROWCOUNT || ' savings accounts');
    COMMIT;
END ProcessMonthlyInterest;
/

-- Scenario 2: Employee bonus by department
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department VARCHAR2,
    p_bonus_percentage NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percentage / 100)
    WHERE Department = p_department;

    DBMS_OUTPUT.PUT_LINE('Bonus applied to ' || SQL%ROWCOUNT || ' employees in ' || p_department);
    COMMIT;
END UpdateEmployeeBonus;
/

-- Scenario 3: Transfer funds between accounts
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account NUMBER,
    p_to_account NUMBER,
    p_amount NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts WHERE AccountID = p_from_account;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance');
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account;

    UPDATE Accounts SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred $' || p_amount || ' from account ' ||
                         p_from_account || ' to ' || p_to_account);
END TransferFunds;
/
