USE customerfinancedata;
DELIMITER //

CREATE TRIGGER actualizar_saldo_retiro
AFTER INSERT ON transacciones
FOR EACH ROW
BEGIN
    -- Verificar si el tipo de transacción es "RETIRO"
    IF NEW.tipo = 'RETIRO' THEN
        -- Obtener el saldo actual de la cuenta
        DECLARE saldo_actual DECIMAL(10, 2);
        SELECT saldo INTO saldo_actual FROM productos WHERE id_producto = NEW.cuenta_id;

        -- Restar el monto al saldo de la cuenta
        UPDATE productos
        SET saldo = saldo_actual - NEW.monto  
        WHERE id_producto = NEW.cuenta_id;
    END IF;
END 

DELIMITER ;
