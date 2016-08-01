package com.speedment.runtime.internal.field;

import com.speedment.runtime.config.identifier.FieldIdentifier;
import com.speedment.runtime.config.mapper.TypeMapper;
import com.speedment.runtime.field.FloatField;
import com.speedment.runtime.field.FloatForeignKeyField;
import com.speedment.runtime.field.method.Finder;
import com.speedment.runtime.field.method.FloatGetter;
import com.speedment.runtime.field.method.FloatSetter;
import com.speedment.runtime.field.predicate.FieldPredicate;
import com.speedment.runtime.field.predicate.Inclusion;
import com.speedment.runtime.internal.field.comparator.FloatFieldComparator;
import com.speedment.runtime.internal.field.comparator.FloatFieldComparatorImpl;
import com.speedment.runtime.internal.field.predicate.floats.FloatBetweenPredicate;
import com.speedment.runtime.internal.field.predicate.floats.FloatEqualPredicate;
import com.speedment.runtime.internal.field.predicate.floats.FloatGreaterOrEqualPredicate;
import com.speedment.runtime.internal.field.predicate.floats.FloatGreaterThanPredicate;
import com.speedment.runtime.internal.field.predicate.floats.FloatInPredicate;
import java.util.Set;
import java.util.function.Predicate;
import static java.util.Objects.requireNonNull;

/**
 * @param <ENTITY>    entity type
 * @param <D>         database type
 * @param <FK_ENTITY> foreign entity type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
public final class FloatForeignKeyFieldImpl<ENTITY, D, FK_ENTITY>  implements FloatField<ENTITY, D>, FloatForeignKeyField<ENTITY, D, FK_ENTITY> {
    
    private final FieldIdentifier<ENTITY> identifier;
    private final FloatGetter<ENTITY> getter;
    private final FloatSetter<ENTITY> setter;
    private final Finder<ENTITY, FK_ENTITY> finder;
    private final TypeMapper<D, Float> typeMapper;
    private final boolean unique;
    
    public FloatForeignKeyFieldImpl(FieldIdentifier<ENTITY> identifier, FloatGetter<ENTITY> getter, FloatSetter<ENTITY> setter, Finder<ENTITY, FK_ENTITY> finder, TypeMapper<D, Float> typeMapper, boolean unique) {
        this.identifier = requireNonNull(identifier);
        this.getter     = requireNonNull(getter);
        this.setter     = requireNonNull(setter);
        this.finder     = requireNonNull(finder);
        this.typeMapper = requireNonNull(typeMapper);
        this.unique     = unique;
    }
    
    @Override
    public FieldIdentifier<ENTITY> identifier() {
        return identifier;
    }
    
    @Override
    public FloatSetter<ENTITY> setter() {
        return setter;
    }
    
    @Override
    public FloatGetter<ENTITY> getter() {
        return getter;
    }
    
    @Override
    public Finder<ENTITY, FK_ENTITY> finder() {
        return finder;
    }
    
    @Override
    public TypeMapper<D, Float> typeMapper() {
        return typeMapper;
    }
    
    @Override
    public boolean isUnique() {
        return unique;
    }
    
    @Override
    public FloatFieldComparator<ENTITY, D> comparator() {
        return new FloatFieldComparatorImpl<>(this);
    }
    
    @Override
    public FloatFieldComparator<ENTITY, D> comparatorNullFieldsFirst() {
        return comparator();
    }
    
    @Override
    public FloatFieldComparator<ENTITY, D> comparatorNullFieldsLast() {
        return comparator();
    }
    
    @Override
    public FieldPredicate<ENTITY> equal(Float value) {
        return new FloatEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterThan(Float value) {
        return new FloatGreaterThanPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterOrEqual(Float value) {
        return new FloatGreaterOrEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> between(Float start, Float end, Inclusion inclusion) {
        return new FloatBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public FieldPredicate<ENTITY> in(Set<Float> set) {
        return new FloatInPredicate<>(this, set);
    }
    
    @Override
    public Predicate<ENTITY> notEqual(Float value) {
        return new FloatEqualPredicate<>(this, value).negate();
    }
    
    @Override
    public Predicate<ENTITY> lessOrEqual(Float value) {
        return new FloatGreaterThanPredicate<>(this, value).negate();
    }
    
    @Override
    public Predicate<ENTITY> lessThan(Float value) {
        return new FloatGreaterOrEqualPredicate<>(this, value).negate();
    }
    
    @Override
    public Predicate<ENTITY> notBetween(Float start, Float end, Inclusion inclusion) {
        return new FloatBetweenPredicate<>(this, start, end, inclusion).negate();
    }
    
    @Override
    public Predicate<ENTITY> notIn(Set<Float> set) {
        return new FloatInPredicate<>(this, set).negate();
    }
}