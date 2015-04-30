/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.matheclipse.commons.math.linear;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IExpr;

/**
 * This class implements the {@link FieldVector} interface with a {@link IExpr} array.
 * 
 * @param <T>
 *            the type of the field elements
 * @since 2.0
 */
public class ArrayFieldVector implements FieldVector, Serializable {
	/** Serializable version identifier. */
	private static final long serialVersionUID = 7648186910365927050L;

	/** Entries of the vector. */
	private IExpr[] data;

	/**
	 * Build a 0-length vector. Zero-length vectors may be used to initialize construction of vectors by data gathering. We start
	 * with zero-length and use either the {@link #ArrayFieldVector(ArrayFieldVector, ArrayFieldVector)} constructor or one of the
	 * {@code append} methods ({@link #add(FieldVector)} or {@link #append(ArrayFieldVector)}) to gather data into this vector.
	 */
	public ArrayFieldVector() {
		this(0);
	}

	/**
	 * Construct a vector of zeroes.
	 * @param size
	 *            Size of the vector.
	 */
	public ArrayFieldVector(int size) {
		this.data = MathArrays.buildArray(size);
	}

	/**
	 * Construct a vector with preset values.
	 *
	 * @param size
	 *            Size of the vector.
	 * @param preset
	 *            All entries will be set with this value.
	 */
	public ArrayFieldVector(int size, IExpr preset) {
		this(size);
		Arrays.fill(data, preset);
	}

	/**
	 * Construct a vector from an array, copying the input array. This constructor needs a non-empty {@code d} array to retrieve the
	 * field from its first element. This implies it cannot build 0 length vectors. To build vectors from any size, one should use
	 * the {@link #ArrayFieldVector()} constructor.
	 *
	 * @param d
	 *            Array.
	 * @throws NullArgumentException
	 *             if {@code d} is {@code null}.
	 * @throws ZeroException
	 *             if {@code d} is empty.
	 * @see #ArrayFieldVector()
	 */
	public ArrayFieldVector(IExpr[] d) throws NullArgumentException, ZeroException {
		MathUtils.checkNotNull(d);
		try {
			// field = d[0].getField();
			data = d.clone();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
		}
	}

	/**
	 * Construct a vector from an array, copying the input array.
	 *
	 * @param field
	 *            Field to which the elements belong.
	 * @param d
	 *            Array.
	 * @throws NullArgumentException
	 *             if {@code d} is {@code null}.
	 * @see #ArrayFieldVector()
	 */
	public ArrayFieldVector(Field<IExpr> field, IExpr[] d) throws NullArgumentException {
		MathUtils.checkNotNull(d);
		data = d.clone();
	}

	/**
	 * Create a new ArrayFieldVector using the input array as the underlying data array. If an array is built specially in order to
	 * be embedded in a ArrayFieldVector and not used directly, the {@code copyArray} may be set to {@code false}. This will prevent
	 * the copying and improve performance as no new array will be built and no data will be copied. This constructor needs a
	 * non-empty {@code d} array to retrieve the field from its first element. This implies it cannot build 0 length vectors. To
	 * build vectors from any size, one should use the {@link #ArrayFieldVector(Field, FieldElemenIExpr[], boolean)} constructor.
	 *
	 * @param d
	 *            Data for the new vector.
	 * @param copyArray
	 *            If {@code true}, the input array will be copied, otherwise it will be referenced.
	 * @throws NullArgumentException
	 *             if {@code d} is {@code null}.
	 * @throws ZeroException
	 *             if {@code d} is empty.
	 * @see #ArrayFieldVector()
	 * @see #ArrayFieldVector(Field, FieldElemenIExpr[], boolean)
	 */
	public ArrayFieldVector(IExpr[] d, boolean copyArray) throws NullArgumentException, ZeroException {
		MathUtils.checkNotNull(d);
		if (d.length == 0) {
			throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
		}
		// field = d[0].getField();
		data = copyArray ? d.clone() : d;
	}

	/**
	 * Create a new ArrayFieldVector using the input array as the underlying data array. If an array is built specially in order to
	 * be embedded in a ArrayFieldVector and not used directly, the {@code copyArray} may be set to {@code false}. This will prevent
	 * the copying and improve performance as no new array will be built and no data will be copied.
	 *
	 * @param field
	 *            Field to which the elements belong.
	 * @param d
	 *            Data for the new vector.
	 * @param copyArray
	 *            If {@code true}, the input array will be copied, otherwise it will be referenced.
	 * @throws NullArgumentException
	 *             if {@code d} is {@code null}.
	 * @see #ArrayFieldVector(boolean)
	 */
	public ArrayFieldVector(Field<IExpr> field, IExpr[] d, boolean copyArray) throws NullArgumentException {
		MathUtils.checkNotNull(d);
		data = copyArray ? d.clone() : d;
	}

	/**
	 * Construct a vector from part of a array.
	 *
	 * @param d
	 *            Array.
	 * @param pos
	 *            Position of the first entry.
	 * @param size
	 *            Number of entries to copy.
	 * @throws NullArgumentException
	 *             if {@code d} is {@code null}.
	 * @throws NumberIsTooLargeException
	 *             if the size of {@code d} is less than {@code pos + size}.
	 */
	public ArrayFieldVector(IExpr[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
		MathUtils.checkNotNull(d);
		if (d.length < pos + size) {
			throw new NumberIsTooLargeException(pos + size, d.length, true);
		}
		data = MathArrays.buildArray(size);
		System.arraycopy(d, pos, data, 0, size);
	}

	/**
	 * Construct a vector from part of a array.
	 *
	 * @param field
	 *            Field to which the elements belong.
	 * @param d
	 *            Array.
	 * @param pos
	 *            Position of the first entry.
	 * @param size
	 *            Number of entries to copy.
	 * @throws NullArgumentException
	 *             if {@code d} is {@code null}.
	 * @throws NumberIsTooLargeException
	 *             if the size of {@code d} is less than {@code pos + size}.
	 */
	public ArrayFieldVector(Field<IExpr> field, IExpr[] d, int pos, int size) throws NullArgumentException,
			NumberIsTooLargeException {
		MathUtils.checkNotNull(d);
		if (d.length < pos + size) {
			throw new NumberIsTooLargeException(pos + size, d.length, true);
		}
		data = MathArrays.buildArray(size);
		System.arraycopy(d, pos, data, 0, size);
	}

	/**
	 * Construct a vector from another vector, using a deep copy.
	 *
	 * @param v
	 *            Vector to copy.
	 * @throws NullArgumentException
	 *             if {@code v} is {@code null}.
	 */
	public ArrayFieldVector(FieldVector v) throws NullArgumentException {
		MathUtils.checkNotNull(v);
		data = MathArrays.buildArray(v.getDimension());
		for (int i = 0; i < data.length; ++i) {
			data[i] = v.getEntry(i);
		}
	}

	/**
	 * Construct a vector from another vector, using a deep copy.
	 *
	 * @param v
	 *            Vector to copy.
	 * @throws NullArgumentException
	 *             if {@code v} is {@code null}.
	 */
	public ArrayFieldVector(ArrayFieldVector v) throws NullArgumentException {
		MathUtils.checkNotNull(v);
		data = v.data.clone();
	}

	/**
	 * Construct a vector from another vector.
	 *
	 * @param v
	 *            Vector to copy.
	 * @param deep
	 *            If {@code true} perform a deep copy, otherwise perform a shallow copy
	 * @throws NullArgumentException
	 *             if {@code v} is {@code null}.
	 */
	public ArrayFieldVector(ArrayFieldVector v, boolean deep) throws NullArgumentException {
		MathUtils.checkNotNull(v);
		data = deep ? v.data.clone() : v.data;
	}

	/**
	 * Construct a vector by appending one vector to another vector.
	 *
	 * @param v1
	 *            First vector (will be put in front of the new vector).
	 * @param v2
	 *            Second vector (will be put at back of the new vector).
	 * @throws NullArgumentException
	 *             if {@code v1} or {@code v2} is {@code null}.
	 * @deprecated as of 3.2, replaced by {@link #ArrayFieldVector(FieldVector, FieldVector)}
	 */
	@Deprecated
	public ArrayFieldVector(ArrayFieldVector v1, ArrayFieldVector v2) throws NullArgumentException {
		this((FieldVector) v1, (FieldVector) v2);
	}

	/**
	 * Construct a vector by appending one vector to another vector.
	 *
	 * @param v1
	 *            First vector (will be put in front of the new vector).
	 * @param v2
	 *            Second vector (will be put at back of the new vector).
	 * @throws NullArgumentException
	 *             if {@code v1} or {@code v2} is {@code null}.
	 * @since 3.2
	 */
	public ArrayFieldVector(FieldVector v1, FieldVector v2) throws NullArgumentException {
		MathUtils.checkNotNull(v1);
		MathUtils.checkNotNull(v2);
		final IExpr[] v1Data = (v1 instanceof ArrayFieldVector) ? ((ArrayFieldVector) v1).data : v1.toArray();
		final IExpr[] v2Data = (v2 instanceof ArrayFieldVector) ? ((ArrayFieldVector) v2).data : v2.toArray();
		data = MathArrays.buildArray(v1Data.length + v2Data.length);
		System.arraycopy(v1Data, 0, data, 0, v1Data.length);
		System.arraycopy(v2Data, 0, data, v1Data.length, v2Data.length);
	}

	/**
	 * Construct a vector by appending one vector to another vector.
	 *
	 * @param v1
	 *            First vector (will be put in front of the new vector).
	 * @param v2
	 *            Second vector (will be put at back of the new vector).
	 * @throws NullArgumentException
	 *             if {@code v1} or {@code v2} is {@code null}.
	 * @deprecated as of 3.2, replaced by {@link #ArrayFieldVector()}
	 */
	@Deprecated
	public ArrayFieldVector(ArrayFieldVector v1, IExpr[] v2) throws NullArgumentException {
		this((FieldVector) v1, v2);
	}

	/**
	 * Construct a vector by appending one vector to another vector.
	 *
	 * @param v1
	 *            First vector (will be put in front of the new vector).
	 * @param v2
	 *            Second vector (will be put at back of the new vector).
	 * @throws NullArgumentException
	 *             if {@code v1} or {@code v2} is {@code null}.
	 * @since 3.2
	 */
	public ArrayFieldVector(FieldVector v1, IExpr[] v2) throws NullArgumentException {
		MathUtils.checkNotNull(v1);
		MathUtils.checkNotNull(v2);
		final IExpr[] v1Data = (v1 instanceof ArrayFieldVector) ? ((ArrayFieldVector) v1).data : v1.toArray();
		data = MathArrays.buildArray(v1Data.length + v2.length);
		System.arraycopy(v1Data, 0, data, 0, v1Data.length);
		System.arraycopy(v2, 0, data, v1Data.length, v2.length);
	}

	/**
	 * Construct a vector by appending one vector to another vector.
	 *
	 * @param v1
	 *            First vector (will be put in front of the new vector).
	 * @param v2
	 *            Second vector (will be put at back of the new vector).
	 * @throws NullArgumentException
	 *             if {@code v1} or {@code v2} is {@code null}.
	 * @deprecated as of 3.2, replaced by {@link #ArrayFieldVector(FieldVector)}
	 */
	@Deprecated
	public ArrayFieldVector(IExpr[] v1, ArrayFieldVector v2) throws NullArgumentException {
		this(v1, (FieldVector) v2);
	}

	/**
	 * Construct a vector by appending one vector to another vector.
	 *
	 * @param v1
	 *            First vector (will be put in front of the new vector).
	 * @param v2
	 *            Second vector (will be put at back of the new vector).
	 * @throws NullArgumentException
	 *             if {@code v1} or {@code v2} is {@code null}.
	 * @since 3.2
	 */
	public ArrayFieldVector(IExpr[] v1, FieldVector v2) throws NullArgumentException {
		MathUtils.checkNotNull(v1);
		MathUtils.checkNotNull(v2);
		final IExpr[] v2Data = (v2 instanceof ArrayFieldVector) ? ((ArrayFieldVector) v2).data : v2.toArray();
		data = MathArrays.buildArray(v1.length + v2Data.length);
		System.arraycopy(v1, 0, data, 0, v1.length);
		System.arraycopy(v2Data, 0, data, v1.length, v2Data.length);
	}

	/**
	 * Construct a vector by appending one vector to another vector. This constructor needs at least one non-empty array to retrieve
	 * the field from its first element. This implies it cannot build 0 length vectors. To build vectors from any size, one should
	 * use the {@link #ArrayFieldVector(Field, FieldElemenIExpr[], FieldElemenIExpr[])} constructor.
	 *
	 * @param v1
	 *            First vector (will be put in front of the new vector).
	 * @param v2
	 *            Second vector (will be put at back of the new vector).
	 * @throws NullArgumentException
	 *             if {@code v1} or {@code v2} is {@code null}.
	 * @throws ZeroException
	 *             if both arrays are empty.
	 * @see #ArrayFieldVector(Field, FieldElemenIExpr[], FieldElemenIExpr[])
	 */
	public ArrayFieldVector(IExpr[] v1, IExpr[] v2) throws NullArgumentException, ZeroException {
		MathUtils.checkNotNull(v1);
		MathUtils.checkNotNull(v2);
		if (v1.length + v2.length == 0) {
			throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
		}
		data = MathArrays.buildArray(v1.length + v2.length);
		System.arraycopy(v1, 0, data, 0, v1.length);
		System.arraycopy(v2, 0, data, v1.length, v2.length);
	}

	/**
	 * Construct a vector by appending one vector to another vector.
	 *
	 * @param field
	 *            Field to which the elements belong.
	 * @param v1
	 *            First vector (will be put in front of the new vector).
	 * @param v2
	 *            Second vector (will be put at back of the new vector).
	 * @throws NullArgumentException
	 *             if {@code v1} or {@code v2} is {@code null}.
	 * @throws ZeroException
	 *             if both arrays are empty.
	 * @see #ArrayFieldVector()
	 */
	public ArrayFieldVector(Field<IExpr> field, IExpr[] v1, IExpr[] v2) throws NullArgumentException, ZeroException {
		MathUtils.checkNotNull(v1);
		MathUtils.checkNotNull(v2);
		if (v1.length + v2.length == 0) {
			throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
		}
		data = MathArrays.buildArray(v1.length + v2.length);
		System.arraycopy(v1, 0, data, 0, v1.length);
		System.arraycopy(v2, 0, data, v1.length, v2.length);
	}

	/** {@inheritDoc} */
	public Field<IExpr> getField() {
		return null;
	}

	/** {@inheritDoc} */
	public FieldVector copy() {
		return new ArrayFieldVector(this, true);
	}

	/** {@inheritDoc} */
	public FieldVector add(FieldVector v) throws DimensionMismatchException {
		try {
			return add((ArrayFieldVector) v);
		} catch (ClassCastException cce) {
			checkVectorDimensions(v);
			IExpr[] out = MathArrays.buildArray(data.length);
			for (int i = 0; i < data.length; i++) {
				out[i] = data[i].plus(v.getEntry(i));
			}
			return new ArrayFieldVector(out, false);
		}
	}

	/**
	 * Compute the sum of {@code this} and {@code v}.
	 * 
	 * @param v
	 *            vector to be added
	 * @return {@code this + v}
	 * @throws DimensionMismatchException
	 *             if {@code v} is not the same size as {@code this}
	 */
	public ArrayFieldVector add(ArrayFieldVector v) throws DimensionMismatchException {
		checkVectorDimensions(v.data.length);
		IExpr[] out = MathArrays.buildArray(data.length);
		for (int i = 0; i < data.length; i++) {
			out[i] = data[i].plus(v.data[i]);
		}
		return new ArrayFieldVector(null, out, false);
	}

	/** {@inheritDoc} */
	public FieldVector subtract(FieldVector v) throws DimensionMismatchException {
		try {
			return subtract((ArrayFieldVector) v);
		} catch (ClassCastException cce) {
			checkVectorDimensions(v);
			IExpr[] out = MathArrays.buildArray(data.length);
			for (int i = 0; i < data.length; i++) {
				out[i] = data[i].subtract(v.getEntry(i));
			}
			return new ArrayFieldVector(out, false);
		}
	}

	/**
	 * Compute {@code this} minus {@code v}.
	 * 
	 * @param v
	 *            vector to be subtracted
	 * @return {@code this - v}
	 * @throws DimensionMismatchException
	 *             if {@code v} is not the same size as {@code this}
	 */
	public ArrayFieldVector subtract(ArrayFieldVector v) throws DimensionMismatchException {
		checkVectorDimensions(v.data.length);
		IExpr[] out = MathArrays.buildArray(data.length);
		for (int i = 0; i < data.length; i++) {
			out[i] = data[i].subtract(v.data[i]);
		}
		return new ArrayFieldVector(out, false);
	}

	/** {@inheritDoc} */
	public FieldVector mapAdd(IExpr d) throws NullArgumentException {
		IExpr[] out = MathArrays.buildArray(data.length);
		for (int i = 0; i < data.length; i++) {
			out[i] = data[i].plus(d);
		}
		return new ArrayFieldVector(out, false);
	}

	/** {@inheritDoc} */
	public FieldVector mapAddToSelf(IExpr d) throws NullArgumentException {
		for (int i = 0; i < data.length; i++) {
			data[i] = data[i].plus(d);
		}
		return this;
	}

	/** {@inheritDoc} */
	public FieldVector mapSubtract(IExpr d) throws NullArgumentException {
		IExpr[] out = MathArrays.buildArray(data.length);
		for (int i = 0; i < data.length; i++) {
			out[i] = data[i].subtract(d);
		}
		return new ArrayFieldVector(out, false);
	}

	/** {@inheritDoc} */
	public FieldVector mapSubtractToSelf(IExpr d) throws NullArgumentException {
		for (int i = 0; i < data.length; i++) {
			data[i] = data[i].subtract(d);
		}
		return this;
	}

	/** {@inheritDoc} */
	public FieldVector mapMultiply(IExpr d) throws NullArgumentException {
		IExpr[] out = MathArrays.buildArray(data.length);
		for (int i = 0; i < data.length; i++) {
			out[i] = data[i].times(d);
		}
		return new ArrayFieldVector(out, false);
	}

	/** {@inheritDoc} */
	public FieldVector mapMultiplyToSelf(IExpr d) throws NullArgumentException {
		for (int i = 0; i < data.length; i++) {
			data[i] = data[i].times(d);
		}
		return this;
	}

	/** {@inheritDoc} */
	public FieldVector mapDivide(IExpr d) throws NullArgumentException, MathArithmeticException {
		MathUtils.checkNotNull(d);
		IExpr[] out = MathArrays.buildArray(data.length);
		for (int i = 0; i < data.length; i++) {
			out[i] = data[i].divide(d);
		}
		return new ArrayFieldVector(out, false);
	}

	/** {@inheritDoc} */
	public FieldVector mapDivideToSelf(IExpr d) throws NullArgumentException, MathArithmeticException {
		MathUtils.checkNotNull(d);
		for (int i = 0; i < data.length; i++) {
			data[i] = data[i].divide(d);
		}
		return this;
	}

	/** {@inheritDoc} */
	public FieldVector mapInv() throws MathArithmeticException {
		IExpr[] out = MathArrays.buildArray(data.length);
		final IExpr one = F.C1;
		for (int i = 0; i < data.length; i++) {
			try {
				out[i] = one.divide(data[i]);
			} catch (final MathArithmeticException e) {
				throw new MathArithmeticException(LocalizedFormats.INDEX, i);
			}
		}
		return new ArrayFieldVector(out, false);
	}

	/** {@inheritDoc} */
	public FieldVector mapInvToSelf() throws MathArithmeticException {
		final IExpr one = F.C1;
		for (int i = 0; i < data.length; i++) {
			try {
				data[i] = one.divide(data[i]);
			} catch (final MathArithmeticException e) {
				throw new MathArithmeticException(LocalizedFormats.INDEX, i);
			}
		}
		return this;
	}

	/** {@inheritDoc} */
	public FieldVector ebeMultiply(FieldVector v) throws DimensionMismatchException {
		try {
			return ebeMultiply((ArrayFieldVector) v);
		} catch (ClassCastException cce) {
			checkVectorDimensions(v);
			IExpr[] out = MathArrays.buildArray(data.length);
			for (int i = 0; i < data.length; i++) {
				out[i] = data[i].times(v.getEntry(i));
			}
			return new ArrayFieldVector(out, false);
		}
	}

	/**
	 * Element-by-element multiplication.
	 * 
	 * @param v
	 *            vector by which instance elements must be multiplied
	 * @return a vector containing {@code this[i] * v[i]} for all {@code i}
	 * @throws DimensionMismatchException
	 *             if {@code v} is not the same size as {@code this}
	 */
	public ArrayFieldVector ebeMultiply(ArrayFieldVector v) throws DimensionMismatchException {
		checkVectorDimensions(v.data.length);
		IExpr[] out = MathArrays.buildArray(data.length);
		for (int i = 0; i < data.length; i++) {
			out[i] = data[i].times(v.data[i]);
		}
		return new ArrayFieldVector(out, false);
	}

	/** {@inheritDoc} */
	public FieldVector ebeDivide(FieldVector v) throws DimensionMismatchException, MathArithmeticException {
		try {
			return ebeDivide((ArrayFieldVector) v);
		} catch (ClassCastException cce) {
			checkVectorDimensions(v);
			IExpr[] out = MathArrays.buildArray(data.length);
			for (int i = 0; i < data.length; i++) {
				try {
					out[i] = data[i].divide(v.getEntry(i));
				} catch (final MathArithmeticException e) {
					throw new MathArithmeticException(LocalizedFormats.INDEX, i);
				}
			}
			return new ArrayFieldVector(out, false);
		}
	}

	/**
	 * Element-by-element division.
	 * 
	 * @param v
	 *            vector by which instance elements must be divided
	 * @return a vector containing {@code this[i] / v[i]} for all {@code i}
	 * @throws DimensionMismatchException
	 *             if {@code v} is not the same size as {@code this}
	 * @throws MathArithmeticException
	 *             if one entry of {@code v} is zero.
	 */
	public ArrayFieldVector ebeDivide(ArrayFieldVector v) throws DimensionMismatchException, MathArithmeticException {
		checkVectorDimensions(v.data.length);
		IExpr[] out = MathArrays.buildArray(data.length);
		for (int i = 0; i < data.length; i++) {
			try {
				out[i] = data[i].divide(v.data[i]);
			} catch (final MathArithmeticException e) {
				throw new MathArithmeticException(LocalizedFormats.INDEX, i);
			}
		}
		return new ArrayFieldVector(out, false);
	}

	/** {@inheritDoc} */
	public IExpr[] getData() {
		return data.clone();
	}

	/**
	 * Returns a reference to the underlying data array.
	 * <p>
	 * Does not make a fresh copy of the underlying data.
	 * </p>
	 * 
	 * @return array of entries
	 */
	public IExpr[] getDataRef() {
		return data;
	}

	/** {@inheritDoc} */
	public IExpr dotProduct(FieldVector v) throws DimensionMismatchException {
		try {
			return dotProduct((ArrayFieldVector) v);
		} catch (ClassCastException cce) {
			checkVectorDimensions(v);
			IExpr dot = F.C0;
			for (int i = 0; i < data.length; i++) {
				dot = dot.plus(data[i].times(v.getEntry(i)));
			}
			return dot;
		}
	}

	/**
	 * Compute the dot product.
	 * 
	 * @param v
	 *            vector with which dot product should be computed
	 * @return the scalar dot product of {@code this} and {@code v}
	 * @throws DimensionMismatchException
	 *             if {@code v} is not the same size as {@code this}
	 */
	public IExpr dotProduct(ArrayFieldVector v) throws DimensionMismatchException {
		checkVectorDimensions(v.data.length);
		IExpr dot = F.C0;
		for (int i = 0; i < data.length; i++) {
			dot = dot.plus(data[i].times(v.data[i]));
		}
		return dot;
	}

	/** {@inheritDoc} */
	public FieldVector projection(FieldVector v) throws DimensionMismatchException, MathArithmeticException {
		return v.mapMultiply(dotProduct(v).divide(v.dotProduct(v)));
	}

	/**
	 * Find the orthogonal projection of this vector onto another vector.
	 * 
	 * @param v
	 *            vector onto which {@code this} must be projected
	 * @return projection of {@code this} onto {@code v}
	 * @throws DimensionMismatchException
	 *             if {@code v} is not the same size as {@code this}
	 * @throws MathArithmeticException
	 *             if {@code v} is the null vector.
	 */
	public ArrayFieldVector projection(ArrayFieldVector v) throws DimensionMismatchException, MathArithmeticException {
		return (ArrayFieldVector) v.mapMultiply(dotProduct(v).divide(v.dotProduct(v)));
	}

	/** {@inheritDoc} */
	public FieldMatrix outerProduct(FieldVector v) {
		try {
			return outerProduct((ArrayFieldVector) v);
		} catch (ClassCastException cce) {
			final int m = data.length;
			final int n = v.getDimension();
			final FieldMatrix out = new Array2DRowFieldMatrix(m, n);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					out.setEntry(i, j, data[i].times(v.getEntry(j)));
				}
			}
			return out;
		}
	}

	/**
	 * Compute the outer product.
	 * 
	 * @param v
	 *            vector with which outer product should be computed
	 * @return the matrix outer product between instance and v
	 */
	public FieldMatrix outerProduct(ArrayFieldVector v) {
		final int m = data.length;
		final int n = v.data.length;
		final FieldMatrix out = new Array2DRowFieldMatrix(m, n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				out.setEntry(i, j, data[i].times(v.data[j]));
			}
		}
		return out;
	}

	/** {@inheritDoc} */
	public IExpr getEntry(int index) {
		return data[index];
	}

	/** {@inheritDoc} */
	public int getDimension() {
		return data.length;
	}

	/** {@inheritDoc} */
	public FieldVector append(FieldVector v) {
		try {
			return append((ArrayFieldVector) v);
		} catch (ClassCastException cce) {
			return new ArrayFieldVector(this, new ArrayFieldVector(v));
		}
	}

	/**
	 * Construct a vector by appending a vector to this vector.
	 * 
	 * @param v
	 *            vector to append to this one.
	 * @return a new vector
	 */
	public ArrayFieldVector append(ArrayFieldVector v) {
		return new ArrayFieldVector(this, v);
	}

	/** {@inheritDoc} */
	public FieldVector append(IExpr in) {
		final IExpr[] out = MathArrays.buildArray(data.length + 1);
		System.arraycopy(data, 0, out, 0, data.length);
		out[data.length] = in;
		return new ArrayFieldVector(null, out, false);
	}

	/** {@inheritDoc} */
	public FieldVector getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
		if (n < 0) {
			throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
		}
		ArrayFieldVector out = new ArrayFieldVector(n);
		try {
			System.arraycopy(data, index, out.data, 0, n);
		} catch (IndexOutOfBoundsException e) {
			checkIndex(index);
			checkIndex(index + n - 1);
		}
		return out;
	}

	/** {@inheritDoc} */
	public void setEntry(int index, IExpr value) {
		try {
			data[index] = value;
		} catch (IndexOutOfBoundsException e) {
			checkIndex(index);
		}
	}

	/** {@inheritDoc} */
	public void setSubVector(int index, FieldVector v) throws OutOfRangeException {
		try {
			try {
				set(index, (ArrayFieldVector) v);
			} catch (ClassCastException cce) {
				for (int i = index; i < index + v.getDimension(); ++i) {
					data[i] = v.getEntry(i - index);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			checkIndex(index);
			checkIndex(index + v.getDimension() - 1);
		}
	}

	/**
	 * Set a set of consecutive elements.
	 *
	 * @param index
	 *            index of first element to be set.
	 * @param v
	 *            vector containing the values to set.
	 * @throws OutOfRangeException
	 *             if the index is invalid.
	 */
	public void set(int index, ArrayFieldVector v) throws OutOfRangeException {
		try {
			System.arraycopy(v.data, 0, data, index, v.data.length);
		} catch (IndexOutOfBoundsException e) {
			checkIndex(index);
			checkIndex(index + v.data.length - 1);
		}
	}

	/** {@inheritDoc} */
	public void set(IExpr value) {
		Arrays.fill(data, value);
	}

	/** {@inheritDoc} */
	public IExpr[] toArray() {
		return data.clone();
	}

	/**
	 * Check if instance and specified vectors have the same dimension.
	 * 
	 * @param v
	 *            vector to compare instance with
	 * @exception DimensionMismatchException
	 *                if the vectors do not have the same dimensions
	 */
	protected void checkVectorDimensions(FieldVector v) throws DimensionMismatchException {
		checkVectorDimensions(v.getDimension());
	}

	/**
	 * Check if instance dimension is equal to some expected value.
	 *
	 * @param n
	 *            Expected dimension.
	 * @throws DimensionMismatchException
	 *             if the dimension is not equal to the size of {@code this} vector.
	 */
	protected void checkVectorDimensions(int n) throws DimensionMismatchException {
		if (data.length != n) {
			throw new DimensionMismatchException(data.length, n);
		}
	}

	/**
	 * Visits (but does not alter) all entries of this vector in default order (increasing index).
	 *
	 * @param visitor
	 *            the visitor to be used to process the entries of this vector
	 * @return the value returned by {@link FieldVectorPreservingVisitor#end()} at the end of the walk
	 * @since 3.3
	 */
	public IExpr walkInDefaultOrder(final FieldVectorPreservingVisitor visitor) {
		final int dim = getDimension();
		visitor.start(dim, 0, dim - 1);
		for (int i = 0; i < dim; i++) {
			visitor.visit(i, getEntry(i));
		}
		return visitor.end();
	}

	/**
	 * Visits (but does not alter) some entries of this vector in default order (increasing index).
	 *
	 * @param visitor
	 *            visitor to be used to process the entries of this vector
	 * @param start
	 *            the index of the first entry to be visited
	 * @param end
	 *            the index of the last entry to be visited (inclusive)
	 * @return the value returned by {@link FieldVectorPreservingVisitor#end()} at the end of the walk
	 * @throws NumberIsTooSmallException
	 *             if {@code end < start}.
	 * @throws OutOfRangeException
	 *             if the indices are not valid.
	 * @since 3.3
	 */
	public IExpr walkInDefaultOrder(final FieldVectorPreservingVisitor visitor, final int start, final int end)
			throws NumberIsTooSmallException, OutOfRangeException {
		checkIndices(start, end);
		visitor.start(getDimension(), start, end);
		for (int i = start; i <= end; i++) {
			visitor.visit(i, getEntry(i));
		}
		return visitor.end();
	}

	/**
	 * Visits (but does not alter) all entries of this vector in optimized order. The order in which the entries are visited is
	 * selected so as to lead to the most efficient implementation; it might depend on the concrete implementation of this abstract
	 * class.
	 *
	 * @param visitor
	 *            the visitor to be used to process the entries of this vector
	 * @return the value returned by {@link FieldVectorPreservingVisitor#end()} at the end of the walk
	 * @since 3.3
	 */
	public IExpr walkInOptimizedOrder(final FieldVectorPreservingVisitor visitor) {
		return walkInDefaultOrder(visitor);
	}

	/**
	 * Visits (but does not alter) some entries of this vector in optimized order. The order in which the entries are visited is
	 * selected so as to lead to the most efficient implementation; it might depend on the concrete implementation of this abstract
	 * class.
	 *
	 * @param visitor
	 *            visitor to be used to process the entries of this vector
	 * @param start
	 *            the index of the first entry to be visited
	 * @param end
	 *            the index of the last entry to be visited (inclusive)
	 * @return the value returned by {@link FieldVectorPreservingVisitor#end()} at the end of the walk
	 * @throws NumberIsTooSmallException
	 *             if {@code end < start}.
	 * @throws OutOfRangeException
	 *             if the indices are not valid.
	 * @since 3.3
	 */
	public IExpr walkInOptimizedOrder(final FieldVectorPreservingVisitor visitor, final int start, final int end)
			throws NumberIsTooSmallException, OutOfRangeException {
		return walkInDefaultOrder(visitor, start, end);
	}

	/**
	 * Visits (and possibly alters) all entries of this vector in default order (increasing index).
	 *
	 * @param visitor
	 *            the visitor to be used to process and modify the entries of this vector
	 * @return the value returned by {@link FieldVectorChangingVisitor#end()} at the end of the walk
	 * @since 3.3
	 */
	public IExpr walkInDefaultOrder(final FieldVectorChangingVisitor visitor) {
		final int dim = getDimension();
		visitor.start(dim, 0, dim - 1);
		for (int i = 0; i < dim; i++) {
			setEntry(i, visitor.visit(i, getEntry(i)));
		}
		return visitor.end();
	}

	/**
	 * Visits (and possibly alters) some entries of this vector in default order (increasing index).
	 *
	 * @param visitor
	 *            visitor to be used to process the entries of this vector
	 * @param start
	 *            the index of the first entry to be visited
	 * @param end
	 *            the index of the last entry to be visited (inclusive)
	 * @return the value returned by {@link FieldVectorChangingVisitor#end()} at the end of the walk
	 * @throws NumberIsTooSmallException
	 *             if {@code end < start}.
	 * @throws OutOfRangeException
	 *             if the indices are not valid.
	 * @since 3.3
	 */
	public IExpr walkInDefaultOrder(final FieldVectorChangingVisitor visitor, final int start, final int end)
			throws NumberIsTooSmallException, OutOfRangeException {
		checkIndices(start, end);
		visitor.start(getDimension(), start, end);
		for (int i = start; i <= end; i++) {
			setEntry(i, visitor.visit(i, getEntry(i)));
		}
		return visitor.end();
	}

	/**
	 * Visits (and possibly alters) all entries of this vector in optimized order. The order in which the entries are visited is
	 * selected so as to lead to the most efficient implementation; it might depend on the concrete implementation of this abstract
	 * class.
	 *
	 * @param visitor
	 *            the visitor to be used to process the entries of this vector
	 * @return the value returned by {@link FieldVectorChangingVisitor#end()} at the end of the walk
	 * @since 3.3
	 */
	public IExpr walkInOptimizedOrder(final FieldVectorChangingVisitor visitor) {
		return walkInDefaultOrder(visitor);
	}

	/**
	 * Visits (and possibly change) some entries of this vector in optimized order. The order in which the entries are visited is
	 * selected so as to lead to the most efficient implementation; it might depend on the concrete implementation of this abstract
	 * class.
	 *
	 * @param visitor
	 *            visitor to be used to process the entries of this vector
	 * @param start
	 *            the index of the first entry to be visited
	 * @param end
	 *            the index of the last entry to be visited (inclusive)
	 * @return the value returned by {@link FieldVectorChangingVisitor#end()} at the end of the walk
	 * @throws NumberIsTooSmallException
	 *             if {@code end < start}.
	 * @throws OutOfRangeException
	 *             if the indices are not valid.
	 * @since 3.3
	 */
	public IExpr walkInOptimizedOrder(final FieldVectorChangingVisitor visitor, final int start, final int end)
			throws NumberIsTooSmallException, OutOfRangeException {
		return walkInDefaultOrder(visitor, start, end);
	}

	/**
	 * Test for the equality of two vectors.
	 *
	 * @param other
	 *            Object to test for equality.
	 * @return {@code true} if two vector objects are equal, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}

		try {
			// May fail, but we ignore ClassCastException
			FieldVector rhs = (FieldVector) other;
			if (data.length != rhs.getDimension()) {
				return false;
			}

			for (int i = 0; i < data.length; ++i) {
				if (!data[i].equals(rhs.getEntry(i))) {
					return false;
				}
			}
			return true;
		} catch (ClassCastException ex) {
			// ignore exception
			return false;
		}
	}

	/**
	 * Get a hashCode for the real vector.
	 * <p>
	 * All NaN values have the same hash code.
	 * </p>
	 * 
	 * @return a hash code value for this object
	 */
	@Override
	public int hashCode() {
		int h = 3542;
		for (final IExpr a : data) {
			h ^= a.hashCode();
		}
		return h;
	}

	/**
	 * Check if an index is valid.
	 *
	 * @param index
	 *            Index to check.
	 * @exception OutOfRangeException
	 *                if the index is not valid.
	 */
	private void checkIndex(final int index) throws OutOfRangeException {
		if (index < 0 || index >= getDimension()) {
			throw new OutOfRangeException(LocalizedFormats.INDEX, index, 0, getDimension() - 1);
		}
	}

	/**
	 * Checks that the indices of a subvector are valid.
	 *
	 * @param start
	 *            the index of the first entry of the subvector
	 * @param end
	 *            the index of the last entry of the subvector (inclusive)
	 * @throws OutOfRangeException
	 *             if {@code start} of {@code end} are not valid
	 * @throws NumberIsTooSmallException
	 *             if {@code end < start}
	 * @since 3.3
	 */
	private void checkIndices(final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
		final int dim = getDimension();
		if ((start < 0) || (start >= dim)) {
			throw new OutOfRangeException(LocalizedFormats.INDEX, start, 0, dim - 1);
		}
		if ((end < 0) || (end >= dim)) {
			throw new OutOfRangeException(LocalizedFormats.INDEX, end, 0, dim - 1);
		}
		if (end < start) {
			throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, end, start, false);
		}
	}

}