package org.matheclipse.core.expression;

import static org.matheclipse.core.expression.F.List;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Set;
import java.util.TreeSet;

import org.hipparchus.fraction.BigFraction;
import org.matheclipse.core.builtin.Combinatoric.Subsets;
import org.matheclipse.core.builtin.Combinatoric.Subsets.KSubsetsList;
import org.matheclipse.core.form.output.OutputFormFactory;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IASTAppendable;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.IFraction;
import org.matheclipse.core.interfaces.IInteger;
import org.matheclipse.core.interfaces.INumber;
import org.matheclipse.core.interfaces.IRational;
import org.matheclipse.core.interfaces.ISignedNumber;
import org.matheclipse.core.numbertheory.Primality;

import com.google.common.math.BigIntegerMath;

/**
 * IInteger implementation which delegates most of the methods to the BigInteger methods.
 * 
 * @see AbstractIntegerSym
 * @see IntegerSym
 */
public class BigIntegerSym extends AbstractIntegerSym {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6389228668633533063L;

	public static BigInteger eulerPhi(BigInteger value) throws ArithmeticException {
		if (value.equals(BigInteger.ZERO)) {
			return BigInteger.ZERO;
		}
		if (value.equals(BigInteger.ONE)) {
			return BigInteger.ONE;
		}
		IAST ast = AbstractIntegerSym.valueOf(value).factorInteger();
		IInteger phi = F.C1;
		for (int i = 1; i < ast.size(); i++) {
			IAST element = (IAST) ast.get(i);
			IInteger q = (IInteger) element.arg1();
			int c = ((IInteger) element.arg2()).toInt();
			if (c == 1) {
				phi = phi.multiply(q.subtract(F.C1));
			} else {
				phi = phi.multiply(q.subtract(F.C1).multiply(q.pow(c - 1)));
			}
		}
		return phi.toBigNumerator();
	}

	public static BigInteger jacobiSymbolF(BigInteger val) {
		BigInteger a = val.mod(AbstractIntegerSym.BI_EIGHT);
		if (a.equals(BigInteger.ONE)) {
			return BigInteger.ONE;
		}
		if (a.equals(AbstractIntegerSym.BI_SEVEN)) {
			return BigInteger.ONE;
		}
		return AbstractIntegerSym.BI_MINUS_ONE;
	}

	public static BigInteger jacobiSymbolG(BigInteger a, BigInteger b) {
		BigInteger i1 = a.mod(AbstractIntegerSym.BI_FOUR);
		if (i1.equals(BigInteger.ONE)) {
			return BigInteger.ONE;
		}
		BigInteger i2 = b.mod(AbstractIntegerSym.BI_FOUR);
		if (i2.equals(BigInteger.ONE)) {
			return BigInteger.ONE;
		}
		return AbstractIntegerSym.BI_MINUS_ONE;
	}

	/* package private */BigInteger fBigIntValue;

	private transient int fHashValue = 0;

	/**
	 * do not use directly, needed for serialization/deserialization
	 * 
	 */
	public BigIntegerSym() {
		fBigIntValue = null;
	}

	public BigIntegerSym(BigInteger value) {
		fBigIntValue = value;
	}

	public BigIntegerSym(byte[] bytes) {
		fBigIntValue = new BigInteger(bytes);
	}

	/** {@inheritDoc} */
	@Override
	public IInteger abs() {
		return valueOf(fBigIntValue.abs());
	}

	/**
	 * @param that
	 * @return
	 */
	@Override
	public IInteger add(final IInteger that) {
		return valueOf(fBigIntValue.add(that.toBigNumerator()));
	}

	@Override
	public IRational add(IRational parm1) {
		if (parm1.isZero()) {
			return this;
		}
		if (parm1 instanceof IFraction) {
			return ((IFraction) parm1).add(this);
		}
		IInteger p1 = (IInteger) parm1;
		BigInteger newnum = toBigNumerator().add(p1.toBigNumerator());
		return valueOf(newnum);
	}

	@Override
	public long bitLength() {
		return fBigIntValue.bitLength();
	}

	/** {@inheritDoc} */
	@Override
	public int compareAbsValueToOne() {
		BigInteger temp = fBigIntValue;
		if (fBigIntValue.compareTo(BigInteger.ZERO) < 0) {
			temp = temp.negate();
		}
		return temp.compareTo(BigInteger.ONE);
	}

	@Override
	public int compareInt(final int value) {
		if (fBigIntValue.bitLength() <= 31) {
			int temp = fBigIntValue.intValue();
			return temp > value ? 1 : temp == value ? 0 : -1;
		}
		return fBigIntValue.signum();
	}

	/**
	 * Compares this expression with the specified expression for order. Returns a negative integer, zero, or a positive
	 * integer as this expression is canonical less than, equal to, or greater than the specified expression.
	 */
	@Override
	public int compareTo(final IExpr expr) {
		if (expr instanceof IntegerSym) {
			return compareInt(((IntegerSym) expr).fIntValue);
		}
		if (expr instanceof BigIntegerSym) {
			return fBigIntValue.compareTo(((BigIntegerSym) expr).fBigIntValue);
		}
		if (expr instanceof IFraction) {
			return -((IFraction) expr).compareTo(AbstractFractionSym.valueOf(fBigIntValue, BigInteger.ONE));
		}
		if (expr.isSignedNumber()) {
			return Double.compare(fBigIntValue.doubleValue(), ((ISignedNumber) expr).doubleValue());
		}
		return super.compareTo(expr);
	}

	@Override
	public ComplexNum complexNumValue() {
		// double precision complex number
		return ComplexNum.valueOf(doubleValue());
	}

	@Override
	public int complexSign() {
		return sign();
	}

	/** {@inheritDoc} */
	@Override
	public IExpr dec() {
		return add(F.CN1);
	}

	/** {@inheritDoc} */
	@Override
	public IExpr inc() {
		return add(F.C1);
	}

	/**
	 * @param that
	 * @return
	 */
	@Override
	public IInteger div(final IInteger that) {
		return valueOf(fBigIntValue.divide(that.toBigNumerator()));
	}

	/** {@inheritDoc} */
	@Override
	public IInteger[] divideAndRemainder(final IInteger that) {
		final IInteger[] res = new IInteger[2];
		BigInteger[] largeRes = fBigIntValue.divideAndRemainder(that.toBigNumerator());
		res[0] = valueOf(largeRes[0]);
		res[1] = valueOf(largeRes[1]);

		return res;
	}

	@Override
	public ISignedNumber divideBy(ISignedNumber that) {
		if (that instanceof BigIntegerSym) {
			return AbstractFractionSym.valueOf(this).divideBy(that);
		}
		if (that instanceof IFraction) {
			return AbstractFractionSym.valueOf(this).divideBy(that);
		}
		return Num.valueOf(fBigIntValue.doubleValue() / that.doubleValue());
	}

	/**
	 * Return the divisors of this integer number.
	 * 
	 * <pre>
	 * divisors(24) ==> {1,2,3,4,6,8,12,24}
	 * </pre>
	 */
	@Override
	public IAST divisors() {
		if (isOne() || isMinusOne()) {
			return F.List(F.C1);
		}
		Set<IInteger> set = new TreeSet<IInteger>();
		final IAST primeFactorsList = factorize();
		int len = primeFactorsList.argSize();

		// build the k-subsets from the primeFactorsList
		for (int k = 1; k < len; k++) {
			final KSubsetsList iter = Subsets.createKSubsets(primeFactorsList, k, F.List(), 1);
			for (IAST subset : iter) {
				if (subset == null) {
					break;
				}
				// create the product of all integers in the k-subset
				IInteger factor = F.C1;
				for (int j = 1; j < subset.size(); j++) {
					factor = factor.multiply((IInteger) subset.get(j));
				}
				// add this divisor to the set collection
				set.add(factor);
			}
		}

		// build the final divisors list from the tree set
		final IASTAppendable resultList = F.ListAlloc(set.size() + 2);
		resultList.append(F.C1);
		for (IInteger entry : set) {
			resultList.append(entry);
		}
		resultList.append(this);
		return resultList;
	}

	/**
	 * @return
	 */
	@Override
	public double doubleValue() {
		return fBigIntValue.doubleValue();
	}

	@Override
	public boolean equals(final Object obj) {
		// EQ_CHECK_FOR_OPERAND_NOT_COMPATIBLE_WITH_THIS
		// if (obj instanceof IntegerSym) {
		// return equalsInt(((IntegerSym) obj).fIntValue);
		// }
		if (obj instanceof BigIntegerSym) {
			if (hashCode() != obj.hashCode()) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			return fBigIntValue.equals(((BigIntegerSym) obj).fBigIntValue);
		}
		return false;
	}

	@Override
	public final boolean equalsFraction(final int numerator, final int denominator) {
		if (denominator != 1) {
			return false;
		}
		return fBigIntValue.intValue() == numerator && fBigIntValue.bitLength() <= 31;
	}

	@Override
	public final boolean equalsInt(int value) {
		return fBigIntValue.intValue() == value && fBigIntValue.bitLength() <= 31;
	}

	/**
	 * Get the highest exponent of <code>base</code> that divides <code>this</code>
	 * 
	 * @return the exponent
	 */
	@Override
	public IExpr exponent(IInteger base) {
		IInteger b = this;
		if (sign() < 0) {
			b = b.negate();
		} else if (b.isZero()) {
			return F.CInfinity;
		} else if (b.isOne()) {
			return F.C0;
		}
		if (b.equals(base)) {
			return F.C1;
		}
		BigInteger rest = Primality.countExponent(b.toBigNumerator(), base.toBigNumerator());
		return valueOf(rest);
	}

	/**
	 * Returns the greatest common divisor of this large integer and the one specified.
	 * 
	 */
	@Override
	public IInteger gcd(final IInteger that) {
		return valueOf(fBigIntValue.gcd(that.toBigNumerator()));
	}

	/** {@inheritDoc} */
	@Override
	public IInteger getDenominator() {
		return F.C1;
	}

	/** {@inheritDoc} */
	@Override
	public BigFraction getFraction() {
		return new BigFraction(fBigIntValue);
	}

	/** {@inheritDoc} */
	@Override
	public IInteger getNumerator() {
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public final int hashCode() {
		if (fHashValue == 0) {
			fHashValue = fBigIntValue.hashCode();
		}
		return fHashValue;
	}

	/** {@inheritDoc} */
	@Override
	public ISignedNumber im() {
		return F.C0;
	}

	@Override
	public long integerLength(IInteger radix) {
		long length = 0L;
		IInteger ai = this;
		while (!ai.isZero()) {
			ai = ai.div(radix);
			length++;
		}
		return length;
	}

	@Override
	public String internalJavaString(boolean symbolsAsFactoryMethod, int depth, boolean useOperators,
			boolean usePrefix, boolean noSymbolPrefix) {
		String prefix = usePrefix ? "F." : "";
		int value = NumberUtil.toInt(fBigIntValue);
		switch (value) {
		case -1:
			return prefix + "CN1";
		case -2:
			return prefix + "CN2";
		case -3:
			return prefix + "CN3";
		case -4:
			return prefix + "CN4";
		case -5:
			return prefix + "CN5";
		case -6:
			return prefix + "CN6";
		case -7:
			return prefix + "CN7";
		case -8:
			return prefix + "CN8";
		case -9:
			return prefix + "CN9";
		case -10:
			return prefix + "CN10";
		case 0:
			return prefix + "C0";
		case 1:
			return prefix + "C1";
		case 2:
			return prefix + "C2";
		case 3:
			return prefix + "C3";
		case 4:
			return prefix + "C4";
		case 5:
			return prefix + "C5";
		case 6:
			return prefix + "C6";
		case 7:
			return prefix + "C7";
		case 8:
			return prefix + "C8";
		case 9:
			return prefix + "C9";
		case 10:
			return prefix + "C10";
		default:
			return prefix + "ZZ(" + value + "L)";
		}

	}

	@Override
	public String internalScalaString(boolean symbolsAsFactoryMethod, int depth) {
		return internalJavaString(symbolsAsFactoryMethod, depth, true, false, false);
	}

	@Override
	public byte byteValue() {
		return fBigIntValue.byteValue();
	}

	@Override
	public int intValue() {
		return fBigIntValue.intValue();
	}

	/**
	 * @return
	 */
	@Override
	public ISignedNumber inverse() {
		if (isOne()) {
			return this;
		}
		if (NumberUtil.isNegative(fBigIntValue)) {
			return AbstractFractionSym.valueOf(BigInteger.valueOf(-1), fBigIntValue.negate());
		}
		return AbstractFractionSym.valueOf(BigInteger.ONE, fBigIntValue);
	}

	@Override
	public boolean isEven() {
		return NumberUtil.isEven(fBigIntValue);
	}

	@Override
	public boolean isGreaterThan(ISignedNumber obj) {
		if (obj instanceof BigIntegerSym) {
			return fBigIntValue.compareTo(((BigIntegerSym) obj).fBigIntValue) > 0;
		}
		if (obj instanceof IFraction) {
			return -((IFraction) obj).compareTo(AbstractFractionSym.valueOf(fBigIntValue, BigInteger.ONE)) > 0;
		}
		return fBigIntValue.doubleValue() > obj.doubleValue();
	}

	/**
	 * @param that
	 * @return
	 */
	public boolean isLargerThan(final BigInteger that) {
		return fBigIntValue.compareTo(that) > 0;
	}

	@Override
	public boolean isLessThan(ISignedNumber obj) {
		if (obj instanceof BigIntegerSym) {
			return fBigIntValue.compareTo(((BigIntegerSym) obj).fBigIntValue) < 0;
		}
		if (obj instanceof IFraction) {
			return -((IFraction) obj).compareTo(AbstractFractionSym.valueOf(fBigIntValue, BigInteger.ONE)) < 0;
		}
		return fBigIntValue.doubleValue() < obj.doubleValue();
	}

	@Override
	public boolean isMinusOne() {
		return fBigIntValue.equals(BI_MINUS_ONE);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNegative() {
		return fBigIntValue.compareTo(BigInteger.ZERO) < 0;
	}

	@Override
	public boolean isOdd() {
		return NumberUtil.isOdd(fBigIntValue);
	}

	@Override
	public boolean isOne() {
		return fBigIntValue.equals(BigInteger.ONE);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPositive() {
		return fBigIntValue.compareTo(BigInteger.ZERO) > 0;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isProbablePrime() {
		return isProbablePrime(PRIME_CERTAINTY);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isProbablePrime(int certainty) {
		return fBigIntValue.isProbablePrime(certainty);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isRationalValue(IRational value) {
		return equals(value);
	}

	@Override
	public boolean isZero() {
		return fBigIntValue.equals(BigInteger.ZERO);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public long longValue() {
		return fBigIntValue.longValue();
	}

	/**
	 * @param that
	 * @return
	 */
	@Override
	public IInteger mod(final IInteger that) {
		return valueOf(fBigIntValue.mod(that.toBigNumerator()));
	}

	@Override
	public IInteger modInverse(final IInteger m) {
		return valueOf(fBigIntValue.modInverse(m.toBigNumerator()));
	}

	@Override
	public IInteger modPow(final IInteger exp, final IInteger m) {
		if (m.isZero()) {
			throw new ArithmeticException("the argument " + m.toString() + " should be nonzero.");
		}
		return valueOf(fBigIntValue.modPow(exp.toBigNumerator(), m.toBigNumerator()));
	}

	/**
	 * @param that
	 * @return
	 */
	@Override
	public IInteger multiply(final IInteger that) {
		if (that instanceof IntegerSym) {
			switch (((IntegerSym) that).fIntValue) {
			case 0:
				return F.C0;
			case 1:
				return this;
			case -1:
				return negate();
			default:
			}
		}
		return valueOf(fBigIntValue.multiply(that.toBigNumerator()));
	}

	/**
	 * @param value
	 * @return
	 */
	@Override
	public IInteger multiply(int value) {
		switch (value) {
		case 0:
			return F.C0;
		case 1:
			return this;
		case -1:
			return negate();
		default:
		}
		return valueOf(fBigIntValue.multiply(BigInteger.valueOf(value)));
	}

	@Override
	public IRational multiply(IRational parm1) {
		if (parm1.isZero()) {
			return F.C0;
		}
		if (parm1.isOne()) {
			return this;
		}
		if (parm1.isMinusOne()) {
			return this.negate();
		}
		if (parm1 instanceof IFraction) {
			return ((IFraction) parm1).multiply(this);
		}
		IInteger p1 = (IInteger) parm1;
		BigInteger newnum = toBigNumerator().multiply(p1.toBigNumerator());
		return valueOf(newnum);
	}

	@Override
	public IInteger negate() {
		return valueOf(fBigIntValue.negate());
	}

	@Override
	public INumber normalize() {
		return this;
	}

	/**
	 * Returns the nth-root of this integer.
	 * 
	 * @return <code>k<code> such as <code>k^n <= this < (k + 1)^n</code>
	 * @throws IllegalArgumentException
	 *             if {@code this < 0}
	 * @throws ArithmeticException
	 *             if this integer is negative and n is even.
	 */
	@Override
	public IExpr nthRoot(int n) throws ArithmeticException {
		if (n < 0) {
			throw new IllegalArgumentException("nthRoot(" + n + ") n must be >= 0");
		}
		if (n == 2) {
			return sqrt();
		}
		if (sign() == 0) {
			return F.C0;
		} else if (sign() < 0) {
			if (n % 2 == 0) {
				// even exponent n
				throw new ArithmeticException();
			} else {
				// odd exponent n
				return negate().nthRoot(n).negate();
			}
		} else {
			IInteger result;
			IInteger temp = this;
			do {
				result = temp;
				temp = divideAndRemainder(temp.pow(((long) n) - 1))[0]
						.add(temp.multiply(AbstractIntegerSym.valueOf(n - 1)))
						.divideAndRemainder(AbstractIntegerSym.valueOf(n))[0];
			} while (temp.compareTo(result) < 0);
			return result;
		}
	}

	@Override
	public final INumber numericNumber() {
		return F.num(this);
	}

	@Override
	public Num numValue() {
		return Num.valueOf(doubleValue());
	}

	/** {@inheritDoc} */
	@Override
	public ISignedNumber re() {
		return this;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
		byte attributeFlags = objectInput.readByte();
		int value;
		switch (attributeFlags) {
		case 1:
			value = objectInput.readByte();
			fBigIntValue = BigInteger.valueOf(value);
			return;
		case 2:
			value = objectInput.readShort();
			fBigIntValue = BigInteger.valueOf(value);
			return;
		case 4:
			value = objectInput.readInt();
			fBigIntValue = BigInteger.valueOf(value);
			return;
		default:
			fBigIntValue = (BigInteger) objectInput.readObject();
		}
	}

	@Override
	public IExpr remainder(final IExpr that) {
		if (that instanceof IntegerSym) {
			return valueOf(fBigIntValue.remainder(((IntegerSym) that).toBigNumerator()));
		}
		if (that instanceof BigIntegerSym) {
			return valueOf(fBigIntValue.remainder(((BigIntegerSym) that).fBigIntValue));
		}
		return this;
	}

	public IInteger remainder(final IInteger that) {
		return valueOf(fBigIntValue.remainder(that.toBigNumerator()));
	}

	@Override
	public IInteger round() {
		return this;
	}

	/**
	 * @param n
	 * @return
	 */
	@Override
	public IInteger shiftLeft(final int n) {
		return valueOf(toBigNumerator().shiftLeft(n));
	}

	/**
	 * @param n
	 * @return
	 */
	@Override
	public IInteger shiftRight(final int n) {
		return valueOf(toBigNumerator().shiftRight(n));
	}

	@Override
	public int sign() {
		return fBigIntValue.signum();
	}

	/**
	 * Returns the integer square root of this integer.
	 * 
	 * @return <code>k<code> such as <code>k^2 <= this < (k + 1)^2</code>. If this integer is negative or it's
	 *         impossible to find a square root return <code>F.Sqrt(this)</code>.
	 */
	public IExpr sqrt() {
		try {
			return valueOf(BigIntegerMath.sqrt(fBigIntValue, RoundingMode.UNNECESSARY));
		} catch (RuntimeException ex) {
			return F.Sqrt(this);
		}
	}

	@Override
	public IInteger subtract(final IInteger that) {
		return valueOf(fBigIntValue.subtract(that.toBigNumerator()));
	}

	/** {@inheritDoc} */
	@Override
	public BigInteger toBigDenominator() {
		return BigInteger.ONE;
	}

	/** {@inheritDoc} */
	@Override
	public BigInteger toBigNumerator() {
		return fBigIntValue;
	}

	/** {@inheritDoc} */
	@Override
	public int toInt() throws ArithmeticException {
		return NumberUtil.toInt(fBigIntValue);
	}

	/** {@inheritDoc} */
	@Override
	public int toIntDefault(int defaultValue) {
		int val = fBigIntValue.intValue();
		if (!fBigIntValue.equals(BigInteger.valueOf(val))) {
			return defaultValue;
		}
		return val;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long toLong() throws ArithmeticException {
		return NumberUtil.toLong(fBigIntValue);
	}

	@Override
	public String toString() {
		try {
			StringBuilder sb = new StringBuilder();
			OutputFormFactory.get().convertInteger(sb, this, Integer.MIN_VALUE, OutputFormFactory.NO_PLUS_CALL);
			return sb.toString();
		} catch (Exception e1) {
			// fall back to simple output format
			return fBigIntValue.toString();
		}
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if ((fBigIntValue.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0)
				&& (fBigIntValue.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0)) {
			int value = fBigIntValue.intValue();
			if (value <= Byte.MAX_VALUE && value >= Byte.MIN_VALUE) {
				objectOutput.writeByte(1);
				objectOutput.writeByte((byte) value);
				return;
			}
			if (value <= Short.MAX_VALUE && value >= Short.MIN_VALUE) {
				objectOutput.writeByte(2);
				objectOutput.writeShort((short) value);
				return;
			}
			objectOutput.writeByte(4);
			objectOutput.writeInt(value);
			return;
		}

		objectOutput.writeByte(0);
		objectOutput.writeObject(fBigIntValue);
	}

	private Object writeReplace() throws ObjectStreamException {
		return optional(F.GLOBAL_IDS_MAP.get(this));
	}
}