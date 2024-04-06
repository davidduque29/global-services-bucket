package com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.entity;

import java.util.Date;

import com.financesbucket.financialservicemanage.infrastructure.adapters.product.entity.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacciones")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Transaccion")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Tipo de transacción'")
    private TipoTransaccion tipo;

    @Column(name = "monto", columnDefinition = "DECIMAL(10, 2) COMMENT 'Monto'")
    private double monto;

    @Column(name = "fecha_transaccion", nullable = false, columnDefinition = "TIMESTAMP COMMENT 'Fecha de transacción'")
    @CreationTimestamp
    private Date fechaTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private ProductEntity cuenta;

    public enum TipoTransaccion {
        CONSIGNACION,
        RETIRO,
        TRANSFERENCIA
    }
}