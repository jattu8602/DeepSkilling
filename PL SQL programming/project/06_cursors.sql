-- ============================================================
-- EXERCISE 6: Cursors
-- ============================================================

-- Scenario 1: Generate monthly statements (explicit cursor)
DECLARE
    CURSOR cur_transactions IS
        SELECT t.*, a.CustomerID, c.Name
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
        ORDER BY c.CustomerID, t.TransactionDate;

    v_last_customer NUMBER := -1;
BEGIN
    FOR trans IN cur_transactions LOOP
        IF trans.CustomerID != v_last_customer THEN
            DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Statement for ' || trans.Name || ' ---');
            v_last_customer := trans.CustomerID;
        END IF;
        DBMS_OUTPUT.PUT_LINE(trans.TransactionDate || ' | ' ||
                             trans.TransactionType || ' | $' || trans.Amount);
    END LOOP;
END;
/

-- Scenario 2: Apply annual fee to all accounts
DECLARE
    CURSOR cur_accounts IS
        SELECT AccountID, Balance FROM Accounts;
    v_annual_fee CONSTANT NUMBER := 50;
BEGIN
    FOR acc IN cur_accounts LOOP
        UPDATE Accounts
        SET Balance = Balance - v_annual_fee,
            LastModified = SYSDATE
        WHERE AccountID = acc.AccountID;

        DBMS_OUTPUT.PUT_LINE('Fee $' || v_annual_fee ||
                             ' applied to account ' || acc.AccountID);
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Update loan interest rates based on new policy
DECLARE
    CURSOR cur_loans IS
        SELECT LoanID, LoanAmount, InterestRate FROM Loans;
    v_new_rate NUMBER;
BEGIN
    FOR loan_rec IN cur_loans LOOP
        IF loan_rec.LoanAmount > 50000 THEN
            v_new_rate := loan_rec.InterestRate - 0.5;
        ELSE
            v_new_rate := loan_rec.InterestRate - 0.25;
        END IF;

        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE LoanID = loan_rec.LoanID;

        DBMS_OUTPUT.PUT_LINE('Loan ' || loan_rec.LoanID ||
                             ': rate from ' || loan_rec.InterestRate ||
                             '% to ' || v_new_rate || '%');
    END LOOP;
    COMMIT;
END;
/
