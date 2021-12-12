package br.com.rafael.api.model;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoContaConverter implements AttributeConverter<TipoConta, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoConta tipoConta) {
        return tipoConta.getTipoConta();
    }

    @Override
    public TipoConta convertToEntityAttribute(Integer tipoConta) {
        if (tipoConta == 1) {
            return TipoConta.CONTA_CORRENTE;
        }

        return TipoConta.CONTA_POUPANCA;
    }
}