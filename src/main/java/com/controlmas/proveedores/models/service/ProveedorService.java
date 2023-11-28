package com.controlmas.proveedores.models.service;

import com.controlmas.proveedores.models.entity.Proveedores;
import com.controlmas.proveedores.models.repository.IProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProveedorService implements IProveedorRepository{

    @Autowired
    private IProveedorRepository proveedorRepository;


    @Autowired
    public ProveedorService(IProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Proveedores> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Proveedores> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Proveedores> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Proveedores getOne(Integer integer) {
        return null;
    }

    @Override
    public Proveedores getById(Integer integer) {
        return null;
    }

    @Override
    public Proveedores getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Proveedores> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Proveedores> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Proveedores> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Proveedores> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Proveedores> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Proveedores> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Proveedores, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Proveedores save(Proveedores proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public <S extends Proveedores> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Proveedores> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Proveedores> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public List<Proveedores> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Proveedores entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Proveedores> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Proveedores> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Proveedores> findAll(Pageable pageable) {
        return null;
    }
}
