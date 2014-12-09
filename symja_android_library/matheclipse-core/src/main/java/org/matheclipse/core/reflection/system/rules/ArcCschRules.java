package org.matheclipse.core.reflection.system.rules;

import static org.matheclipse.core.expression.F.*;
import org.matheclipse.core.interfaces.IAST;

/**
 * Generated by <code>org.matheclipse.core.preprocessor.RulePreprocessor</code>.<br />
 * See GIT repository at: <a href="https://bitbucket.org/axelclk/symjaunittests">https://bitbucket.org/axelclk/symjaunittests</a>.
 */
public interface ArcCschRules {
  final public static IAST RULES = List(
    ISet(ArcCsch(C0),
      CComplexInfinity),
    ISet(ArcCsch(CI),
      Times(CC(0L,1L,-1L,2L),Pi)),
    ISet(ArcCsch(Times(CC(0L,1L,2L,1L),C1DSqrt3)),
      Times(CC(0L,1L,-1L,3L),Pi)),
    ISet(ArcCsch(Times(CI,CSqrt2)),
      Times(CC(0L,1L,-1L,4L),Pi)),
    ISet(ArcCsch(CC(0L,1L,2L,1L)),
      Times(CC(0L,1L,-1L,6L),Pi)),
    ISet(ArcCsch(C1),
      Log(Plus(C1,CSqrt2))),
    ISet(ArcCsch(CInfinity),
      C0),
    ISet(ArcCsch(DirectedInfinity(CI)),
      C0),
    ISet(ArcCsch(CComplexInfinity),
      C0)
  );
}