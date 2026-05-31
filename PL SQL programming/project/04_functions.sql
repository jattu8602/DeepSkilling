-- ============================================================
-- EXERCISE 4: Functions
-- ============================================================

-- Scenario 1: Calculate age from date of birth
CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE) RETURN NUMBER IS
BEGIN
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END CalculateAge;
/

-- Scenario 2: Calculate monthly loan installment
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_annual_rate NUMBER,
    p_years NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_months NUMBER;
    v_installment NUMBER;
BEGIN
    v_monthly_rate := p_annual_rate / 12 / 100;
    v_months := p_years * 12;

    IF v_monthly_rate = 0 THEN
        v_installment := p_loan_amount / v_months;
    ELSE
        v_installment := p_loan_amount *
            (v_monthly_rate * POWER(1 + v_monthly_rate, v_months)) /
            (POWER(1 + v_monthly_rate, v_months) - 1);
    END IF;

    RETURN ROUND(v_installment, 2);
END CalculateMonthlyInstallment;
/

-- Scenario 3: Check if account has sufficient balance
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts WHERE AccountID = p_account_id;
    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END HasSufficientBalance;
/
