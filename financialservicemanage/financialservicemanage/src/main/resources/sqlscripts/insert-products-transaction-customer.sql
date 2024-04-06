SELECT * FROM customerfinancedata.productos;
SELECT * FROM customerfinancedata.clientes;
SELECT * FROM customerfinancedata.transacciones;
SELECT COUNT(*) FROM customerfinancedata.productos WHERE cliente_id = 1;

INSERT INTO customerfinancedata.transacciones (`monto`,`cuenta_id`,`fecha_transaccion`,
`id_transaccion`,`tipo`)VALUES
('500', '1', '2024-03-25 22:18:17', '4', 'RETIRO');

INSERT INTO customerfinancedata.productos (`exenta_gmf`, `saldo`, `cliente_id`, `fecha_creacion`, `fecha_modificacion`, `id_producto`, `numero_cuenta`, `estado`, `tipo_cuenta`) 
VALUES (TRUE, '2', '1', '2024-03-25 22:18:17', '2024-03-25 22:18:17', '1', '5354549309', 'ACTIVA', 'CUENTA_DE_AHORROS');

INSERT INTO customerfinancedata.transacciones (`monto`, `cuenta_id`, `fecha_transaccion`, `id_transaccion`, `tipo`) VALUES ('100000', '1', '2024-03-25 22:18:17', '1', 'RETIRO');

SELECT saldo FROM productos WHERE id_producto = (SELECT cuenta_id FROM transacciones WHERE id_transaccion = 1);
UPDATE productos SET saldo = saldo - 500 WHERE id_producto = (SELECT cuenta_id FROM transacciones WHERE id_transaccion = 1);