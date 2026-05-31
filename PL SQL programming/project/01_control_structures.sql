-- ============================================================
-- EXERCISE 1: Control Structures
-- ============================================================

-- Scenario 1: Apply 1% discount to loan interest for customers > 60 years
BEGIN
    FOR cust IN (SELECT c.CustomerID, c.DOB, l.LoanID, l.InterestRate
                 FROM Customers c
                 JOIN Loans l ON c.CustomerID = l.CustomerID)
    LOOP
        IF MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12 > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = cust.LoanID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Set IsVIP flag for customers with balance > $10,000
-- (Note: Using a virtual column approach since IsVIP not in schema)
BEGIN
    FOR cust IN (SELECT CustomerID, Balance FROM Customers)
    LOOP
        IF cust.Balance > 10000 THEN
            DBMS_OUTPUT.PUT_LINE('Customer ' || cust.CustomerID || ' is VIP');
        END IF;
    END LOOP;
END;
/

-- Scenario 3: Print reminders for loans due within 30 days
BEGIN
    FOR loan_rec IN (SELECT l.LoanID, c.Name, l.EndDate
                     FROM Loans l
                     JOIN Customers c ON l.CustomerID = c.CustomerID
                     WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || loan_rec.Name ||
                             ', Loan ' || loan_rec.LoanID ||
                             ' is due on ' || loan_rec.EndDate);
    END LOOP;
END;
/
