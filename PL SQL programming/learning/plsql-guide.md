# PL/SQL Programming - Simple Learning Guide

## What We Built

SQL scripts for an **Oracle Bank Management System** covering all 7 PL/SQL exercise categories.

```
PL SQL programming/project/
├── 00_schema_and_data.sql      (5 tables + sample data)
├── 01_control_structures.sql   (IF/LOOP/FOR)
├── 02_error_handling.sql       (EXCEPTION, ROLLBACK)
├── 03_stored_procedures.sql    (PROCEDURE)
├── 04_functions.sql            (FUNCTION)
├── 05_triggers.sql             (TRIGGER)
├── 06_cursors.sql              (CURSOR)
└── 07_packages.sql             (PACKAGE + PACKAGE BODY)
```

---

## Database Schema (what we store)

```
Customers  ──── Accounts ──── Transactions
    │                              │
    └────────── Loans              │
                                   │
Employees                     AuditLog (created by trigger)
```

| Table | Stores |
|-------|--------|
| **Customers** | Customer info (name, DOB, balance) |
| **Accounts** | Bank accounts (type: Savings/Checking) |
| **Transactions** | Deposits, withdrawals, transfers |
| **Loans** | Loan details (amount, rate, tenure) |
| **Employees** | Bank employee records |

---

## 1. Control Structures (IF, LOOP, FOR)

### What they do
Control the **flow** of your PL/SQL code - make decisions and repeat actions.

### IF Statement
```sql
IF condition THEN
    do_something;
ELSIF other_condition THEN
    do_something_else;
ELSE
    fallback;
END IF;
```
**Example:** Give 1% discount to customers over 60.
```sql
IF MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12 > 60 THEN
    UPDATE Loans SET InterestRate = InterestRate - 1;
END IF;
```

### FOR Loop (iterates through query results)
```sql
FOR record IN (SELECT ... FROM ...) LOOP
    process(record);
END LOOP;
```
**Example:** Check every customer's balance.
```sql
FOR cust IN (SELECT * FROM Customers) LOOP
    IF cust.Balance > 10000 THEN
        DBMS_OUTPUT.PUT_LINE('VIP: ' || cust.Name);
    END IF;
END LOOP;
```

---

## 2. Error Handling (EXCEPTION)

### What it does
**Catches errors** gracefully instead of crashing.

### Structure
```sql
BEGIN
    -- risky code
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        -- handle duplicate key
    WHEN OTHERS THEN
        -- handle anything else
        ROLLBACK;
        RAISE;  -- re-throw error
END;
```

### Common Exceptions
| Exception | When it happens |
|-----------|----------------|
| `NO_DATA_FOUND` | SELECT returns nothing |
| `DUP_VAL_ON_INDEX` | INSERTing duplicate primary key |
| `OTHERS` | Any other error |

**Example:** Add customer, but don't crash if ID exists.
```sql
PROCEDURE AddNewCustomer(...) IS
BEGIN
    INSERT INTO Customers VALUES (...);
    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Customer already exists!');
END;
```

---

## 3. Stored Procedures (PROCEDURE)

### What they are
**Named blocks** of code you can call by name. Like a "recipe" you can run anytime.

### Structure
```sql
CREATE OR REPLACE PROCEDURE procedure_name(
    p_param1 NUMBER,
    p_param2 VARCHAR2
) AS
    v_local_var NUMBER;
BEGIN
    -- logic here
    COMMIT;
END;
/

-- Call it:
BEGIN
    procedure_name(100, 'test');
END;
/
```

**Example:** Process monthly interest on savings accounts.
```sql
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 1 / 100)
    WHERE AccountType = 'Savings';
    COMMIT;
END;
/
```

---

## 4. Functions (FUNCTION)

### What they are
Like procedures but **return a value**.

### Structure
```sql
CREATE OR REPLACE FUNCTION function_name(
    p_input NUMBER
) RETURN VARCHAR2 IS
    v_result VARCHAR2(100);
BEGIN
    -- logic
    RETURN v_result;
END;
/
```

**Example:** Calculate age from DOB.
```sql
FUNCTION CalculateAge(p_dob DATE) RETURN NUMBER IS
BEGIN
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/
```

### Procedure vs Function
| | Procedure | Function |
|---|-----------|----------|
| Returns value? | No | Yes (RETURN) |
| Used in SQL? | No | Yes (SELECT func() FROM dual) |
| Purpose | Do an action | Calculate a value |

---

## 5. Triggers (TRIGGER)

### What they are
Code that **fires automatically** when something happens (INSERT/UPDATE/DELETE).

### Structure
```sql
CREATE OR REPLACE TRIGGER trigger_name
    BEFORE (or AFTER) INSERT (or UPDATE or DELETE)
    ON TableName
    FOR EACH ROW
BEGIN
    -- :NEW refers to new values
    -- :OLD refers to old values
END;
/
```

**Example 1:** Auto-update LastModified when customer changes.
```sql
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;  -- Set timestamp automatically
END;
```

**Example 2:** Prevent withdrawal if insufficient balance.
```sql
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance FROM Accounts ... ;
        IF v_balance < :NEW.Amount THEN
            RAISE_APPLICATION_ERROR(-20003, 'Insufficient funds');
        END IF;
    END IF;
END;
```

---

## 6. Cursors (CURSOR)

### What they are
**Named queries** that you can loop through row by row.

### Structure
```sql
DECLARE
    CURSOR cursor_name IS
        SELECT ... FROM ... WHERE ...;
BEGIN
    FOR record IN cursor_name LOOP
        process(record.column);
    END LOOP;
END;
/
```

**Example:** Apply annual fee to all accounts.
```sql
DECLARE
    CURSOR cur_accounts IS
        SELECT AccountID, Balance FROM Accounts;
BEGIN
    FOR acc IN cur_accounts LOOP
        UPDATE Accounts
        SET Balance = Balance - 50  -- annual fee
        WHERE AccountID = acc.AccountID;
    END LOOP;
    COMMIT;
END;
/
```

---

## 7. Packages (PACKAGE)

### What they are
**Group related procedures and functions** together in one container.

### Package = Specification + Body
```sql
-- SPECIFICATION (what's available)
CREATE OR REPLACE PACKAGE PackageName AS
    PROCEDURE proc1(...);
    FUNCTION func1(...) RETURN ...;
END PackageName;
/

-- BODY (how it works)
CREATE OR REPLACE PACKAGE BODY PackageName AS
    PROCEDURE proc1(...) IS
    BEGIN ... END;
    FUNCTION func1(...) RETURN ... IS
    BEGIN ... END;
END PackageName;
/
```

**Example:** Customer Management Package
```
CustomerManagement
  ├── AddCustomer(id, name, dob, balance)
  ├── UpdateCustomerDetails(id, name, dob)
  └── GetCustomerBalance(id) → NUMBER
```

Call it like:
```sql
CustomerManagement.AddCustomer(5, 'New User', SYSDATE, 1000);
CustomerManagement.GetCustomerBalance(1);
```

### Why packages?
- **Organization** - related code stays together
- **Encapsulation** - hide implementation details
- **Reusability** - call from any other PL/SQL block

---

## Quick Reference

| Concept | Key Words | Analogy |
|---------|-----------|---------|
| **Control** | `IF`, `LOOP`, `FOR` | Traffic lights |
| **Error Handling** | `EXCEPTION`, `WHEN`, `OTHERS` | Safety net |
| **Procedure** | `PROCEDURE`, `IS`, `BEGIN` | A recipe |
| **Function** | `FUNCTION`, `RETURN` | Calculator |
| **Trigger** | `TRIGGER`, `BEFORE/AFTER`, `:NEW` | Security camera |
| **Cursor** | `CURSOR`, `FOR...LOOP` | Reading a book line by line |
| **Package** | `PACKAGE`, `PACKAGE BODY` | Toolbox |

## How to Run
```sql
-- In Oracle SQL Developer or SQL*Plus:
@00_schema_and_data.sql
@01_control_structures.sql
@02_error_handling.sql
-- ... and so on
```
