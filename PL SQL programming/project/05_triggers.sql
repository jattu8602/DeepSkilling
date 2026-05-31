-- ============================================================
-- EXERCISE 5: Triggers
-- ============================================================

-- Scenario 1: Auto-update LastModified when customer record changes
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
    BEFORE UPDATE ON Customers
    FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/

-- Scenario 2: Audit log for transactions
CREATE TABLE AuditLog (
    LogID NUMBER PRIMARY KEY,
    TransactionID NUMBER,
    AccountID NUMBER,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    LogDate DATE
);

CREATE SEQUENCE SEQ_AUDIT START WITH 1;

CREATE OR REPLACE TRIGGER LogTransaction
    AFTER INSERT ON Transactions
    FOR EACH ROW
BEGIN
    INSERT INTO AuditLog(LogID, TransactionID, AccountID, Amount, TransactionType, LogDate)
    VALUES (SEQ_AUDIT.NEXTVAL, :NEW.TransactionID, :NEW.AccountID,
            :NEW.Amount, :NEW.TransactionType, SYSDATE);
END LogTransaction;
/

-- Scenario 3: Enforce business rules on deposits/withdrawals
CREATE OR REPLACE TRIGGER CheckTransactionRules
    BEFORE INSERT ON Transactions
    FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'Withdrawal' OR :NEW.TransactionType = 'Transfer' THEN
        SELECT Balance INTO v_balance
        FROM Accounts WHERE AccountID = :NEW.AccountID;

        IF v_balance < ABS(:NEW.Amount) THEN
            RAISE_APPLICATION_ERROR(-20003, 'Insufficient balance for withdrawal');
        END IF;
    END IF;

    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Deposit amount must be positive');
    END IF;
END CheckTransactionRules;
/
