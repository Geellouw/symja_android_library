package org.matheclipse.core.reflection.system.rules;

import static org.matheclipse.core.expression.F.*;
import org.matheclipse.core.interfaces.IAST;

/**
 * Generated by <code>org.matheclipse.core.preprocessor.RulePreprocessor</code>.<br />
 * See GIT repository at: <a href="https://bitbucket.org/axelclk/symjaunittests">https://bitbucket.org/axelclk/symjaunittests</a>.
 */
public interface SecRules {
  final public static IAST RULES = List(
    Set(Sec(C0),
      C1),
    Set(Sec(Times(C1D2,Pi)),
      CComplexInfinity),
    Set(Sec(Times(C1D3,Pi)),
      C2),
    Set(Sec(Times(C1D4,Pi)),
      Sqrt(C2)),
    Set(Sec(Times(QQ(1L,5L),Pi)),
      Plus(CN1,Sqrt(C5))),
    Set(Sec(Times(QQ(1L,6L),Pi)),
      Times(QQ(2L,3L),Sqrt(C3))),
    Set(Sec(Times(QQ(1L,8L),Pi)),
      Sqrt(Plus(C4,Times(CN2,Sqrt(C2))))),
    Set(Sec(Times(QQ(1L,10L),Pi)),
      Times(QQ(1L,5L),Sqrt(Plus(ZZ(50L),Times(ZZ(-10L),Sqrt(C5)))))),
    Set(Sec(Times(QQ(1L,12L),Pi)),
      Plus(Times(CN1,Sqrt(C2)),Sqrt(ZZ(6L)))),
    Set(Sec(Times(QQ(3L,8L),Pi)),
      Sqrt(Plus(C4,Times(C2,Sqrt(C2))))),
    Set(Sec(Times(QQ(3L,10L),Pi)),
      Times(QQ(1L,5L),Sqrt(Plus(ZZ(50L),Times(ZZ(10L),Sqrt(C5)))))),
    Set(Sec(Times(QQ(2L,5L),Pi)),
      Plus(C1,Sqrt(C5))),
    Set(Sec(Times(QQ(5L,12L),Pi)),
      Plus(Sqrt(C2),Sqrt(ZZ(6L))))
  );
}