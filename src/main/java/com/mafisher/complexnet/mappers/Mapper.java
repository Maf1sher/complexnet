package com.mafisher.complexnet.mappers;

public interface Mapper<A, B> {
    B toDto(A a);

    A toEntity(B b);
}
