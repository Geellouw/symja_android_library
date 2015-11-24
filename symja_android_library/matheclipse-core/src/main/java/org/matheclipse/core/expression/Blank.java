package org.matheclipse.core.expression;

import java.io.ObjectStreamException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.matheclipse.core.basic.Config;
import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.generic.Predicates;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.IPattern;
import org.matheclipse.core.interfaces.IPatternObject;
import org.matheclipse.core.interfaces.ISymbol;
import org.matheclipse.core.patternmatching.IPatternMatcher;
import org.matheclipse.core.patternmatching.PatternMap;
import org.matheclipse.core.patternmatching.PatternMatcher;
import org.matheclipse.core.visit.IVisitor;
import org.matheclipse.core.visit.IVisitorBoolean;
import org.matheclipse.core.visit.IVisitorInt;
import org.matheclipse.core.visit.IVisitorLong;

/**
 * A &quot;blank pattern&quot; with no assigned &quot;pattern name&quot; (i.e.
 * &quot;<code>_</code>&quot;)
 * 
 */
public class Blank extends ExprImpl implements IPattern {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1306007999071682207L;

	/**
	 * The expression which should check this pattern
	 */
	protected final IExpr fCondition;

	/**
	 * Use default value, if no matching expression was found
	 */
	final boolean fDefault;

	// private static Blank NULL_PATTERN = new Blank();

	public static IPattern valueOf() {
		return new Blank(); // NULL_PATTERN;
	}

	public static IPattern valueOf(final IExpr condition) {
		return new Blank(condition);
	}

	public Blank() {
		this(null);
	}

	public Blank(final IExpr condition) {
		this(condition, false);
	}

	public Blank(final IExpr condition, boolean def) {
		super();
		this.fCondition = condition;
		this.fDefault = def;
	}

	public int[] addPattern(PatternMap patternMap, Map<IExpr, Integer> patternIndexMap) {
		patternMap.addPattern(patternIndexMap, this);
		int[] result = new int[2];
		if (isPatternDefault()) {
			// the ast contains a pattern with default value (i.e. "x_.")
			result[0] = IAST.CONTAINS_DEFAULT_PATTERN;
			result[1] = 2;
		} else {
			// the ast contains a pattern without default value (i.e. "x_")
			result[0] = IAST.CONTAINS_PATTERN;
			result[1] = 5;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> T accept(IVisitor<T> visitor) {
		return visitor.visit(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean accept(IVisitorBoolean visitor) {
		return visitor.visit(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public int accept(IVisitorInt visitor) {
		return visitor.visit(this);
	}

	/** {@inheritDoc} */
	@Override
	public long accept(IVisitorLong visitor) {
		return visitor.visit(this);
	}

	@Override
	public int compareTo(final IExpr expr) {
		if (expr instanceof Blank) {
			Blank pat = ((Blank) expr);
			if (fCondition == null) {
				return (pat.fCondition != null) ? -1 : 0;
			} else {
				return (pat.fCondition == null) ? 1 : fCondition.compareTo(pat.fCondition);
			}
		}
		return super.compareTo(expr);
	}

	/**
	 * Check if the two left-hand-side pattern expressions are equivalent. (i.e.
	 * <code>f[x_,y_]</code> is equivalent to <code>f[a_,b_]</code> )
	 * 
	 * @param patternObject
	 * @param pm1
	 * @param pm2
	 * @return
	 */
	public boolean equivalent(final IPatternObject patternObject, final PatternMap pm1, PatternMap pm2) {
		if (this == patternObject) {
			return true;
		}
		if (patternObject instanceof Blank) {
			// test if the "check" expressions are equal
			final IExpr o1 = getCondition();
			final IExpr o2 = patternObject.getCondition();
			if ((o1 == null) || (o2 == null)) {
				return o1 == o2;
			}
			return o1.equals(o2);
		}
		return false;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Blank) {
			if (hashCode() != obj.hashCode()) {
				return false;
			}
			Blank blank = (Blank) obj;
			if ((fCondition != null) && (blank.fCondition != null)) {
				return fCondition.equals(blank.fCondition);
			}
			return fCondition == blank.fCondition;
		}
		return false;
	}

	public boolean matchPattern(final IExpr expr, PatternMap patternMap) {
		return isConditionMatched(expr, patternMap);
	}

	public IExpr getCondition() {
		return fCondition;
	}

	@Override
	public int getIndex(PatternMap pm) {
		if (pm != null) {
			return pm.get(this);
		}
		return -1;
	}

	@Override
	public int getEvalFlags() {
		if (isPatternDefault()) {
			// the ast contains a pattern with default value (i.e. "x_.")
			return IAST.CONTAINS_DEFAULT_PATTERN;
		}
		// the ast contains a pattern without default value (i.e. "x_")
		return IAST.CONTAINS_PATTERN;
	}

	@Override
	public ISymbol getSymbol() {
		return null;
	}

	@Override
	public int hashCode() {
		return (fCondition == null) ? 193 : 23 + fCondition.hashCode();
	}

	public ISymbol head() {
		return F.BlankHead;
	}

	@Override
	public int hierarchy() {
		return BLANKID;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isBlank() {
		return true;
	}

	/**
	 * Groovy operator overloading
	 */
	public boolean isCase(IExpr that) {
		final IPatternMatcher matcher = new PatternMatcher(this);
		if (matcher.test(that)) {
			return true;
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPatternDefault() {
		return fDefault;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isPatternExpr() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFreeOfPatterns() {
		return false;
	}

	@Override
	public boolean isConditionMatched(final IExpr expr, PatternMap patternMap) {
		if (fCondition == null || expr.head().equals(fCondition)) {
			patternMap.setValue(this, expr);
			return true;
		}
		EvalEngine engine = EvalEngine.get();
		boolean traceMode = false;
		try {
			traceMode = engine.isTraceMode();
			engine.setTraceMode(false);
			final Predicate<IExpr> matcher = Predicates.isTrue(engine, fCondition);
			if (matcher.test(expr)) {
				patternMap.setValue(this, expr);
				return true;
			}
		} finally {
			if (traceMode) {
				engine.setTraceMode(true);
			}
		}
		return false;
	}

	@Override
	public String internalJavaString(boolean symbolsAsFactoryMethod, int depth, boolean useOperators) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("$b(");
		if (fCondition != null) {
			buffer.append(fCondition.internalJavaString(symbolsAsFactoryMethod, 0, useOperators));
		}
		buffer.append(')');
		return buffer.toString();
	}

	public String fullFormString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Blank");
		if (Config.PARSER_USE_LOWERCASE_SYMBOLS) {
			buf.append('(');
		} else {
			buf.append('[');
		}
		if (fCondition != null) {
			buf.append(fCondition.fullFormString());
		}
		if (Config.PARSER_USE_LOWERCASE_SYMBOLS) {
			buf.append(')');
		} else {
			buf.append(']');
		}
		return buf.toString();
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append('_');
		if (fCondition != null) {
			buffer.append(fCondition.toString());
		}
		return buffer.toString();
	}

	@Override
	public IExpr variables2Slots(final Map<IExpr, IExpr> map, final List<IExpr> variableList) {
		return null;
	}

	private Object writeReplace() throws ObjectStreamException {
		return optional(F.GLOBAL_IDS_MAP.get(this));
	}
}